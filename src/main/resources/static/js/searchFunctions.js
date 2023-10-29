$(document).ready(function() {

	$("#registrationFormSumitButton").click(function(event) {
		// stop submit the form, we will post it manually.
		
		event.preventDefault();
		var recommond1UnitId = $("#recommand1_placeOfWorkId").val();
		var recommond2UnitId = $("#recommand2_placeOfWorkId").val();
		var approvedUnitId = $("#approvedDetails_placeOfWorkId").val();
		var paymentConfId = $("#regPaymentDetails_paymentTypeId").val();	
		var paymentConfIdOption=$( "#regPaymentDetails_paymentTypeId option:selected" ).text();
		$("#regPaymentDetails_paymentTypeIdOption").val(paymentConfIdOption);
		$("#recommand1_unitIdId").val(recommond1UnitId);
		$("#recommand2_unitIdId").val(recommond2UnitId);
		var natureOfWork=$("#regPaymentDetails_paymentTypeId").val();
		var placeOfWork=$("#regPaymentDetails_paymentTypeId").val();
		$("#approvedDetails_unitIdId").val(placeOfWork);
		var workDetails_deptName=$( "#workDetails_deptNameIdSelect" ).val();
		$("#workDetails_deptNameId").val(workDetails_deptName);
		var workDetails_placeOfWorkId=$( "#workDetails_placeOfWorkIdSelect" ).val();
		$("#workDetails_placeOfWorkId").val(workDetails_placeOfWorkId);
		// Get form
		var form = $('#registrationFormId')[0];
		// Create an FormData object
		var data = new FormData(form);
		 $("#loader").css("display","block");
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "registration",
			data : data,
			async:true,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 6000000,
			success : function(data) {
				
				var result = responseHandler(data);				
				if (result != null && result != "") {
					redirectingMessage(result);
				}

			},
			error : function(e) {
				
				$("#registrationFormSumitButton").prop("disabled", false);

			}
		});
	});
	
	$("#registrationUpdateButton").click(function(event) {
		// stop submit the form, we will post it manually.
		event.preventDefault();
		var recommond1UnitId = $("#recommand1_placeOfWorkId").val();
		var recommond2UnitId = $("#recommand2_placeOfWorkId").val();
		var approvedUnitId = $("#approvedDetails_placeOfWorkId").val();
		var paymentConfId = $("#regPaymentDetails_paymentTypeId").val();
		var paymentConfIdOption=$( "#regPaymentDetails_paymentTypeId option:selected" ).text();
		$("#regPaymentDetails_paymentTypeIdOption").val(paymentConfIdOption);
		$("#recommand1_unitIdId").val(recommond1UnitId);
		$("#recommand2_unitIdId").val(recommond2UnitId);
		$("#approvedDetails_unitIdId").val(approvedUnitId);	
		var workDetails_deptName=$( "#workDetails_deptNameIdSelect" ).val();
		$("#workDetails_deptNameId").val(workDetails_deptName);
		var workDetails_placeOfWorkId=$( "#workDetails_placeOfWorkIdSelect" ).val();
		$("#workDetails_placeOfWorkId").val(workDetails_placeOfWorkId);
		$("#DEPT_ID").val($("#deptNameId").val());		
		$("#DEPT_ID").val($("#deptNameId").val());		
		$("#recommand1_deptIdId").val($("#recommand1_deptNameId").val());		
		$("#recommand2_deptIdId").val($("#recommand2_deptNameId").val());
		// Get form
		var form = $('#updateMemberDetailsFormId')[0];
		// Create an FormData object
		var data = new FormData(form);
		 $("#loader").css("display","block");
		$.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "updateRegisteredMemeberDetails",
			data : data,
			async:true,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 6000000,
			success : function(data) {
			
				var result = responseHandler(data);				
				if (result != null && result != "") {
					redirectingMessage(result);
				}

			},
			
			error : function(e) {

			}
		});
	});

	
});

$(document).ajaxStart(function() {
    // show loader on start
    $("#loader").css("display","block");
}).ajaxSuccess(function() {
    // hide loader on success
    $("#loader").css("display","none");
});

$(function() {
	$("#commonSearchCardNo").autocomplete(
			{
				minLength : 1,
				maxResults: 5,
				source : function(request, response) {
					$.ajax({
						url : "getCardNumbersByDeptIdForAutocomplete",
						type : "POST",
						data : {
							deptId : $("#commonSeachDeptSelectId option")
									.filter(":selected").val(),
							term : request.term
						},
						success : function(data) {							
							 var filteredArray = $.map(JSON.parse(data), function(item) {
										item=""+item;if( item.startsWith(request.term)){							        	
							            return item;
							        }
							        else{
							            return null;
							        }							        
							    });
							 response(filteredArray); 
						}
					});
				}
			});

});





$(function() {
	$("#recommand1_cardNoId").autocomplete(
			{
				minLength : 1,
				maxResults: 5,
				source : function(request, response) {
					$.ajax({
						url : "getCardNumbersByDeptIdForAutocomplete",
						type : "POST",
						data : {
							deptId : $("#deptNameId option")
									.filter(":selected").val(),
							term : request.term
						},
						success : function(data) {
							
							
							 var filteredArray = $.map(JSON.parse(data), function(item) {
								 console.log("item "+item);
								 item=(" "+item).trim();
							        if( item.startsWith(request.term)){							        	
							            return item;
							        }
							        else{
							            return null;
							        }							        
							    });
							 response(filteredArray); 
						}
					});
				},
				select : function(event, ui) {
					$("#recommand1_cardNoId").val(ui.item.value);
					getRecomondationDetails('recommand1_deptNameId',
							'recommand1_cardNoId', 'recommand1_nameId',
							'recommand1_placeOfWorkId');
				}
			})._renderItem = function(ul, item) {
		$("#recommand1_cardNoId").val(item);
		return $("<li></li>").data("item.autocomplete", item);
	};

});

$(function() {
	$("#recommand2_cardNoId").autocomplete(
			{
				minLength : 1,
				maxResults: 5,
				source : function(request, response) {
					$.ajax({
						url : "getCardNumbersByDeptIdForAutocomplete",
						type : "POST",
						data : {
							deptId : $("#deptNameId option")
									.filter(":selected").val(),
							term : request.term
						},
						success : function(data) {							
							 var filteredArray = $.map(JSON.parse(data), function(item) {
								 item=(" "+item).trim();
							        if( item.startsWith(request.term)){							        	
							            return item;
							        }
							        else{
							            return null;
							        }							        
							    });
							 response(filteredArray); 
						}
					});
				},

				select : function(event, ui) {
					$("#recommand2_cardNoId").val(ui.item.value);
					getRecomondationDetails('recommand2_deptNameId',
							'recommand2_cardNoId', 'recommand2_nameId',
							'recommand2_placeOfWorkId');
				}

			})._renderItem = function(ul, item) {
		$("#recommand2_cardNoId").val(item);
		return $("<li></li>").data("item.autocomplete", item);
	};

});

function recommondGetCardNumbers() {

	var deptId = $("#deptNameId option").filter(":selected").val();
	if (deptId != null && deptId != "" && deptId != "SELECT") {
		$("#recommand1_deptNameId").val(deptId).change();
		$('#recommand1_deptNameId').attr("disabled", true);
		$('#recommand1_cardNoId').val("");
		$("#recommand2_deptNameId").val(deptId).change();
		$('#recommand2_deptNameId').attr("disabled", true);
		$('#recommand2_cardNoId').val("");
		$('#recommand1_nameId').val("");
		$('#recommand2_nameId').val("");
		$('#recommand1_deptIdId').val(deptId);
		$('#recommand2_deptIdId').val(deptId);
		$('#deptIdId').val(deptId);
		$('#approvedDetails_deptIdId').val(deptId);


		$.ajax({
			type : "POST",
			url : 'getPaymentConfDetails',
			data : {
				deptId : deptId
			},
			traditional : true,
			cache : false,
			success : function(response) {
				obj = JSON.parse(response);
			
				$("#PAYMENT_CONF_ID_id").val(obj.PAYMENT_CONF_ID);
				$("#MEMBERSHIP_AMOUNT_id").val(obj.MEMBERSHIP_AMOUNT);
				$("#ADMIN_AMOUNT_id").val(obj.ADMIN_AMOUNT);
				$("#SUBSCRIPTION_AMOUNT_id").val(obj.SUBSCRIPTION_AMOUNT);
				$("#DONATION_AMOUNT_id").val(obj.DONATION_AMOUNT);
				$("#approvedDetails_cardAmountId").val(obj.MEMBERSHIP_AMOUNT);
				$("#approvedDetails_cardAmountId").attr("disabled", true);

			},
			error : function(response) {
			}
		});

	}

}

function clearMemberDetails() {

	$("#memberNameId").val("");
	$("#fatherNameId").val("");
	$("#dateOfBirthId").val("");
	$("#eduQualificationId").val("");
	$("#motherTongueId").val("");
	$("#knownLangId").val("");
	$("#bloodGroupId").val("");
	$("#workDetails_fefsiNumberId").val("");
	$("#workDetails_dateOfJoiningId").val("");
	$("#workDetails_nameOfCompanyId").val("");
	$("#workDetails_natureOfWorkId").val("");
	$("#presentAddressId").val("");
	$("#perminentAddressId").val("");
	$("#approvedDetails_cardAmountId").val("");
	$("#approvedDetails_paidAmmountId").val("");
	$("#approvedDetails_cardNoId").val("");
	$("#motherTongueId").val("");
	$("#recommand1_nameId").val("");
	$("#recommand1_cardNoId").val("");
	$("#recommand2_nameId").val("");
	$("#recommand2_cardNoId").val("");
	$("#nomineeDetails_nameId").val("");
	$("#aadharCardNoId").val("");
	$("#approvedDetails_appliedDateId").val("");
	$("#phoneNo1Id").val("");
	$("#phoneNo2Id").val("");
	$("#bankDetails_bankNameId").val("");
	$("#bankDetails_accHolderNameId").val("");
	$("#bankDetails_accNumberId").val("");
	$("#bankDetails_branchId").val("");
	$("#bankDetails_ifscId").val("");
	$("#approvedDetails_receiptId").val("");
	$("#profilePicShowCase").attr("src", contextFromHeader+"/images/default-profile-pic.png");
	$("#deptNameId option[value='SELECT']").attr('selected', true);
	$("#deptIdId").val("");
	$("#approvedDetails_placeOfWorkId option[value='SELECT']").attr('selected',
			true);
	$("#approvedDetails_unitId").val("");

	$("#recommand1_deptNameId option[value='SELECT']").attr('selected', true);
	$("#recommand1_deptIdId").val("");
	$("#recommand1_placeOfWorkId option[value='SELECT']")
			.attr('selected', true);
	$("#recommand1_unitIdId").val("");

	$("#recommand2_deptNameId option[value='SELECT']").attr('selected', true);
	$("#recommand2_deptIdId").val("");
	$("#recommand2_placeOfWorkId option[value='SELECT']")
			.attr('selected', true);
	$("#recommand2_unitIdId").val("");

	$("#nomineeDetails_relationId option[value='SELECT']").attr('selected',
			true);

}

function commonSearchGetCardNumbersByDeptId() {

	var deptId = $("#commonSeachDeptSelectId option").filter(":selected").val();
	if (deptId != null && deptId != "" && deptId != "SELECT") {
		$('#commonSearchCardNo').attr("disabled", false);
		$('#commonSearchCardNo').val("");
		var pageId = $("#PAGE_ID").val();
		$.ajax({
			type : "POST",
			url : 'getCardNumbersByDeptId',
			data : {
				deptId : deptId,
				pageId : pageId
			},
			traditional : true,
			cache : false,
			success : function(response) {
				commonSearchCardNumbers = JSON.parse(response);
				
			},
			error : function(response) {
			}
		});

	} else {

		$('#commonSearchCardNo').val("");
		$('#commonSearchCardNo').attr("disabled", true);

		messageDialog("Please Select Department first!");

	}

}

function checkForValidDeptSelectd() {
	var deptId = $("#commonSeachDeptSelectId option").filter(":selected").val();
	if (deptId != null && deptId != "" && deptId != "SELECT") {

	} else {

		$('#commonSearchCardNo').val("");
		$('#commonSearchCardNo').attr("disabled", true);

		messageDialog("Please Select Department first!");
		$("#commonSeachDeptSelectId").click();

	}
}

function commonSearchCardNoAutocomplete() {

	$('#commonSearchCardNo').autocomplete({
		minLength : 1,
		maxResults : 5,
		source :  function(data) {							
			 var filteredArray = $.map(availableTags, function(item) {
			        if( item.startsWith(request.term)){							        	
			            return item;
			        }
			        else{
			            return null;
			        }							        
			    });
			 response(filteredArray); 
		},

		select : function(event, ui) {
			$("#commonSearchCardNo").val(ui.item.value);
		}
	});

}

function getSearchDetails() {

	
	var deptId = $("#commonSeachDeptSelectId option").filter(":selected").val();
	
	if (deptId != null && deptId != "" && deptId != "SELECT") {
		var cardNo = $("#commonSearchCardNo").val();
		if (cardNo != null && cardNo != "" && !isNaN(cardNo)) {	
		var pageId = $("#PAGE_ID").val();
		var memberId = $("#memberId").val();
		var deptId = $("#commonSeachDeptSelectId option").filter(":selected").val();		
		if (pageId != null && pageId != "" && pageId == "UPDATE_MEMBER_DETAILS") {
			clearMemberDetails();
		}
		if (cardNo == null || cardNo == "") {
		} else if (cardNo == null || cardNo == "") {

		} else {
			
			$.ajax({
						type : "POST",
						url : 'getCommonSearchResults',
						async:true,
						data : {
							deptId : deptId,
							cardNo : cardNo,
							pageId : pageId

						},
						traditional : true,
						cache : false,
						 async: true,
						 beforeSend: function () {
						        $('#loading').show();
						        
						        window.setTimeout(function(){
					                  }, 6000);
						    },
						    complete: function () {
						        $("#loading").hide();
						    },
						    success : function(response) {
							
							obj = JSON.parse(response);	
							var finalResul = obj.FINAL_RESULT_CODE;
							if (obj.FINAL_RESULT_CODE != null
									&& obj.FINAL_RESULT_CODE != "") {

								if (obj.FINAL_RESULT_CODE == "200"
										|| obj.FINAL_RESULT_CODE == "300") {
									messageDialog(obj.ERROR_MSG);

								} else if (obj.FINAL_RESULT_CODE == "400") {
									
									var finalResultWrapObj = obj.FINAL_RESULT;
									var innterResultCode = finalResultWrapObj.FINAL_RESULT_CODE;
									var pageId = finalResultWrapObj.PAGE_ID;
									if (innterResultCode == "300") {
										
										messageDialog(finalResultWrapObj.ERROR_MSG);

									} else if (innterResultCode == "200" || innterResultCode == "400") {										

										if (pageId != null && pageId != "" && pageId == "CARD_BALANCE_PAYMENT") {
											setTopPanelDetails(finalResultWrapObj);										
											
											$("#searchDetails").empty();
											$("#searchDetails").append(finalResultWrapObj.CARD_BALANCE_PAYMENT);											
											$('#paymentConfIdId').append(finalResultWrapObj.PAYMENT_CONF_ID);
											$("#memberId").val(finalResultWrapObj.MEMBER_ID);										

										} else if (pageId != null && pageId != "" && pageId == "PAY_SUBSCRIPTION_AMOUNT") {
											
											setTopPanelDetails(finalResultWrapObj);
											$("#searchDetails").empty();
											$("#searchDetails").append(finalResultWrapObj.PAY_SUBSCRIPTION_AMOUNT);;
											$('#paySubscription_subscriptionYear').append(finalResultWrapObj.SUBSCRIPTION_YEAR);
											$('#paySubscription_paymentConfId').append(finalResultWrapObj.PAYMENT_CONF_ID);											
										}
										if (pageId != null && pageId != "" && pageId == "GET_SCUBSRIPTION_DETAILS") {

											setTopPanelDetails(finalResultWrapObj);
											$("#searchDetails").empty();
											$("#searchDetails").append( finalResultWrapObj.GET_SCUBSRIPTION_DETAILS);

										} else if (pageId != null && pageId != "" && pageId == "LOAN_SANCTION") {
											setTopPanelDetails(finalResultWrapObj);
											$("#searchDetails").empty();
											$("#searchDetails").append(finalResultWrapObj.LOAN_SANCTION);
										} else if (pageId != null && pageId != "" && pageId == "LOAN_PAYMENT") {
											setTopPanelDetails(finalResultWrapObj);
											$("#searchDetails").empty();
											$("#searchDetails").append(finalResultWrapObj.LOAN_PAYMENT);
										} else if (pageId != null && pageId != "" && (pageId == "UPDATE_MEMBER_DETAILS")) {
											$("#MEMBER_ID").val(finalResultWrapObj.MEMBER_ID);
												var resultcode = finalResultWrapObj.FINAL_RESULT_CODE;
												clearMemberDetails();
												if (resultcode == "400") {
													displayRegisteredMemberDetails(finalResultWrapObj.UPDATE_MEMBER_DETAILS);
												} else if (resultcode == "200") {
													messageDialog(finalResultWrapObj.ERROR_MSG);
												}
										}else if (pageId != null && pageId != "" && (pageId == "LOAN_SUMMARY")) {
											setTopPanelDetails(finalResultWrapObj);
										
											
											$("#searchDetails1").empty();
											$("#searchDetails2").empty();
											$("#searchDetails1").append(finalResultWrapObj.LOAN_DETAILS);
											$("#searchDetails2").append(finalResultWrapObj.LOAN_SUMMARY);
											
											
										}

									}

								}

							}

						},
						
						error : function(response) {
							
						}
					});
		}
	}else{
		
		messageDialog("Please enter valid Card Number and press search");
		$('#commonSearchCardNo').val("");
		
	}

	} else {

		$('#commonSearchCardNo').val("");
		$('#commonSearchCardNo').attr("disabled", true);
		messageDialog("Please Select Department first!");
		$("#commonSeachDeptSelectId").click();

	}
}
function setTopPanelDetails(topPanelDetailsTotalObject) {
	obj=topPanelDetailsTotalObject;
	
	var resultCode=topPanelDetailsTotalObject.TOP_PANEL_DETAILS.TOP_PANEL_RESULT_CODE;
	
	if(resultCode!=null && resultCode!="" && resultCode!="undefined"){	
		
		if( resultCode=="300"  ||  resultCode=="200"){	
			messageDialog(obj.ERROR_MSG);
			clearTopPanel();
		}else if( resultCode=="400" ){
			
			var topPanelDetails = topPanelDetailsTotalObject.TOP_PANEL_DETAILS.TOP_PANEL_DETAILS;
			if (topPanelDetails != null && topPanelDetails != "") {			
				if (topPanelDetails != null && topPanelDetails != "") {
					$("#tpnl_memberName").val(topPanelDetails.FIRST_NAME);
					
					$("#tpnl_deparmentName").val(topPanelDetails.DEPT_NAME);
					$("#tpnl_deptId").val(topPanelDetails.DEPT_ID);
					$("#tpnl_loanBalance").val(topPanelDetails.CURRENT_LOAN_BALANCE);
					$("#tpnl_cardBalance").val(topPanelDetails.CARD_BALANCE);
					$("#tpnl_cardNo").val(topPanelDetails.CARD_NO);
					$("#tpnl_phoneNo").val(topPanelDetails.PHONE_NO);
					$("#tpnl_dateOfJoining").val(topPanelDetails.REGISTERED_DATE);
					$("#tpnl_address").val(topPanelDetails.PERMINENT_ADDRESS);
					$("#memberId").val(topPanelDetails.MEMBER_ID);
					$("#tpnl_profilePic").attr("src", topPanelDetails.FILE_CONTENT);
					$("#MEMBER_ID").val( topPanelDetails.MEMBER_ID);
					$("#tpnl_receiptNo").val( topPanelDetails.PAYMENT_RECEIPT_NO);
					//
					$("#tpnl_fatherName").val( topPanelDetails.FATHER_NAME);
				}
			}else{
				
				messageDialog("Sorry unable to fetch toppanel1!");
				clearTopPanel();
			}
			
		}
		
	}else{
		messageDialog("Sorry unable to fetch toppanel2!");		
		clearTopPanel();
		
	}	

}

function clearTopPanel(){
		$("#tpnl_memberName").val("");
		$("#tpnl_cardBalance").val("");
		$("#tpnl_deparmentName").val("");
		$("#tpnl_deptId").val("");
		$("#tpnl_loanBalance").val("");
		$("#tpnl_cardNo").val("");
		$("#tpnl_phoneNo").val("");
		$("#tpnl_dateOfJoining").val("");
		$("#tpnl_address").val("");
		$("#memberId").val("");
		$("#tpnl_profilePic").attr("src", "");
		$("#MEMBER_ID").val("");	
}

function getRecomondationDetails(deptIdSelectId, cardNoInputId, nameInputId,
		unitidSelectId) {
	
	var cardNo = $("#" + cardNoInputId).val();
	var deptId = $("#" + deptIdSelectId + "  option").filter(":selected").val();
	

	$.ajax({
		type : "POST",
		url : 'getMemberDetailsForRecomondation',
		data : {
			deptId : deptId,
			cardNo : cardNo

		},
		traditional : true,
		cache : false,
		async : false,
		success : function(response) {

			var result = responseHandler(response);

			if (result != null && result != "") {

				$("#" + nameInputId).val(result.NAME);
				
				$("#" + unitidSelectId).val(result.UNIT_ID).change();
			}

		},
		error : function(response) {
			

		}
	});
}

function responseHandler(response) {

	if (response != null && response != "") {
		obj = JSON.parse(response);

		var resultcode = obj.FINAL_RESULT_CODE;
		

		if (resultcode != null && resultcode != "") {

			if (resultcode == "200" || resultcode == "300") {
				
				messageDialog(obj.ERROR_MSG);
				return "";

			} else {
				return obj.DATA_DETAILS;

			}

		} else {

			messageDialog(response);
			return "";

		}

	} else {

		messageDialog(response);
		return "";

	}
}

function displayRegisteredMemberDetails(result) {
	$("#memberNameId").val(result.FIRST_NAME);
	$("#fatherNameId").val(result.FATHER_NAME);
	$("#dateOfBirthId").val(result.DATE_OF_BIRTH);
	$("#eduQualificationId").val(result.EDU_QUALIFICATION);
	$("#motherTongueId").val(result.MOTHER_TONGUE);
	$("#knownLangId").val(result.KNOWN_LANGUAGES);
	$("#bloodGroupId").val(result.BLOOD_GROUP);
	$("#workDetails_fefsiNumberId").val(result.FEFSI_MEMBER);
	$("#workDetails_dateOfJoiningId").val(result.REGISTERED_DATE);	
	$("#presentAddressId").val(result.PRESENT_ADDRESS);
	$("#perminentAddressId").val(result.PERMINENT_ADDRESS);
	$("#approvedDetails_cardAmountId").val(result.CARD_AMOUNT);
	$("#approvedDetails_paidAmmountId").val(result.PAID_AMOUNT);
	$("#approvedDetails_cardNoId").val(result.CARD_NO);
	$("#motherTongueId").val(result.MOTHER_TONGUE);
	$("#recommand1_nameId").val(result.RECOMMEND1_NAME);
	$("#recommand1_cardNoId").val(result.RECOMMEND1_CARD_NO);
	$("#recommand2_nameId").val(result.RECOMMEND2_NAME);
	$("#recommand2_cardNoId").val(result.RECOMMEND2_CARD_NO);
	$("#nomineeDetails_nameId").val(result.NOMINIE);
	$("#aadharCardNoId").val(result.AADHAR_CARD_NO);
	$("#approvedDetails_appliedDateId").val(result.APPLIED_DATAE);
	$("#phoneNo1Id").val(result.PHONE_NO);
	$("#phoneNo2Id").val(result.ALT_PHONE_NO);
	$("#bankDetails_bankNameId").val(result.BANK_NAME);
	$("#bankDetails_accHolderNameId").val(result.BANK_ACC_HOLDER_NAME);
	$("#bankDetails_accNumberId").val(result.BANK_ACC_NO);
	$("#bankDetails_branchId").val(result.BANK_BRANCH);
	$("#bankDetails_ifscId").val(result.BANK_IFSC_CODE);

	$("#approvedDetails_receiptId").val(result.RECEIPT_NO);

	$("#profilePicShowCase").attr("src", result.FILE_CONTENT);

	$("#deptNameId option[value=" + result.DEPT_ID + "]")
			.attr('selected', true);
	$("#deptIdId").val(result.DEPT_ID);

	$("#approvedDetails_placeOfWorkId option[value=" + result.UNIT_ID + "]")
			.attr('selected', true);
	$("#approvedDetails_unitId").val(result.UNIT_ID);

	$("#recommand1_deptNameId option[value=" + result.RECOMMEND1_DEPT_ID + "]")
			.attr('selected', true);
	$("#recommand1_deptIdId").val(result.RECOMMEND1_DEPT_ID);
	$("#recommand1_placeOfWorkId option[value="+ result.RECOMMEND1_UNIT_ID + "]").attr('selected', true);
	
	$("#recommand1_unitIdId").val(result.RECOMMEND1_UNIT_ID);
	$("#recommand2_deptNameId option[value=" + result.RECOMMEND2_DEPT_ID + "]")
			.attr('selected', true);
	$("#recommand2_deptIdId").val(result.RECOMMEND2_DEPT_ID);
	$(
			"#recommand2_placeOfWorkId option[value="
					+ result.RECOMMEND2_UNIT_ID + "]").attr('selected', true);
	$("#recommand2_unitIdId").val(result.RECOMMEND2_UNIT_ID);

	$("#nomineeDetails_relationId option[value="
					+ result.RELATION_WITH_NOMINIE + "]")
			.attr('selected', true);
	 $("#nomineeDetails_relationId").val(result.RELATION_WITH_NOMINIE);
	$("#MEMBER_ID").val(result.MEMBER_ID);
	$("#DEPT_ID").val(result.DEPT_ID);
	
	$("#RECEIPT_NO_id").val(result.RECEIPT_NO);
	
	$("#workDetails_nameOfCompanyId").val(result.NAME_OF_COMPANY);
	$("#workDetails_natureOfWorkId").val(result.NATURE_OF_WORK);
	
	$("#workDetails_deptNameIdSelect option[value="+ result.NATURE_OF_WORK + "]")
	.attr('selected', true);
	
	$("#workDetails_placeOfWorkIdSelect option[value="
			+ result.NAME_OF_COMPANY + "]")
	.attr('selected', true);
	
}
function viewRegisteredMemberDetails(member) {

	
	$.ajax({
		type : "POST",
		url : "viewMemberDetails",

		data : {

			'memberId' : member
		},
		cache : false,
		timeout : 6000000,
		success : function(response) {
			
			
			var result = responseHandler(response);
			if (result != null && result != "") {

				$("#memberNameId").val(result.FIRST_NAME);
				$("#fatherNameId").val(result.FATHER_NAME);
				$("#dateOfBirthId").val(result.DATE_OF_BIRTH);
				$("#eduQualificationId").val(result.EDU_QUALIFICATION);
				$("#motherTongueId").val(result.MOTHER_TONGUE);
				$("#knownLangId").val(result.KNOWN_LANGUAGES);
				$("#bloodGroupId").val(result.BLOOD_GROUP);
				$("#workDetails_fefsiNumberId").val(result.FEFSI_MEMBER);
				$("#workDetails_dateOfJoiningId").val(result.REGISTERED_DATE);

				$("#workDetails_nameOfCompanyId").val(result.NAME_OF_COMPANY);
				$("#workDetails_natureOfWorkId").val(result.NATURE_OF_WORK);

				$("#presentAddressId").val(result.PRESENT_ADDRESS);
				$("#perminentAddressId").val(result.PERMINENT_ADDRESS);

				$("#approvedDetails_cardAmountId").val(result.CARD_AMOUNT);
				$("#approvedDetails_paidAmmountId").val(result.PAID_AMOUNT);
				$("#approvedDetails_cardNoId").val(result.CARD_NO);

				$("#motherTongueId").val(result.MOTHER_TONGUE);
				$("#recommand1_nameId").val(result.RECOMMEND1_NAME);
				$("#recommand1_cardNoId").val(result.RECOMMEND1_CARD_NO);

				$("#recommand2_nameId").val(result.RECOMMEND2_NAME);
				$("#recommand2_cardNoId").val(result.RECOMMEND2_CARD_NO);

				$("#nomineeDetails_nameId").val(result.NOMINIE);

				$("#aadharCardNoId").val(result.AADHAR_CARD_NO);

				$("#approvedDetails_appliedDateId").val(result.APPLIED_DATAE);
				$("#phoneNo1Id").val(result.PHONE_NO);
				$("#phoneNo2Id").val(result.ALT_PHONE_NO);

				$("#bankDetails_bankNameId").val(result.BANK_NAME);
				$("#bankDetails_accHolderNameId").val(
						result.BANK_ACC_HOLDER_NAME);
				$("#bankDetails_accNumberId").val(result.BANK_ACC_NO);
				$("#bankDetails_branchId").val(result.BANK_BRANCH);
				$("#bankDetails_ifscId").val(result.BANK_IFSC_CODE);

				$("#approvedDetails_receiptId").val(result.RECEIPT_NO);
				
				$("#profilePicShowCase").attr("src", result.FILE_CONTENT);

				$("#deptNameId option[value=" + result.DEPT_ID + "]").attr(
						'selected', true);
				$("#deptIdId").val(result.DEPT_ID);

				$(
						"#approvedDetails_placeOfWorkId option[value="
								+ result.UNIT_ID + "]").attr('selected', true);
				$("#approvedDetails_unitId").val(result.UNIT_ID);

				$(
						"#recommand1_deptNameId option[value="
								+ result.RECOMMEND1_DEPT_ID + "]").attr(
						'selected', true);
				$("#recommand1_deptIdId").val(result.RECOMMEND1_DEPT_ID);
				$(
						"#recommand1_placeOfWorkId option[value="
								+ result.RECOMMEND1_UNIT_ID + "]").attr(
						'selected', true);
				$("#recommand1_unitIdId").val(result.RECOMMEND1_UNIT_ID);

				$(
						"#recommand2_deptNameId option[value="
								+ result.RECOMMEND2_DEPT_ID + "]").attr(
						'selected', true);
				$("#recommand2_deptIdId").val(result.RECOMMEND2_DEPT_ID);
				$(
						"#recommand2_placeOfWorkId option[value="
								+ result.RECOMMEND2_UNIT_ID + "]").attr(
						'selected', true);
				$("#recommand2_unitIdId").val(result.RECOMMEND2_UNIT_ID);

				$(
						"#nomineeDetails_relationId option[value="
								+ result.RELATION_WITH_NOMINIE + "]").attr(
						'selected', true);

			}

		},
		error : function(e) {
			

		}
	});

}
function regPaymentMode(sourceId,targetId){
	
	var pymentMode = $("#"+sourceId).val();		

	if (pymentMode != null	&& pymentMode != "" && pymentMode == "DEMAND DRAFT") {
		$("#"+targetId).val("");
		$("#"+targetId).attr("disabled",false);
		} else if (pymentMode != null && pymentMode != "" && pymentMode == "CASH") {
			$("#"+targetId).val("");
			$("#"+targetId).attr("disabled",true);
		}
	}

function fetchRequestParams(){
	
	var urlParams = location.search.split(/[?&]/).slice(1).map(function(paramPair) {
        return paramPair.split(/=(.+)?/).slice(0, 2);
    }).reduce(function (obj, pairArray) {            
        obj[pairArray[0]] = pairArray[1];
        return obj;
    }, {});
	
	return urlParams;
	
}

function getLoanSummary(){

$.ajax({
	type : "POST",
	enctype : 'multipart/form-data',
	url : "getLoanSummary",		
	async:true,
	processData : false,
	contentType : false,
	cache : false,
	timeout : 6000000,
	success : function(data) {
		
		console.log(data);
		$('#LoanDetailedSummary').empty().prepend(data);
		

	},
	
	error : function(e) {
		
		

	}
});

}

function getAllBalanceDetails() {

	
	var deptId = $("#commonSeachDeptSelectId option").filter(":selected").val();
	
	if (deptId != null && deptId != "" && deptId != "SELECT") {
		var cardNo = $("#commonSearchCardNo").val();
		if (cardNo != null && cardNo != "" && !isNaN(cardNo)) {	
		var pageId = $("#PAGE_ID").val();
		var memberId = $("#memberId").val();
		var deptId = $("#commonSeachDeptSelectId option").filter(":selected").val();		
		if (pageId != null && pageId != "" && pageId == "UPDATE_MEMBER_DETAILS") {
			clearMemberDetails();
		}
		if (cardNo == null || cardNo == "") {
		} else if (cardNo == null || cardNo == "") {

		} else {
			
			$.ajax({
						type : "POST",
						url : 'getCommonSearchResults',
						async:true,
						data : {
							deptId : deptId,
							cardNo : cardNo,
							pageId : "CARD_BALANCE_PAYMENT"

						},
						traditional : true,
						cache : false,
						async: true,
						beforeSend: function () {
						        $('#loading').show();
						        window.setTimeout(function(){}, 6000);
						    },
						    complete: function () {
						        $("#loading").hide();
						    },
						    success : function(response) {setResponse(response);
						},
						
						error : function(response) {
							
						}
					});
					
								$.ajax({
						type : "POST",
						url : 'getCommonSearchResults',
						async:true,
						data : {
							deptId : deptId,
							cardNo : cardNo,
							pageId : "GET_SCUBSRIPTION_DETAILS"

						},
						traditional : true,
						cache : false,
						async: true,
						beforeSend: function () {
						        $('#loading').show();
						        window.setTimeout(function(){}, 6000);
						    },
						    complete: function () {
						        $("#loading").hide();
						    },
						    success : function(response) {setResponse(response);
						},
						
						error : function(response) {
							
						}
					});
					
					
					
						$.ajax({
						type : "POST",
						url : 'getCommonSearchResults',
						async:true,
						data : {
							deptId : deptId,
							cardNo : cardNo,
							pageId : "LOAN_SUMMARY"

						},
						traditional : true,
						cache : false,
						async: true,
						beforeSend: function () {
						        $('#loading').show();
						        window.setTimeout(function(){}, 6000);
						    },
						    complete: function () {
						        $("#loading").hide();
						    },
						    success : function(response) {setResponse(response);
						},
						
						error : function(response) {
							
						}
					});
		}
	}else{
		
		messageDialog("Please enter valid Card Number and press search");
		$('#commonSearchCardNo').val("");
		
	}

	} else {

		$('#commonSearchCardNo').val("");
		$('#commonSearchCardNo').attr("disabled", true);
		messageDialog("Please Select Department first!");
		$("#commonSeachDeptSelectId").click();

	}
}

function setResponse(response){




							
							obj = JSON.parse(response);	
							var finalResul = obj.FINAL_RESULT_CODE;
							if (obj.FINAL_RESULT_CODE != null
									&& obj.FINAL_RESULT_CODE != "") {

								if (obj.FINAL_RESULT_CODE == "200"
										|| obj.FINAL_RESULT_CODE == "300") {
									messageDialog(obj.ERROR_MSG);

								} else if (obj.FINAL_RESULT_CODE == "400") {
									
									var finalResultWrapObj = obj.FINAL_RESULT;
									var innterResultCode = finalResultWrapObj.FINAL_RESULT_CODE;
									var pageId = finalResultWrapObj.PAGE_ID;
									if (innterResultCode == "300") {
										
										messageDialog(finalResultWrapObj.ERROR_MSG);

									} else if (innterResultCode == "200" || innterResultCode == "400") {										

										if (pageId != null && pageId != "" && pageId == "CARD_BALANCE_PAYMENT") {
											setTopPanelDetails(finalResultWrapObj);										
											
											$("#MembershipAmountPaymentHistory_SearchDetails").empty();
											$("#MembershipAmountPaymentHistory_SearchDetails").append(finalResultWrapObj.CARD_BALANCE_PAYMENT);											
											$("#memberId").val(finalResultWrapObj.MEMBER_ID);										

										} else if (pageId != null && pageId != "" && pageId == "GET_SCUBSRIPTION_DETAILS") {

											setTopPanelDetails(finalResultWrapObj);
											$("#Subscription_SearchDetails").empty();
											$("#Subscription_SearchDetails").append( finalResultWrapObj.GET_SCUBSRIPTION_DETAILS);

										} else if (pageId != null && pageId != "" && (pageId == "LOAN_SUMMARY")) {
											setTopPanelDetails(finalResultWrapObj);
											$("#LoanSanctioned_SearchDetails").empty();
											$("#LoanRecovery_SearchDetails").empty();
											$("#LoanSanctioned_SearchDetails").append(finalResultWrapObj.LOAN_DETAILS);
											$("#LoanRecovery_SearchDetails").append(finalResultWrapObj.LOAN_SUMMARY);
											
											
										}

									}

								}

							}



}
