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
<script src="<%=context%>/js/genericGrid.js"></script>
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
/* GRID SELECT ALL CHECK BOX  CODE STARTS PART 1
var selectedItems = [];
var selectItem = function(item) {
    
    selectedItems.push(item);
    if ($(".singleCheckbox").length == $(".singleCheckbox:checked").length) {
        $("#selectAllCheckbox").prop("checked", true);
    } else {
        $("#selectAllCheckbox").prop("checked", false);
    }
	loadDependentGrid();
    
};

var unselectItem = function(item) {
    selectedItems = $.grep(selectedItems, function(i) {
        return i !== item;
    });
    if (selectedItems.length == 0) {
        $("#selectAllCheckbox").attr('checked', false);
    }
    if ($(".singleCheckbox").length == $(".singleCheckbox:checked").length) {
        $("#selectAllCheckbox").prop("checked", true);
    } else {
        $("#selectAllCheckbox").prop("checked", false);
    }
    loadDependentGrid();
};

GRID SELECT ALL CHECK BOX  CODE END    */
$( function() {
	jsGrid.Grid.prototype.rowByIndex = function(arg) {
        //this._content.find("tr")[arg] returns a DOM element instead of a jQuery object
        //Pass the DOM element to the find method to get a jQuery object representing it
        return this._content.find(this._content.find("tr")[arg]);
    };
	
	initializeGrid("GRID_CONGFIGURATIONS","GRID_CONFIG_DETAILS");
	
	//initializeGrid("GRID_CONGFIGURATIONS_DETIALS","GRID_COLUMN_CONFIG_DETAILS");
	
	/* GRID SELECT ALL CHECK BOX  CODE STARTS PART 2
    $("#selectAllCheckbox").click(function(item) {
    	console.log("selectedItems "+selectedItems);
        selectedItems = [];
        if (this.checked) { // check select status
            $('.singleCheckbox').each(function() {
                this.checked = true;
                selectItem($(this).parent().next().text());
            });
        } else {

            $('.singleCheckbox').each(function() {
                this.checked = false;
                unselectItem(item);
            });
            selectedItems = [];
        }
    });
    
   GRID SELECT ALL CHECK BOX  CODE ENDS PART 2*/

    
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

<fieldset>    
    <legend>Grid Configurations</legend>
    <div id="GRID_CONGFIGURATIONS"></div>
     	<div id="externalPager" class="external-pager"></div>
</fieldset>
<fieldset id="">
 <legend>Grid Column Configuration Details</legend>
    <div id="GRID_CONGFIGURATIONS_DETIALS" ></div>
    <div id="GRID_CONGFIGURATIONS_DETIALS_externalPager" class="external-pager" ></div>
  
     	
    
</fieldset>
</div>
</div>
<jsp:include page="footer.jsp" />
<div id='logoutDailog'></div>
</body>
</html>