package com.alexecollins.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
public class ReleaseController {
	@PersistenceContext
	private EntityManager entityManager;
	@RequestMapping("/releases")
	public String releases(Model model) {
		model.addAttribute("releases", entityManager.createQuery("select r from Release r").getResultList());

		return "releases";
	}
}
