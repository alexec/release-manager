package com.alexecollins.releasemanager.web;

import com.alexecollins.releasemanager.model.Watch;
import com.alexecollins.releasemanager.model.WatchRepository;
import com.alexecollins.releasemanager.web.view.WatchView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
public class WatchController {
	@Autowired
	WatchRepository watchRepository;

	@RequestMapping(value="/watches", method = RequestMethod.POST)
	public String watches(String user, String subject) {
		final Watch watch = new Watch();
		watch.setUser(user);
		watch.setSubject(subject);
		watchRepository.save(watch);
		return "redirect:watches.html";
	}

	@RequestMapping(value="/watches", method = RequestMethod.GET)
	public String watches(Model model) {
		final ArrayList<WatchView> watchViews = new ArrayList<>();
		for (Watch watch : watchRepository.findAll()) {
			watchViews.add(new WatchView(watch.getId(), watch.getUser(), watch.getSubject()));
		}
		model.addAttribute("watches", watchViews);
		return "watches";
	}

	@RequestMapping(value="/watches/{id}", method = RequestMethod.GET)
	public String watch(@PathVariable("id") String id, String submit) {
		watchRepository.delete(id);
		return "watches";
	}
}
