package com.raman.flightreservation.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.raman.flightreservation.dto.DynamicFilterBean;

@RestController
public class DynamicFilterBeanController {

	@GetMapping(path = "/dynamicFilterBean")
	public MappingJacksonValue getDynamicFilterBean() {
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name","email");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFB", filter);
		
		DynamicFilterBean dynamicFilterBean = new DynamicFilterBean("Raman", "rkp@gmail.com", "Raman's Password");
		
		MappingJacksonValue mapping = new MappingJacksonValue(dynamicFilterBean);
		mapping.setFilters(filters);
		
		return mapping;
		
	}
	
	@GetMapping(path = "/dynamicFilterBeanList")
	public MappingJacksonValue getDynamicFilterBeanList() {
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFB", filter);
		
		List<DynamicFilterBean> list = Arrays.asList(new DynamicFilterBean("Raman", "rkp@gmail.com", "Raman's Password"),
				new DynamicFilterBean("Pawan", "pkp@gmail.com", "Pawan's Password"));

		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
}
