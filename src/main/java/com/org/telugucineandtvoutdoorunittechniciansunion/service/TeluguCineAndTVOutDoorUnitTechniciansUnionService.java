package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.TeluguCineAndTVOutDoorUnitTechniciansUnionDAO;

@Service
public class TeluguCineAndTVOutDoorUnitTechniciansUnionService {
	@Autowired
	public TeluguCineAndTVOutDoorUnitTechniciansUnionDAO appDAO;

	public String login(HttpServletRequest request, Map<String, Object> model) {
		return this.appDAO.login(request, model);
	}

	public String registration(JSONObject items, MultipartFile[] file) {
		return this.appDAO.registration(items, file);
	}

	public String viewMemberDetails(HttpServletRequest request) {
		return this.appDAO.viewMemberDetails(request);
	}

	public String updateMemberDetails(JSONObject items, MultipartFile[] file) {
		return this.appDAO.updateMemberDetails(items, file);
	}

	public String payMembershipAmount(HttpServletRequest request, Map<String, Object> model) {
		return this.appDAO.payMembershipAmount(request, model);
	}

	public String getCardBalance(HttpServletRequest request, Map<String, Object> model) {
		return this.appDAO.getCardBalance(request, model);
	}

	public String getMembersDetails(HttpServletRequest request, Map<String, Object> model) {
		return this.appDAO.getMembersDetails(request, model);
	}

	public String payCardBalance(HttpServletRequest request) {
		return this.appDAO.payCardBalance(request);
	}

	public JSONObject updateCardBalanceFormDetails(HttpServletRequest request) {
		return this.appDAO.updateCardBalanceFormDetails(request);
	}

	public JSONObject deleteCardBalanceFormDetails(HttpServletRequest request) {
		return this.appDAO.deleteCardBalanceFormDetails(request);
	}

	public String updateMembershipPayments(HttpServletRequest request) {
		return this.appDAO.updateMembershipPayments(request);
	}

	public String deleteMembershipPayments(HttpServletRequest request) {
		return this.appDAO.deleteMembershipPayments(request);
	}
}
