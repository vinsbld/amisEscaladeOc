package com.oc.web;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * The Class viewController.
 */
@Controller
public class viewController {
	
	final static Logger logger = LogManager.getLogger(Level.ALL);
	
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
