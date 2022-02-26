/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.service;
/*    */ 
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.LoginAuthenticationDAO;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class LoginAuthenticationService
/*    */ {
/*    */   @Autowired
/*    */   public LoginAuthenticationDAO loginAuthenticationDAO;
/*    */   
/*    */   public boolean authenticatUser(HttpServletRequest request, HttpSession session) {
/* 22 */     return this.loginAuthenticationDAO.authenticatUser(request, session);
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\service\LoginAuthenticationService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */