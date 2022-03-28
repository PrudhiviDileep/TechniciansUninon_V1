/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.service;
/*    */ import java.util.Map;

/*    */ import javax.servlet.http.HttpServletRequest;

/*    */ import org.json.simple.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.web.multipart.MultipartFile;

/*    */ 
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.TeluguCineAndTVOutDoorUnitTechniciansUnionDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class TeluguCineAndTVOutDoorUnitTechniciansUnionService
/*    */ {
/*    */   @Autowired
/*    */   public TeluguCineAndTVOutDoorUnitTechniciansUnionDAO appDAO;
/*    */   
/*    */   public String login(HttpServletRequest request, Map<String, Object> model) {
/* 23 */     return this.appDAO.login(request, model);
/*    */   }
/*    */ 
/*    */   
/*    */   public String registration(JSONObject items, MultipartFile[] file) {
/* 28 */     return this.appDAO.registration(items, file);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String viewMemberDetails(HttpServletRequest request) {
/* 40 */     return this.appDAO.viewMemberDetails(request);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String updateMemberDetails(JSONObject items, MultipartFile[] file) {
/* 51 */     return this.appDAO.updateMemberDetails(items, file);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String payMembershipAmount(HttpServletRequest request, Map<String, Object> model) {
/* 57 */     return this.appDAO.payMembershipAmount(request, model);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCardBalance(HttpServletRequest request, Map<String, Object> model) {
/* 63 */     return this.appDAO.getCardBalance(request, model);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMembersDetails(HttpServletRequest request, Map<String, Object> model) {
/* 69 */     return this.appDAO.getMembersDetails(request, model);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String payCardBalance(HttpServletRequest request) {
/* 75 */     return this.appDAO.payCardBalance(request);
/*    */   }
/*    */   
/*    */   public JSONObject updateCardBalanceFormDetails(HttpServletRequest request) {
/* 79 */     return this.appDAO.updateCardBalanceFormDetails(request);
/*    */   }
/*    */   
/*    */   public JSONObject deleteCardBalanceFormDetails(HttpServletRequest request) {
/* 83 */     return this.appDAO.deleteCardBalanceFormDetails(request);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String updateMembershipPayments(HttpServletRequest request) {
/* 90 */     return this.appDAO.updateMembershipPayments(request);
/*    */   }
/*    */ 
/*    */   
/*    */   public String deleteMembershipPayments(HttpServletRequest request) {
/* 95 */     return this.appDAO.deleteMembershipPayments(request);
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\service\TeluguCineAndTVOutDoorUnitTechniciansUnionService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */