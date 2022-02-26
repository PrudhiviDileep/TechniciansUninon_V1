<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Subscriptions Payments</title>


</head>
<body>
	<div class="container">
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<script type="text/javascript">
$(document).ready(function() {		
var urlParams=	fetchRequestParams();
if(urlParams.deptId!=null&& urlParams.deptId!='undefined' && urlParams.deptId!=''
		&& urlParams.cardNo!=null && urlParams.cardNo!='undefined' && urlParams.cardNo!=''){	
	$("#commonSeachDeptSelectId option[value="+ urlParams.deptId + "]").attr('selected', true);
	$("#commonSearchCardNo").val(urlParams.cardNo);
	$("#commonSearchCardNo").attr("disabled",false);
	getSearchDetails();
}
	
});
</script>
		<div class='mainbody'>
				
		


			<div class="form-wrap">
			<jsp:include page="searchBar.jsp" />
				<jsp:include page="topPanel.jsp" />

				<fieldset>
					<legend>
						<h4>Subscription Form</h4>
					</legend>

					
					
					<div class="" style="width: 80%; height: auto; margin: 0 auto;">

						<table>
							<form  method="POST" id="paySubscription">
							<input type="hidden" name="pageId" value="PAY_SUBSCRIPTION_AMOUNT" id="PAGE_ID">
							<input type="hidden" name="memberId"  value="" id="MEMBER_ID">
							
				
									
							
								<tr>
									<td>Subscribed Year</td>
									<td><div class="select-style ">
											<select id='paySubscription_subscriptionYear' name='subscriptionYear'>${SUBSCRIPTION_YEAR}</select>
										</div></td>

									<td>Paid Date</td>
									<td><input type='text' id='paySubscription_paidDate'
										name='paidDate' value='${PAID_DATE}'
										class='texBoxCss form-control currentDateDatepicker'></td>
								</tr>
								<tr>
									<td>Paid Amount</td>
									<td><input type='text' id='paySubscription_paidAmount'
										name='paidAmount' value='${PAID_AMOUNT}' class='texBoxCss'></td>
									<td>Receipt No</td>
									<td><input type='text' id='paySubscription_receiptNo'
										name='receiptNo' value='${RECEIPT_NO}' class='texBoxCss'></td>
								</tr>
								<tr>
									<td>Payment Status</td>
									<td><div class="select-style ">
											<select id='paySubscription_paymentStatus' name='paymentStatus'>${PAYMENT_STATUS}</select>
										</div></td>
									<td>Remarks</td>
									<td><input type='text' id='paySubscription_remarks'
										name='remarks' value='${REMARKS}' class='texBoxCss'></td>
								</tr>
									<tr>
									<td>Payment Type</td>
									<td><div class="select-style ">
											<select id='paySubscription_paymentConfId' name='paymentConfId' onchange="setSelectValueToTarget('paySubscription_paymentConfId','paySubscription_subscriptionAmount')">${PAYMENT_CONF_ID}</select>
										</div></td>
									<td>Subscription Amount</td>
									<td><input type='text' id='paySubscription_subscriptionAmount' name='subscriptionAmount'
										 value='${SUBSCRIPTION_AMOUNT}' class='texBoxCss' disabled="disabled"></td>
								</tr>

								<tr>
								<td colspan="4" align="center">
										<button class="w3-button w3-white w3-border w3-border-blue" style="margin:5px;" id="paySubscriptionAmountBtn">Pay Subscription</button>
										
									<!-- <button class="w3-button w3-white w3-border w3-border-blue" style="margin:5px;" id="subscriptionsUpdateBtn">Reset</button> -->	
										</td>
								
								</tr>
							</form>
						</table>
					</div>

				</fieldset>
				<fieldset>
					<legend>
						<h4>Subscripton Details</h4>
					</legend>
					<div id="searchDetails"></div>
				</fieldset>










			</div>

			
		</div>
		<jsp:include page="footer.jsp" />
		<div id='logoutDailog'></div>
</body>
</html>