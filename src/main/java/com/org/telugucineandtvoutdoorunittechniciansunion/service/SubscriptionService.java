package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.SubscriptionDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
	@Autowired
	public SubscriptionDAO subscriptionDAO;

	public String getSubscriptionDetatils(HttpServletRequest request, Map<String, Object> model) {
		return this.subscriptionDAO.getSubscriptionDetatils(request, model);
	}

	public String paySubscriptionAmount(HttpServletRequest request) {
		return this.subscriptionDAO.paySubscriptionAmount(request);
	}

	public JSONObject updateSubcriptionsFormDetails(HttpServletRequest request) {
		return this.subscriptionDAO.updateSubcriptionsFormDetails(request);
	}

	public JSONObject deleteSubcriptionsFormDetails(HttpServletRequest request) {
		return this.subscriptionDAO.deleteSubcriptionsFormDetails(request);
	}

	public SubscriptionPayments getSubscriptionDetails(String memberId, String transId) {
		return this.subscriptionDAO.getSubscriptionDetails(memberId, transId);
	}

	public String updateSubcriptions(HttpServletRequest request) {
		return this.subscriptionDAO.updateSubcriptions(request);
	}

	public String deleteSubcriptions(HttpServletRequest request) {
		return this.subscriptionDAO.deleteSubcriptions(request);
	}
}