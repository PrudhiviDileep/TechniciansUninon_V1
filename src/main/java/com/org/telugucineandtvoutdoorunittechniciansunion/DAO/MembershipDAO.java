 package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;
 
 import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
 import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
 import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPayments;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.transaction.Transactional;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 
 
 
 @Repository
 public class MembershipDAO
 {
   @Autowired
   public DataAccess dataAccess;
   
   @Transactional
   public List<MembershipPayments> getMembershipPaymentsByMemberId(String memberId) {
     List<MembershipPayments> list = null;
     try {
       String query = "from MembershipPayments where membershipPaymentsPK.memberId =:memberId ";
       Map<String, Object> parametersMap = new HashMap<String, Object>();
       parametersMap.put("memberId", memberId);
       list = this.dataAccess.queryWithParams(query, parametersMap);
     } catch (Exception e) {
       
       ApplicationUtilities.error(getClass(), e, "getMembershipPaymentsByMemberId");
     } 
     
     return list;
   }
   
   @Transactional
   public MembershipPayments getMembershipPaymentsDetails(String memberId, String transId) {
     MembershipPayments membershipPayments = null;
     
     try {
       String query = "from MembershipPayments where membershipPaymentsPK.memberId =:memberId and membershipPaymentsPK.transactionId =:transactionId";
       Map<String, Object> parametersMap = new HashMap<String, Object>();
       parametersMap.put("memberId", memberId);
       parametersMap.put("transactionId", transId);
       List<MembershipPayments> list = this.dataAccess.queryWithParams(query, parametersMap);
       if (list != null && list.size() > 0) {
         membershipPayments = list.get(0);
       }
     }
     catch (Exception e) {
       
       ApplicationUtilities.error(getClass(), e, "getMembershipPaymentsDetails");
     } 
     
     return membershipPayments;
   }
 }
