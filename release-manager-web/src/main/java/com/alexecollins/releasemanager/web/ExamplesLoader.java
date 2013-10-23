package com.alexecollins.releasemanager.web;

import com.alexecollins.releasemanager.model.*;
import com.mdimension.jchronic.Chronic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Slf4j
public class ExamplesLoader {
	public static String LIPSUM = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	@Autowired
	ReleaseRepository releaseRepository;
	@Autowired
	ComponentRepository componentRepository;
	@Autowired
	ChangeLogRepository changeLogRepository;
	@Autowired
	WatchRepository watchRepository;
    @Autowired
    ArtifactRepositoryRepository artifactRepositoryRepository;

	@PostConstruct
	public void createExamples() {
		ChangeLog examples = getExamples();
		switch (examples.getVersion()) {
			case 0:
			case 1:
				v1();
			case 2:
				v2();
			case 3:
				v3();
			case 4:
				v4();
            case 5:
                v5();

		}
		examples.setVersion(6);
		changeLogRepository.save(examples);
	}



	private Release v1() {
		ExamplesLoader.log.info("loading examples v1");
		final Component component1 = new Component();
		component1.setName("Example Component 1");
		componentRepository.save(component1);
		final Component component2 = new Component();
		component2.setName("Example Component 2");
		componentRepository.save(component2);
		final Release release = new Release();
		release.setName("Example Release 1");
		release.setDesc("Interesting Release\n---\n\nstuff\n\n\tcode example\n\n");
		release.getComponents().add(new ReleaseComponent(component1.getId(), "1.0.0"));
		releaseRepository.save(release);
		return release;
	}

	private void v2() {
		ExamplesLoader.log.info("loading examples v2");
		final Release release = releaseRepository.findAll().get(0);
		release.getSignOffs().put("user", new SignOff());
		releaseRepository.save(release);
	}

	private void v3() {
		ExamplesLoader.log.info("loading examples v3");
		final Release release = releaseRepository.findByName("Example Release 1");

		release.setWhen(Chronic.parse("tuesday").getBeginCalendar().getTime());
		release.setDuration(TimeSpan.parse("2 hours"));
		releaseRepository.save(release);
	}

	private void v4() {
		ExamplesLoader.log.info("loading examples v4");

		final Watch watch = new Watch();
		watch.setUser("user");
		watch.setSubject("page:/releases.html");

		watchRepository.save(watch);
	}

    void v5() {
        ExamplesLoader.log.info("loading examples v5");
        final ArtifactRepository artifactRepository = new ArtifactRepository();
        artifactRepository.setName("Maven 2");
        artifactRepository.setType(ArtifactRepository.Type.MAVEN_2);
        artifactRepository.setPath("http://repo.maven.apache.org/maven2");

        artifactRepositoryRepository.save(artifactRepository);

        final Component component = new Component();
        component.setName("org.apache.maven:maven-core");
        component.setArtifactRepository(artifactRepository);

        componentRepository.save(component);

        final Release release = releaseRepository.findByName("Example Release 1");
        release.getComponents().add(new ReleaseComponent(component.getId(), "3.1.0"));

        releaseRepository.save(release);

    }

	private ChangeLog getExamples() {
		ChangeLog examples = changeLogRepository.findByName("examples");
		if (examples == null) {
			examples = new ChangeLog();
			examples.setName("examples");
		}
		return examples;
	}
}
