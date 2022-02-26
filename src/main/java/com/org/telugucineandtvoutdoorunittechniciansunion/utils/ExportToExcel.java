package com.org.telugucineandtvoutdoorunittechniciansunion.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.GenericCRUDOperationsDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.GenericGridDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.exceptions.GenericProcedureCallException;	
@WebServlet(
		  name = "ExportToExcel",
		  description = "ExportToExcel",
		  urlPatterns = {"/ExportToExcel"}
		)
public class ExportToExcel extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GenericCRUDOperationsDAO genericGridDAO;
	ConfigUtility configUtility;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			  ServletContext context = getServletContext();
		        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( context);
		        if (ctx != null) {
		        	genericGridDAO=ctx.getBean(GenericCRUDOperationsDAO.class);
		        	 configUtility=ctx.getBean(ConfigUtility.class);
		        }

//			response.setContentType("application/vnd.ms-excel");
//			response.setHeader("Cache-Control", "max-age=0");
			//response.setHeader("Content-Disposition", "attachment; filename=" + l_FileName + ".xls");
			
			genericExcelWriting(request, response);
			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void genericExcelWriting(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("application/vnd.ms-excel");
		HttpSession session = request.getSession(false);
		String l_ExcelType = (request.getParameter("ExcelType") != null) ? request.getParameter("ExcelType") : "";
		String l_FileName=l_ExcelType+"_"+new SimpleDateFormat("yyyyMMddHHmm").format(new Date());	;
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Cache-Control", "max-age=0");
		response.setHeader("Content-Disposition", "attachment; filename=" + l_FileName + ".xlsx");
		
		String ourChequeNo=request.getParameter("SELECTED_ITEMS")!=null?request.getParameter("SELECTED_ITEMS") :"";
		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Data");
		// header styling
		
		XSSFCellStyle headerStyle =getHeaderStyle(workbook);
		XSSFFont headerFont= getHeaderFont(workbook);
		XSSFFont bodyFont=getBodyFont(workbook);
		XSSFCellStyle bodystyle=getBodyStyle(workbook);
		 //end header styling
		JSONObject jobj = null;
		try {
			String SELECTED_ITEMS=request.getParameter("SELECTED_ITEMS");
			String SelectedItems=Utils.setSelectedItemsToPassInSQLIn(SELECTED_ITEMS);
			Map<String,String> reqMap=Utils.requestParamsToMap(request);
			reqMap.put("SELECTED_ITEMS",SELECTED_ITEMS);
			String l_JsonStr=	genericGridDAO.getDataForGenericExportToExcel(reqMap,l_ExcelType);
			JSONParser parser = new JSONParser();
			JSONArray jsonArr = (JSONArray) parser.parse(l_JsonStr);
			//JSONArray colsArr= = getKeysOfJOSNObj((JSONObject)jsonArr.get(0));
			//String colsArr[]=Utils.getOrderOfColumns(genericGridDAO.dataAccess.getPropertyValue(l_ExcelType));
			
			String colsArr[]=Utils.getOrderOfColumns(configUtility.getProperty(l_ExcelType));
			//
			int l_intNewLine = 0;
			XSSFRow row = sheet.createRow(l_intNewLine++);
			int cellnum = 0;
			
			
			
			
			if("CHEQUEDISBURSEDETAILS".equalsIgnoreCase(l_ExcelType) || "GET_PAID_CHEQUES_DISBURSMENT".equalsIgnoreCase(l_ExcelType)) {
				//	         NAME	          ACCOUNT NO	 AMOUNT	Cell.No.	Dept	Card No
				cellnum = 11;
				l_intNewLine = 8;
				
				row = sheet.createRow(l_intNewLine++);				
				Cell bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("Bank Request Leatter Head");
				
				
				row = sheet.createRow(l_intNewLine++);				
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("");
				
				row = sheet.createRow(l_intNewLine++);				
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("Respected Sir,");
				
				
				row = sheet.createRow(l_intNewLine++);				
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("Sub: Disburerment of our Members Payments");
				
				
				row = sheet.createRow(l_intNewLine++);				
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("Ref :Current Account No140311100002218");
				
				
				row = sheet.createRow(l_intNewLine++);				
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("TELUGU  CINE AND T V OUT DOOR UNIT TECHNICIANS UNION");
				
				
				row = sheet.createRow(l_intNewLine);				
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("With raference to the above current account Number our Union requested that please disburse");
				
				
				row = sheet.createRow(l_intNewLine++);				
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("the members Payment by debiting our current Account No.140311100002218 Maintained with you\r\n" + 
						"");
				
				
				row = sheet.createRow(l_intNewLine++);				
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("Please send  us, the  confirmation of Disbursement of  payments to the  individual accounts of the\r\n" + 
						"");
				
				
				row = sheet.createRow(l_intNewLine++);				
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("Members daily wages(Battas)      Permanent Account Number. AADAA.0969L");
				
				
				row = sheet.createRow(l_intNewLine++);	
				cellnum=cellnum+2;
				bodyCell=	row.createCell(cellnum);
				bodyCell.setCellValue("Cheque No."+ourChequeNo);
				
				cellnum = 10;
				row = sheet.createRow(l_intNewLine++);	
			}
			
			
			Cell cell1 = row.createCell(cellnum++);
			headerStyle.setFont(headerFont);
			cell1.setCellStyle(headerStyle);
			cell1.setCellValue("SNO");
			if(colsArr!=null &&colsArr.length>0)
			for (int i = 0; i < colsArr.length; i++) {
				cell1 = row.createCell(cellnum++);
				headerStyle.setFont(headerFont);
				cell1.setCellStyle(headerStyle);
				cell1.setCellValue(colsArr[i]);
				sheet.autoSizeColumn(i);

			}
			else {
				JSONArray colsArry =Utils. getKeysOfJOSNObj((JSONObject)jsonArr.get(0));
				colsArr=new String[colsArry.size()];
				for (int i = 0; i < colsArry.size(); i++) {
				cell1 = row.createCell(cellnum++);
				headerStyle.setFont(headerFont);
				cell1.setCellStyle(headerStyle);
				String columnName=(String)colsArry.get(i);
				colsArr[i]=columnName;
				cell1.setCellValue(columnName);
				sheet.autoSizeColumn(i);
				}
			}
			
			
			String rowVal = "";
			for (int i = 0; i < jsonArr.size(); i++) {
				
				JSONObject rowObj = (JSONObject) jsonArr.get(i);
				XSSFRow dataRow = sheet.createRow(l_intNewLine++);
				cellnum = 0;
				if("CHEQUEDISBURSEDETAILS".equalsIgnoreCase(l_ExcelType)||"GET_PAID_CHEQUES_DISBURSMENT".equalsIgnoreCase(l_ExcelType) ) 
					cellnum = 10;
				
				Cell bodyCell=	dataRow.createCell(cellnum++);
				bodyCell.setCellStyle(bodystyle);
				bodyCell.setCellValue(i+1);
				
				
				for ( int j = 0; j < colsArr.length; j++) {
					rowVal = (String) rowObj.get(colsArr[j]);
					try {
						if (rowVal == null)
							rowVal = "";
					bodyCell=dataRow.createCell(cellnum++);
					bodyCell.setCellStyle(bodystyle);
					bodyCell.setCellValue(rowVal);
					
					} catch (Exception ex) {
						
					}
				}

			}
			workbook.write(response.getOutputStream()); // Write workbook to response.
			//workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GenericProcedureCallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
			}
		}

	}

	public String getData(String excelType) {
		if (excelType != null && !"".equals(excelType)) {
			if ("CHEQUE_DETAILS".equalsIgnoreCase(excelType)) {
			}
		}
		return "";

	}


	public XSSFFont getBodyFont(XSSFWorkbook workbook) {	

		 XSSFFont bodyFont= getDefaultFont(workbook);
		 bodyFont.setColor(IndexedColors.BLACK.getIndex());
		 
		 return bodyFont;
		
	}
	
	public XSSFFont getHeaderFont(XSSFWorkbook workbook) {
		
		 XSSFFont headerFont= workbook.createFont();
		 headerFont.setFontHeightInPoints((short)10);
		 headerFont.setFontName("Arial");
		 headerFont.setColor(IndexedColors.WHITE.getIndex());
		 headerFont.setBold(true);
		 headerFont.setItalic(false);
		 return headerFont;
		
	}
	
	public XSSFFont getDefaultFont(XSSFWorkbook workbook) 
	{
		
		XSSFFont defaultFont= workbook.createFont();
		 defaultFont.setFontHeightInPoints((short)10);
		 defaultFont.setFontName("Arial");
		 defaultFont.setColor(IndexedColors.BLACK.getIndex());
		 defaultFont.setBold(false);
		 defaultFont.setItalic(false);
		 return defaultFont;
	}

	public XSSFCellStyle getBodyStyle(XSSFWorkbook workbook) {
		XSSFCellStyle bodystyle = workbook.createCellStyle();

		bodystyle.setBorderTop(BorderStyle.THIN);
		bodystyle.setBorderLeft(BorderStyle.THIN);
		bodystyle.setBorderRight(BorderStyle.THIN);
		bodystyle.setBorderBottom(BorderStyle.THIN);
		return bodystyle;
	}
	
	public XSSFCellStyle getHeaderStyle(XSSFWorkbook workbook) {
		
		XSSFCellStyle headerStyle =  workbook.createCellStyle();

		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.index);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		
		
		return headerStyle;
	}
	
	
}
