 package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;
 
 import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
 import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpSession;
 import javax.transaction.Transactional;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 
 
 
 @Repository
 public class LoginAuthenticationDAO
 {
   @Autowired
   private DataAccess dataAccess;
   
   @Transactional
   public boolean authenticatUser(HttpServletRequest request, HttpSession session) {
     boolean result = false;
     
     try {
       String query = "from Login where userName=:userName and password=:password";
       
       Map<String, Object> parametersMap = new HashMap<String, Object>();
       String userName = request.getParameter("userName");
       String password = request.getParameter("userName");
       if (userName != null && !"".equalsIgnoreCase(userName) && password != null && !"".equalsIgnoreCase(password)) {
         parametersMap.put("userName", request.getParameter("userName"));
         parametersMap.put("password", request.getParameter("password"));
         List list = this.dataAccess.queryWithParams(query, parametersMap);
         if (list != null && !list.isEmpty() && list.size() > 0) {
           session.setMaxInactiveInterval(36000000);
           session.setAttribute("userName", userName);
           
           return true;
         } 
         
         result = false;
       
       }
       else {
 
         
         result = false;
       
       }
     
     }
     catch (Exception e) {
       
       ApplicationUtilities.error(getClass(), e, "authenticatUser");
       return result;
     } 
     return result;
   }
 }

