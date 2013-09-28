package com.alexecollins.web;

import com.alexecollins.releasemanager.model.Component;
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
@RequestMapping("/components")
public class ComponentController {
	@PersistenceContext
	private EntityManager entityManager;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("components", entityManager.createQuery("select r from Component r order by r.name").getResultList());

		return "components/index";
	}

	@RequestMapping("create")
	public String create() {
		return "components/create";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	@Transactional
	public String components(Model model, String name) {
		final Component item = new Component();
		System.out.println(name);
		item.setName(name);
		entityManager.persist(item);
		return "redirect:index.html";
	}
}
