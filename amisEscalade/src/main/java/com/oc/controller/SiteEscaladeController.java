package com.oc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteEscaladeController {

	@GetMapping("/formCreaSite")
	public String formSite() {
		return "formSiteEscalade";
	}
}
