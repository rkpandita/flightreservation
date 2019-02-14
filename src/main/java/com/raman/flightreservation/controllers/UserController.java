package com.raman.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.raman.flightreservation.entities.User;
import com.raman.flightreservation.repository.UserRepository;

@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private static final String LOGIN_PAGE = "login/login";

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "showLogin", method = RequestMethod.GET)
	public String showLoginPage() {
		LOGGER.info("Inside showLoginPage()");
		return LOGIN_PAGE;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
		LOGGER.info("Inside login() with email {} ", email);
		User user = userRepository.findByEmail(email);
		if (user.getPassword().equals(password)) {
			return "findFlights";
		} else {
			modelMap.addAttribute("msg","Invalid User Name or Password. Please try again.");
		}
		return LOGIN_PAGE;
	}
	
	@RequestMapping(value = "showReg", method = RequestMethod.GET)
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}

	@RequestMapping(value = "registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register() {}", user);
		userRepository.save(user);
		return LOGIN_PAGE;
	}
	
}
