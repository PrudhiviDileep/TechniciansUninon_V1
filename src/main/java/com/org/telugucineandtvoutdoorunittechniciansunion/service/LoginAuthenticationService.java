package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.LoginAuthenticationDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAuthenticationService {
	@Autowired
	public LoginAuthenticationDAO loginAuthenticationDAO;

	public boolean authenticatUser(HttpServletRequest request, HttpSession session) {
		return this.loginAuthenticationDAO.authenticatUser(request, session);
	}
}
