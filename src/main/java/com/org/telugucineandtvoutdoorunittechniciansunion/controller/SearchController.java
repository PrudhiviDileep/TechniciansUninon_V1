 package com.org.telugucineandtvoutdoorunittechniciansunion.controller;
 
 import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
 import com.org.telugucineandtvoutdoorunittechniciansunion.service.MiscellaneousService;
 import com.org.telugucineandtvoutdoorunittechniciansunion.service.SearchService;
 import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import org.json.simple.JSONObject;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.ResponseBody;
 
 
 @Controller
 public class SearchController
 {
   @Autowired
   public SearchService searchService;
   @Autowired
   public MiscellaneousService miscellaneousService;
   Utils utils = new Utils();
 
public static final String FINAL_RESULT_CODE="FINAL_RESULT_CODE";
public static final String FINAL_RESULT="FINAL_RESULT";
   @RequestMapping(value = {"/search"}, method = {RequestMethod.GET})
   public String search(HttpServletRequest request, Map<String, Object> model) {
     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
     
     return this.searchService.search(request, model);
   }
 
 
 @RequestMapping(value = {"/reports"}, method = {RequestMethod.GET})
   public String reports(HttpServletRequest request, Map<String, Object> model) {
     model.put("DEPARTMENTS", this.utils.getDepartmentsAsHTMLSelect(this.miscellaneousService.getDepartments()));
     
     return this.searchService.search(request, model);
   }
   
   @RequestMapping(value = {"/getCommonSearchResults"}, method = {RequestMethod.POST})
   @ResponseBody
   public String getCommonSearchResults(HttpServletRequest request, Map<String, Object> model) {
     JSONObject resultObj = null;
     int cardNumber = 0;
 
     
     try {
       String cardNo = request.getParameter("cardNo");
       String deptId = request.getParameter("deptId");
 
       
       if (cardNo == null || "".equals(cardNo)) {
         
         resultObj = new JSONObject();
         resultObj.put(FINAL_RESULT_CODE, "200");
         resultObj.put(FINAL_RESULT, "Card Number should not be empty! ");
       }
       else if (deptId == null || "".equals(deptId)) {
         
         resultObj = new JSONObject();
         resultObj.put(FINAL_RESULT_CODE, "200");
         resultObj.put(FINAL_RESULT, "Card Number should not be empty! ");
       } else {
 
         
         try {
           cardNumber = Integer.parseInt(cardNo);
           resultObj = new JSONObject();
           resultObj.put(FINAL_RESULT_CODE, "400");
           resultObj.put(FINAL_RESULT, this.searchService.getCommonSearchResults(request));
         
         }
         catch (NumberFormatException nfe) {
           
           resultObj = new JSONObject();
           resultObj.put(FINAL_RESULT_CODE, "300");
           resultObj.put(FINAL_RESULT, "Card Number should Numeric! ");
           ApplicationUtilities.error(getClass(), nfe, "getCommonSearchResults");
         } catch (Exception e) {
 
           
           resultObj = new JSONObject();
           resultObj.put(FINAL_RESULT_CODE, "300");
           resultObj.put(FINAL_RESULT, e.getMessage());
           ApplicationUtilities.error(getClass(), e, "getCommonSearchResults");
         }
       
       }
     
     } catch (Exception e) {
       
       ApplicationUtilities.error(getClass(), e, "getCommonSearchResults");
       resultObj = new JSONObject();
       resultObj.put(FINAL_RESULT_CODE, "300");
       resultObj.put(FINAL_RESULT, e.getMessage());
     } 
     
     return resultObj.toJSONString();
   }
 }