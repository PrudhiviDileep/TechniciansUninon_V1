/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.exceptions;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NotValidAmountException
/*    */   extends RuntimeException
/*    */ {
/*    */   private String exceptinmessage;
/*    */   private String ammount;
/*    */   
/*    */   public NotValidAmountException() {}
/*    */   
/*    */   public NotValidAmountException(String message) {
/* 14 */     this.exceptinmessage = message;
/*    */   }
/*    */   
/*    */   public NotValidAmountException(String message, String strAmount) {
/* 18 */     super(message);
/* 19 */     this.ammount = strAmount;
/*    */   }
/*    */   
/*    */   public NotValidAmountException(String message, String strAmount, Throwable cause) {
/* 23 */     super(message, cause);
/* 24 */     this.ammount = strAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 29 */     return super.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 34 */     return String.valueOf(String.valueOf(super.getMessage())) + " for Entered Amount :" + this.ammount;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAmmount() {
/* 39 */     return this.ammount;
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\exceptions\NotValidAmountException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */