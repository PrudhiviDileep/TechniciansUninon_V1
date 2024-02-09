package com.org.telugucineandtvoutdoorunittechniciansunion.reports;

public class CardBalanceReport {

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
	public Double getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(Double cardAmount) {
		this.cardAmount = cardAmount;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Double getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Double cardBalance) {
		this.cardBalance = cardBalance;
	}
	private int cardNo;
	private String deptId;
	private String memberId;
	private String firstName;
	private String mobilePhone;
	private Double cardAmount;
	private Double paidAmount;
	private Double cardBalance;
	

}
