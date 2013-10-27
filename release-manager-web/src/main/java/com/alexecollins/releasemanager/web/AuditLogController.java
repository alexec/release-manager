package com.alexecollins.releasemanager.web;

import com.alexecollins.releasemanager.model.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
public class AuditLogController {

	@Autowired
	AuditLogRepository repo;

	@RequestMapping("/audit_logs")
	public String index(Model model) {
		model.addAttribute("audit_logs", repo.findAll(new Sort(Sort.Direction.DESC, "created")));

		return "audit_logs";
	}
}
