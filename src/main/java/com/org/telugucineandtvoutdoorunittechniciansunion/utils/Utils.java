/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
import java.util.HashMap;
/*     */ import java.util.Map;
import java.util.Properties;
import java.util.Set;

/*     */ import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/*     */ import org.json.simple.JSONArray;
/*     */ import org.json.simple.JSONObject;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.support.PropertiesLoaderUtils;
/*     */ import org.springframework.util.FileCopyUtils;

/*     */ import sun.misc.BASE64Encoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Utils
/*     */ {
/*     */   public String notNullChecker(String str) {
/*  23 */     return (str != null) ? str.trim() : "";
/*     */   }
/*     */   
/*     */   public boolean nonNullNonEmpty(String str) {
/*  27 */     return (str != null && !"".equals(str));
/*     */   }
/*     */ 
/*     */   
/*     */   public JSONObject requestParamsToJSON(ServletRequest req) {
/*  32 */     JSONObject jsonObj = new JSONObject();
/*  33 */     Map<String, String[]> params = req.getParameterMap();
/*  34 */     for (Map.Entry<String, String[]> entry : params.entrySet()) {
/*  35 */       String[] v = entry.getValue();
/*  36 */       Object o = (v.length == 1) ? v[0] : v;
/*  37 */       jsonObj.put(entry.getKey(), o);
/*     */     } 
/*  39 */     return jsonObj;
/*     */   }
/*     */ 
/*     */ 

/*     */   public static Map<String,String>  requestParamsToMap(HttpServletRequest	 req) {
/*  32 */     Map<String,String> reqMap=new HashMap<String, String>();
/*  33 */     Map<String, String[]> params = req.getParameterMap();
/*  34 */     for (Map.Entry<String, String[]> entry : params.entrySet()) {
/*  35 */       String[] v = entry.getValue();
/*  36 */       Object o = (v.length == 1) ? v[0] : v;
/*  37 */       reqMap.put(entry.getKey().toString(), o.toString());
/*     */     } 
/*  39 */     return reqMap;
/*     */   }

/*     */   
/*     */   public void fileWriter(String dir, String filenameWithExt, byte[] bytes) {
/*     */     try {
/*  46 */       File directory = new File(dir);
/*     */ 
/*     */       
/*  49 */       if (!directory.exists()) {
/*  50 */         directory.mkdirs();
/*     */       } else {
/*  52 */         directory.delete();
/*  53 */         directory.mkdirs();
/*     */       } 
/*  56 */       FileCopyUtils.copy(bytes, 
/*  57 */           new FileOutputStream(String.valueOf(String.valueOf(directory.getAbsolutePath())) + File.separator + filenameWithExt));
/*     */     }
/*  59 */     catch (IOException ex) {
/*  60 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String convertImageToBase64(byte[] imageBytes, String fileExtension) {
/*  65 */     String result = "";
/*     */     try {
/*  67 */       if (fileExtension != null && !"".equals(fileExtension) && imageBytes != null && imageBytes.length > 0) {
/*  68 */         fileExtension = fileExtension.trim();
/*  69 */         fileExtension = fileExtension.toLowerCase();
/*  70 */         result = (new BASE64Encoder()).encode(imageBytes);
/*  71 */         if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg") || 
/*  72 */           fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("gif")) {
/*  73 */           result = "data:image/" + fileExtension + ";base64," + result;
/*  74 */         } else if (fileExtension.equalsIgnoreCase("svg")) {
/*  75 */           result = "data:image/" + fileExtension + "+xml;base64," + result;
/*     */         } 
/*     */       } 
/*  78 */     } catch (Exception e) {
/*  79 */       e.printStackTrace();
/*     */     } 
/*  81 */     return result;
/*     */   }
/*     */   
/*     */   public String getUnitsAsHTMLSelect(JSONArray arrObj) {
/*  85 */     String optionsStr = "<option value='SELECT'>Select Units</option>";
/*     */     try {
/*  87 */       if (arrObj != null && arrObj.size() > 0) {
/*  88 */         for (int i = 0; i < arrObj.size(); i++) {
/*  89 */           JSONObject obj = (JSONObject)arrObj.get(i);
/*  90 */           optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option value='" + (String)obj.get("UNIT_ID") + "'>" + (String)obj.get("UNIT_NAME") + 
/*     */             
/*  92 */             "</option>";
/*     */         }
/*     */       
/*     */       }
/*  96 */     } catch (Exception e) {
/*     */       
/*  98 */       e.printStackTrace();
/*     */     } 
/* 100 */     return optionsStr;
/*     */   }
/*     */   
/*     */   public boolean isNumericString(String numericString) {
/* 104 */     boolean isNumeric = false;
/*     */     
/*     */     try {
/* 107 */       if (numericString != null && !"".equalsIgnoreCase(numericString)) {
/* 109 */         numericString = numericString.trim();
/*     */         
/* 112 */         isNumeric = true;
/*     */       }
/*     */     
/* 115 */     } catch (NumberFormatException nfe) {
/*     */       
/* 117 */       nfe.printStackTrace();
/* 118 */       isNumeric = false;
/* 119 */     } catch (Exception e) {
/* 120 */       e.printStackTrace();
/* 121 */       isNumeric = false;
/*     */     } 
/*     */     
/* 124 */     return isNumeric;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDepartmentsAsHTMLSelect(JSONArray arrObj) {
/* 129 */     String optionsStr = "<option value='SELECT'>Select Department</option>";
/*     */     try {
/* 131 */       for (int i = 0; i < arrObj.size(); i++) {
/* 132 */         JSONObject obj = (JSONObject)arrObj.get(i);
/* 133 */         optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option value='" + (String)obj.get("DEPT_ID") + "'>" + (String)obj.get("DEPT_NAME") + 
/* 134 */           "</option>";
/*     */       }
/*     */     
/* 137 */     } catch (Exception e) {
/*     */       
/* 139 */       e.printStackTrace();
/*     */     } 
/* 141 */     return optionsStr;
/*     */   }
/*     */   
/*     */   public String getYears(JSONArray arrObj) {
/* 145 */     String optionsStr = "";
/*     */ 
/*     */     
/*     */     try {
/* 149 */       if (arrObj != null && arrObj.size() > 0) {
/* 150 */         for (int i = 0; i < arrObj.size(); i++)
/*     */         {
/* 152 */           optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>" + arrObj.get(i) + "</option>";
/*     */         }
/*     */       } else {
/* 155 */         optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option></option>";
/*     */       }
/*     */     
/* 158 */     } catch (Exception e) {
/*     */       
/* 160 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 163 */     return optionsStr;
/*     */   }
/*     */ 
/*     */   
/*     */   public String setSubscribedYearsSelectedToGiverYear(int Year) {
/* 168 */     String optionsStr = "";
/*     */     try {
/* 170 */       int currentYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
/* 171 */       currentYear += 13;
/* 172 */       if (Year >= 1993 && Year <= currentYear) {
/* 173 */         for (int i = 1993; i <= currentYear; i++)
/*     */         {
/* 175 */           if (i == Year) {
/* 176 */             optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option selected>" + i + "</option>";
/*     */           } else {
/* 178 */             optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>" + i + "</option>";
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 184 */     } catch (Exception e) {
/*     */       
/* 186 */       e.printStackTrace();
/*     */     } 
/* 188 */     return optionsStr;
/*     */   }
/*     */   
/*     */   public String setSubscribedYearsSelectedToCurrentYear() {
/* 192 */     String optionsStr = "";
/*     */     try {
/* 194 */       int currentYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
/*     */       
/* 196 */       int subscriptionYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
/* 197 */       subscriptionYear += 13;
/* 198 */       for (int i = 1993; i <= subscriptionYear; i++) {
/* 199 */         if (i == currentYear) {
/* 200 */           optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option selected>" + i + "</option>";
/*     */         } else {
/* 202 */           optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>" + i + "</option>";
/*     */         }
/*     */       
/*     */       } 
/* 206 */     } catch (Exception e) {
/*     */       
/* 208 */       e.printStackTrace();
/*     */     } 
/* 210 */     return optionsStr;
/*     */   }
/*     */   
/*     */   public String setSubscriptionPaymentStatus(String setPaidStatus) {
/* 214 */     String optionsStr = "";
/*     */     try {
/* 216 */       if (setPaidStatus != null && !"".equalsIgnoreCase(setPaidStatus)) {
/*     */         
/* 218 */         if (setPaidStatus.toUpperCase().equalsIgnoreCase("YES")) {
/* 219 */           optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option selected>Yes</option>";
/* 220 */           optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>No</option>";
/*     */         } else {
/* 222 */           optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>Yes</option>";
/* 223 */           optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option selected>No</option>";
/*     */         } 
/*     */       } else {
/* 226 */         optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option selected>Select Paid Status</option>";
/* 227 */         optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>Yes</option>";
/* 228 */         optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option >No</option>";
/*     */       }
/*     */     
/* 231 */     } catch (Exception e) {
/*     */       
/* 233 */       e.printStackTrace();
/*     */     } 
/* 235 */     return optionsStr;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubscriptionPaymentStatus() {
/* 240 */     String optionsStr = "";
/*     */     
/*     */     try {
/* 243 */       optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>Yes</option>";
/* 244 */       optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>No</option>";
/*     */     }
/* 246 */     catch (Exception e) {
/*     */       
/* 248 */       e.printStackTrace();
/*     */     } 
/* 250 */     return optionsStr;
/*     */   }
/*     */ 
/*     */   
/*     */   public String convertPaymentConfigDetailsToHTMLSelect(JSONArray payconfDetArr) {
/* 255 */     String result = "<option value='SELECT'>Select Payment Type</option>";
/*     */     
/*     */     try {
/* 258 */       if (payconfDetArr != null && payconfDetArr.size() > 0)
/*     */       {
/*     */         
/* 261 */         for (int i = 0; i < payconfDetArr.size(); i++) {
/* 262 */           JSONObject obj = (JSONObject)payconfDetArr.get(i);
/* 263 */           result = String.valueOf(String.valueOf(result)) + "<option value='" + obj.get("MEMBERSHIP_AMOUNT") + "'>" + (String)obj.get("PAYMENT_CONF_ID") + 
/* 264 */             "</option>";
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 270 */     catch (Exception e) {
/*     */       
/* 272 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/* 276 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String convertPaymentConfigDetailsToHTMLSelected(JSONArray payconfDetArr, String selectedOption) {
/* 284 */     String result = "<option value='SELECT'>Select Payment Type</option>";
/*     */     
/*     */     try {
/* 287 */       if (payconfDetArr != null && payconfDetArr.size() > 0) {
/* 288 */         if (selectedOption != null && !"".equalsIgnoreCase(selectedOption)) {
/*     */           
/* 293 */           for (int i = 0; i < payconfDetArr.size(); i++) {
/* 294 */             JSONObject obj = (JSONObject)payconfDetArr.get(i);
/*     */             
/* 296 */             if (((String)obj.get("PAYMENT_CONF_ID")).equalsIgnoreCase(selectedOption)) {
/* 297 */               result = String.valueOf(String.valueOf(result)) + "<option value='" + obj.get("MEMBERSHIP_AMOUNT") + "' selected>" + (String)obj.get("PAYMENT_CONF_ID") + "</option>";
/*     */             } else {
/*     */               
/* 300 */               result = String.valueOf(String.valueOf(result)) + "<option value='" + obj.get("MEMBERSHIP_AMOUNT") + "'>" + (String)obj.get("PAYMENT_CONF_ID") + "</option>";
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 307 */           for (int i = 0; i < payconfDetArr.size(); i++) {
/* 308 */             JSONObject obj = (JSONObject)payconfDetArr.get(i);
/* 309 */             result = String.valueOf(String.valueOf(result)) + "<option value='" + obj.get("MEMBERSHIP_AMOUNT") + "'>" + (String)obj.get("PAYMENT_CONF_ID") + "</option>";
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 318 */     catch (Exception e) {
/*     */       
/* 320 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/* 324 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidDate(String value) {
/*     */     try {
/* 330 */       (new SimpleDateFormat("dd/mm/yyyy")).parse(value);
/* 331 */       return true;
/* 332 */     } catch (ParseException e) {
/* 333 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String convertNullToEmptyString(Object obj) {
/* 338 */     if (obj != null) {
/* 339 */       return obj.toString();
/*     */     }
/* 341 */     return "";
/*     */   }

public static JSONArray convertResultSetToJsonArray(ResultSet rs) {
	JSONArray json = new JSONArray();
	ResultSetMetaData rsmd;
	try {
		if(rs==null)
			return json;
		rsmd = rs.getMetaData();
		while(rs.next()) {
			  int numColumns = rsmd.getColumnCount();
			  JSONObject obj = new JSONObject();
			  for (int i=1; i<=numColumns; i++) {
			    String column_name = rsmd.getColumnName(i).toUpperCase();
			    String data=rs.getObject(column_name)!=null?rs.getObject(column_name).toString():"";
			    obj.put(column_name, data);
			  }
			  json.add(obj);
			}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return json;
}

public static String setSelectedItemsToPassInSQLIn(String seletedItems) {
	String result="";
	if(seletedItems!=null && !"".equals(seletedItems)) {
		
		String temp[]=seletedItems.split(",");
		for (int i = 0; i < temp.length; i++) {
			if(temp[i]!=null && !"".equalsIgnoreCase(temp[i])) {
			result=result+"'"+temp[i]+"',";
			
			}
		}
		
		if(result.indexOf(",")>-1);
		return result.substring(0,result.length()-1);
		
	}
	
	return result;
	
}
public static JSONArray getKeysOfJOSNObj(JSONObject sampleObj) {

	Set<Map.Entry<String, String>> entries = sampleObj.entrySet();
	JSONArray colsArr = new JSONArray();
	for (Map.Entry<String, String> entry : entries)

		colsArr.add(entry.getKey());

	return colsArr;
}
public static String getProperty(String propertyName) {
	String propertyValue="";
	Properties props=null;
	try {
		props = PropertiesLoaderUtils.loadAllProperties("application.properties");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	propertyValue=props.getProperty(propertyName)!=null?props.getProperty(propertyName):"";
	
	return propertyValue;
}


public static String[] getOrderOfColumns(String propValue) {

	String colsArray[]=new String[] {};
	
	if(propValue!=null && !"".equals(propValue)) {
		
		
		if(propValue.indexOf(",")>-1) 
			colsArray=propValue.split(",");
			
		
		
	}
		
		
	return colsArray;
	
}

public static String padding(String source,int paddingLength) {

	
	 return  String .format("%" + paddingLength + "s",source );
}
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunio\\utils\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */