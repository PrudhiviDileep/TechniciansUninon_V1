<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Search</title>
</head>
<body>
	<div class="container">
	<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
		<jsp:include page="header.jsp" />
		<jsp:include page="navigation.jsp" />
		<div class='mainbody'>
		
		<input type="hidden" name="pageId" value="LOAN_PAYMENT" id="PAGE_ID">
		<input type="hidden" name="memberId"  value=""              id="MEMBER_ID">
			<div class="form-wrap">
			<jsp:include page="searchBar.jsp" />
				<jsp:include page="topPanel.jsp" />
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


				<div calss="" style="width: 100%; height: auto; margin: 0 auto;">
				<!-- 	<fieldset>
						<legend>
							<h4>Loan Details</h4>
						</legend>
						<table border="0">


							<tr>
								<td style="width: 12%;">Loan Sanction Date</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
								<td style="width: 12%;">Sanctioned Amount</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
							</tr>
							<tr>
								<td style="width: 12%;">Loan Sanctioned By</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
								<td style="width: 12%;">Recovered Amount</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
							</tr>
							<tr>
								<td style="width: 12%;">Remarks</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
								<td style="width: 12%;">Due Amount</td>
								<td style="width: 30%;" align="center"><input type='text'
									name='memberName' id='memberNameId' class='texBoxCss'
									disabled="true"></td>
							</tr>


						</table>



					</fieldset>
					 -->

					<fieldset>
						<legend>
							<h4>Instalment Payment</h4>
						</legend>

						<div class="" style="width: 80%; height: auto; margin: 0 auto;">

							<table style="width: 80%;margin:0 auto">
								<form action="" method="get" onsubmit="">

									<tr>
										<td>Paying Amount</td>
										<td><input type='number' name='payingAmount' id='loanPaymentPyingAmount'
											class='texBoxCss'></td>
									</tr>
								
									<tr>
										<td>Paid Date</td>
										<td><input type='text' name='paidDate' id='loanPaymentPaidDate'
											class='texBoxCss currentDateDatepicker'></td>
									</tr>
									<tr>
										<td>Receipt No</td>
										<td><input type='text' name='receiptNo' id='loanPaymentReceiptNo'
											class='texBoxCss'></td>
									</tr>
										<tr>
										<td>Remarks</td>
										<td><input type='text' name='remarks' id='loanPaymentRemarks'
											class='texBoxCss'></td>
									</tr>

								<tr>
									<td  align="center">&nbsp;</td><td align="center">
									   <button class="w3-button w3-white w3-border w3-border-blue" style="margin:5px;" id="payLoanAmountBtn">Submit</button>
										<button class="w3-button w3-white w3-border w3-border-blue" style="margin:5px;" id="">Clear</button>
									</td>
								</tr>
								</form>
							</table>

						</div>

					</fieldset>

					<fieldset>
						<legend>
							<h4>Loan Payment History</h4>
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