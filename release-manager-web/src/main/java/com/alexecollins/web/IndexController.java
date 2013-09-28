package com.alexecollins.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
public class IndexController {

	@RequestMapping({"/","/index"})
	public String index() {
		return "index";
	}
}
