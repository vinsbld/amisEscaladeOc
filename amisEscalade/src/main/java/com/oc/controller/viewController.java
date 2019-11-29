package com.oc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class viewController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/association")
	public String asso() {
		return "association";
	}
	
	@GetMapping("/userco")
	public String userCo() {
		return "userGrimperPage";
	}
	



}
