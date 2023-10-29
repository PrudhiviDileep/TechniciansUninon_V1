package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.GenericGridDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.MiscellaneousDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.PaymentConfigurations;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;

@Service
public class MiscellaneousService {
	@Autowired
	public MiscellaneousDAO miscellaneousDAO;

	@Autowired
	public GenericGridDAO genericDAO;

	public JSONArray getDepartments() {
		return this.miscellaneousDAO.getDepartments();
	}

	public JSONObject getTopPanel(int cardNo, String deptId, String pageId) {
		return this.miscellaneousDAO.getTopPanel(cardNo, deptId, pageId);
	}

	public String getCardNumbersByDeptId(String deptId) {
		return this.miscellaneousDAO.getCardNubersByDeptId(deptId);
	}

	public String getCardNumbersByDeptIdForAutocomplete(String deptId, String term) {
		return this.miscellaneousDAO.getCardNumbersByDeptIdForAutocomplete(deptId, term);
	}

	public JSONArray getUnits() {
		return this.miscellaneousDAO.getUnits();
	}

	public String getDetialsBySelectAtion(HttpServletRequest request) {
		return this.miscellaneousDAO.getDetialsBySelectAtion(request);
	}

	public JSONObject getMemberDetailsForRecomondation(String deptId, String cardNo) {
		return this.miscellaneousDAO.getMemberDetailsForRecomondation(deptId, cardNo);
	}

	public JSONObject getPaymentConfigDetials(String deptId, String category) {
		return this.miscellaneousDAO.getPaymentConfigDetials(deptId, category);
	}

	public JSONArray getUnsubsribedYears(String deptId, String cardNo) {
		return this.miscellaneousDAO.getUnsubsribedYears(deptId, cardNo);
	}

	public JSONArray getPaymentConfigDetialsForSelect(String deptId, String category) {
		return this.miscellaneousDAO.getPaymentConfigDetialsForSelect(deptId, category);
	}

	public PaymentConfigurations getPaymentConfigurationsDetailsById(String paymentconfId) {
		return this.miscellaneousDAO.getPaymentConfigurationsDetailsById(paymentconfId);
	}

	public Registration getMemberDetailsByDeptIdAndCardNo(String deptId, String cardNo) {
		return this.miscellaneousDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
	}
}