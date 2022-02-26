/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;
/*    */ 
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
/*    */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPayments;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.transaction.Transactional;
/*    */ import org.json.simple.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Repository
/*    */ public class MembershipDAO
/*    */ {
/*    */   @Autowired
/*    */   public DataAccess dataAccess;
/*    */   
/*    */   @Transactional
/*    */   public List<MembershipPayments> getMembershipPaymentsByMemberId(String memberId) {
/* 24 */     List<MembershipPayments> list = null;
/*    */     try {
/* 26 */       String query = "from MembershipPayments where membershipPaymentsPK.memberId =:memberId ";
/* 27 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 28 */       parametersMap.put("memberId", memberId);
/* 29 */       list = this.dataAccess.queryWithParams(query, parametersMap);
/* 30 */     } catch (Exception e) {
/*    */       
/* 32 */       ApplicationUtilities.error(getClass(), e, "getMembershipPaymentsByMemberId");
/*    */     } 
/*    */     
/* 35 */     return list;
/*    */   }
/*    */   
/*    */   @Transactional
/*    */   public MembershipPayments getMembershipPaymentsDetails(String memberId, String transId) {
/* 40 */     JSONObject resultObj = new JSONObject();
/* 41 */     MembershipPayments membershipPayments = null;
/*    */     
/*    */     try {
/* 44 */       String query = "from MembershipPayments where membershipPaymentsPK.memberId =:memberId and membershipPaymentsPK.transactionId =:transactionId";
/* 45 */       Map<String, Object> parametersMap = new HashMap<String, Object>();
/* 46 */       parametersMap.put("memberId", memberId);
/* 47 */       parametersMap.put("transactionId", transId);
/* 48 */       List<MembershipPayments> list = this.dataAccess.queryWithParams(query, parametersMap);
/* 49 */       if (list != null && list.size() > 0) {
/* 50 */         membershipPayments = list.get(0);
/*    */       }
/*    */     }
/* 53 */     catch (Exception e) {
/*    */       
/* 55 */       ApplicationUtilities.error(getClass(), e, "getMembershipPaymentsDetails");
/*    */     } 
/*    */     
/* 58 */     return membershipPayments;
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\DAO\MembershipDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */