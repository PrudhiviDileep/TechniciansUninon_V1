package com.org.telugucineandtvoutdoorunittechniciansunion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.org.telugucineandtvoutdoorunittechniciansunion.service.MembershipService;

@Controller
public class MembershipController {
	@Autowired
	public MembershipService membershipService;
}
