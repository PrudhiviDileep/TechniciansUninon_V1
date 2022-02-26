package com.org.telugucineandtvoutdoorunittechniciansunion.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.telugucineandtvoutdoorunittechniciansunion.service.GenericGridService;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Controller
public class GenericGridController {

	@Autowired
	public GenericGridService genericGridService;

	@RequestMapping(value = { "/gridConfigurations" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String gridConfigurations(HttpServletRequest request, Map<String, Object> model) {
		return "GridConfigurations";
	}

	@RequestMapping(value = { "/procConfigurations" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String procConfigurations(HttpServletRequest request, Map<String, Object> model) {
		return "ProcedureConfigurations";
	}

	@RequestMapping(value = { "/getGenericGridConfigData" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getGenericGridConfigData(HttpServletRequest request) {
		return this.genericGridService.getGenericGridConfigData(Utils.requestParamsToMap(request));
	}

	@RequestMapping(value = { "/getGenericGridData", "/getgenericgriddata" }, method = { RequestMethod.POST })
	@ResponseBody
	public String getGenericGridData(HttpServletRequest request) {

		return this.genericGridService.doGenericCRUDOpertion(Utils.requestParamsToMap(request));
	}

	@RequestMapping(value = { "/doGenericGridOpertion" }, method = { RequestMethod.POST })
	@ResponseBody
	public String doGenericGridOpertion(HttpServletRequest request) {
		return this.genericGridService.doGenericCRUDOpertion(Utils.requestParamsToMap(request));
	}

}
