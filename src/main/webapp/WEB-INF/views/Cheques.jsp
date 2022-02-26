<!DOCTYPE html>
<html>
<head>
<%String context=request.getContextPath(); %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>jsGrid - Basic Scenario</title>
    <link rel="stylesheet" href="<%=context %>/css/jquery-ui.css">
<link rel="stylesheet" href="<%=context %>/css/main.css">
 <link rel="stylesheet" type="text/css" href="<%=context %>/js/jsgrid/css/jsgrid.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=context %>/js/jsgrid/css/theme.css" />

    <script src="<%=context %>/js/jsgrid/external/jquery/jquery-1.8.3.js"></script>
 <script src="<%=context %>/js/jsgrid/db.js"></script> 

<script type="text/javascript"  src="<%=context %>/js/jquery-ui.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.core.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.load-indicator.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.load-strategies.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.sort-strategies.js"></script>
    <script src="<%=context %>/js/jsgrid/src/jsgrid.field.js"></script>
    <script src="<%=context %>/js/jsgrid/src/fields/jsgrid.field.text.js"></script>
    <script src="<%=context %>/js/jsgrid/src/fields/jsgrid.field.number.js"></script>
    <script src="<%=context %>/js/jsgrid/src/fields/jsgrid.field.select.js"></script>
<script
	src="<%=context%>/js/jsgrid/src/fields/jsgrid.field.checkbox.js"></script>
<script
	src="<%=context%>/js/jsgrid/src/fields/jsgrid.field.control.js"></script>
<script src="<%=context%>/js/jsgrid/src/jsgrid.validation.js"></script>
<script src="<%=context%>/js/cheque.js"></script>
<style>
.saveButn {
	margin: 5px;
	background: #4fcdff !important;
	border-radius: 5px;
	color: #FFF !important;
	padding: 5px 9px;
	margin: 0px auto;
	color: #FFF;
	background-color: #555;
	background: -webkit-linear-gradient(#888, #555);
	background: linear-gradient(#888, #555);
	border: 0 none;
	border-radius: 3px;
	cursor: pointer !important;
	/*font-weight: bold;*/
}

.external-pager {
	margin: 10px 0;
}

.external-pager .jsgrid-pager-current-page {
	background: #c4e2ff;
	color: #fff;
}

.config-panel {
	padding: 10px;
	margin: 10px 0;
	background: #fcfcfc;
	border: 1px solid #e9e9e9;
	display: inline-block;
}

.config-panel label {
	margin-right: 10px;
}
</style>
<script> var context="<%=context %>"; </script>

</head>
<body>
<button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>
<div id="loader"></div>
<div class="headerWrapper">
<h1 style="horizontal-allign: middle; color: white; text-shadow: 2px 2px 4px #000000;">TELUGU CINE & T.V. OUTDOOR UNIT TECHNICIANS UNION</h1>
</div>
<jsp:include page="navigation.jsp" />
<div class="" >
<div style="width: 97%;margin:0 auto;" >
<fieldset>
    <legend>Search Cheques</legend><div id="" style="">
    
    
    <table>
    
    <tr>
    <td><label>From Date</label></td>
     <td><input type="text" name="frmDate" id="FromDate"  class='texBoxCss form-control datepicker currentDateDatepicker'></td>
      <td><label>To Date</label></td>      
        <td><input type="text" name="toDate" id="ToDate"  class='texBoxCss form-control datepicker currentDateDatepicker'></td>
         <td><label>Cheque No</label></td>
         <td><input type="text" name="chequeNo" id="ChequeNo" class=""></td>
    
   
     
     <td><label>Amount</label></td>
       <td><input type="text" name="amount" id="Amount" class=""></td>
       
     </tr>
        <tr>
      <td><label>Company Name</label></td>
       <td><input type="text" name="companyName" id="CompanyName" class=""></td>
        <td><label>Recieved From</label></td>
         <td><input type="text" name="recievedFrom" id="RecievedFrom" class=""></td>
   
    
<td><label>Bank Name</label></td>
         <td><input type="text" name="bankName" id="BankName" class=""></td>
       <td><label>Mode of Payment</label></td>
     <td><select name="modeOfPayment" id="ModeOfPayment"><option value="CHEQUE">CHEQUE</option><option value="NEFT">NEFT</option><option value="IMPS">IMPS</option></select>
     
     <button type="button" class="saveButn " onclick="getChecqueData()">Search</button>
     </td>
        </tr>
       
    </table>
    
    
    </div>
</fieldset>
<fieldset>    <div class="config-panel" style="width: 100%;margin:0 auto;text-align: center">
        <label><input id="heading" type="checkbox" checked /> Heading</label>
        <label><input id="filtering" type="checkbox"  /> Filtering</label>
        <!-- <label><input id="inserting" type="checkbox" /> Inserting</label> -->
        <!-- <label><input id="editing" type="checkbox" checked /> Editing</label> -->
        <label><input id="paging" type="checkbox" checked /> Paging</label>
        <label><input id="sorting" type="checkbox" checked /> Sorting</label>
        <label><input id="selecting" type="checkbox" checked /> Selecting</label>
      	 
      	 
      	<!--  <button type="button" class="saveButn" onclick="exportToExcel('exportToExcelFrame','CHEQUEDETAILS')" id="exporExcel_chequeDet">Download</button>
    	 <button type="button" class="saveButn" onclick="exportToExcel('exportToExcelFrame2','CHEQUEDISBURSEDETAILS')" id="exporExcel_chequeDisbrsDet">Download</button>
 		<button type="button" class="saveButn" onclick="exportAsText('exportAsTextFrame','CHEQUEDISBURSEDETAILS')" id="exportAsText">ExportAsText</button>
 		 -->
 		
 		<label >Select Download :
 		<select  id="downLoadTypeSelect">
 		<option value="SELECT">Select</option>
 		<option value="ChequeDetails">Cheque Details</option>
 		<option value="ChequeDisburseDetails">Cheque Disburse Details</option>
 		<option value="BankFormat">Bank Format</option></select>
 		<button type="button" class="saveButn" onclick="downloadBySelection()" id="COMMON_DOWNLOAD">Download</button>
 		</label>
 		<iframe id="exportAsTextFrame"  style="display: none;"></iframe>
 		<iframe id="exportToExcelFrame"  style="display: none;"></iframe>
		<!-- <button type="button" class="saveButn" onclick="deleteSelectedCeques()">Delete</button></label> -->
    </div>




    <legend>Checques</legend>
    <div id="jsGrid"></div>
     	<div class="config-panel" style="width: 100%;margin:0 auto;">
     	<div id="externalPager" class="external-pager" style="margin: 0 auto;text-align: center"></div>
     
    </div>
    </fieldset>
<fieldset id="fieldset_jsGrid2">
<iframe id="exportToExcelFrame2"  style="display: none;"></iframe>
    <legend>Cheque Benificiars</legend><div id="jsGrid2" ></div><div class="config-panel" style="width: 100%;margin:0 auto;">
     	<div id="externalPager2" class="external-pager" style="margin: 0 auto;text-align: center;display:none;">
     	
     	</div>
     
    </div>
</fieldset>
</div>
</div>
<jsp:include page="footer.jsp" />
<div id='logoutDailog'></div>
</body>
</html>