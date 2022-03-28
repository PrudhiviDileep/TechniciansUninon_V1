package com.org.telugucineandtvoutdoorunittechniciansunion.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.GenericCRUDOperationsService;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.MiscellaneousService;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Controller
public class MiscellaneousController {
	public static final String FINAL_RESULT_CODE = "FINAL_RESULT_CODE";
	public static final String FINAL_RESULT = "FINAL_RESULT";
	@Autowired
	public MiscellaneousService miscellaneousService;

	@RequestMapping(value = { "/getTopPanel" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getTopPanel(@RequestParam("cardNo") String cardNo, @RequestParam("deptId") String deptId,
			@RequestParam("pageId") String pageId, HttpServletRequest request, Map<String, Object> model) {
		JSONObject resultObj = null;
		int cardNumber = 0;

		try {
			if (cardNo == null || "".equals(cardNo)) {

				resultObj = new JSONObject();
				resultObj.put(FINAL_RESULT_CODE, "200");
				resultObj.put(FINAL_RESULT, "Card Number should not be empty! ");
			} else if (deptId == null || "".equals(deptId)) {

				resultObj = new JSONObject();
				resultObj.put(FINAL_RESULT_CODE, "200");
				resultObj.put(FINAL_RESULT, "Card Number should not be empty! ");
			} else {

				try {
					cardNumber = Integer.parseInt(cardNo);

					resultObj = new JSONObject();
					resultObj.put(FINAL_RESULT_CODE, "400");
					resultObj.put(FINAL_RESULT, this.miscellaneousService.getTopPanel(cardNumber, deptId, pageId));
				} catch (NumberFormatException nfe) {

					resultObj = new JSONObject();
					resultObj.put(FINAL_RESULT_CODE, "300");
					resultObj.put(FINAL_RESULT, "Card Number should Numeric! ");
				} catch (Exception e) {

					resultObj = new JSONObject();
					resultObj.put(FINAL_RESULT_CODE, "300");
					resultObj.put(FINAL_RESULT, e.getMessage());
				}

			}

		} catch (Exception e) {

			resultObj = new JSONObject();
			resultObj.put(FINAL_RESULT_CODE, "300");
			resultObj.put(FINAL_RESULT, e.getMessage());
			ApplicationUtilities.error(getClass(), e, "getTopPanel");
		}
		return resultObj.toJSONString();
	}

	@RequestMapping(value = { "/getCardNumbersByDeptId" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getCardNumbersByDeptId(HttpServletRequest request, @RequestParam("deptId") String deptId) {
		String resutlt = "";
		JSONObject obj = new JSONObject();

		try {
			resutlt = this.miscellaneousService.getCardNumbersByDeptId(deptId);
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "getCardNumbersByDeptId");
		}

		return resutlt;
	}

	@RequestMapping(value = { "/getCardNumbersByDeptIdForAutocomplete" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getCardNumbersByDeptIdForAutocomplete(HttpServletRequest request,
			@RequestParam("deptId") String deptId, @RequestParam("term") String term) {
		String resutlt = "";
		JSONObject obj = new JSONObject();

		try {
			resutlt = this.miscellaneousService.getCardNumbersByDeptIdForAutocomplete(deptId,
					(term != null) ? term : "");
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "getCardNumbersByDeptIdForAutocomplete");
		}

		return resutlt;
	}

	@RequestMapping(value = { "/getUnits" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getUnits(HttpServletRequest request, HttpServletResponse response) {
		String result = "";

		try {
			result = this.miscellaneousService.getUnits().toJSONString();
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "getUnits");
		}

		return result;
	}

	@RequestMapping(value = { "/getDetialsBySelectAtion" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getDetialsBySelectAtion(HttpServletRequest request) {
		String result = "";
		try {
			result = this.miscellaneousService.getDetialsBySelectAtion(request);
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "getDetialsBySelectAtion");
		}
		return result;
	}

	@RequestMapping(value = { "/getMemberDetailsForRecomondation" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getMemberDetailsForRecomondation(String deptId, String cardNo) {
		return this.miscellaneousService.getMemberDetailsForRecomondation(deptId, cardNo).toJSONString();
	}

	
	@Autowired
	public GenericCRUDOperationsService genericCRUDOperationsService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/getReceiptPrintDetails" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getReceiptPrintDetails(HttpServletRequest request) {
		String result=genericCRUDOperationsService.doGenericCRUDOpertion(Utils.requestParamsToMap(request));
		JSONObject recietpDetObj=(JSONObject)JSONValue.parse(result);
		recietpDetObj.put("AMOUNT", Utils.convertToWord(Integer.parseInt((String)recietpDetObj.get("AMOUNT"))));
		
		return recietpDetObj.toJSONString();
	}
}