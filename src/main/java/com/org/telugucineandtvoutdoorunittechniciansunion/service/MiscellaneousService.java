/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.service;
/*    */ import javax.servlet.http.HttpServletRequest;

/*    */ import org.json.simple.JSONArray;
/*    */ import org.json.simple.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.GenericGridDAO;
/*    */ 
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.MiscellaneousDAO;
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.PaymentConfigurations;
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class MiscellaneousService
/*    */ {
/*    */   @Autowired
/*    */   public MiscellaneousDAO miscellaneousDAO;
/*    */   
/*    */   @Autowired
/*    */   public GenericGridDAO genericDAO;

/*    */   public JSONArray getDepartments() {
/* 21 */     return this.miscellaneousDAO.getDepartments();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JSONObject getTopPanel(int cardNo, String deptId, String pageId) {
/* 27 */     return this.miscellaneousDAO.getTopPanel(cardNo, deptId, pageId);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCardNumbersByDeptId(String deptId) {
/* 32 */     return this.miscellaneousDAO.getCardNubersByDeptId(deptId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCardNumbersByDeptIdForAutocomplete(String deptId, String term) {
/* 38 */     return this.miscellaneousDAO.getCardNumbersByDeptIdForAutocomplete(deptId, term);
/*    */   }
/*    */ 
/*    */   
/*    */   public JSONArray getUnits() {
/* 43 */     return this.miscellaneousDAO.getUnits();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDetialsBySelectAtion(HttpServletRequest request) {
/* 49 */     return this.miscellaneousDAO.getDetialsBySelectAtion(request);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JSONObject getMemberDetailsForRecomondation(String deptId, String cardNo) {
/* 55 */     return this.miscellaneousDAO.getMemberDetailsForRecomondation(deptId, cardNo);
/*    */   }
/*    */ 
/*    */   
/*    */   public JSONObject getPaymentConfigDetials(String deptId, String category) {
/* 60 */     return this.miscellaneousDAO.getPaymentConfigDetials(deptId, category);
/*    */   }
/*    */ 
/*    */   
/*    */   public JSONArray getUnsubsribedYears(String deptId, String cardNo) {
/* 65 */     return this.miscellaneousDAO.getUnsubsribedYears(deptId, cardNo);
/*    */   }
/*    */ 
/*    */   
/*    */   public JSONArray getPaymentConfigDetialsForSelect(String deptId, String category) {
/* 70 */     return this.miscellaneousDAO.getPaymentConfigDetialsForSelect(deptId, category);
/*    */   }
/*    */ 
/*    */   
/*    */   public PaymentConfigurations getPaymentConfigurationsDetailsById(String paymentconfId) {
/* 75 */     return this.miscellaneousDAO.getPaymentConfigurationsDetailsById(paymentconfId);
/*    */   }
/*    */   
/*    */   public Registration getMemberDetailsByDeptIdAndCardNo(String deptId, String cardNo) {
/* 79 */     return this.miscellaneousDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
/*    */   }






/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\service\MiscellaneousService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */