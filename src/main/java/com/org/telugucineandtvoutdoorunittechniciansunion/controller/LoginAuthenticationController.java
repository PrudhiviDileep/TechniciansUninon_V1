package com.org.telugucineandtvoutdoorunittechniciansunion.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.telugucineandtvoutdoorunittechniciansunion.service.LoginAuthenticationService;

@Controller
public class LoginAuthenticationController {
	@Autowired
	public LoginAuthenticationService loginAuthenticationService;
	@Autowired
	HttpSession httpSession;

	@RequestMapping(value = { "/authenticateUser" }, method = { RequestMethod.POST })
	public String authenticatUser(HttpServletRequest request, Map<String, Object> model) {
		String result = "";

		if (this.loginAuthenticationService.authenticatUser(request, this.httpSession)) {
			model.put("LOGIN_MESSAGE", "");
			result = "welcome";
		} else {

			model.put("LOGIN_MESSAGE", "Invalid Username or password");
			result = "login";
		}

		return result;
	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
	public String login(HttpServletRequest request) {
		return "loginpage";
	}

	@RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
	public String logout(HttpServletRequest request, Map<String, Object> model) {
		this.httpSession.invalidate();
		return "redirect:login";
	}
}