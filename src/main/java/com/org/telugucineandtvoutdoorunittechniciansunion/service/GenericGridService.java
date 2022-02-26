package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.GenericCRUDOperationsDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.GenericGridDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.exceptions.GenericProcedureCallException;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Service
public class GenericGridService {

	@Autowired
	public GenericGridDAO genericDAO;
	@Autowired
	GenericCRUDOperationsDAO genericCRUDOperationsDAO;
	
	
	public String getGenericGridConfigData(Map<String, String> actionData) {

		return this.genericDAO.getGenericGridConfigData(actionData);
	}
	
	public String doGenericCRUDOpertion(Map<String, String> actionData) {

		return this.genericCRUDOperationsDAO.doGenericCRUDOpertion(actionData);
	}
	
	
	
	public String getDataForGenericExportToExcel(Map<String, String> actionData, String procId) throws GenericProcedureCallException{

		return this.genericCRUDOperationsDAO.getDataForGenericExportToExcel(actionData,procId);
	}
	
	
}
