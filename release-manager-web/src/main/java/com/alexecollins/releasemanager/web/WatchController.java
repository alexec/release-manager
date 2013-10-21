package com.alexecollins.releasemanager.web;

import com.alexecollins.releasemanager.model.Watch;
import com.alexecollins.releasemanager.model.WatchRepository;
import com.alexecollins.releasemanager.web.view.WatchView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
public class WatchController {
	@Autowired
	WatchRepository watchRepository;

	@RequestMapping(value="/watches", method = RequestMethod.POST)
	public String watches(Principal user, String subject, String submit) {
		final Watch existingWatch = watchRepository.findByUserAndSubject(user.getName(), subject);
		if ("Watch".equals(subject)) {
				final Watch watch = existingWatch != null ? existingWatch : new Watch();
				watch.setUser(user.getName());
				watch.setSubject(subject);
				watchRepository.save(watch);
        } else if ("Unwatch".equals(subject)) {
				watchRepository.delete(existingWatch);
        } else{
            throw new IllegalArgumentException("unknown submit " + submit);
		}
		return "redirect:" + (subject.startsWith("page:") ? subject.substring(5) : "watches.html");
	}

	@RequestMapping(value="/watches", method = RequestMethod.GET)
	public String watches(Model model, Principal principal) {
		final ArrayList<WatchView> watchViews = new ArrayList<WatchView>();
		for (Watch watch : watchRepository.findByUser(principal.getName())) {
			watchViews.add(new WatchView(watch.getId(), watch.getUser(), watch.getSubject()));
		}
		model.addAttribute("watches", watchViews);
		return "watches";
	}
}
