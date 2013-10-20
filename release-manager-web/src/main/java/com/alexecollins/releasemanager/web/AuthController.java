package com.alexecollins.releasemanager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

	@RequestMapping("/security")
	public String login() {
		return "auth/security";
	}

	@RequestMapping("/denied")
	public String denied() {
		return "auth/denied";
	}
}
