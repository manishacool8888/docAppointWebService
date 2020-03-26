package com.docappoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.docappoint.responsebean.AuthResponseBean;
import com.docappoint.service.LoginService;

@CrossOrigin(origins="${doc.appoint.crossorigins}")
@RestController
public class BasicAuthenticationController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping(path="/api/login/{username}")
	public ResponseEntity<?> login(@PathVariable String username) {
		
		AuthResponseBean authResponse = loginService.login(username);
		return new ResponseEntity<AuthResponseBean>(authResponse,HttpStatus.OK);
	}
}
