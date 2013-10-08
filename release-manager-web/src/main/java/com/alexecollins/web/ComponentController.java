package com.alexecollins.web;

import com.alexecollins.releasemanager.model.Component;
import com.alexecollins.releasemanager.model.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
public class ComponentController {

	@Autowired
	ComponentRepository repo;

	@RequestMapping("/components")
	public String index(Model model) {
		model.addAttribute("components", repo.findAll());

		return "components";
	}

	@RequestMapping("/components/create")
	public String create() {
		return "components/create";
	}

	@RequestMapping(value = "/components", method = RequestMethod.POST)
	@Transactional
	public String newComponent(@RequestParam("name") String name) {
		final Component item = new Component();
		item.setName(name);
		repo.save(item);
		return "redirect:/components.html";
	}

	@RequestMapping(value = "/components/{id}", method = RequestMethod.POST)
	@Transactional
	public String deleteComponent(@PathVariable("id") String id) {
		repo.delete(id);
		return "redirect:/components.html";
	}
}
