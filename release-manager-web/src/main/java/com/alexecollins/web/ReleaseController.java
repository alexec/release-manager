package com.alexecollins.web;

import com.alexecollins.releasemanager.model.Release;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	@RequestMapping("create")
	public String create() {
		return "releases/create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@Transactional
	public String releases(Model model, String name) {
		final Release release = new Release();
		System.out.println(name);
		release.setName(name);
		entityManager.persist(release);
		return index(model);
	}
}
