<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><% String context=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pay Membership Amount</title>


</head>
<body>


	<div class="container">
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
		<script type="text/javascript">$(document).ready(function() {		
	var urlParams=	fetchRequestParams();
	if(urlParams.deptId!=null&& urlParams.deptId!='undefined' && urlParams.deptId!=''
			&& urlParams.cardNo!=null && urlParams.cardNo!='undefined' && urlParams.cardNo!=''){	
		$("#commonSeachDeptSelectId option[value="+ urlParams.deptId + "]").attr('selected', true);
		$("#commonSearchCardNo").val(urlParams.cardNo);
		$("#commonSearchCardNo").attr("disabled",false);
		getSearchDetails();
	}
		
	});</script>
				
		<input type="hidden" name="pageId" value="CARD_BALANCE_PAYMENT" id="PAGE_ID">
		<input type="hidden" name="memberId"  value='' id="MEMBER_ID">

			<div class="form-wrap">

				<jsp:include page="searchBar.jsp" />
				

				<jsp:include page="topPanel.jsp" />

				<div class="" id="membershipDeatils">


					<fieldset>
						<legend>
							<h4>Membership Details</h4>
						</legend>


						<table>
							<form action="" method="get" onsubmit="" id="payMembershipAmountForm">
								<tr><td>Payment Type</td>
									<td><div class="select-style ">
											<select id='paymentConfIdId' name='paymentConfId' onchange="setSelectValueToTarget('paymentConfIdId','payMembershipAmountId')"  >${PAYMENT_CONF_ID}</select>
										</div></td>
										

									<td>Membership Amount</td>
									<td><input type='text' name='membershipAmount' id='payMembershipAmountId' class='texBoxCss' disabled="disabled" value='${MEMBERSHIP_AMOUNT}'></td>
								</tr>
									<tr><td>
							Mode of Payment 
							</td>
								<td><div class="select-style "><select  name='payMembershipPaymentMode' id='payMembershipPaymentModeId' class='texBoxCss' onchange="regPaymentMode('payMembershipPaymentModeId','payMembershipDdNoId')">
							
								<option>CASH</option>
								<option>DEMAND DRAFT</option>
								
								</select></div></td>
							
							
								<td><div class="required"><label>DD No</label></div></td>
								<td><input type='text' name='payMembershipDdNo' id='payMembershipDdNoId' class='texBoxCss' value="" disabled="disabled"></td>
							</tr>
								<!--<tr>
									<td>Paid Amount</td>
									<td><input type='text' name='paidAmount' id='payMembershipPaidAmountId' class='texBoxCss' disabled="disabled" value='${PAID_AMOUNT}'></td>
								
									<td>Balance Amount</td>
									<td><input type='text' name='balanceAmount' id='payMembershipBalanceAmountId' class='texBoxCss' disabled="disabled" value='${BALANCE_AMOUNT}'></td>
								</tr>-->
								<tr>
									<td>Paying Amount</td>
									<td><input type='text' name='payingAmount' id='payMembershipPayingAmountId' class='texBoxCss'></td>
								
									<td>Receipt No</td>
									<td><input type='text' name='receiptNo' id='payMembershipReceiptNoId' class='texBoxCss' value='${RECEIPT_NO}'></td>
								</tr>
							
								
								<tr>
									<td>Paid Date</td>
									<td><input type='text' id='payMembershipPaidDate'
										name='paidDate' value=''
										class='texBoxCss form-control currentDateDatepicker'></td>
								
									<td>Remarks</td>
									<td><input type='text' name='remarks' id='payMembershipRemarks' class='texBoxCss'></td>
								</tr>




								<tr>
									<td colspan="4" align="center"><button class="w3-button w3-white w3-border w3-border-blue" style="margin:5px;" id="payMembershipAmountBtn">Submit</button>
										<button class="w3-button w3-white w3-border w3-border-blue" style="margin:5px;" id="">Clear</button>
								</td>
								</tr>
							</form>
						</table>



					</fieldset>


				</div>
				<div class="" id="membershipDeatilsHistory">

					<fieldset>
						<legend>
							<h4>Membership Amount Payment History</h4>
						</legend>


						<div id="searchDetails"></div>



					</fieldset>
				</div>




			</div>



		</div>
		<jsp:include page="footer.jsp" />
		<div id='logoutDailog'></div>
</body>

</html>
