/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.controller;
/*     */ import java.util.Map;

/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;

/*     */ import org.json.simple.JSONObject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestMethod;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;

/*     */ 
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.service.MiscellaneousService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ public class MiscellaneousController
/*     */ {
	public static final String FINAL_RESULT_CODE="FINAL_RESULT_CODE";
	public static final String FINAL_RESULT="FINAL_RESULT";
/*     */   @Autowired
/*     */   public MiscellaneousService miscellaneousService;
/*     */   
/*     */   @RequestMapping(value = {"/getTopPanel"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String getTopPanel(@RequestParam("cardNo") String cardNo, @RequestParam("deptId") String deptId, @RequestParam("pageId") String pageId, HttpServletRequest request, Map<String, Object> model) {
/*  29 */     JSONObject resultObj = null;
/*  30 */     int cardNumber = 0;
/*  31 */     JSONObject obj = new JSONObject();
/*     */     
/*     */     try {
/*  34 */       if (cardNo == null || "".equals(cardNo)) {
/*     */         
/*  36 */         resultObj = new JSONObject();
/*  37 */         resultObj.put(FINAL_RESULT_CODE, "200");
/*  38 */         resultObj.put(FINAL_RESULT, "Card Number should not be empty! ");
/*     */       }
/*  40 */       else if (deptId == null || "".equals(deptId)) {
/*     */         
/*  42 */         resultObj = new JSONObject();
/*  43 */         resultObj.put(FINAL_RESULT_CODE, "200");
/*  44 */         resultObj.put(FINAL_RESULT, "Card Number should not be empty! ");
/*     */       } else {
/*     */         
/*     */         try {
/*  48 */           cardNumber = Integer.parseInt(cardNo);
/*     */           
/*  50 */           resultObj = new JSONObject();
/*  51 */           resultObj.put(FINAL_RESULT_CODE, "400");
/*  52 */           resultObj.put(FINAL_RESULT, this.miscellaneousService.getTopPanel(cardNumber, deptId, pageId));
/*     */         }
/*  54 */         catch (NumberFormatException nfe) {
/*     */           
/*  56 */           resultObj = new JSONObject();
/*  57 */           resultObj.put(FINAL_RESULT_CODE, "300");
/*  58 */           resultObj.put(FINAL_RESULT, "Card Number should Numeric! ");
/*  59 */         } catch (Exception e) {
/*     */ 
/*     */           
/*  62 */           resultObj = new JSONObject();
/*  63 */           resultObj.put(FINAL_RESULT_CODE, "300");
/*  64 */           resultObj.put(FINAL_RESULT, e.getMessage());
/*     */         }
/*     */       
/*     */       }
/*     */     
/*  69 */     } catch (Exception e) {
/*     */       
/*  71 */       resultObj = new JSONObject();
/*  72 */       resultObj.put(FINAL_RESULT_CODE, "300");
/*  73 */       resultObj.put(FINAL_RESULT, e.getMessage());
/*  74 */       ApplicationUtilities.error(getClass(), e, "getTopPanel");
/*     */     } 
/*  76 */     return resultObj.toJSONString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getCardNumbersByDeptId"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String getCardNumbersByDeptId(HttpServletRequest request, @RequestParam("deptId") String deptId) {
/*  84 */     String resutlt = "";
/*  85 */     JSONObject obj = new JSONObject();
/*     */     
/*     */     try {
/*  88 */       resutlt = this.miscellaneousService.getCardNumbersByDeptId(deptId);
/*  89 */     } catch (Exception e) {
/*     */       
/*  91 */       ApplicationUtilities.error(getClass(), e, "getCardNumbersByDeptId");
/*     */     } 
/*     */     
/*  94 */     return resutlt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getCardNumbersByDeptIdForAutocomplete"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String getCardNumbersByDeptIdForAutocomplete(HttpServletRequest request, @RequestParam("deptId") String deptId, @RequestParam("term") String term) {
/* 102 */     String resutlt = "";
/* 103 */     JSONObject obj = new JSONObject();
/*     */     
/*     */     try {
/* 106 */       resutlt = this.miscellaneousService.getCardNumbersByDeptIdForAutocomplete(deptId, (term != null) ? term : "");
/* 107 */     } catch (Exception e) {
/*     */       
/* 109 */       ApplicationUtilities.error(getClass(), e, "getCardNumbersByDeptIdForAutocomplete");
/*     */     } 
/*     */     
/* 112 */     return resutlt;
/*     */   }
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getUnits"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String getUnits(HttpServletRequest request, HttpServletResponse response) {
/* 119 */     String result = "";
/*     */     
/*     */     try {
/* 122 */       result = this.miscellaneousService.getUnits().toJSONString();
/*     */     }
/* 124 */     catch (Exception e) {
/* 125 */       ApplicationUtilities.error(getClass(), e, "getUnits");
/*     */     } 
/*     */     
/* 128 */     return result;
/*     */   }
/*     */   
/*     */   @RequestMapping(value = {"/getDetialsBySelectAtion"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String getDetialsBySelectAtion(HttpServletRequest request) {
/* 134 */     String result = "";
/*     */     try {
/* 136 */       result = this.miscellaneousService.getDetialsBySelectAtion(request);
/*     */     }
/* 138 */     catch (Exception e) {
/*     */       
/* 140 */       ApplicationUtilities.error(getClass(), e, "getDetialsBySelectAtion");
/*     */     } 
/* 142 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @RequestMapping(value = {"/getMemberDetailsForRecomondation"}, method = {RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public String getMemberDetailsForRecomondation(String deptId, String cardNo) {
/* 150 */     return this.miscellaneousService.getMemberDetailsForRecomondation(deptId, cardNo).toJSONString();
/*     */   }
                                                                                                       


/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\controller\MiscellaneousController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */