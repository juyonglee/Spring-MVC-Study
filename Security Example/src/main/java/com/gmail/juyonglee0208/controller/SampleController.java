package com.gmail.juyonglee0208.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping(value = {"/sample/*"})
public class SampleController {
	
	@RequestMapping(value = {"/all"}, method = {RequestMethod.GET})
	public void all() {
		log.info("[All] Fully Access!!");
	}
	
	@RequestMapping(value = {"/member"}, method = {RequestMethod.GET})
	public void memeberAccess() {
		log.info("[Member] Member Access!!");
	}
	
	@RequestMapping(value = {"/admin"}, method = {RequestMethod.GET})
	public void adminAccess() {
		log.info("[Admin] Admin Access!!");
	}
}
