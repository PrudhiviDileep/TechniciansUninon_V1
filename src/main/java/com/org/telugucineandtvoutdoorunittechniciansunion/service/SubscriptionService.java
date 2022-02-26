/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.service;
/*    */ 
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.SubscriptionDAO;
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.json.simple.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class SubscriptionService
/*    */ {
/*    */   @Autowired
/*    */   public SubscriptionDAO subscriptionDAO;
/*    */   
/*    */   public String getSubscriptionDetatils(HttpServletRequest request, Map<String, Object> model) {
/* 22 */     return this.subscriptionDAO.getSubscriptionDetatils(request, model);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String paySubscriptionAmount(HttpServletRequest request) {
/* 28 */     return this.subscriptionDAO.paySubscriptionAmount(request);
/*    */   }
/*    */   
/*    */   public JSONObject updateSubcriptionsFormDetails(HttpServletRequest request) {
/* 32 */     return this.subscriptionDAO.updateSubcriptionsFormDetails(request);
/*    */   }
/*    */   
/*    */   public JSONObject deleteSubcriptionsFormDetails(HttpServletRequest request) {
/* 36 */     return this.subscriptionDAO.deleteSubcriptionsFormDetails(request);
/*    */   }
/*    */   
/*    */   public SubscriptionPayments getSubscriptionDetails(String memberId, String transId) {
/* 40 */     return this.subscriptionDAO.getSubscriptionDetails(memberId, transId);
/*    */   }
/*    */   
/*    */   public String updateSubcriptions(HttpServletRequest request) {
/* 44 */     return this.subscriptionDAO.updateSubcriptions(request);
/*    */   }
/*    */   
/*    */   public String deleteSubcriptions(HttpServletRequest request) {
/* 48 */     return this.subscriptionDAO.deleteSubcriptions(request);
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\service\SubscriptionService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */