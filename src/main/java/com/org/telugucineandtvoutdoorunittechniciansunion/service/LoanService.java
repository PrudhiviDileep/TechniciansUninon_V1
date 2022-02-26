/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.service;
/*    */ 
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.LoanDAO;
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.LoanRecoveryDetails;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.json.simple.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class LoanService
/*    */ {
/*    */   @Autowired
/*    */   public LoanDAO loanDAO;
/*    */   
/*    */   public String sanctionLoan(HttpServletRequest request) {
/* 20 */     return this.loanDAO.sanctionLoan(request);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String loanSummary(HttpServletRequest request) {
/* 26 */     return this.loanDAO.loanSummary(request);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String payLoanAmount(HttpServletRequest request) {
/* 32 */     return this.loanDAO.payLoanAmount(request);
/*    */   }
/*    */   public JSONObject updateLoanSanctionDetailsFormDetails(HttpServletRequest request) {
/* 35 */     return this.loanDAO.updateLoanSanctionDetailsFormDetails(request);
/*    */   }
/*    */   
/*    */   public JSONObject updateLoanRecoveryDetailsFormDetails(HttpServletRequest request) {
/* 39 */     return this.loanDAO.updateLoanRecoveryDetailsFormDetails(request);
/*    */   }
/*    */   
/*    */   public JSONObject deleteLoanRecoveryDetailsFormDetails(HttpServletRequest request) {
/* 43 */     return this.loanDAO.deleteLoanRecoveryDetailsFormDetails(request);
/*    */   }
/*    */   
/*    */   public String updateLoanRecoveryDetails(HttpServletRequest request) {
/* 47 */     return this.loanDAO.updateLoanRecoveryDetails(request);
/*    */   }
/*    */ 
/*    */   
/*    */   public String updateSanctionDetails(HttpServletRequest request) {
/* 52 */     return this.loanDAO.updateSanctionDetails(request);
/*    */   }
/*    */   public String deleteLoanRecoveryDetails(HttpServletRequest request) {
/* 55 */     return this.loanDAO.deleteLoanRecoveryDetails(request);
/*    */   }
/*    */   
/*    */   public String deleteLoanSanctionDetails(HttpServletRequest request) {
/* 59 */     return this.loanDAO.deleteLoanSanctionDetails(request);
/*    */   }
/*    */   
/*    */   public LoanRecoveryDetails getLoanRecoveryDetails(String memberId, String transId, String loanId) {
/* 63 */     return this.loanDAO.getLoanRecoveryDetails(memberId, transId, loanId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLoanDetails(HttpServletRequest request) {
/* 69 */     return this.loanDAO.getLoanDetails(request);
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\service\LoanService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */