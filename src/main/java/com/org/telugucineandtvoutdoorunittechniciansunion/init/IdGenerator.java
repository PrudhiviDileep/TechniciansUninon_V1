/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.init;
/*     */ import java.math.BigInteger;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;

import javax.transaction.Transactional;

/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Repository;

/*     */ 
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.NumberGeneration;
/*     */ import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.NumberGenerationPK;
///*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Repository
/*     */ public class IdGenerator
/*     */ {
/*     */   @Autowired
/*     */   private DataAccess dataAccess;
/*     */   
/*     */   @Transactional
/*     */   public String get(String domainType, String colName) throws Exception {
/*  26 */     String unique = null;
/*     */     try {
/*  28 */       unique = getRugged(domainType, colName);
/*  29 */     } catch (Exception e) {
/*  30 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  33 */     return unique;
/*     */   }
/*     */   
/*     */   @Transactional
/*     */   public String getRugged(String domainType, String colName) throws Exception {
/*  38 */     String unique = null;
/*     */     
/*  40 */     unique = getUniqueString(domainType, colName);
/*  41 */     if (unique == null) {
/*  42 */       createUNACTEntry(domainType, colName);
/*  43 */       unique = getUniqueString(domainType, colName);
/*     */     } 
/*     */     
/*  46 */     incrementUniqueid(domainType, colName);
/*  47 */     return unique;
/*     */   }
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   private String getUniqueString(String domainType, String colName) throws Exception {
/*  53 */     String rtn = null;
/*  54 */     String prefix = "";
/*  55 */     String padSize = "";
/*  56 */     String number = "";
/*     */     
/*     */     try {
/*  59 */       String query = "FROM NumberGeneration WHERE  type=:type AND columnName=:colName";
/*  60 */       Map<String, Object> map = new HashMap<String, Object>();
/*     */       
/*  62 */       map.put("type", domainType);
/*  63 */       map.put("colName", colName);
/*     */       
/*  65 */       List<NumberGeneration> list = this.dataAccess.queryWithParams(query, map);
/*     */       
/*  67 */       if (!list.isEmpty() && list.size() > 0) {
/*  68 */         NumberGeneration dalNumberGeneration = list.get(0);
/*  69 */         prefix = dalNumberGeneration.getNumberGenerationPK().getSeqTxt();
/*  70 */         padSize = String.valueOf(dalNumberGeneration.getSeqSize().intValue());
/*  71 */         number = String.valueOf(dalNumberGeneration.getNumberGenerationPK().getStrtUniqueSeq());
/*     */       } else {
/*     */         
/*  74 */         return (new Exception("Unique No Could not be generated")).getMessage();
/*     */       } 
/*     */       
/*  77 */       prefix = (prefix == null || prefix.equals("null")) ? "" : prefix;
/*  78 */       if (prefix.contains("<<-") && prefix.contains("->>")) {
/*  79 */         prefix = prefix.replace("<<-", "");
/*  80 */         prefix = prefix.replace("->>", "");
/*     */       } 
/*     */       
/*  83 */       int currentLength = prefix.length() + number.length();
/*  84 */       int totalLength = 0;
/*  85 */       if (padSize != null && !padSize.equals("null") && !padSize.equals("0") && !padSize.isEmpty()) {
/*  86 */         totalLength = Integer.parseInt(padSize);
/*  87 */         if (currentLength == totalLength && endOfSequence(number)) {
/*  88 */           updateEndOfSequence(domainType);
/*  89 */           rtn = padString(prefix, number, padSize, "0");
/*     */         } else {
/*  91 */           rtn = padString(prefix, number, padSize, "0");
/*     */         } 
/*     */       } else {
/*  94 */         rtn = String.valueOf(String.valueOf(prefix)) + number;
/*     */       }
/*     */     
/*     */     }
/*  98 */     catch (Exception e) {
/*  99 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/* 103 */     return rtn;
/*     */   }
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   private void createUNACTEntry(String domainType, String colName) throws SQLException {
/*     */     try {
/* 110 */       NumberGeneration dalNumberGeneration = new NumberGeneration();
/* 111 */       NumberGenerationPK dalNumberGenerationId = new NumberGenerationPK();
/*     */       
/* 113 */       dalNumberGenerationId.setStrtUniqueSeq(new BigInteger("0"));
/* 114 */       dalNumberGeneration.setColumnName(colName);
/* 115 */       dalNumberGenerationId.setSeqTxt("");
/* 116 */       dalNumberGeneration.setNumberGenerationPK(dalNumberGenerationId);
/*     */       
/* 118 */       dalNumberGeneration.setSeqSize(new BigInteger("12"));
/* 119 */       dalNumberGeneration.setType(domainType);
/* 120 */       dalNumberGeneration.setEndUniqueSeq(new BigInteger("100000000"));
/* 121 */       dalNumberGeneration.setRegexpr("");
/* 122 */       dalNumberGeneration.setDescription("");
/* 123 */       this.dataAccess.save(dalNumberGeneration);
/*     */     }
/* 125 */     catch (Exception e) {
/* 126 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   public String padString(String pre, String suf, String padSize, String padString) {
/*     */     try {
/* 136 */       int finalTotal = Integer.parseInt(padSize);
/* 137 */       int beforePad = pre.length() + suf.length();
/* 138 */       for (int x = 0; x < finalTotal - beforePad; x++) {
/* 139 */         pre = pre.concat(padString);
/*     */       }
/* 141 */       return String.valueOf(String.valueOf(pre)) + suf;
/* 142 */     } catch (Exception ex) {
/* 143 */       ex.printStackTrace();
/*     */       
/* 145 */       return String.valueOf(String.valueOf(pre)) + suf;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   private void incrementUniqueid(String domainType, String colName) throws SQLException {
/*     */     try {
/* 153 */       String query = "UPDATE NumberGeneration SET numberGenerationPK.strtUniqueSeq =numberGenerationPK.strtUniqueSeq+1 WHERE  columnName =:colName AND TYPE =:domainType ";
/*     */       
/* 155 */       Map<String, Object> map = new HashMap<String, Object>();
/* 156 */       map.put("colName", colName);
/* 157 */       map.put("domainType", domainType);
/*     */       
/* 159 */       this.dataAccess.updateQuery(query, map);
/*     */     }
/* 161 */     catch (Exception e) {
/* 162 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   private boolean endOfSequence(String suf) {
/* 170 */     while (suf.length() > 0) {
/* 171 */       if (!suf.endsWith("9")) {
/* 172 */         return false;
/*     */       }
/* 174 */       if (suf.length() == 1) {
/* 175 */         return true;
/*     */       }
/* 177 */       suf = suf.substring(0, suf.length() - 1);
/*     */     } 
/* 179 */     return true;
/*     */   }
/*     */   
/*     */   @Transactional
/*     */   private void logError(Object message, Exception ex) {
/* 184 */     ex.printStackTrace();
/*     */   }
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   private void updateEndOfSequence(String code) throws SQLException {
/* 190 */     String uniqueText = "";
/* 191 */     String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\init\IdGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */