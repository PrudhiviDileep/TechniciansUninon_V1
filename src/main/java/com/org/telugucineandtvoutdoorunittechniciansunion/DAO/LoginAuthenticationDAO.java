/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;
/*    */ 
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import javax.transaction.Transactional;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class LoginAuthenticationDAO
/*    */ {
/*    */   @Autowired
/*    */   private DataAccess dataAccess;
/*    */   
/*    */   @Transactional
/*    */   public boolean authenticatUser(HttpServletRequest request, HttpSession session) {
/* 27 */     boolean result = false;
/*    */     
/*    */     try {
/* 30 */       String query = "from Login where userName=:userName and password=:password";
/*    */       
/* 32 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 33 */       String userName = request.getParameter("userName");
/* 34 */       String password = request.getParameter("userName");
/* 35 */       if (userName != null && !"".equalsIgnoreCase(userName) && password != null && !"".equalsIgnoreCase(password)) {
/* 36 */         parametersMap.put("userName", request.getParameter("userName"));
/* 37 */         parametersMap.put("password", request.getParameter("password"));
/* 38 */         List list = this.dataAccess.queryWithParams(query, parametersMap);
/* 39 */         if (list != null && !list.isEmpty() && list.size() > 0) {
/* 40 */           session.setMaxInactiveInterval(36000000);
/* 41 */           session.setAttribute("userName", userName);
/*    */           
/* 43 */           return true;
/*    */         } 
/*    */         
/* 46 */         result = false;
/*    */       
/*    */       }
/*    */       else {
/*    */ 
/*    */         
/* 52 */         result = false;
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 57 */     catch (Exception e) {
/*    */       
/* 59 */       ApplicationUtilities.error(getClass(), e, "authenticatUser");
/* 60 */       return result;
/*    */     } 
/* 62 */     return result;
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\DAO\LoginAuthenticationDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */