package com.oc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * The Class viewController.
 */
@Controller
public class viewController {
	
	/**
	 * Index.
	 *
	 * @return the string
	 */
	@GetMapping("/index")
	public String index() {
		return "index";
	}

}
