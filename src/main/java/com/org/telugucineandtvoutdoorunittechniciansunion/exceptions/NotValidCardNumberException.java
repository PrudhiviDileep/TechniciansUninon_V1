/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.exceptions;
/*    */ 
/*    */ public class NotValidCardNumberException
/*    */   extends RuntimeException {
/*  5 */   private String exceptinmessage = " Card Number Should contain Numerics Only! ";
/*    */   
/*    */   private String cardNo;
/*    */ 
/*    */   
/*    */   public NotValidCardNumberException() {}
/*    */ 
/*    */   
/*    */   public NotValidCardNumberException(String message) {
/* 14 */     this.exceptinmessage = message;
/*    */   }
/*    */   
/*    */   public NotValidCardNumberException(String message, String cardNo) {
/* 18 */     super(message);
/* 19 */     this.cardNo = cardNo;
/*    */   }
/*    */   
/*    */   public NotValidCardNumberException(String message, String cardNo, Throwable cause) {
/* 23 */     super(message, cause);
/* 24 */     this.cardNo = cardNo;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 29 */     return super.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 34 */     return this.exceptinmessage;
/*    */   }
/*    */   
/*    */   public String getAmmount() {
/* 38 */     return this.cardNo;
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\exceptions\NotValidCardNumberException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */