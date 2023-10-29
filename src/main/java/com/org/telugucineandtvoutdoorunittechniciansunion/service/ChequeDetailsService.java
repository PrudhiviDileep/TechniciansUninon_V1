package com.org.telugucineandtvoutdoorunittechniciansunion.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.telugucineandtvoutdoorunittechniciansunion.DAO.ChequeDetailsDAO;

@Service
public class ChequeDetailsService {
	@Autowired
	public ChequeDetailsDAO chequeDetailsDAO;

	public String getAllChequeDetails(String fromDate, String toDate, String chequeNo, String chequeType, String amount,
			String chequeRecievedFrom, String companyName, String bankName) {
		
		return chequeDetailsDAO.getAllChequeDetails(fromDate, toDate, chequeNo, chequeType, amount, chequeRecievedFrom,
				companyName, bankName);
	}

	public String getSelectedChequeDisburseDetails(String selectedChequeNo, String chequeNo, String deptId,
			String cardNo, String amount, String ourChequeId) {
		
		return chequeDetailsDAO.getSelectedChequeDisburseDetails(selectedChequeNo, chequeNo, deptId, cardNo, amount,
				ourChequeId);
	}

	public String getChequeDesburseDetails(String chequeNo) {
		
		return chequeDetailsDAO.getChequeDesburseDetails(chequeNo);
	}

	public String saveChequeDetails(String chequeNo, String chequeType, String phoneNo, String amount,
			String chequeRecievedFrom, String companyName, String chequeDate, String bankName, String remarks) {
		
		return chequeDetailsDAO.saveChequeDetails(chequeNo, chequeType, phoneNo, amount, chequeRecievedFrom,
				companyName, chequeDate, bankName, remarks);
	}

	public String saveChequeDisburseDetails(String chequeNo, String deptId, String cardNo, String bankAccNo,
			String amount, String ourChequeId) {
		
		return chequeDetailsDAO.saveChequeDisburseDetails(chequeNo, deptId, cardNo, bankAccNo, amount, ourChequeId);
	}

	public String updateChequeDetails(String chequeNo, String chequeType, String phoneNo, String amount,
			String chequeRecievedFrom, String companyName, String chequeDate, String bankName, String remarks) {// CHEQUE
																												// DETAILS
		
		return chequeDetailsDAO.updateChequeDetails(chequeNo, chequeType, phoneNo, amount, chequeRecievedFrom,
				companyName, chequeDate, bankName, remarks);
	}

	public String updateChequeDisburseDetails(String chequeDsbId, String chequeNo, String deptId, String cardNO,
			String bankAccNo, String amount, String ourChequeId) {
		
		return chequeDetailsDAO.updateChequeDisburseDetails(chequeDsbId, chequeNo, deptId, cardNO, bankAccNo, amount,
				ourChequeId);
	}

	public String deleteChequeDetails(String chequeNo) {
		
		return chequeDetailsDAO.deleteChequeDetails(chequeNo);
	}

	public String deleteCheques(String chequeNos) {
		
		return chequeDetailsDAO.deleteCheques(chequeNos);
	}

	public String deleteChequeDesburseDetails(String chqDsbId) {
		
		return chequeDetailsDAO.deleteChequeDesburseDetails(chqDsbId);
	}

	public String getSelectedChequeDetails(String chequeNos) {
		
		return chequeDetailsDAO.getSelectedChequeDetails(chequeNos);
	}

	// getChequeNos
	public String getChequeNos(String searchTerm) {
		
		return chequeDetailsDAO.getChequeNos(searchTerm);
	}

	// getChequeNosByDeptId
	public String getCardNosByDeptId(String deptId) {
		
		return chequeDetailsDAO.getCardNosByDeptId(deptId);
	}

	// getMemberDetailsByDeptAndCardNo
	public String getMemberDetailsByDeptAndCardNo(String deptId, String cardNo) {
		
		return chequeDetailsDAO.getMemberDetailsByDeptAndCardNo(deptId, cardNo);
	}

	public String getAllCardNo() {
		
		return chequeDetailsDAO.getAllCardNo();
	}

	public String getAllPaidCheques(Map<String, String> requestData) {
		
		return chequeDetailsDAO.getAllPaidCheques(requestData);
	}

	public String getPaidChequeById(Map<String, String> requestData) {

		return chequeDetailsDAO.getPaidChequeById(requestData);

	}
	public String savePaidCheque(Map<String, String> requestData) {

		return chequeDetailsDAO.savePaidCheque(requestData);

	}

	public String updatePaidCheque(Map<String, String> requestData) {

		return chequeDetailsDAO.updatePaidCheque(requestData);

	}

	public String deletePaidCheque(Map<String, String> requestData) {

		return chequeDetailsDAO.deletePaidCheque(requestData);

	}

}
