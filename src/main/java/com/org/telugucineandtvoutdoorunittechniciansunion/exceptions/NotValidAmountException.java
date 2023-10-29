 package com.org.telugucineandtvoutdoorunittechniciansunion.exceptions;
 
 
 
 public class NotValidAmountException
   extends RuntimeException
 {
   private String exceptinmessage;
   private String ammount;
   
   public NotValidAmountException() {}
   
   public NotValidAmountException(String message) {
     this.exceptinmessage = message;
   }
   
   public NotValidAmountException(String message, String strAmount) {
     super(message);
     this.ammount = strAmount;
   }
   
   public NotValidAmountException(String message, String strAmount, Throwable cause) {
     super(message, cause);
     this.ammount = strAmount;
   }
 
   
   public String toString() {
     return super.toString();
   }
 
   
   public String getMessage() {
     return String.valueOf(String.valueOf(super.getMessage())) + " for Entered Amount :" + this.ammount;
   }
 
   
   public String getAmmount() {
     return this.ammount;
   }
 }
