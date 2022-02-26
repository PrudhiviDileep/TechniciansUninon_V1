/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Locale;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMethod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ public class HomeController
/*    */ {
/* 20 */   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @RequestMapping(value = {"/loggin"}, method = {RequestMethod.GET})
/*    */   public String home(Locale locale, Model model) {
/* 27 */     logger.info("Welcome home! The client locale is {}.", locale);
/*    */     
/* 29 */     Date date = new Date();
/* 30 */     DateFormat dateFormat = DateFormat.getDateTimeInstance(1, 1, locale);
/*    */     
/* 32 */     String formattedDate = dateFormat.format(date);
/*    */     
/* 34 */     model.addAttribute("serverTime", formattedDate);
/*    */     
/* 36 */     return "home";
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\HomeController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */