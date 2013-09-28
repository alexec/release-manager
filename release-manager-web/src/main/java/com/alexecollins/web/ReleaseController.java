package com.alexecollins.web;

import com.alexecollins.releasemanager.model.Component;
import com.alexecollins.releasemanager.model.Release;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
public class ReleaseController {
	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping("/releases")
	public String index(Model model) {
		model.addAttribute("releases", entityManager.createQuery("select r from Release r order by r.id").getResultList());

		return "releases";
	}

	@RequestMapping("/releases/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		final Release r = entityManager.find(Release.class, id);

		model.addAttribute("release", r);
		model.addAttribute("included_components", new ArrayList<>(r.getComponents()));
		final List<Component> components = new ArrayList<>(entityManager.createQuery("select c from Component c ", Component.class)
				.getResultList());
		components.removeAll(r.getComponents());
		model.addAttribute("excluded_components", new ArrayList<>(components));

		return "releases/edit";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	@Transactional
	public String edit(Model model, @PathVariable("id") int id, @PathVariable("component_id") int componentId) {

		final Release release = entityManager.find(Release.class, id);

		release.addComponent(entityManager.find(Component.class, componentId));

		entityManager.persist(release);

		return "redirect:/releases/" + id + ".html";
	}
	@RequestMapping("/releases/create")
	public String create() {
		return "releases/create";
	}

	@RequestMapping(value = "/releases", method = RequestMethod.POST)
	@Transactional
	public String releases(Model model, String name) {
		final Release release = new Release();
		release.setName(name);
		entityManager.persist(release);
		return "redirect:/releases.html";
	}
}
