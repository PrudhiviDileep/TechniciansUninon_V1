package com.org.telugucineandtvoutdoorunittechniciansunion.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.telugucineandtvoutdoorunittechniciansunion.service.GenericCRUDOperationsService;
import com.org.telugucineandtvoutdoorunittechniciansunion.service.GenericGridService;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Controller
public class GenericCRUDOperationsController {

	@Autowired
	public GenericCRUDOperationsService genericCRUDOperationsService;

	@Autowired
	public GenericGridService genericGridService;

	@RequestMapping(value = { "/genericGridSaveUpdateDelete" }, method = { RequestMethod.POST })
	public @ResponseBody String deletePaidCheque(HttpServletRequest request, Map<String, Object> model) {

		return genericCRUDOperationsService.doGenericCRUDOpertion(Utils.requestParamsToMap(request));

	}

	@RequestMapping(value = { "/doGenericCRUDOpertion" }, method = { RequestMethod.POST })
	public @ResponseBody String doGenericCRUDOpertion(HttpServletRequest request, Map<String, Object> model) {

		return genericCRUDOperationsService.doGenericCRUDOpertion(Utils.requestParamsToMap(request));

	}

}
