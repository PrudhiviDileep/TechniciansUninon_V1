<!DOCTYPE html>
<html>
<head>
<%
String context=request.getContextPath(); 

String reportId=request.getAttribute("REPORT_ID")!=null?(String)request.getAttribute("REPORT_ID"):"";



%>
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
<script src="<%=context%>/js/genericReports.js"></script>
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
	margin: 0 auto;text-align: center;
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
	
}

.config-panel label {
	margin-right: 10px;
}
</style>
<script>
var context="<%=context %>";
<%
String gridId="";
if(reportId.equalsIgnoreCase("CASH_REPORT")){
	gridId="GRID_CASH_COLLECTED_REPORT";
}
if(reportId.equalsIgnoreCase("BANK_DETAILS")){
	gridId="GRID_BANK_DETIALS_REPORT";
}
if(reportId.equalsIgnoreCase("CONTACT_DETAILS")){
	gridId="GRID_CONTACT_DETAILS_REPORT";
}
if(reportId.equalsIgnoreCase("CARD_BALANCE")){
gridId="GRID_CARD_BALANCE_REPORT";}
if(reportId.equalsIgnoreCase("LOAN_BALANCE")){
gridId="GRID_LOAN_BALANCE_REPORT";}
%>

var reportGridId="<%=gridId %>";
var reportId="<%=reportId %>";
$( function() {
	
	initializeGrid(reportGridId,reportGridId);
	
    
} );
</script>

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
<%if(reportId.equalsIgnoreCase("CASH_REPORT")){ %>
<fieldset>    
    <legend>Cash Report</legend>
    <div id="GRID_CASH_COLLECTED_REPORT"></div>
     	<div id="externalPager" class="external-pager"></div>
</fieldset>
<%} %>


<%if(reportId.equalsIgnoreCase("BANK_DETAILS")){ %>
<fieldset>    
    <legend>Bank Details</legend>
    <div id="GRID_BANK_DETIALS_REPORT"></div>
     	<div id="externalPager" class="external-pager"></div>
</fieldset>
<%} %>
<%if(reportId.equalsIgnoreCase("CONTACT_DETAILS")){ %>
<fieldset>    
    <legend>Procedure Configurations</legend>
    <div id="GRID_CONTACT_DETAILS_REPORT"></div>
     	<div id="externalPager" class="external-pager"></div>
</fieldset>
<%} %>

<%if(reportId.equalsIgnoreCase("CARD_BALANCE")){ %>
<fieldset>    
    <legend>Procedure Configurations</legend>
    <div id="GRID_CARD_BALANCE_REPORT"></div>
     	<div id="externalPager" class="external-pager"></div>
</fieldset>
<%} %>

<%if(reportId.equalsIgnoreCase("LOAN_BALANCE")){ %>
<fieldset>    
    <legend>Procedure Configurations</legend>
    <div id="GRID_LOAN_BALANCE_REPORT"></div>
     	<div id="externalPager" class="external-pager"></div>
</fieldset>
<%} %>
</div>
</div>
<jsp:include page="footer.jsp" />
<div id='logoutDailog'></div>
</body>
</html>