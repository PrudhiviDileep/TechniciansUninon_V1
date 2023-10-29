<html>
<head>
<meta charset="ISO-8859-1">
<%
	String context = request.getContextPath();
	String receiptNo = request.getParameter("RECEIPT_NO");
%>
<script type="text/javascript" src="<%=context%>/js/jquery.js"></script>
<title>Receipt</title>

<style>
button{margin: 5px;
    background: #4fcdff !important;
    border-radius: 5px;
    color: #FFF !important;
    padding: 10px 18px;
    margin: 20px auto;
    color: #FFF;
    background-color: #555;
    background: -webkit-linear-gradient(#888, #555);
    background: linear-gradient(#888, #555);
    border: 0 none;
    border-radius: 3px;
    font-weight: bold;
    cursor: pointer;}
u.dotted {
	border-bottom: 1px dashed #999;
	text-decoration: none;
}
.otherInfodashedInput{
	margin: 0px 0px;
	width: 100%;
	border-top: none;
	border-left: none;
	border-right: none;
	border-bottom: 1px dashed black;
	padding-bottom: 2px;
	text-align: center;
}
.dashedInput {
	margin: 0px 0px;
	width: 100%;
	border-top: none;
	border-left: none;
	border-right: none;
	border-bottom: 1px dashed black;
	padding-bottom: 2px;
}

.dateDashedInput {
	margin: 0px 0px;
	float: left;
	width: 140px;
	border-top: none;
	border-left: none;
	border-right: none;
	border-bottom: 1px dashed black;
	padding-left: 5px;
	padding-bottom: 2px;
}

.receiptNoDashedInput {
	margin: 0px 0px;
	width: 130px;
	float: left;
	border-top: none;
	border-left: none;
	border-right: none;
	border-bottom: 1px dashed black;
	padding-bottom: 2px;
	padding-left: 10px;
}

.rsDashedInput {
	margin: 0px 0px;
	width: 130px;
	border-top: none;
	border-left: none;
	border-right: none;
	border-bottom: 1px dashed black;
	padding-bottom: 2px;
	padding-left: 10px;
}
</style>
</head>
<body>
	<div class=""
		style="width: 92%; margin: 10% auto; height: auto; padding: 20px; border: 1px #000 solid;">
		<div style="width: 95%; margin: 0 auto; height: auto;">
			<table style="width: 90%; margin: 0 auto;">
				<tr>
					<td><label
						style="float: right; text-align: right; display: block">Phone
							: 040 - 23542638</label></td>
				
				</tr>
				<tr>
					<td>
<div style="width:150px;height:120px;float:left;background-img: 100%;"><a href="#" title="TELUGU CINE
							& T.V. OUTDOOR UNIT TECHNICIANS UNION" >
					<img src="<%=context%>/images/Logo.jpeg" alt="Logo" style="width:150px;height:120px;"></a>
			   </div><H1 style="text-align: center; font-size: 18px;">TELUGU CINE
							&amp; T.V. OUTDOOR UNIT TECHNICIANS UNION</H1> <label
						style="line-height: 25px; display: block; text-align: center; font-weight: bold;">(Regd.
							No.A-2328)</label> <label
						style="line-height: 25px; display: block; text-align: center;">8-3-231/A/195/A,
							Sri Krishna Nagar, Near Sai Ram School, Hyderabad - 45</label> <label
						style="line-height: 25px; display: block; text-align: center; font-weight: bold;">(Affiliated
							to All India Film Employees Confederation)</label>



					</td>
				</tr>
				<tr>
					<td >&nbsp;</td>
				</tr>
					<tr>
					<td >&nbsp;</td>
				</tr>
			</table>


			<table style="width: 95%; margin: 0 auto;">
				<tr>
					<td width="33%" align="left"><label
						style="line-height: 25px; float: left;">No.</label><input
						type="text" class="receiptNoDashedInput" value="${RECEIPT_ID}"></td>
					<td width="35%" align="center"><label
						style="line-height: 25px; font-size: 16px; color: red;">RECEIPT</label></td>
					<td width="28%"><label style="line-height: 25px; float: left;">Date:</label><input
						type="text" class="dateDashedInput" value="${CREATED_DATAE}"></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 10%"><label
						style="line-height: 25px; display: block">Received with
							thanks from Sri </label></td>
					<td colspan="2" align="left" style="width: 10%"><input
						type="text" class="dashedInput"  value="${FIRST_NAME}"></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
								<tr>
					<td colspan="3"><input
						type="text" class="otherInfodashedInput"  value="${OTHER_DETAILS}"></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 10%"><label
						style="line-height: 25px; display: block">Sum of Rupees</label></td>
					<td colspan="2" align="left" style="width: 10%"><input
						type="text" class="dashedInput" value="${AMOUNT_IN_WORDS}"></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 10%"><label
						style="line-height: 25px; display: block">Towards</label></td>
					<td colspan="2" align="left" style="width: 10%"><input
						type="text" class="dashedInput"  value="${RECEIPT_TYPE}"></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td style="width: 10%"><label
						style="line-height: 25px; display: block">Vide Cash/Cheque
							No</label></td>
					<td colspan="2" align="left" style="width: 10%"><input
						type="text" class="dashedInput" value="${REMARKS}"></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
				</tr>

				<tr>
					<td><label
						style="line-height: 25px; float: left; font-size: 16px; color: red; font-weight: 300; font-weight: 900;">Rs </label>
						<input type="text" class="rsDashedInput" value="${AMOUNT}"></td>
					<td colspan="2"><label style="float: right"> Gen Secretary/Tresurer</label></td>
				</tr>
				<tr  id="buttonsTr">
				
				<td colspan="3" align="center">
						<div style="width:150px;margin:0px auto;height: 100px;borders:1px red solid;"><button type="button" id="printId" name="Print" value="Print" onclick="printReceipt()">Print</button>
						<button type="button" id="closeId"name="Close" value="Close" onclick="closeReceipt()">Close</button>
		</div>
				</td>
				</tr>
			</table>
		</div>

		<script type="text/javascript">
		
	    function closeReceipt(){

		    document.close();
	    	
	    }
	    
	    function printReceipt(){
	    	
	    	
	    	$('#buttonsTr').hide();
	    	$('#printId').hide();	
	    	$('#closeId').hide();	
	    	window.print();
		    document.close();
	    	
	    }
	    
		</script>
	</div>
</body>
</html>