/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.controller;
/*    */ 
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.service.LoginAuthenticationService;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMethod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ public class LoginAuthenticationController
/*    */ {
/*    */   @Autowired
/*    */   public LoginAuthenticationService loginAuthenticationService;
/*    */   @Autowired
/*    */   HttpSession httpSession;
/*    */   
/*    */   @RequestMapping(value = {"/authenticateUser"}, method = {RequestMethod.POST})
/*    */   public String authenticatUser(HttpServletRequest request, Map<String, Object> model) {
/* 27 */     String result = "";
/*    */     
/* 29 */     if (this.loginAuthenticationService.authenticatUser(request, this.httpSession)) {
/* 30 */       model.put("LOGIN_MESSAGE", "");
/* 31 */       result = "welcome";
/*    */     } else {
/*    */       
/* 34 */       model.put("LOGIN_MESSAGE", "Invalid Username or password");
/* 35 */       result = "login";
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 40 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"/login"}, method = {RequestMethod.GET})
/*    */   public String login(HttpServletRequest request) {
/* 47 */     return "loginpage";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"/logout"}, method = {RequestMethod.GET})
/*    */   public String logout(HttpServletRequest request, Map<String, Object> model) {
/* 57 */     this.httpSession.invalidate();
/* 58 */     return "redirect:login";
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\controller\LoginAuthenticationController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */