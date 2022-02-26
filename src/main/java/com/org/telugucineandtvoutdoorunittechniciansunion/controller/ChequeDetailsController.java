package com.org.telugucineandtvoutdoorunittechniciansunion.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.StringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.telugucineandtvoutdoorunittechniciansunion.exceptions.GenericProcedureCallException;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.ChequeDetailsService;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.GenericGridService;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Controller
public class ChequeDetailsController {
	@Autowired
	public ChequeDetailsService chequeDetailsService;

	@Autowired
	public GenericGridService genericGridService;

	@RequestMapping(value = { "/cheques" }, method = { RequestMethod.GET })
	public String cheques(Map<String, Object> model) {
		return "Cheques";
	}
	@RequestMapping(value = { "/ChequeDisbursment" }, method = { RequestMethod.GET })
	public String chequeDisbursment(Map<String, Object> model) {
		return "ChequeDisbursment";
	}
	@RequestMapping(value = { "/cheques2" }, method = { RequestMethod.GET })
	public String cheques2(Map<String, Object> model) {
		return "Cheques2";
	}

	@RequestMapping(value = { "/getAllChequeDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String getAllChequeDetails(HttpServletRequest request, Map<String, Object> model) {

		String chequeNo = request.getParameter("CHEQUE_NO");
		String chequeType = request.getParameter("CHEQUE_TYPE");
		String amount = request.getParameter("AMOUNT");
		String chequeRecievedFrom = request.getParameter("CHEQUE_RECIEVED_FROM");
		String companyName = request.getParameter("COMPANY_NAME");
		String chequeDate = request.getParameter("CHEQUE_DATE");
		String fromDate = request.getParameter("FromDate");
		String toDate = request.getParameter("ToDate");

		if (chequeDate != null && chequeDate.length() > 0) {
			chequeDate = chequeDate.substring(0, 11);
			fromDate = chequeDate;
			toDate = chequeDate;
		}

		String bankName = request.getParameter("BANKNAME");
		String remarks = request.getParameter("FIELD1");
		String resultData = chequeDetailsService.getAllChequeDetails(fromDate, toDate, chequeNo, chequeType, amount,
				chequeRecievedFrom, companyName, bankName);
		// var
		// UrlStr=context+"/exportToExcel?ExcelType=ChequeDetails&SELECTED_CHEQUE_NOS="+selectedCheques;
		HttpSession session = request.getSession(false);
		if (session.getAttribute("CHEQUEDETAILS") != null) {
			session.removeAttribute("CHEQUEDETAILS");
			session.setAttribute("CHEQUEDETAILS", resultData);
		} else
			session.setAttribute("CHEQUEDETAILS", resultData);

		return resultData;
	}

	@RequestMapping(value = { "/getSelectedChequeDisburseDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String getChequeDetails(HttpServletRequest request, Map<String, Object> model) {
		String selectedChequeNo = request.getParameter("SELECTED_CHEQUE_NO");
		String chequeNo = request.getParameter("CHEQUE_NO");
		String deptId = request.getParameter("DEPT_ID");
		String cardNo = request.getParameter("CARD_NO");
		String amount = request.getParameter("AMOUNT");
		String ourChequeId = request.getParameter("PAID_CHQ_NO");
		
		return chequeDetailsService.getSelectedChequeDisburseDetails(selectedChequeNo,
				chequeNo,
				deptId,
				cardNo,
				amount,
				ourChequeId
				);
	}

	@RequestMapping(value = { "/getChequeDesburseDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String getChequeDesburseDetails(HttpServletRequest request, Map<String, Object> model) {
		String chequeNo = request.getParameter("CHEQUE_NO");
		return chequeDetailsService.getChequeDesburseDetails(chequeNo);
	}

	@RequestMapping(value = { "/saveChequeDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String saveChequeDetails(HttpServletRequest request, Map<String, Object> model) {
		String chequeNo = request.getParameter("CHEQUE_NO");
		String chequeType = request.getParameter("CHEQUE_TYPE");
		String amount = request.getParameter("AMOUNT");
		String chequeRecievedFrom = request.getParameter("CHEQUE_RECIEVED_FROM");
		String companyName = request.getParameter("COMPANY_NAME");
		String chequeDate = request.getParameter("CHEQUE_DATE");
		String bankName = request.getParameter("BANKNAME");
		String remarks = request.getParameter("FIELD1");
		String phoneNo = request.getParameter("PHONE_NO");

		return chequeDetailsService.saveChequeDetails(chequeNo, chequeType, phoneNo, amount, chequeRecievedFrom,
				companyName, chequeDate, bankName, remarks);
	}

	@RequestMapping(value = { "/saveChequeDisburseDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String saveChequeDisburseDetails(HttpServletRequest request, Map<String, Object> model) {
		String chequeNo = request.getParameter("CHEQUE_NO");
		String deptId = request.getParameter("DEPT_ID");
		String cardNo = request.getParameter("CARD_NO");
		String bankAccNo = request.getParameter("BANK_ACC_NO");
		String amount = request.getParameter("AMOUNT");
		String ourChequeId = request.getParameter("PAID_CHQ_NO");

		return chequeDetailsService.saveChequeDisburseDetails(chequeNo, deptId, cardNo, bankAccNo, amount, ourChequeId);
	}

	@RequestMapping(value = { "/updateChequeDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String updateChequeDetails(HttpServletRequest request, Map<String, Object> model) {
		String chequeNo = request.getParameter("CHEQUE_NO");
		String chequeType = request.getParameter("CHEQUE_TYPE");
		String amount = request.getParameter("AMOUNT");
		String chequeRecievedFrom = request.getParameter("CHEQUE_RECIEVED_FROM");
		String companyName = request.getParameter("COMPANY_NAME");
		String chequeDate = request.getParameter("CHEQUE_DATE");
		String bankName = request.getParameter("BANKNAME");
		String remarks = request.getParameter("FIELD1");
		String phoneNo = request.getParameter("PHONE_NO");

		if (chequeDate != null && chequeDate.length() > 0) {
			chequeDate = chequeDate.substring(0, 10);

		}
		return chequeDetailsService.updateChequeDetails(chequeNo, chequeType, phoneNo, amount, chequeRecievedFrom,
				companyName, chequeDate, bankName, remarks);
	}

	@RequestMapping(value = { "/updateChequeDisburseDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String updateChequeDisburseDetails(HttpServletRequest request, Map<String, Object> model) {

		String chequeDsbId = request.getParameter("CHQ_DSB_ID");
		String chequeNo = request.getParameter("CHEQUE_NO");
		String deptId = request.getParameter("DEPT_ID");
		String cardNo = request.getParameter("CARD_NO");
		String bankAccNo = request.getParameter("BANK_ACC_NO");
		String amount = request.getParameter("AMOUNT");
		String ourChequeId = request.getParameter("PAID_CHQ_NO");
		return chequeDetailsService.updateChequeDisburseDetails(chequeDsbId, chequeNo, deptId, cardNo, bankAccNo,
				amount, ourChequeId);
	}

	@RequestMapping(value = { "/deleteChequeDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String deleteChequeDetails(HttpServletRequest request, Map<String, Object> model) {
		String chequeNo = request.getParameter("CHEQUE_NO");
		return chequeDetailsService.deleteChequeDetails(chequeNo);
	}

	@RequestMapping(value = { "/deleteCheques" }, method = { RequestMethod.POST })
	public @ResponseBody String deleteCheques(HttpServletRequest request, Map<String, Object> model) {
		String chequeNos = request.getParameter("cheques");
		return chequeDetailsService.deleteCheques(chequeNos);
	}

	@RequestMapping(value = { "/deleteChequeDesburseDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String deleteChequeDesburseDetails(HttpServletRequest request, Map<String, Object> model) {
		String chqDsbId = request.getParameter("CHQ_DSB_ID");
		return chequeDetailsService.deleteChequeDesburseDetails(chqDsbId);
	}

	@RequestMapping(value = { "/exportToExcel" }, method = { RequestMethod.GET })
	public String exportToExcel(HttpServletRequest request, Map<String, Object> model) {
		// ExcelType=ChequeDetails
		String chequeNo = request.getParameter("SELECTED_ITEMS");
		HttpSession session = request.getSession(false);
		session.setAttribute("HeaderTitile", request.getParameter("ExcelType"));
		session.setAttribute("ExcelType", request.getParameter("ExcelType"));
		// session.setAttribute("FileName","CHEQUEDETAILS");
		session.setAttribute("ChequeDetails", chequeDetailsService.getSelectedChequeDetails(chequeNo));

		return "redirect:/ExportToExcel?SELECTED_ITEMS=" + chequeNo + "&ExcelType=" + request.getParameter("ExcelType");
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
   public ResponseEntity<byte[]> getDownloadData(HttpServletRequest request) throws Exception {

      
       
       String SELECTED_ITEMS=request.getParameter("SELECTED_ITEMS");
       String l_ExcelType = (request.getParameter("ExcelType") != null) ? request.getParameter("ExcelType") : "";
		String l_FileName=new SimpleDateFormat("yyyyMMddHHmm").format(new Date());	;
      // String SelectedItems=Utils.setSelectedItemsToPassInSQLIn(SELECTED_ITEMS);
		Map<String,String> reqMap=Utils.requestParamsToMap(request);
		reqMap.put("SELECTED_ITEMS",SELECTED_ITEMS);
		String l_JsonStr="";
		try {
			l_JsonStr = genericGridService.getDataForGenericExportToExcel(reqMap,l_ExcelType);
		} catch (GenericProcedureCallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONParser parser = new JSONParser();
		JSONArray jsonArr = (JSONArray) parser.parse(l_JsonStr);
		 StringBuffer regData =new StringBuffer();
		 
		 regData.append(Utils.padding("BANK ACCOUNT", -17))
		 .append(Utils.padding("C",-3))
		 .append(Utils.padding("DAILY",-7))
		 .append(Utils.padding("WAGES",-7))
		 .append(Utils.padding("AMOUNT",-9)).append("\n");
		 
       if(jsonArr!=null && jsonArr.size()>0) {
    	   for (int j = 0; j <  jsonArr.size(); j++) {
    		   JSONObject obj=(JSONObject)jsonArr.get(j);
    		   
    		   String bankAccount=obj.get("BANK_ACC_NO")!=null?(String)obj.get("BANK_ACC_NO"):"";
    		   String amount=obj.get("AMOUNT")!=null?(String)obj.get("AMOUNT"):"";
//    		   
//    		   regData.append(bankAccount).append( "\t ")
//    		   			.append(" C 	\t ")
//		   				.append(" DAILY \t ")
//   						.append(" WAGES \t  ")
//   						.append(amount).append("\n");
    		   
    		   regData.append(Utils.padding(bankAccount, -17))
    			 .append(Utils.padding("C",-3))
    			 .append(Utils.padding("DAILY",-7))
    			 .append(Utils.padding("WAGES",-7))
    			 .append(Utils.padding(amount,8)).append("\n");
		}
    	  
    	   
       }
       
       
       
       byte[] output = regData.toString().getBytes();

       HttpHeaders responseHeaders = new HttpHeaders();
       responseHeaders.set("charset", "utf-8");
       responseHeaders.setContentType(MediaType.valueOf("text/html"));
       responseHeaders.setContentLength(output.length);
       responseHeaders.set("Content-disposition", "attachment; filename=BankFormat_"+l_FileName+".txt");

       return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
   }

	/*
	 * @RequestMapping(value = {"/exportToExcel_Old"}, method = {RequestMethod.GET})
	 * public ModelAndView exportToExcel(HttpServletRequest request,ModelMap model)
	 * { String chequeNo=request.getParameter("SELECTED_CHEQUE_NOS");
	 * model.addAttribute("HeaderTitile", "ChequeDetails");
	 * model.addAttribute("ExcelType", request.getParameter("ExcelType"));
	 * model.addAttribute("FileName", "ChequeDetails");
	 * model.addAttribute("ChequeDetails",
	 * chequeDetailsService.getSelectedChequeDetails(chequeNo));
	 * model.addAttribute("attribute", "redirectWithRedirectPrefix"); return new
	 * ModelAndView("redirect:/ExportToExcel", model); }
	 */

	// FileName HeaderTitile
	// String l_ExcelType = (String)request.getAttribute("ExcelType");

	@RequestMapping(value = { "/getSelectedChequeDetails" }, method = { RequestMethod.POST })
	public @ResponseBody String getSelectedChequeDetails(HttpServletRequest request, Map<String, Object> model) {
		String chequeNos = request.getParameter("SELECTED_CHEQUE_NOS");

		return chequeDetailsService.getSelectedChequeDetails(chequeNos);
	}

	// getChequeNos

	@RequestMapping(value = { "/getChequeNos" }, method = { RequestMethod.POST })
	public @ResponseBody String getChequeNos(HttpServletRequest request, Map<String, Object> model) {
		String searchTerm = request.getParameter("searchTerm") != null ? request.getParameter("searchTerm") : "";
		return chequeDetailsService.getChequeNos(searchTerm);
	}

	@RequestMapping(value = { "/getMemberDetailsByDeptAndCardNo" }, method = { RequestMethod.POST })
	public @ResponseBody String getMemberDetailsByDeptAndCardNo(HttpServletRequest request, Map<String, Object> model) {
		String cardNo = request.getParameter("CARD_NO") != null ? request.getParameter("CARD_NO") : "";
		String deptId = request.getParameter("DEPT_ID") != null ? request.getParameter("DEPT_ID") : "";
		return chequeDetailsService.getMemberDetailsByDeptAndCardNo(deptId, cardNo);
	}

	@RequestMapping(value = { "/getCardNosByDeptId" }, method = { RequestMethod.POST })
	public @ResponseBody String getCardNosByDeptId(HttpServletRequest request, Map<String, Object> model) {
		String deptId = request.getParameter("DEPT_ID") != null ? request.getParameter("DEPT_ID") : "";
		return chequeDetailsService.getCardNosByDeptId(deptId);
	}

	@RequestMapping(value = { "/getAllCardNo" }, method = { RequestMethod.POST })
	public @ResponseBody String getAllCardNo(HttpServletRequest request, Map<String, Object> model) {

		return chequeDetailsService.getAllCardNo();
	}
	
	
	@RequestMapping(value = { "/getAllPaidCheques" }, method = { RequestMethod.POST })
	public @ResponseBody String getAllPaidCheques(HttpServletRequest request, Map<String, Object> model) {

		return chequeDetailsService.getAllPaidCheques(Utils.requestParamsToMap(request));
	}
	

	@RequestMapping(value = { "/getPaidChequeById" }, method = { RequestMethod.POST })
	public @ResponseBody String getPaidChequeById(HttpServletRequest request, Map<String, Object> model) {

		return chequeDetailsService.getPaidChequeById(Utils.requestParamsToMap(request));

	}
	@RequestMapping(value = { "/savePaidCheque" }, method = { RequestMethod.POST })
	public @ResponseBody String savePaidCheque(HttpServletRequest request, Map<String, Object> model) {

		return chequeDetailsService.savePaidCheque(Utils.requestParamsToMap(request));

	}
	@RequestMapping(value = { "/updatePaidCheque" }, method = { RequestMethod.POST })
	public @ResponseBody String updatePaidCheque(HttpServletRequest request, Map<String, Object> model) {

		return chequeDetailsService.updatePaidCheque(Utils.requestParamsToMap(request));

	}

	@RequestMapping(value = { "/deletePaidCheque" }, method = { RequestMethod.POST })
	public @ResponseBody String deletePaidCheque(HttpServletRequest request, Map<String, Object> model) {

		return chequeDetailsService.deletePaidCheque(Utils.requestParamsToMap(request));

	}
	
}
