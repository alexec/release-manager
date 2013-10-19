package com.alexecollins.web;

import com.alexecollins.releasemanager.model.Release;
import com.alexecollins.releasemanager.model.ReleaseRepository;
import com.mdimension.jchronic.Chronic;
import com.mdimension.jchronic.utils.Span;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
@Slf4j
public class ReleaseCalendarController {

	@Autowired
	ReleaseRepository releaseRepository;

	@RequestMapping("/releases_calendar")
	public
	@ResponseBody
	CalendarResponse calendar(long from, long to, HttpServletRequest request) {
		log.info("args={}..{}", from, to);
		List<Event> events = new ArrayList<>();
		for (Release release : releaseRepository.findAll(new Sort(new Sort.Order("begin")))) {
			if (release.getWhen() == null) continue;
			final Span when = Chronic.parse(release.getWhen());
			log.info("when={}..{}", when.getBegin(), when.getEnd());
			//log.info(when.getEnd()*1000 +">" +from +"&&" +when.getBegin() *1000+"<"+ to);
			if (when.getEnd()*1000 > from && when.getBegin()*100 < to)
				events.add(new Event(release.getId(), release.getName(), request.getContextPath() + "/releases/" + release.getId() + ".html", when.getBegin()*1000, when.getEnd()*1000));
		}

		Collections.sort(events);

		log.info("events={}", events);

		return new CalendarResponse(events);
	}

}
