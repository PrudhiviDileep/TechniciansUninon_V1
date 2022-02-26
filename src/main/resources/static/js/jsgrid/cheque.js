/**
 * @Author
 P Dileep Kumar
 */
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
    	value=new Date();
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
							pageSize : 5,
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
											alert(resultData);
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
											alert(resultData);
											
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
											alert(resultData);
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
									  {  title: "Type", name: "CHEQUE_TYPE", selectedIndex: 0 , type: "select", width: 70, items: [ {"Id":"CHEQUE","Name":"CHEQUE"},{"Id":"IMPS","Name":"IMPS"}], valueField: "Id", textField: "Name" },
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
	
	var UrlStr=context+"/exportToExcel?ExcelType=ChequeDetails&SELECTED_CHEQUE_NOS="+selectedCheques;
	alert(UrlStr);
			document.getElementById('exportToExcelFrame').src=UrlStr;
	}