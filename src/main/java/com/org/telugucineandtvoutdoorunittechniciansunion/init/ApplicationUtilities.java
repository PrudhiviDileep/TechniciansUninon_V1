/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.init;
/*    */ 
/*    */ import java.io.File;

/*    */ import org.apache.log4j.PropertyConfigurator;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class ApplicationUtilities
/*    */ {
/*    */   static {
/* 16 */     PropertyConfigurator cnf = new PropertyConfigurator();
/*    */     
/* 19 */     File log4jfile = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\AppLoggerConfigPropertes.lcf");
/* 20 */     PropertyConfigurator.configure(log4jfile.getAbsolutePath());
/*    */   }
/*    */   
/*    */   public static void error(Class className, Exception e, String methodName) {
	System.out.println("ERROR MESSAGE :: "+e.getMessage());
	e.printStackTrace();
/* 24 */     Logger logger = LoggerFactory.getLogger(className.getClass().getName());
/* 25 */     logger.error(String.valueOf(String.valueOf(className.getName())) + " << ::: " + methodName + " ::: >>" + e.getMessage());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void debug(Class className, String debugMessabe) {
	System.out.println("DEBUG MESSAGE :: "+debugMessabe);
/* 31 */     Logger logger = LoggerFactory.getLogger(className.getClass().getName());
/* 32 */     logger.debug("!!" + debugMessabe + "!!");
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\init\ApplicationUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */