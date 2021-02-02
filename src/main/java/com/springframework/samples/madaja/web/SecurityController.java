package com.springframework.samples.madaja.web;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {
	
	@GetMapping(value = "/userAuthority")
    @ResponseBody
    public String currentUserAuthority(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String autoridad = user.getAuthorities().iterator().next().toString();
        return autoridad;
    }
	
}
