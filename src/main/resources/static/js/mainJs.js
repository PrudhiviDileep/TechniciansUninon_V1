var availableTags = [];
$(document).ready(function() {

});
$(function() {

	$("#img").change(function() {
		readURL(this);
	});

});
$(function() {
	// $( ".datepicker" ).datepicker({
	// showOtherMonths: true,
	// selectOtherMonths: true
	// });

	$(".datepicker").datepicker({
		dateFormat : 'dd/mm/yy'
	});
	$(".currentDateDatepicker").datepicker({
		dateFormat : 'dd/mm/yy'
	}).datepicker("setDate", "0");
});

function getCardNumbersByDeptId() {
	$('input#myInput').attr("disable", false);
	$('input#myInput').val("");
	$.ajax({
		type : "POST",
		url : 'getCardNumbersByDeptId',
		data : {
			deptId : "CAMERA"
		},
		traditional : true,
		cache : false,
		// async: false,
		success : function(response) {
			availableTags = JSON.parse(response);
			

		},
		error : function(response) {
		}
	});
}

function cardNoAutocomplete() {
//	alert("calling autocomplet *******" + availableTags);
	$('input#myInput').autocomplete({
		minLength : 1,
		maxResults : 5,
		source : function(data) {							
//			response(JSON.parse(data));	
			
//			alert("caling auto");
			 var filteredArray = $.map(JSON.parse(availableTags), function(item) {
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
			$("input#myInput").val(ui.item.value);
			getEmployeeDetails();
		}
	});

}

function onCardNoCahnge() {

	getEmployeeDetails();

}

function getDetails() {


	var deptId = $("#getDetailsDeptSelectId option").filter(":selected").val();
	var action = $("#selectActionId option").filter(":selected").val();
	
	
	var orderBy = "";
	if(action=='CARD_BALANCE_DETAILS'){
		console.log(deptId);
		orderBy = $("#ORDER_BY_CARD_BALANCE_DETAILS option").filter(":selected").val();
		
		$('#ORDER_BY_CONTACT_DETAILS').css('display','none');
		$('#ORDER_BY_LOAN_BALANCE_DETAILS').css('display','none');
		$('#ORDER_BY_BANK_DETAILS').css('display','none');
		$('#ORDER_BY_SUBSCRIPTION_BALANCE').css('display','none');
		$('#ORDER_BY_CARD_BALANCE_DETAILS').css('display','block');
		
		
	}else if(action=='CONTACT_DETAILS'){
		console.log(deptId);
		orderBy = $("#ORDER_BY_CONTACT_DETAILS option").filter(":selected").val();
		$('#ORDER_BY_CARD_BALANCE_DETAILS').css('display','none');
		$('#ORDER_BY_LOAN_BALANCE_DETAILS').css('display','none');
		$('#ORDER_BY_BANK_DETAILS').css('display','none');
		$('#ORDER_BY_SUBSCRIPTION_BALANCE').css('display','none');
		$('#ORDER_BY_CONTACT_DETAILS').css('display','block');
	
		
	}else if(action=='LOAN_BALANCE_DETAILS'){
		console.log(deptId);
		orderBy = $("#ORDER_BY_LOAN_BALANCE_DETAILS option").filter(":selected").val();
		$('#ORDER_BY_CARD_BALANCE_DETAILS').css('display','none');
		$('#ORDER_BY_CONTACT_DETAILS').css('display','none');
		$('#ORDER_BY_BANK_DETAILS').css('display','none');
		$('#ORDER_BY_SUBSCRIPTION_BALANCE').css('display','none');
		$('#ORDER_BY_LOAN_BALANCE_DETAILS').css('display','block');
		
		
	}else if(action=='BANK_DETAILS'){
		console.log(deptId);
		orderBy = $("#ORDER_BY_BANK_DETAILS option").filter(":selected").val();
		$('#ORDER_BY_CARD_BALANCE_DETAILS').css('display','none');
		$('#ORDER_BY_LOAN_BALANCE_DETAILS').css('display','none');
		$('#ORDER_BY_CONTACT_DETAILS').css('display','none');
		$('#ORDER_BY_SUBSCRIPTION_BALANCE').css('display','none');
		$('#ORDER_BY_BANK_DETAILS').css('display','block');
		
		
		
	}else if(action=='SUBSCRIPTION_BALANCE'){
		
		orderBy = $("#ORDER_BY_SUBSCRIPTION_BALANCE option").filter(":selected").val();
		$('#ORDER_BY_CARD_BALANCE_DETAILS').css('display','none');
		$('#ORDER_BY_LOAN_BALANCE_DETAILS').css('display','none');
		$('#ORDER_BY_BANK_DETAILS').css('display','none');
		$('#ORDER_BY_CONTACT_DETAILS').css('display','none');
		$('#ORDER_BY_SUBSCRIPTION_BALANCE	').css('display','block');
		
		
	}

	
	var sortBy = $("#SORT_TYPE option").filter(":selected").val();
	

	$.ajax({
		type : "POST",
		url : 'getDetialsBySelectAtion',
		data : {
			deptId : deptId,
			action : action,
			orderBy : orderBy,
			sortBy : sortBy
			

		},
		traditional : true,
		cache : false,
		// async: false,
		success : function(response) {
			$('#searchDetails').empty();
			$('#searchDetails').append(response);

		},
		error : function(response) {
		}
	});

}

function readURL(input) {
	
	var ext = input.files[0]['name'].substring(
			input.files[0]['name'].lastIndexOf('.') + 1).toLowerCase();
	if (input.files && input.files[0]
			&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#profilePicShowCase').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	} else {
		messageDialog("Please Select Valid Format Image");
		$('#profilePicShowCase').attr('src', contextFromHeader+"/images/default-profile-pic.png");

	}
	;
}

function messageDialog(message) {

	$("#logoutDailog")
			.html(
					"<div style='margin-left: 5%;'><div style='float:left'><img height='30px' src='images/help.png'></img></div><div style='float:left;margin-left: 20px;width:80%'>"
							+ message + "</div></div>");
	$("#logoutDailog").dialog(
			{
				title : 'Message',
				modal : true,
				width : 600,
				height : 235,
				fluid : true,
				buttons : {
					Ok : function() {
						$(this).html("");
						$(this).dialog("close");
						$(this).dialog("destroy");
						// navigationMenuUrl('timeout');
						// window.location.href = "timeout";

					}
				},
				open : function() {
					$(this).closest(".ui-dialog").find(".ui-button").eq(1)
							.addClass("dialogyes");
					$(".visionHeaderMain").css("z-index", "999");
					$(".visionFooterMain").css("z-index", "999");
				},
				beforeClose : function(event, ui) {
					$(".visionHeaderMain").css("z-index", "99999");
					$(".visionFooterMain").css("z-index", "99999");
				}
			});

}

function conformationMessage(message, pageId) {
	var flag = false;

	if (message != null && message != 'undefined') {

		$("#logoutDailog")
				.html(
						"<div style='margin-left: 5%;'><div style='float:left'><img height='30px' src='images/help.png'></img></div><div style='float:left;margin-left: 20px;width:80%'>"
								+ message + "</div></div>");
		$("#logoutDailog").dialog({
			title : 'Message',
			modal : true,
			width : 600,
			height : 235,
			fluid : true,
			buttons : {
				ok : function() {
					flag = true;
					$(this).html("");
					$(this).dialog("close");
					$(this).dialog("destroy");
					// window.location.href =
					// "displayMemberDetails?memberId="+dataObject.MEBER_ID;
				},
				cancel : function() {
					flag = false;
					$(this).html("");
					$(this).dialog("close");
					$(this).dialog("destroy");

				}
			},
			open : function() {

			},
			beforeClose : function(event, ui) {
				event.preventDefault();
				if (flag) {
					if (pageId == "DELETE_SUBSCRIPTION_PAYMENT") {

						deleteSubscription();
					} else if (pageId == "DELETE_CARD_BALANCE_PAYMENT") {
						deleteMembershipPayment();
					}else if(pageId == "DELETE_LOAN_PAYMENT"){
						
						deleteLoanRecoveryDetails();
					}else if(pageId == "DELETE_LOAN_SANCTION"){
						
						deleteLoanSanctionDetails();
					}

				}
			}
		});

	}

}

function redirectingMessage(dataObject) {

	if (dataObject != null && dataObject != 'undefined') {

		dataObject.REGISTRATION_RESULT;
		dataObject.MEBER_ID;
		dataObject.MEBER_DEPT_ID;
		dataObject.MEBER_CARD_NO;

		$("#logoutDailog")
				.html(
						"<div style='margin-left: 5%;'><div style='float:left'><img height='30px' src='images/help.png'></img></div><div style='float:left;margin-left: 20px;width:80%'>"
								+ dataObject.REGISTRATION_RESULT
								+ " Do You want to see the registerd member details !</div></div>");
		$("#logoutDailog")
				.dialog(
						{
							title : 'Message',
							modal : true,
							width : 600,
							height : 235,
							fluid : true,
							buttons : {
								Ok : function() {
									$(this).html("");
									$(this).dialog("close");
									$(this).dialog("destroy");
									// navigationMenuUrl('timeout');
									// window.location.href = "timeout";
//									alert("MEBER_ID " + dataObject.MEBER_ID);
									// viewRegisteredMemberDetails(dataObject.MEBER_ID);
									window.location.href = "updateMemberDetails?deptId="
											+ dataObject.MEBER_DEPT_ID+"&cardNo="+dataObject.MEBER_CARD_NO;

								},
								cancel : function() {
									$(this).html("");
									$(this).dialog("close");
									$(this).dialog("destroy");
									// navigationMenuUrl('timeout');
									// window.location.href = "timeout";

								}
							},
							open : function() {
								$(this).closest(".ui-dialog")
										.find(".ui-button").eq(1).addClass(
												"dialogyes");
								$(".visionHeaderMain").css("z-index", "999");
								$(".visionFooterMain").css("z-index", "999");
							},
							beforeClose : function(event, ui) {
								$(".visionHeaderMain").css("z-index", "99999");
								$(".visionFooterMain").css("z-index", "99999");
							}
						});

	} else {

	}
}

function redirectingMessageWithURL(message, redierctionURL) {

	$("#logoutDailog")
			.html(
					"<div style='margin-left: 5%;'><div style='float:left'><img height='30px' src='images/help.png'></img></div><div style='float:left;margin-left: 20px;width:80%'>"
							+ message + "</div></div>");
	$("#logoutDailog").dialog(
			{
				title : 'Message',
				modal : true,
				width : 600,
				height : 235,
				fluid : true,
				buttons : {
					Ok : function() {
						$(this).html("");
						$(this).dialog("close");
						$(this).dialog("destroy");
						// navigationMenuUrl('timeout');
						// window.location.href = "timeout";
						// viewRegisteredMemberDetails(dataObject.MEBER_ID);
						//window.location.href = redierctionURL;
						
						

					},
					cancel : function() {
						$(this).html("");
						$(this).dialog("close");
						$(this).dialog("destroy");
						// navigationMenuUrl('timeout');
						// window.location.href = "timeout";

					}
				},
				open : function() {
					$(this).closest(".ui-dialog").find(".ui-button").eq(1)
							.addClass("dialogyes");
					$(".visionHeaderMain").css("z-index", "999");
					$(".visionFooterMain").css("z-index", "999");
				},
				beforeClose : function(event, ui) {
					$(".visionHeaderMain").css("z-index", "99999");
					$(".visionFooterMain").css("z-index", "99999");
				}
			});

}

function printForm(divName) {
	var printContents = document.getElementById(divName).innerHTML;
	var originalContents = document.body.innerHTML;
	document.body.innerHTML = printContents;
	window.print();
	document.body.innerHTML = originalContents;
}

// When the user scrolls down 20px from the top of the document, show the button
window.onscroll = function() {
	scrollFunction()
};

function scrollFunction() {
	if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
		document.getElementById("myBtn").style.display = "block";
	} else {
		document.getElementById("myBtn").style.display = "none";
	}
}

// When the user clicks on the button, scroll to the top of the document
function topFunction() {
	document.body.scrollTop = 0;
	document.documentElement.scrollTop = 0;
}