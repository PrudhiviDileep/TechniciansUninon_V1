<!DOCTYPE html>
<html>
<head>
<%String context=request.getContextPath(); %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>jsGrid - Basic Scenario</title>
    <link rel="stylesheet" href="<%=context %>/css/jquery-ui.css">
<link rel="stylesheet" href="<%=context %>/css/main.css">
 <link rel="stylesheet" type="text/css" href="/TechniciansUnion/js/jsgrid/css/jsgrid.css" /> 
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
<script>
var MyDateField = function(config) {
    jsGrid.Field.call(this, config);
};
 
MyDateField.prototype = new jsGrid.Field({
 
    css: "date-field",            // redefine general property 'css'
    align: "center",              // redefine general property 'align'
 
    myCustomProperty: "foo",      // custom property
 
    sorter: function(date1, date2) {
        return new Date(date1) - new Date(date2);
    },
 
    itemTemplate: function(value) {
        return new Date(value).toDateString();
    },
 
    insertTemplate: function(value) {
        return this._insertPicker = $("<input class='texBoxCss form-control datepicker' >").datepicker({ defaultDate: new Date() });
    },
 
    editTemplate: function(value) {
        return this._editPicker = $("<input>").datepicker().datepicker("setDate", new Date(value));
    },
 
    insertValue: function() {
        return this._insertPicker.datepicker("getDate").toISOString();
    },
 
    editValue: function() {
        return this._editPicker.datepicker("getDate").toISOString();
    }
});
 
jsGrid.fields.date = MyDateField;
	var selectedItems = [];
	$(function() {

		$("#jsGrid")
				.jsGrid(
						{

							width : "100%",
							filtering : false,
							editing : true,
							inserting : true,
							sorting : true,
							paging : true,
							autoload : true,
							pageSize : 10,
							pageButtonCount : 5,
							pagerContainer : "#externalPager",
							pagerFormat : " Total Pages: {pageCount} &nbsp;&nbsp; {first} {prev} {pages} {next} {last}   &nbsp;&nbsp;  Total Records: {itemCount}",
							pagePrevText : "<",
                			pageNextText: ">",
							pageFirstText : "<<",
                			pageLastText: ">>",
							pageNavigatorNextText : "&#8230;",
							pageNavigatorPrevText : "&#8230;",
							deleteConfirm : "Do you really want to delete the record?",
							controller : {

								//getAllChequeDetails getChequeDetails saveChequeDetails updateChequeDetails deleteChequeDetails
								loadData : function(filter) {

									// return	 JSON.parse
									var get_dataObj = "";
									var save_dataObj = "";
									var update_dataObj = "";
									var delete_dataObj = "";

									$.ajax({
										type : "POST",
										url : "getAllChequeDetails",
										async : false,
										data : filter,
										success : function(resultData) {
											save_dataObj = resultData;
										}
									});

									return JSON.parse(save_dataObj);
								},

								insertItem : function(item) {
									$.ajax({
										type : "POST",
										async : false,
										url : "saveChequeDetails",
										data : item,
										success : function(resultData) {
											//alert(resultData);
										}
									});

									// return JSON.parse(dataObj);
								},

								updateItem : function(item) {
									$.ajax({
										type : "POST",
										async : false,
										url : "updateChequeDetails",
										data : item,
										success : function(resultData) {
											//alert(resultData);
											
										}
									});

									
								},

								deleteItem : function(item) {
									$.ajax({
										type : "POST",
										async : false,
										url : "deleteChequeDetails",
										data : item,
										success : function(resultData) {
											//alert(resultData);
										}
									});
									
								},

							},
							fields : [

									{
										headerTemplate : function() {
											return $("<input>").attr("type",
													"checkbox").attr("id",
													"selectAllCheckbox")
										},
										 headerTemplate: function() {
							                    return $("<button>").attr("type", "button").text("Delete")
							                            .on("click", function () {
							                                deleteSelectedItems();
							                            });
							                },
										itemTemplate : function(_, item) {
											return $("<input>")
													.attr("type", "checkbox")
													.attr("class",
															"singleCheckbox")
													.prop(
															"checked",
															$
																	.inArray(
																			item.firstName,
																			selectedItems) > -1)
													.on(
															"change",
															function() {
																$(this)
																		.is(
																				":checked") ? selectItem($(
																		this)
																		.parent()
																		.next()
																		.text())
																		: unselectItem($(
																				this)
																				.parent()
																				.next()
																				.text());
																loadDisbursmentDetailsGrid();

															});
										},
										align : "center",
										width : 25,
										sorting : false
									},
									//CHEQUE_NO,COMPANY_NAME,CHEQUE_DATE,AMOUNT,CHEQUE_RECIEVED_FROM,CHEQUE_TYPE

									{
										title : "Cheque No",
										name : "CHEQUE_NO",
										type : "text",
										width : 150,
										validate : {
											message : "Please enter cheque no!",
											validator : function(value) {
												return value.length > 0;
											}
										}

									},
									  {  title: "Type", name: "CHEQUE_TYPE", type: "select", width: 70, items: [ {"Id":"CHEQUE","Name":"CHEQUE"},{"Id":"IMPS","Name":"IMPS"}], valueField: "Id", textField: "Name" },
									{
										title : "Company Name",
										name : "COMPANY_NAME",
										type : "text",
										width : 200,
										validate : {
											message : "Please enter company name!",
											validator : function(value) {
												return value.length > 0;
											}
										}
									},
									{
										title : "Recieved From",
										name : "CHEQUE_RECIEVED_FROM",
										type : "text",
										width : 200,
										validate : {
											message : "Please enter cheque recieved from!",
											validator : function(value) {
												return value.length > 0;
											}
										}
									},
									{
										title : "Date",
										name : "CHEQUE_DATE",
										type : "date",
										myCustomProperty: "bar",
										width : 100,
										validate : {
											message : "Please enter cheque date",
											validator : function(value) {
												return value.length > 0;
											}
										}
									}, {
										title : "Amount",
										name : "AMOUNT",
										type : "text",
										validate : {
											message : "Please enter amount",
											validator : function(value) {
												return value.length > 0;
											}
										}
									}, {
										title : "Bank Name",
										name : "BANKNAME",
										type : "text",
										validate : {
											message : "Please enter bank name",
											validator : function(value) {
												return value.length > 0;
											}
										}
									}, {
										title : "Remarks",
										name : "FIELD1",
										type : "text",
										validate : {
											message : "Please enter amount",
											validator : function(value) {
												return value.length > 0;
											}
										}
									},

									{
										type : "control"
										
									} ],
							rowClick : function(args) {
								var $target = $(args.event.target);

								if ($target.closest(".your-field-css-class").length) {
									// handle cell click
								}

								// otherwise handle row click
								if (this.editing) {
									//this.editItem($target.closest("tr"));
									return false;
								}
							},

						});

		$(".jsgrid-table").css("height", "auto");

		var selectItem = function(item) {
			selectedItems.push(item);
			if ($(".singleCheckbox").length == $(".singleCheckbox:checked").length) {
				$("#selectAllCheckbox").prop("checked", true);
			} else {
				$("#selectAllCheckbox").prop("checked", false);
			}
		};

		var unselectItem = function(item) {
			selectedItems = $.grep(selectedItems, function(i) {
				return i !== item;
			});
			if (selectedItems.length == 0) {
				$('#selectAllCheckbox').attr('checked', false);
			}
			if ($(".singleCheckbox").length == $(".singleCheckbox:checked").length) {
				$("#selectAllCheckbox").prop("checked", true);
			} else {
				$("#selectAllCheckbox").prop("checked", false);
			}
		};

		$("#selectAllCheckbox").click(function(item) {
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

		$(".config-panel input[type=checkbox]").on("click", function() {
			var $cb = $(this);
			$("#jsGrid").jsGrid("option", $cb.attr("id"), $cb.is(":checked"));
		});
		//------------------------------------------------
		
		
		
		
		 var myTagField = function(config) {
    jsGrid.Field.call(this, config);
  };
  myTagField.prototype = new jsGrid.Field({
    autosearch: true,
    sorter: function(tag1, tag2) {
      return tag1.localeCompare(tag2);
    },
    itemTemplate: function(value) {
      return '<span class="label label-primary">'+value+'</span>';
    },
    insertTemplate: function(value) {
      return this._insertAuto = $("<input>").autocomplete({source : tags});
    },
    filterTemplate: function(value) {
      if(!this.filtering)
        return "";

      var grid = this._grid,
          $result = this._filterAuto = $("<input>").autocomplete({source : tags});

      if(this.autosearch) {
        $result.on("change", function(e) {
          grid.search();
        });
      }

      return $result;
    },

    editTemplate: function(value) {
      return this._editAuto = $("<input>").autocomplete({source : tags}).val(value);
    },
    insertValue: function() {
      return this._insertAuto.val();
    },
    filterValue: function() {
      return this._filterAuto.val();
    },
    editValue: function() {
      return this._editAuto.val();
    }
  });
  jsGrid.fields.myTagField = myTagField;
  
  
  
  

		$("#jsGrid2").jsGrid({
			width : "100%",
			filtering : true,
			editing : true,
			inserting : true,
			sorting : true,
			paging : true,
			autoload : true,
			pageSize : 15,
			pageButtonCount : 5,
			deleteConfirm : "Do you really want to delete the client?",
			controller : db,
			fields : [ {
				name : "Name",
				type : "text",
				width : 150
			},
			
			
			
			{
				name : "Age",
				type : "myTagField",
				width : 50
			}, {
				name : "Address",
				type : "text",
				width : 200
			}, {
				name : "Country",
				type : "select",
				items : [ {
					"Id" : "CHEQUE",
					"Name" : "CHEQUE"
				}, {
					"Id" : "IMPS",
					"Name" : "IMPS"
				} ],
				valueField : "Id",
				textField : "Name"
			}, {
				name : "Married",
				type : "checkbox",
				title : "Is Married",
				sorting : false
			}, {
				type : "control"
			} ],
		    controller: {
		        loadData:  function(filter) {
		          return $.grep(autocompletData.users, function(item) {
		            return (!filter.Name || item.Name.toLowerCase().indexOf(filter.Name.toLowerCase()) > -1);
		          });
		        }
		      }
		});

	});

	//alert(Object.keys(db.countries[0]));

	function loadDisbursmentDetailsGrid() {
		$("#grid").jsGrid("refresh");

	}
	
	function deleteSelectedCeques(){
		
		if(selectedItems.length>0){
		
		$.ajax({
			type : "POST",
			async : false,
			url : "deleteCheques",
			data :{cheques:selectedItems.toString()
				
			
			} ,
			success : function(resultData) {
				alert(resultData);
				$("#grid").jsGrid("refresh");
			}
		});
		}else
			alert("Please select atleast one cheque to delete!");
		
	}
	
	

	var tags = ["tag0","tag4","tag1", "tag2", "tag3"];

	var autocompletData = {};

	autocompletData.users = [
	  {
	    "Name": "Carson Kelley",
	    "Age": "tag0",
	    "Address":"",
	    "Country":"",
	    "Married":""
	  },
	  {
	    "Name": "Prescott Griffin",
	    "Age": "tag1",
	    "Address":"",
	    "Country":"",
	    "Married":""
	  },
	  {
	    "Name": "Amir Saunders",
	    "Age": "tag3",
	    "Address":"",
	    "Country":"",
	    "Married":""
	  },
	  {
	    "Name": "Derek Thornton",
	    "Age": "tag2",
	    "Address":"",
	    "Country":"",
	    "Married":""
	  },
	  {
	    "Name": "Fletcher Romero",
	    "Age": "tag4",
	    "Address":"",
	    "Country":"",
	    "Married":""
	  }];  
	
	function exportToExcel(){
var selectedCheques=selectedItems.toString();
alert("selectedCheques :: "+selectedCheques);
var context="<%=context %>";
var UrlStr=context+"/exportToExcel?ExcelType=ChequeDetails&SELECTED_CHEQUE_NOS="+selectedCheques;
alert(UrlStr);
		document.getElementById('exportToExcelFrame').src=UrlStr;
	}
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
 	<div class="config-panel" style="width: 100%;margin:0 auto;">
     	<div id="externalPager" class="external-pager" style="margin: 0 auto;text-align: center"></div>
     
    </div>
<fieldset>    <div class="config-panel" style="width: 100%;margin:0 auto;text-align: center">
        <label><input id="heading" type="checkbox" checked /> Heading</label>
        <label><input id="filtering" type="checkbox"  /> Filtering</label>
        <!-- <label><input id="inserting" type="checkbox" /> Inserting</label> -->
        <!-- <label><input id="editing" type="checkbox" checked /> Editing</label> -->
        <label><input id="paging" type="checkbox" checked /> Paging</label>
        <label><input id="sorting" type="checkbox" checked /> Sorting</label>
        <label><input id="selecting" type="checkbox" checked /> Selecting</label>
      	 <label style="float: right">
      	 
      	 <button type="button" class="saveButn" onclick="exportToExcel()">Download Cheques</button>
      	 <iframe id="exportToExcelFrame" frameborder="0" marginheight="0" marginwidth="0" height="0%" width="0%" style="display: none;"></iframe>
		<button type="button" class="saveButn" onclick="deleteSelectedCeques()">Delete</button></label>
    </div>


<!-- <button type="type" onclick="exportToExcel()">Export</button> -->


    <legend>Checques</legend>
    <div id="jsGrid"></div>
    </fieldset>
<fieldset>
    <legend>Cheque Benificiars</legend><div id="jsGrid2" style="margin: 30px 0"></div>
</fieldset>
</div>
</div>
<jsp:include page="footer.jsp" />
<div id='logoutDailog'></div>
</body>
</html>