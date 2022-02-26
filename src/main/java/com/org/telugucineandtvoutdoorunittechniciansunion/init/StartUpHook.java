/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.init;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.log4j.PropertyConfigurator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StartUpHook
/*    */   extends HttpServlet
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public StartUpHook() {
/* 24 */     PropertyConfigurator cnf = new PropertyConfigurator();
/*    */     
/* 26 */     File log4jfile = new File("ApplicationProperties/WEB-INF/AppLoggerConfigPropertes.lcf");
/*    */     
/* 28 */     PropertyConfigurator.configure(log4jfile.getAbsolutePath());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 39 */     response.getWriter().append("Served At : ").append(request.getContextPath());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 47 */     doGet(request, response);
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\init\StartUpHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */