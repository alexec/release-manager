package com.alexecollins.web;

import com.alexecollins.releasemanager.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Slf4j
public class ExamplesLoader {
	@Autowired
	ReleaseRepository releaseRepository;
	@Autowired
	ComponentRepository componentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ChangeLogRepository changeLogRepository;

	@PostConstruct
	public void createExamples() {
		ChangeLog examples = getExamples();
		switch (examples.getVersion()) {
			case 0:
				v0();
			case 1:
				v1();
			case 2:
				v2();

		}
		examples.setVersion(3);
		changeLogRepository.save(examples);
	}

	private void v2() {
		log.info("loading examples v2");
		final Release release = releaseRepository.findAll().get(0);
		release.getSignOffs().put(userRepository.findAll().get(0).getId(), new SignOff());
		releaseRepository.save(release);
	}

	private Release v1() {
		log.info("loading examples v1");
		final Component component1 = new Component();
		component1.setName("Example Component 1");
		componentRepository.save(component1);
		final Component component2 = new Component();
		component2.setName("Example Component 2");
		componentRepository.save(component2);
		final Release release = new Release();
		release.setName("Example Release 1");
		release.setDesc("Interesting Release\n---\n\nstuff\n\n\tcode example\n\n");
		release.getComponents().add(component1);
		releaseRepository.save(release);
		return release;
	}

	private void v0() {
		log.info("loading examples v0");
		final User user = new User();
		user.setEmail("alex.e.c@gmail.com");
		userRepository.save(user);
	}

	private ChangeLog getExamples() {
		ChangeLog examples = changeLogRepository.findByName("examples");
		if (examples == null) {
			examples = new ChangeLog();
		}
		return examples;
	}
}
