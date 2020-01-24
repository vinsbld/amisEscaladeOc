package com.oc.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



/**
 * The Class ConnexionControlleur.
 */
@Controller
public class ConnexionControlleur {
	
	/**
	 * Login get.
	 *
	 * @param model the model
	 * @param error the error
	 * @param logout the logout
	 * @return the model and view formLogIn form
	 */
	@GetMapping("/connexion")
	public ModelAndView loginGet (Model model, @RequestParam(value = "error", required = false)String error, @RequestParam(value = "logout", required = false) String logout) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String errorMessage = null;
		
		if(error != null) {
			errorMessage = "Pseudo ou mot de passe incorrects !";
		}
        if(logout != null) {
            errorMessage = "Vous avez été déconecté avec succès !";
        }
        model.addAttribute("errorMessge", errorMessage);
		
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("formLogIn");
	}
	
	/**
	 * Logout page.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the login form 
	 */
	@GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}