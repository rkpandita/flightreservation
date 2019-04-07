package com.raman.flightreservation.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raman.flightreservation.dto.StaticFilterBean;

@RestController
public class StaticFilterBeanController {

	@GetMapping(path = "/staticFilterBean")
	public StaticFilterBean getStaticFilterBean() {
		return new StaticFilterBean("Raman", "rkp@gmail.com", "Raman's Password");
	}
	
	@GetMapping(path = "/staticFilterBeanList")
	public List<StaticFilterBean> getStaticFilterBeanList() {
		return Arrays.asList(new StaticFilterBean("Raman", "rkp@gmail.com", "Raman's Password"),
				new StaticFilterBean("Pawan", "pkp@gmail.com", "Pawan's Password"));
	}
	
}
