package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.LoanDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoanRecoveryDetails;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
	@Autowired
	public LoanDAO loanDAO;

	public String sanctionLoan(HttpServletRequest request) {
		return this.loanDAO.sanctionLoan(request);
	}

	public String loanSummary(HttpServletRequest request) {
		return this.loanDAO.loanSummary(request);
	}

	public String payLoanAmount(HttpServletRequest request) {
		return this.loanDAO.payLoanAmount(request);
	}

	public JSONObject updateLoanSanctionDetailsFormDetails(HttpServletRequest request) {
		return this.loanDAO.updateLoanSanctionDetailsFormDetails(request);
	}

	public JSONObject updateLoanRecoveryDetailsFormDetails(HttpServletRequest request) {
		return this.loanDAO.updateLoanRecoveryDetailsFormDetails(request);
	}

	public JSONObject deleteLoanRecoveryDetailsFormDetails(HttpServletRequest request) {
		return this.loanDAO.deleteLoanRecoveryDetailsFormDetails(request);
	}

	   public String updateLoanRecoveryDetails(HttpServletRequest request) {
     return this.loanDAO.updateLoanRecoveryDetails(request);
   }
 
   
   public String updateSanctionDetails(HttpServletRequest request) {
     return this.loanDAO.updateSanctionDetails(request);
   }
   public String deleteLoanRecoveryDetails(HttpServletRequest request) {
     return this.loanDAO.deleteLoanRecoveryDetails(request);
   }
   
   public String deleteLoanSanctionDetails(HttpServletRequest request) {
     return this.loanDAO.deleteLoanSanctionDetails(request);
   }
   
   public LoanRecoveryDetails getLoanRecoveryDetails(String memberId, String transId, String loanId) {
     return this.loanDAO.getLoanRecoveryDetails(memberId, transId, loanId);
   }
 
 
   
   public String getLoanDetails(HttpServletRequest request) {
     return this.loanDAO.getLoanDetails(request);
   }
 }