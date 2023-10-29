package com.org.telugucineandtvoutdoorunittechniciansunion.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.FileCopyUtils;

import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Utils {
	public String notNullChecker(String str) {
		return (str != null) ? str.trim() : "";
	}

	public boolean nonNullNonEmpty(String str) {
		return (str != null && !"".equals(str));
	}

	public JSONObject requestParamsToJSON(ServletRequest req) {
		JSONObject jsonObj = new JSONObject();
		Map<String, String[]> params = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String[] v = entry.getValue();
			Object o = (v.length == 1) ? v[0] : v;
			jsonObj.put(entry.getKey(), o);
		}
		return jsonObj;
	}

	public static Map<String, String> requestParamsToMap(HttpServletRequest req) {
		Map<String, String> reqMap = new HashMap<String, String>();
		Map<String, String[]> params = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			String[] v = entry.getValue();
			Object o = (v.length == 1) ? v[0] : v;
			reqMap.put(entry.getKey().toString(), o.toString());
		}
		return reqMap;
	}

	public void fileWriter(String dir, String filenameWithExt, byte[] bytes) {
		try {
			File directory = new File(dir);

			if (!directory.exists()) {
				directory.mkdirs();
			} else {
				directory.delete();
				directory.mkdirs();
			}
			FileCopyUtils.copy(bytes, new FileOutputStream(
					String.valueOf(String.valueOf(directory.getAbsolutePath())) + File.separator + filenameWithExt));
		} catch (IOException ex) {
			ApplicationUtilities.error(this.getClass(),ex.getMessage(),ex);
		}
	}

	public String convertImageToBase64(byte[] imageBytes, String fileExtension) {
		StringBuilder result = new StringBuilder("");
		try {
			if (fileExtension != null && !"".equals(fileExtension) && imageBytes != null && imageBytes.length > 0) {
				fileExtension = fileExtension.trim();
				fileExtension = fileExtension.toLowerCase();
				
				if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg")
						|| fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("gif")) {
					result.append("data:image/").append(fileExtension).append(";base64,");
				} else if (fileExtension.equalsIgnoreCase("svg")) {
					result.append("data:image/").append(fileExtension).append("+xml;base64,");
				}
				result.append((new BASE64Encoder()).encode(imageBytes));
			}
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return result.toString();
	}

	public String getUnitsAsHTMLSelect(JSONArray arrObj) {
		StringBuilder optionsStr =new StringBuilder( "<option value='SELECT'>Select Units</option>");
		try {
			if (arrObj != null && arrObj.size() > 0) {
				for (int i = 0; i < arrObj.size(); i++) {
					JSONObject obj = (JSONObject) arrObj.get(i);
					optionsStr.append("<option value='")
						.append((String) obj.get("UNIT_ID") ).append( "'>" ).append( (String) obj.get("UNIT_NAME") ).append("</option>");
				}

			}
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return optionsStr.toString();
	}

	public boolean isNumericString(String numericString) {
		boolean isNumeric = false;

		try {
			if (numericString != null && !"".equalsIgnoreCase(numericString)) {
				numericString = numericString.trim();

				isNumeric = true;
			}

		} catch (NumberFormatException nfe) {

			ApplicationUtilities.error(this.getClass(),nfe.getMessage(),nfe);
			isNumeric = false;
		} catch (Exception e) {
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
			isNumeric = false;
		}

		return isNumeric;
	}

	public String getDepartmentsAsHTMLSelect(JSONArray arrObj) {
		StringBuilder optionsStr = new StringBuilder("<option value='SELECT'>Select Department</option>");
		try {
			for (int i = 0; i < arrObj.size(); i++) {
				JSONObject obj = (JSONObject) arrObj.get(i);
				optionsStr.append( "<option value='")
				.append( (String) obj.get("DEPT_ID") ).append( "'>" ).append( (String) obj.get("DEPT_NAME") ).append( "</option>");
			}

		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return optionsStr.toString();
	}

	public String getYears(JSONArray arrObj) {
		String optionsStr = "";

		try {
			if (arrObj != null && arrObj.size() > 0) {
				for (int i = 0; i < arrObj.size(); i++) {
					optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>" + arrObj.get(i) + "</option>";
				}
			} else {
				optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option></option>";
			}

		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}

		return optionsStr;
	}

	public String setSubscribedYearsSelectedToGiverYear(int Year) {
		String optionsStr = "";
		try {
			int currentYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
			currentYear += 13;
			if (Year >= 1993 && Year <= currentYear) {
				for (int i = 1993; i <= currentYear; i++) {
					if (i == Year) {
						optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option selected>" + i + "</option>";
					} else {
						optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>" + i + "</option>";
					}

				}

			}
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return optionsStr;
	}

	public String setSubscribedYearsSelectedToCurrentYear() {
		String optionsStr = "";
		try {
			int currentYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));

			int subscriptionYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
			subscriptionYear += 13;
			for (int i = 1993; i <= subscriptionYear; i++) {
				if (i == currentYear) {
					optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option selected>" + i + "</option>";
				} else {
					optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>" + i + "</option>";
				}

			}
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return optionsStr;
	}

	public String setSubscriptionPaymentStatus(String setPaidStatus) {
		String optionsStr = "";
		try {
			if (setPaidStatus != null && !"".equalsIgnoreCase(setPaidStatus)) {

				if (setPaidStatus.toUpperCase().equalsIgnoreCase("YES")) {
					optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option selected>Yes</option>";
					optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>No</option>";
				} else {
					optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>Yes</option>";
					optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option selected>No</option>";
				}
			} else {
				optionsStr = String.valueOf(String.valueOf(optionsStr))
						+ "<option selected>Select Paid Status</option>";
				optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>Yes</option>";
				optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option >No</option>";
			}

		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return optionsStr;
	}

	public String getSubscriptionPaymentStatus() {
		String optionsStr = "";

		try {
			optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>Yes</option>";
			optionsStr = String.valueOf(String.valueOf(optionsStr)) + "<option>No</option>";
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return optionsStr;
	}

	public String convertPaymentConfigDetailsToHTMLSelect(JSONArray payconfDetArr) {
		String result = "<option value='SELECT'>Select Payment Type</option>";

		try {
			if (payconfDetArr != null && payconfDetArr.size() > 0) {

				for (int i = 0; i < payconfDetArr.size(); i++) {
					JSONObject obj = (JSONObject) payconfDetArr.get(i);
					result = String.valueOf(String.valueOf(result)) + "<option value='" + obj.get("MEMBERSHIP_AMOUNT")
							+ "'>" + (String) obj.get("PAYMENT_CONF_ID") + "</option>";

				}

			}
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}

		return result;
	}

	public String convertPaymentConfigDetailsToHTMLSelected(JSONArray payconfDetArr, String selectedOption) {
		String result = "<option value='SELECT'>Select Payment Type</option>";

		try {
			if (payconfDetArr != null && payconfDetArr.size() > 0) {
				if (selectedOption != null && !"".equalsIgnoreCase(selectedOption)) {

					for (int i = 0; i < payconfDetArr.size(); i++) {
						JSONObject obj = (JSONObject) payconfDetArr.get(i);

						if (((String) obj.get("PAYMENT_CONF_ID")).equalsIgnoreCase(selectedOption)) {
							result = String.valueOf(String.valueOf(result)) + "<option value='"
									+ obj.get("MEMBERSHIP_AMOUNT") + "' selected>" + (String) obj.get("PAYMENT_CONF_ID")
									+ "</option>";
						} else {

							result = String.valueOf(String.valueOf(result)) + "<option value='"
									+ obj.get("MEMBERSHIP_AMOUNT") + "'>" + (String) obj.get("PAYMENT_CONF_ID")
									+ "</option>";
						}

					}

				} else {

					for (int i = 0; i < payconfDetArr.size(); i++) {
						JSONObject obj = (JSONObject) payconfDetArr.get(i);
						result = String.valueOf(String.valueOf(result)) + "<option value='"
								+ obj.get("MEMBERSHIP_AMOUNT") + "'>" + (String) obj.get("PAYMENT_CONF_ID")
								+ "</option>";

					}

				}

			}
		} catch (Exception e) {

			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}

		return result;
	}

	public boolean isValidDate(String value) {
		try {
			(new SimpleDateFormat("dd/mm/yyyy")).parse(value);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public String convertNullToEmptyString(Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return "";
	}

	public static JSONArray convertResultSetToJsonArray(ResultSet rs) {
		JSONArray json = new JSONArray();
		ResultSetMetaData rsmd;
		try {
			if (rs == null)
				return json;
			rsmd = rs.getMetaData();
			while (rs.next()) {
				int numColumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i = 1; i <= numColumns; i++) {
					String column_name = rsmd.getColumnName(i).toUpperCase();
					String data = rs.getObject(column_name) != null ? rs.getObject(column_name).toString() : "";
					obj.put(column_name, data);
				}
				json.add(obj);
			}
		} catch (SQLException e) {
			ApplicationUtilities.error(Utils.class,e.getMessage(),e);
		}
		return json;
	}

	public static String setSelectedItemsToPassInSQLIn(String seletedItems) {
		String result = "";
		if (seletedItems != null && !"".equals(seletedItems)) {

			String temp[] = seletedItems.split(",");
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] != null && !"".equalsIgnoreCase(temp[i])) {
					result = result + "'" + temp[i] + "',";

				}
			}

			if (result.indexOf(",") > -1)
				;
			return result.substring(0, result.length() - 1);

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
		String propertyValue = "";
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadAllProperties("application.properties");

		} catch (IOException e) {
			ApplicationUtilities.error(Utils.class,e.getMessage(),e);
		} catch (Exception e) {
			ApplicationUtilities.error(Utils.class,e.getMessage(),e);
		}

		propertyValue = props.getProperty(propertyName) != null ? props.getProperty(propertyName) : "";

		return propertyValue;
	}

	public static String[] getOrderOfColumns(String propValue) {

		String colsArray[] = new String[] {};

		if (propValue != null && !"".equals(propValue)) {

			if (propValue.indexOf(",") > -1)
				colsArray = propValue.split(",");

		}

		return colsArray;

	}

	public static String padding(String source, int paddingLength) {

		return String.format("%" + paddingLength + "s", source);
	}

	private static final String[] units = {
		    "",
		    " One",
		    " Two",
		    " Three",
		    " Four",
		    " Five",
		    " Six",
		    " Seven",
		    " Eight",
		    " Nine"
		  }; 
		  private static final String[] doubles = {
		    " Ten",
		    " Eleven",
		    " Twelve",
		    " Thirteen",
		    " Fourteen",
		    " Fifteen",
		    " Sixteen",
		    " Seventeen",
		    " Eighteen",
		    " Nineteen"
		  };
		  private static final String[] tens = {
		    "",
		    "",
		    " Twenty",
		    " Thirty",
		    " Forty",
		    " Fifty",
		    " Sixty",
		    " Seventy",
		    " Eighty",
		    " Ninety"
		  };

		  private static final String[] hundreds = {
		    "",
		    " Thousand",
		    " Lakh",
		    " Crore",
		    " Hudred",
		    " Thousand"
		  };

		  public static String convertToWord(int number) {    
		    String num = "";    
		    int index = 0;
		    int n;
		    int digits;
		    boolean firstIteration = true;
		    do {
		      if(firstIteration){
		        digits = 1000;
		      }else{
		        digits = 100;
		      }
		      n = number % digits;
		      if (n != 0){
		        String s = convertThreeOrLessThanThreeDigitNum(n);
		        num = s + hundreds[index] + num;
		      }
		      index++;
		      number = number/digits;
		      firstIteration = false;
		    } while (number > 0);
		    return num;
		  }
		  private static String convertThreeOrLessThanThreeDigitNum(int number) {
		    String word = "";    
		    int num = number % 100;
		    // Logic to take word from appropriate array
		    if(num < 10){
		      word = word + units[num];
		    }
		    else if(num < 20){
		      word = word + doubles[num%10];
		    }else{
		      word = tens[num/10] + units[num%10];
		    }
		    word = (number/100 > 0)? units[number/100] + " hundred" + word : word;
		    return word;
		  }
		        


}