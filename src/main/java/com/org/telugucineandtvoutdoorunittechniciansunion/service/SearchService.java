package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.SearchDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;

@Service
public class SearchService {
	@Autowired
	public SearchDAO searchDAO;

	public String search(HttpServletRequest request, Map<String, Object> model) {
		return this.searchDAO.search(request, model);
	}

	public JSONObject getCommonSearchResults(HttpServletRequest request) {
		return this.searchDAO.getCommonSearchResults(request);
	}

	public String searchDetails(String deptId) {
		StringBuilder result = new StringBuilder();
		List<Registration> list = this.searchDAO.searchDetails(deptId);

		if (list != null && !list.isEmpty() && list.size() > 0) {

			for (int i = 1; i <= list.size(); i++) {
				Registration registration = list.get(i - 1);

				result.append(String.valueOf(String.valueOf(result))).append("<tr><td>").append(i).append("</td><td>")
						.append(registration.getRegistrationPK().getCardNo()).append("</td><td>")
						.append(registration.getFirstName()).append("</td><td>").append(registration.getPhoneNo())
						.append("</td><td>").append(registration.getPerminentAddress()).append("</td></tr>");
			}

		} else {

			result.append(String.valueOf(String.valueOf(result))).append("<tr><td colspan='5'>No Data Found</td></tr>");
		}

		return result.toString();
	}
}