package com.org.telugucineandtvoutdoorunittechniciansunion.reports;

import java.sql.Date;

public class SubscriptionReport {
	
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
	public String getDatOfJoining() {
		return datOfJoining;
	}
	public void setDatOfJoining(String datOfJoining) {
		this.datOfJoining = datOfJoining;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getUnsubScribedYears() {
		return unsubScribedYears;
	}
	public void setUnsubScribedYears(String unsubScribedYears) {
		this.unsubScribedYears = unsubScribedYears;
	}
	public String getSubscriptionCharges() {
		return subscriptionCharges;
	}
	public void setSubscriptionCharges(String subscriptionCharges) {
		this.subscriptionCharges = subscriptionCharges;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	private Integer cardNo;
	private String deptId;
	private String memberId;
	private String firstName;
	private String datOfJoining;
	private String mobilePhone;
	private String unsubScribedYears;
	private String subscriptionCharges;
	private Double balance;
	private Integer subscribedYear;
	public Integer getSubscribedYear() {
		return subscribedYear;
	}
	@Override
	public String toString() {
		return "SubscriptionReport [cardNo=" + cardNo + ", deptId=" + deptId + ", memberId=" + memberId + ", firstName="
				+ firstName + ", datOfJoining=" + datOfJoining + ", mobilePhone=" + mobilePhone + ", unsubScribedYears="
				+ unsubScribedYears + ", subscriptionCharges=" + subscriptionCharges + ", balance=" + balance
				+ ", subscribedYear=" + subscribedYear + "]";
	}
	public void setSubscribedYear(Integer subscribedYear) {
		this.subscribedYear = subscribedYear;
	}
	

}
