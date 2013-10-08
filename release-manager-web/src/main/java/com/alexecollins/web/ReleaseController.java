package com.alexecollins.web;

import com.alexecollins.releasemanager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
public class ReleaseController {

	@Autowired
	ReleaseRepository releaseRepository;
	@Autowired
	ComponentRepository componentRepository;
	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void createExamples() {
		if (userRepository.findAll().size() == 0 ) {
			final User user = new User();
			user.setEmail("alex.e.c@gmail.com");
			userRepository.save(user);
			final Component component1 = new Component();
			component1.setName("Example Component 1");
			componentRepository.save(component1);
			final Component component2 = new Component();
			component2.setName("Example Component 2");
			componentRepository.save(component2);
			final Release release = new Release();
			release.setName("Example Release 1");
			release.getComponents().add(component1);
			releaseRepository.save(release);
		}
	}

	@RequestMapping("/releases")
	public String index(Model model) {
		model.addAttribute("releases", releaseRepository.findAll());

		return "releases";
	}

	@RequestMapping("/releases/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		final Release r = releaseRepository.findOne(id);

		model.addAttribute("release", r);
		model.addAttribute("included_components", new ArrayList<>(r.getComponents()));
		final List<Component> components = new ArrayList<>(componentRepository.findAll());

		components.removeAll(r.getComponents());
		model.addAttribute("excluded_components", new ArrayList<>(components));

		model.addAttribute("sign_offs", r.getSignOffs());

		final List<User> users = userRepository.findAll();
		users.removeAll(r.getSignOffs().keySet());
		model.addAttribute("excluded_users", users);

		model.addAttribute("created", new SimpleDateFormat("dd MMM yy hh:MMaa").format(r.getCreated()));

		return "releases/edit";
	}

	@RequestMapping(value = "/releases/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id) {

		releaseRepository.delete(id);

		return "redirect:/releases.html";
	}

	@RequestMapping(value = "/releases/{id}/components", method = RequestMethod.POST)
	public String addComponent(@PathVariable("id") String id, @RequestParam("component_id") String componentId) {

		final Release release = releaseRepository.findOne(id);

		release.getComponents().add(componentRepository.findOne(componentId));

		releaseRepository.save(release);

		return redirectToRelease(id);
	}

	@RequestMapping(value = "/releases/{id}/components/{component_id}", method = RequestMethod.POST)
	@Transactional
	public String updateComponent(@PathVariable("id") String id, @PathVariable("component_id") String componentId) {

		final Release release = releaseRepository.findOne(id);

		release.getComponents().remove(releaseRepository.findOne(componentId));

		releaseRepository.save(release);

		return redirectToRelease(id);
	}

	private String redirectToRelease(String id) {
		return "redirect:/releases/" + id + ".html";
	}

	@RequestMapping(value = "/releases/{id}/sign-offs", method = RequestMethod.POST)
	@Transactional
	public String addSignOff(@PathVariable("id") String id, @RequestParam("user_id") String userId) {

		final Release release = releaseRepository.findOne(id);
		final User user = userRepository.findOne(userId);

		release.getSignOffs().put(user, new SignOff());

		releaseRepository.save(release);

		return redirectToRelease(id);
	}

	@RequestMapping(value = "/releases/{id}/sign-offs/{sign_off_id}", method = RequestMethod.POST)
	@Transactional
	public String updateSignOff(@PathVariable("id") String id, @PathVariable("user_id") String userId,
	                            @RequestParam(value = "status", required = false) String status,
	                            @RequestParam(value = "action", required = false) String action) {

		final Release release = releaseRepository.findOne(id);
		if ("REMOVE".equals(action)) {
			release.getSignOffs().remove(userRepository.findOne(userId));
		} else {
			release.getSignOffs().get(userRepository.findOne(userId)).setStatus(SignOffStatus.valueOf(status));
		}

		releaseRepository.save(release);

		return redirectToRelease(id);
	}

	@RequestMapping("/releases/create")
	public String create() {
		return "releases/create";
	}

	@RequestMapping(value = "/releases", method = RequestMethod.POST)
	@Transactional
	public String releases(String name) {
		final Release release = new Release();
		release.setName(name);
		releaseRepository.save(release);
		return redirectToRelease(release.getId());
	}
}
