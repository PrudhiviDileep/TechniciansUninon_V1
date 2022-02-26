	$(document).ready(function() {

	$("#subscriptionsUpdateBtn").click(function(event) {
		event.preventDefault();

			
		var data = {};		
		data.category=$('#updateSubscrib_category').val();
		data.memberId=$('#updateSubscrib_memberId').val();
		data.transactionId=$('#updateSubscrib_ctransactionId').val();
		data.subscriptionYear=$('#updateSubscrib_subscriptionYear').val();
		data.paidDate=$('#updateSubscrib_paidDate').val();
		data.paidAmount=$('#updateSubscrib_paidAmount').val();

		data.receiptNo=$('#updateSubscrib_receiptNo').val();
		data.paymentStatus=$('#updateSubscrib_paymentStatus').val();
		data.remarks=$('#updateSubscrib_remarks').val();
		data.subscriptionAmount=$('#updateSubscription_subscriptionAmount').val();
//		data.paymentConfId=$('#updateSubscription_paymentConfId').val();	
		data.paymentConfId=$('#updateSubscription_paymentConfId option:selected').text();
		
	
		data.cardNo=$('#updateSubscrib_cardNo').val();
		data.deptId=$('#updateSubscrib_deptId').val();	
			
		
		
		$.ajax({
			type : "POST",			
			url : "updateSubcriptions",
			data : data,
			traditional : true,
			cache : false,			
			success : function(response) {
													
						obj=JSON.parse(response);						
						if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
							messageDialog(obj.ERROR_MSG);
						} else if (obj.FINAL_RESULT_CODE == "400") {							
							redirectingMessageWithURL(obj.DATA_DETAILS, "paySubscriptionAmountForm?deptId="+data.deptId+"&cardNo="+data.cardNo);						
							
						}						
			},
			error : function(e) {
				
			}
		});
	});
	
	$("#subscriptionsdeleteBtn").click(function(event) {
		event.preventDefault();
		var pageId=$("#PAGE_ID").val();
		conformationMessage("Are you seure do you want to delete this record ?",pageId);
	
	});
	
	

	$("#paySubscriptionAmountBtn").click(function(event) {
		event.preventDefault();				
					
		var data = {};	
		data.paidAmount=$('#paySubscription_paidAmount').val();
//		data.paymentConfId=$('#paySubscription_paymentConfId').val();		
		data.subscriptionYear=$('#paySubscription_subscriptionYear').val();
		data.paidDate=$('#paySubscription_paidDate').val();
		data.subscriptionAmount=$('#paySubscription_subscriptionAmount').val();
		data.receiptNo=$('#paySubscription_receiptNo').val();
		data.paymentStatus=$('#paySubscription_paymentStatus').val();
		data.remarks=$('#paySubscription_remarks').val();
		data.deptId=$("#commonSeachDeptSelectId option").filter(":selected").val();
		data.cardNo=$('#commonSearchCardNo').val();	
		data.memberId=$('#MEMBER_ID').val();	
		data.paymentConfId=$('#paySubscription_paymentConfId option:selected').text();
		data.paymentConfIdlabel=$('#paySubscription_paymentConfId').val();	
		
		if(data.paymentConfIdlabel!="" && data.paymentConfIdlabel!='undefined' && data.paymentConfIdlabel!="SELECT" ){
		$.ajax({
			type : "POST",			
			url : "paySubscriptionAmount",
			data : data,
			traditional : true,
			cache : false,			
			success : function(response) {
													
						obj=JSON.parse(response);					
						
						if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
							messageDialog(obj.ERROR_MSG);
						} else if (obj.FINAL_RESULT_CODE == "400") {
							redirectingMessageWithURL(obj.DATA_DETAILS, "paySubscriptionAmountForm?deptId="+data.deptId+"&cardNo="+data.cardNo);	
							
						}						
			},
			error : function(e) {
				
			}
		});
		}else{
			messageDialog("Please select Payment type!");
			
		}
	});
	
	
	
	
	
	$("#payMembershipAmountBtn").click(function(event) {
		event.preventDefault();
				
				
		var data = {};	
		
		data.membershipAmount=$('#payMembershipAmountId').val();		
		data.paidAmount=$('#payMembershipPaidAmountId').val();
		data.balanceAmount=$('#payMembershipBalanceAmountId').val();
		data.payingAmount=$('#payMembershipPayingAmountId').val();
		data.paymentConfId=$('#paymentConfIdId option:selected').text();
		data.memberId=$('#MEMBER_ID').val();		
		data.pageId=$('#PAGE_ID').val();
		data.receiptNo=$('#payMembershipReceiptNoId').val();
		data.paymentConfIdlabel=$('#paymentConfIdId').val();		
		data.paidDate=$('#payMembershipPaidDate').val();
		data.remarks=$('#payMembershipRemarks').val();

		data.cardNo=$('#commonSearchCardNo').val();
		data.deptId=$('#commonSeachDeptSelectId').val();	
		
		
		data.paymentMode=$('#payMembershipPaymentModeId').val();
		data.ddNo=$('#payMembershipDdNoId').val();	
		
		payMembershipDdNoId
		
		
		if(data.paymentConfIdlabel!="" && data.paymentConfIdlabel!='undefined' && data.paymentConfIdlabel!="SELECT" ){
		$.ajax({
			type : "POST",			
			url : "payCardBalance",
			data : data,
			traditional : true,
			cache : false,			
			success : function(response) {
						
						
						obj=JSON.parse(response);
						
						if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
							messageDialog(obj.ERROR_MSG);
						} else if (obj.FINAL_RESULT_CODE == "400") {
							
							redirectingMessageWithURL(obj.DATA_DETAILS, "payMembershipAmount?deptId="+data.deptId+"&cardNo="+data.cardNo);
							//redirectingMessageWithURL(obj.DATA_DETAILS, "paySubscriptionAmountForm?deptId="+data.deptId+"&cardNo="+data.cardNo);	
							
							
							
						}						
			},
			error : function(e) {
				
			}
		});
		}else{
			messageDialog("Please select Payment type!");
			
		}
	});
	
	
	$("#updateMembershipAmountBtn").click(function(event) {
		event.preventDefault();
		
		var data = {};
		data.membershipAmount=$('#updateMembershipAmountId').val();
		data.paidAmount=$('#updateMembershipPaidAmountId').val();
		data.paymentConfId=$('#updatePaymentConfIdId option:selected').text();		
		data.paymentConfIdlabel=$('#updatePaymentConfIdId').val();
		data.receiptNo=$('#updateMembershipReceiptNoId').val();
		data.paidDate=$('#updateMembershipPaidDate').val();
		data.remarks=$('#updateMembershipRemarks').val();			
		data.deptId=$("#updatePaymentDeptId").val();
		data.cardNo=$('#updatePaymentCardNo').val();			
		data.memberId=$('#MEMBER_ID').val();
		data.transactionId=$('#updatePaymentTransactionId').val();
		
		
		
		if(data.paymentConfIdlabel!="" && data.paymentConfIdlabel!='undefined' && data.paymentConfIdlabel!="SELECT" ){
		$.ajax({
			type : "POST",			
			url : "updateCardBalance",
			data : data,
			traditional : true,
			cache : false,			
			success : function(response) {
							
						
						obj=JSON.parse(response);
						
						if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
							messageDialog(obj.ERROR_MSG);
						} else if (obj.FINAL_RESULT_CODE == "400") {
							
							
							redirectingMessageWithURL(obj.DATA_DETAILS, "payMembershipAmount?deptId="+data.deptId+"&cardNo="+data.cardNo);	
							
							
							
						}						
			},
			error : function(e) {
				
			}
		});
		}else{
			messageDialog("Please select Payment type!");
			
		}
	});
	
	$("#deleteMembershipAmountBtn").click(function(event) {
		event.preventDefault();
		var pageId=$("#PAGE_ID").val();
		conformationMessage("Are you seure do you want to delete this record ?",pageId);
	
	});
	
	$("#sactionLoanBtn").click(function(event) {
		event.preventDefault();
		var pageId=$("#PAGE_ID").val();
		var data = {};
		data.loanAmount=$('#loanSanctionAmount').val();
		data.loanSanctioneddDate=$('#loanSanctionLoanSanctioneddDate').val();
		data.remarks=$('#loanSanctionRmarks').val();
		data.memberId=$('#MEMBER_ID').val();
		data.deptId=$("#tpnl_deptId").val();
		data.cardNo=$('#tpnl_cardNo').val();
		
		$.ajax({
			type : "POST",			
			url : "sanctionLoan",
			data : data,
			traditional : true,
			cache : false,	
			success : function(response) {
					
				
				obj=JSON.parse(response);
				
				if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
					messageDialog(obj.ERROR_MSG);
				} else if (obj.FINAL_RESULT_CODE == "400") {						
						redirectingMessageWithURL(obj.DATA_DETAILS, "loanSummaryPage?deptId="+data.deptId+"&cardNo="+data.cardNo);
				
				}				
	},
			error : function(e) {
				

			}
		});
	
	});
	
	
	
	$("#payLoanAmountBtn").click(function(event) {
		
		event.preventDefault();
		var pageId=$("#PAGE_ID").val();
		var data = {};
		data.payingAmount=$('#loanPaymentPyingAmount').val();
		
		data.paidDate=$('#loanPaymentPaidDate').val();
		data.receiptNo=$('#loanPaymentReceiptNo').val();
		data.remarks=$('#loanPaymentRemarks').val();
		data.memberId=$('#MEMBER_ID').val();		
		data.deptId=$("#tpnl_deptId").val();
		data.cardNo=$('#tpnl_cardNo').val();
		
		
		$.ajax({
			type : "POST",			
			url : "payLoanAmount",
			data : data,
			traditional : true,
			cache : false,	
			success : function(response) {
				
				
				obj=JSON.parse(response);
			
				if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
					messageDialog(obj.ERROR_MSG);
				} else if (obj.FINAL_RESULT_CODE == "400") {						
						redirectingMessageWithURL(obj.DATA_DETAILS, "payLoanAmountForm?deptId="+data.deptId+"&cardNo="+data.cardNo);
				
				}				
	},
			error : function(e) {
				

			}
		});
	
	});
	
	$("#updateLoanRecoveryDetailsBtn").click(function(event) {
		event.preventDefault();
		var pageId=$("#PAGE_ID").val();
		var data = {};		
		
		data.loanId=$("#updateLoanRecoveryDetailsLoanId").val();
		data.memberId=$('#MEMBER_ID').val();		
		data.deptId=$("#updateLoanRecoveryDetailsDeptId").val();
		data.cardNo=$('#updateLoanRecoveryDetailsCardNo').val();
		data.transactionId=$('#updateLoanRecoveryDetailsTransactionId').val();
		data.paidAmount=$('#updateLoanRecoveryDetailsPaidAmountId').val();
		data.paidDate=$('#updateLoanRecoveryDetailsPaidDate').val();
		data.receiptNo=$('#updateLoanRecoveryDetailsReceiptNoId').val();
		data.remarks=$('#updateLoanRecoveryDetailsRemarks').val();
		
		

		$.ajax({
			type : "POST",			
			url : "updateLoanRecoveryDetails",
			data : data,
			traditional : true,
			cache : false,	
			success : function(response) {
				
	
				obj=JSON.parse(response);
	
				if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
					messageDialog(obj.ERROR_MSG);
				} else if (obj.FINAL_RESULT_CODE == "400") {						
						redirectingMessageWithURL(obj.DATA_DETAILS, "payLoanAmountForm?deptId="+data.deptId+"&cardNo="+data.cardNo);
				
				}				
	},
			error : function(e) {
				

			}
		});
	
	});
	
	
	$("#deleteLoanRecoveryDetailsBtn").click(function(event) {
		event.preventDefault();
		var pageId=$("#PAGE_ID").val();
		conformationMessage("Are you seure do you want to delete this record ?",pageId);
	
	});
	
	$("#updateLoanSanctionDetailsBtn").click(function(event) {
		event.preventDefault();
		var pageId=$("#PAGE_ID").val();
		var data = {};		
		
		
		
		data.deptId=$("#DEPT_ID").val();
		data.cardNo=$("#CARD_NO").val();
		
		data.loanId=$("#updateLoanSanctionDetailsLoanId").val();		
		data.loanAmount=$("#updateLoanSanctionDetailsLoanAmount").val();
		data.loanSanctionedDate=$("#updateLoanSanctionDateId").val();
		data.memberId=$("#MEMBER_ID").val();
	
		//alert(JSON.stringify(data));
		

		$.ajax({
			type : "POST",			
			url : "updateLoanSanctionDetails",
			data : data,
			traditional : true,
			cache : false,	
			success : function(response) {
				
	
				obj=JSON.parse(response);
	
				if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
					messageDialog(obj.ERROR_MSG);
				} else if (obj.FINAL_RESULT_CODE == "400") {						
						redirectingMessageWithURL(obj.DATA_DETAILS, "loanSummaryPage?deptId="+data.deptId+"&cardNo="+data.cardNo);
				
				}				
	},
			error : function(e) {
				

			}
		});
	
	});
	
	
	$("#deleteLoanSanctionDetailsBtn").click(function(event) {
		event.preventDefault();
		var pageId=$("#PAGE_ID").val();
		conformationMessage("Are you seure do you want to delete this record ?",pageId);
	
	});
	
	
	
	});
	
	
	
	function deleteSubscription(){	
		var pageId=$("#PAGE_ID").val();
		
	
		var data = {};		
		data.category=$('#deleteSubscrib_category').val();
		data.memberId=$('#deleteSubscrib_memberId').val();
		data.transactionId=$('#deleteSubscrib_ctransactionId').val();
		data.subscriptionYear=$('#deleteSubscrib_subscriptionYear').val();
		data.paidDate=$('#deleteSubscrib_paidDate').val();
		data.paidAmount=$('#deleteSubscrib_paidAmount').val();
		data.receiptNo=$('#deleteSubscrib_receiptNo').val();
		data.paymentStatus=$('#deleteSubscrib_paymentStatus').val();
		data.remarks=$('#deleteSubscrib_remarks').val();
		data.subscriptionAmount=$('#deleteSubscrib_subscriptionAmount').val();
		data.paymentConfId=$('#deleteSubscrib_paymentConfId').val();	
		data.cardNo=$('#deleteSubscrib_cardNo').val();
		data.deptId=$('#deleteSubscrib_deptId').val();	
		
		$.ajax({
			type : "POST",			
			url : "deleteSubcriptions",
			data : data,
			traditional : true,
			cache : false,	
			success : function(response) {
				
			
				obj=JSON.parse(response);
				
				if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
					messageDialog(obj.ERROR_MSG);
				} else if (obj.FINAL_RESULT_CODE == "400") {
					
				
						
						redirectingMessageWithURL(obj.DATA_DETAILS, "paySubscriptionAmountForm?deptId="+data.deptId+"&cardNo="+data.cardNo);
					
				
					
					
				}
				
	},
			error : function(e) {
				

			}
		});
	}
function deleteMembershipPayment(){	
	var pageId=$("#PAGE_ID").val();		
			
		var data = {};			
				
		
		var data = {};
		data.membershipAmount=$('#deleteMembershipAmountId').val();
		data.paidAmount=$('#deleteMembershipPaidAmountId').val();
		data.paymentConfId=$('#deletePaymentConfIdIdd option:selected').text();
		data.paymentConfIdlabel=$('#deletePaymentConfIdId').val();
		data.receiptNo=$('#deleteMembershipReceiptNoId').val();
		data.paidDate=$('#deleteMembershipPaidDate').val();
		data.remarks=$('#deleteMembershipRemarks').val();			
		data.deptId=$("#deletePaymentDeptId").val();
		data.cardNo=$('#deletePaymentCardNo').val();			
		data.memberId=$('#MEMBER_ID').val();
		data.transactionId=$('#deletePaymentTransactionId').val();		
		
		$.ajax({
			type : "POST",			
			url : "deleteCardBalance",
			data : data,
			traditional : true,
			cache : false,	
			success : function(response) {
				
				obj=JSON.parse(response);
				
				
				if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
					messageDialog(obj.ERROR_MSG);
				} else if (obj.FINAL_RESULT_CODE == "400") {
						
						redirectingMessageWithURL(obj.DATA_DETAILS, "payMembershipAmount?deptId="+data.deptId+"&cardNo="+data.cardNo);
					
				}
				
	},
			error : function(e) {
				

			}
		});
	}
function deleteLoanRecoveryDetails(){	
	var pageId=$("#PAGE_ID").val();	
	var data={};
	data.loanId=$("#deleteLoanRecoveryDetailsLoanId").val();
	data.memberId=$('#MEMBER_ID').val();		
	data.deptId=$("#deleteLoanRecoveryDetailsDeptId").val();
	data.cardNo=$('#deleteLoanRecoveryDetailsCardNo').val();
	data.transactionId=$('#deleteLoanRecoveryDetailsTransactionId').val();
	data.paidAmount=$('#deleteLoanRecoveryDetailsPaidAmountId').val();
	data.paidDate=$('#deleteLoanRecoveryDetailsPaidDate').val();
	data.receiptNo=$('#deleteLoanRecoveryDetailsReceiptNoId').val();
	data.remarks=$('#deleteLoanRecoveryDetailsRemarks').val();	
	
		
		
		$.ajax({
			type : "POST",			
			url : "deleteLoanRecoveryDetails",
			data : data,
			traditional : true,
			cache : false,	
			success : function(response) {
			
				obj=JSON.parse(response);
				
				if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
					messageDialog(obj.ERROR_MSG);
				} else if (obj.FINAL_RESULT_CODE == "400") {
						
						redirectingMessageWithURL(obj.DATA_DETAILS, "payLoanAmountForm?deptId="+data.deptId+"&cardNo="+data.cardNo);
					
				}
				
	},
			error : function(e) {
				

			}
		});
	}


function deleteLoanSanctionDetails(){	
	var pageId=$("#PAGE_ID").val();	
	var data={};
	data.deptId=$("#DEPT_ID").val();
	data.cardNo=$("#CARD_NO").val();
	data.loanId=$("#deleteLoanSanctionDetailsLoanId").val();
	data.memberId=$('#MEMBER_ID').val();	

	
		
		
		$.ajax({
			type : "POST",			
			url : "deleteLoanSanctionDetails",
			data : data,
			traditional : true,
			cache : false,	
			success : function(response) {
			
				obj=JSON.parse(response);
				
				if (obj.FINAL_RESULT_CODE == "200" || obj.FINAL_RESULT_CODE == "300") {
					messageDialog(obj.ERROR_MSG);
				} else if (obj.FINAL_RESULT_CODE == "400") {
						
						redirectingMessageWithURL(obj.DATA_DETAILS, "loanSummaryPage?deptId="+data.deptId+"&cardNo="+data.cardNo);
					
				}
				
	},
			error : function(e) {
				

			}
		});
	}



	function setSelectValueToTarget(sourceId, tragetid) {
	var sourceValue = $("#" + sourceId + " option").filter(":selected").val();

	if (sourceValue != null && sourceValue != "" && sourceValue != "SELECT") {

	$("#" + tragetid).val(sourceValue);
	}else{
		$("#" + tragetid).val(0);
	}
}