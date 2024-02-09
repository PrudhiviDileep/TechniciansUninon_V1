package com.org.telugucineandtvoutdoorunittechniciansunion.reports;


public class LoanBalanceReport {
	
	public Integer getCardNo() {
		return cardNo;
	}
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Double getLoanRePaid() {
		return loanRePaid;
	}
	public void setLoanRePaid(Double loanRePaid) {
		this.loanRePaid = loanRePaid;
	}
	public Double getLoanBalance() {
		return loanBalance;
	}
	public void setLoanBalance(Double loanBalance) {
		this.loanBalance = loanBalance;
	}
	private Integer cardNo;
	private String deptId;
	private String memberId;
	private String firstName;
	private String mobilePhone;
	private Double loanAmount;
	private Double loanRePaid;
	private Double loanBalance;	

}
