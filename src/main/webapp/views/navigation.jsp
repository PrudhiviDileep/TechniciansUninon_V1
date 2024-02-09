<div class='navigation'>
	<%
		String context = request.getContextPath();
	%>
	<div style="margin: 0 auto; width: 80%; height: auto;">
		<ul id="navigationManu">
			<!-- <li><a href="#home">Home</a></li> -->
			<li><a href="<%=context%>/registrationForm">Register</a></li>
			<li><a href="<%=context%>/updateMemberDetails">Update
					Member Details</a></li>
			<li><a href="<%=context%>/paySubscriptionAmountForm">Subscriptions</a></li>
			<li><a href="<%=context%>/payMembershipAmount">Card Balance</a></li>
			<li class="dropdown"><a href="javascript:void(0)"
				class="dropbtn">Loans</a>
				<div class="dropdown-content">
					<a href='<%=context%>/sanctionLoanForm'>Loan Sanction</a> <a
						href='<%=context%>/payLoanAmountForm'>Pay Loan Amount</a> <a
						href='<%=context%>/loanSummaryPage'>Loans Summary</a>
				</div></li>
			<!-- 
			<li class="dropdown"><a href="javascript:void(0)"
				class="dropbtn">Cheques</a>
				<div class="dropdown-content">
					<a href='<%=context%>/cheques'>Recieved Cheques</a> <a
						href='<%=context%>/ChequeDisbursment'>Cheque Disbursment</a>
				</div>
			</li>
			
			<li class="dropdown"><a href="javascript:void(0)"
					class="dropbtn">Configurations</a>
					<div class="dropdown-content">
						<a href='<%=context%>/gridConfigurations'>GridConfigurations</a> 
						<a href='<%=context%>/genericProcConfig'>ProcedureConfigurations</a>
					</div>
			</li>
			 -->
			 
			<li class="dropdown"><a href="javascript:void(0)" class="dropbtn">Yearly Summary</a>
					<div class="dropdown-content">
						<a href='<%=context%>/cardsSummary'>Cards</a>
						<a href='<%=context%>/loansSummary'>Loans</a>
					</div>
			</li>
			 
			 <!-- 
			<li class="dropdown"><a href="javascript:void(0)"
					class="dropbtn">Reports</a>
					<div class="dropdown-content">
						<a href='<%=context%>/getGenericReports?REPORT_ID=CASH_REPORT'>Cash Report</a> 
						<a href='<%=context%>/getGenericReports?REPORT_ID=BANK_DETAILS'>Bank Details</a>
						<a href='<%=context%>/getGenericReports?REPORT_ID=CONTACT_DETAILS'>Contact Details</a>
						<a href='<%=context%>/getGenericReports?REPORT_ID=CARD_BALANCE'>Card Balance</a>
						<a href='<%=context%>/getGenericReports?REPORT_ID=LOAN_BALANCE'>Loan Balance</a>
						
					</div>
			</li>
			-->
			<li><a href="<%=context%>/getBalanceInfo">All Balance Info</a></li>
			<li><a href="<%=context%>/search">Search</a></li>
			<li><a href="<%=context%>/logout">Logout</a></li>
		</ul>
	</div>
</div>