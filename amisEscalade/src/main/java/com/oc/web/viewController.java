package com.oc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * The Class viewController.
 */
@Controller
public class viewController {
	
	/**
	 * Index.display the index page
	 *
	 * @return the index
	 */
	@GetMapping("/index")
	public String index() {
		return "index";
	}

}
