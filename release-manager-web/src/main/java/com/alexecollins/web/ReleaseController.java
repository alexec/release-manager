package com.alexecollins.web;

import com.alexecollins.releasemanager.model.Release;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
@RequestMapping("/releases")
public class ReleaseController {
	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("releases", entityManager.createQuery("select r from Release r order by r.id").getResultList());

		return "releases/index";
	}

	@RequestMapping("{id}")
	public String view(Model model, @PathVariable("id") int id) {
		final Release r = entityManager.createQuery("select r from Release r where r.id = :id", Release.class).setParameter("id", id).getSingleResult();
		model.addAttribute("release", r);
		model.addAttribute("components", new HashSet<>(r.getComponents()));

		return "releases/view";
	}

	@RequestMapping("create")
	public String create() {
		return "releases/create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@Transactional
	public String releases(Model model, String name) {
		final Release release = new Release();
		release.setName(name);
		entityManager.persist(release);
		return "redirect:index.html";
	}
}
