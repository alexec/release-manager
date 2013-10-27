package com.alexecollins.releasemanager.web;

import com.alexecollins.releasemanager.model.Approver;
import com.alexecollins.releasemanager.model.ApproverRepository;
import com.alexecollins.releasemanager.web.audit.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author alex.collins
 */
@Controller
public class ApproverController {

    @Autowired
    ApproverRepository approverRepository;

    @RequestMapping("/approvers")
    public String index(Model model) {
        model.addAttribute("approvers", approverRepository.findAll());

        return "approvers";
    }

    @RequestMapping("/approvers/create")
    public String create() {
        return "approvers/create";
    }

	@Audit("create approver")
    @RequestMapping(value = "/approvers", method = RequestMethod.POST)
    @Transactional
    public String newApprover(@RequestParam("name") String name) {
        final Approver item = new Approver();
        item.setName(name);
        approverRepository.save(item);
        return "redirect:/approvers.html";
    }

	@Audit("delete approver")
    @RequestMapping(value = "/approvers/{id}", method = RequestMethod.POST)
    @Transactional
    public String deleteApprover(@PathVariable("id") String id) {
        approverRepository.delete(id);
        return "redirect:/approvers.html";
    }

}

