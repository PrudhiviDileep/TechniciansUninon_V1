package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.GenericCRUDOperationsDAO;
import com.org.telugucineandtvoutdoorunittechniciansunion.exceptions.GenericProcedureCallException;
@Service
public class GenericCRUDOperationsService {
	
	@Autowired
	public GenericCRUDOperationsDAO genericDAO; 


	
	
	public String doGenericCRUDOpertion(Map<String, String> actionData) {

		return this.genericDAO.doGenericCRUDOpertion(actionData);
	}
	
	public String getGenericGridData(Map<String, String> actionData) {

		return this.genericDAO.doGenericCRUDOpertion(actionData);
	}
	
	public String getDataForGenericExportToExcel(Map<String, String> actionData, String procId) throws GenericProcedureCallException{

		return this.genericDAO.getDataForGenericExportToExcel(actionData,procId);
	}
	

}
