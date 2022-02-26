/**
 * @Author
 P Dileep Kumar
 */
var selectedItems = [];
var cardNoData={};
var deptIdsArr=[];
var deptIdsObj={};
var deptIdsDropDownArr=[];
 deptIdsObj.Id='ALL';
 deptIdsObj.Name='ALL';
 deptIdsDropDownArr.push(deptIdsObj);
$(function() {


                    $.ajax({
                        type: "POST",
                        async: false,
                        url: "getAllCardNo",
                        data: {},
                        success: function(resultData) {
                          cardNoData=JSON.parse(  resultData);
                          deptIdsArr=Object.keys(cardNoData);
                          for(var i=0;i<deptIdsArr.length;i++){
                           
                           deptIdsObj={};
                           deptIdsObj.Id=deptIdsArr[i];
                           deptIdsObj.Name=deptIdsArr[i];
                          deptIdsDropDownArr.push(deptIdsObj);
                          }
                          
                          
                        }
                    });



    $(".currentDateDatepicker").datepicker({
        dateFormat: 'dd-mm-yy'
    }).datepicker("setDate", "0");
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
            return '<span class="label label-primary">' + value + '</span>';
        },
        insertTemplate: function(value) {
        
            return this._insertAuto = $("<input>").autocomplete({
                source: allChequeAutoCompleteData
            });
        },
        filterTemplate: function(value) {
            if (!this.filtering)
                return "";

            var grid = this._grid,
                $result = this._filterAuto = $("<input>").autocomplete({
                    source: allChequeAutoCompleteData
                });

            if (this.autosearch) {
                $result.on("change", function(e) {
                    grid.search();
                });
            }

            return $result;
        },

        editTemplate: function(value) {
            return this._editAuto = $("<input>").autocomplete({
                source: allChequeAutoCompleteData
            }).val(value);
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
    
    //cardNoAutocomplete
     var cardNoAutoComplete = function(config) {
        jsGrid.Field.call(this, config);
    };
    cardNoAutoComplete.prototype = new jsGrid.Field({
        autosearch: true,
        sorter: function(tag1, tag2) {
            return tag1.localeCompare(tag2);
        },
        itemTemplate: function(value) {
            return '<span class="label label-primary">' + value + '</span>';
        },
        insertTemplate: function(value) {
        
        //var cardNoByDepts=getCardNosByDeptId(item.DEPT_ID);
        
            return this._insertAuto = $("<input>").autocomplete({
                source: cardNoData['CAMERA']
            });
        },
        filterTemplate: function(value) {
      //  var cardNoByDepts=getCardNosByDeptId(item.DEPT_ID);
            if (!this.filtering)
                return "";

            var grid = this._grid,
                $result = this._filterAuto = $("<input>").autocomplete({
                    source: cardNoData['CAMERA']
                });

            if (this.autosearch) {
                $result.on("change", function(e) {
                    grid.search();
                });
            }

            return $result;
        },

        editTemplate: function(value,item) {
        var cardNoByDepts=getCardNosByDeptId(item.DEPT_ID);
            return this._editAuto = $("<input>").autocomplete({
                source: cardNoByDepts,//cardNoData[item.DEPT_ID],
                   select: function(event, ui) { 
                   		var membrDetails={};
                  		membrDetails= getMemberDetailsByDeptAndCardNo(item.DEPT_ID,ui.item.value);
                   console.log(JSON.stringify(membrDetails));
	                   	item.FIRST_NAME=membrDetails.FIRST_NAME;  
	                   	item.BANK_ACC_NO=membrDetails.BANK_ACC_NO;   
	                   //	item.MEMBER_ID= membrDetails.MEMBER_ID;  
	                   	  	item.CARD_NO     =ui.item.value;
	             		//$("#jsGrid2").jsGrid("editItem", item);
            }
            }).val(value);
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
    
    //MyDateField
    
   var MyDateField = function(config) {
    jsGrid.Field.call(this, config);
  };
  MyDateField.prototype = new jsGrid.Field({
    sorter: function(date1, date2) {
      return new Date(date1) - new Date(date2);
    },
    itemTemplate: function(value) {
 
    var convertedDate=new Date(value);
var dd = String(convertedDate.getDate()).padStart(2, '0');
var mm = String(convertedDate.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = convertedDate.getFullYear();

convertedDate = dd + '-' + mm + '-' + yyyy;

    return convertedDate;
   
    //return new Date(value).toDateString();
   
    },
    insertTemplate: function(value) {
      return this._insertPicker = $("<input>").datepicker({  dateFormat: 'dd-mm-yy',defaultDate: new Date() });
    },
    editTemplate: function(value) {
    
      return this._editPicker = $("<input>").datepicker({
        dateFormat: 'dd-mm-yy'
    }).datepicker("setDate", new Date(value));
    },
    insertValue: function() {

 
           var insertVal= this._insertPicker.datepicker({
        dateFormat: 'dd-mm-yy'
    }).datepicker("getDate");
    
       
	var dd = String(insertVal.getDate()).padStart(2, '0');
	var mm = String(insertVal.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = insertVal.getFullYear();
	
	insertVal = dd + '-' + mm + '-' + yyyy;
	return insertVal;
    
    },
    editValue: function() {
    
      var editedVal= this._editPicker.datepicker({
        dateFormat: 'dd-mm-yy'
    }).datepicker("getDate");
    
    
       
	var dd = String(editedVal.getDate()).padStart(2, '0');
	var mm = String(editedVal.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = editedVal.getFullYear();
	
	editedVal = dd + '-' + mm + '-' + yyyy;
	return editedVal;
	    
    
    }
  });
  
   
    jsGrid.fields.myTagField = myTagField;
	jsGrid.fields.cardNoAutoComplete = cardNoAutoComplete;
	jsGrid.fields.myDateField = MyDateField;

    $("#jsGrid")
        .jsGrid({

            width: "100%",
            filtering: false,
            editing: true,
            inserting: true,
            sorting: true,
            paging: true,
            autoload: true,
            pageSize: 5,
            pageButtonCount: 5,
            
            pagerContainer: "#externalPager",
            pagerFormat: " Total Pages: {pageCount} &nbsp;&nbsp; {first} {prev} {pages} {next} {last}   &nbsp;&nbsp;  Total Records: {itemCount}",
            pagePrevText: "<",
            pageNextText: ">",
            pageFirstText: "<<",
            pageLastText: ">>",
            pageNavigatorNextText: "&#8230;",
            pageNavigatorPrevText: "&#8230;",
            deleteConfirm: "Do you really want to delete the record?",
           
            controller: {

                //getAllChequeDetails getChequeDetails saveChequeDetails updateChequeDetails deleteChequeDetails
                loadData: function(filter) {

                    // return	 JSON.parse
                    var get_dataObj = "";
                    var save_dataObj = "";
                    var update_dataObj = "";
                    var delete_dataObj = "";

                    if (Object.keys(filter).length == 0) {

                        filter.FromDate = $("#FromDate").val();
                        filter.ToDate = $("#ToDate").val();
                        filter.CHEQUE_NO = $("#ChequeNo").val();
                        filter.AMOUNT = $("#Amount").val();
                        filter.COMPANY_NAME = $("#CompanyName").val();
                        filter.CHEQUE_RECIEVED_FROM = $("#RecievedFrom").val();
                        filter.BANKNAME = $("#BankName").val();
                        filter.CHEQUE_TYPE = $("#ModeOfPayment option").filter(":selected").val();

                    } 
  filter.FromDate = $("#FromDate").val();
                        filter.ToDate = $("#ToDate").val();
                    $.ajax({
                        type: "POST",
                        url: "getAllChequeDetails",
                        async: false,
                        data: filter,
                        success: function(resultData) {
                            save_dataObj = resultData;
                        }
                    });

                    return JSON.parse(save_dataObj);
                },

                insertItem: function(item) {
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: "saveChequeDetails",
                        data: item,
                        success: function(resultData) {
                            alert(resultData);
                            $("#jsGrid").jsGrid("loadData");
                        }
                    });

                    // return JSON.parse(dataObj);
                },

                updateItem: function(item) {
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: "updateChequeDetails",
                        data: item,
                        success: function(resultData) {
                            alert(resultData);
                            $("#jsGrid").jsGrid("loadData");

                        }
                    });


                },

                deleteItem: function(item) {
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: "deleteChequeDetails",
                        data: item,
                        success: function(resultData) {
                            alert(resultData);
                            $("#jsGrid").jsGrid("loadData");
                        }
                    });

                },

            },
            fields: [

                {
                    headerTemplate: function() {
                        return $("<input>").attr("type",
                            "checkbox").attr("id",
                            "selectAllCheckbox")
                    },
                    itemTemplate: function(_, item) {
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
                                            ":checked") ? 
                                            selectItem(
                                            $(this)
                                            .parent()
                                            .next()
                                            .text()) 
                                            
                                            :
                                        unselectItem(
                                        $(this)
                                            .parent()
                                            .next()
                                            .text());
                                  //  loadDisbursmentDetailsGrid();

                                });
                    },
                    align: "center",
                    width: 25,
                    sorting: false
                },
                //CHEQUE_NO,COMPANY_NAME,CHEQUE_DATE,AMOUNT,CHEQUE_RECIEVED_FROM,CHEQUE_TYPE

                {
                    title: "Cheque No",
                    name: "CHEQUE_NO",
                    type: "text",
                    width: 150,
                    validate: {
                        message: "Please enter cheque no!",
                        validator: function(value) {
                            return value.length > 0;
                        }
                    },
                    

                },
                {
                    title: "Type",
                    name: "CHEQUE_TYPE",
                    selectedIndex: 0,
                    type: "select",
                    width: 70,
                    items: [{
                        "Id": "CHEQUE",
                        "Name": "CHEQUE"
                    }, {
                        "Id": "IMPS",
                        "Name": "IMPS"
                    }, {
                        "Id": "NEFT",
                        "Name": "NEFT"
                    }],
                    valueField: "Id",
                    textField: "Name"
                },
                {
                    title: "Company Name",
                    name: "COMPANY_NAME",
                    type: "text",
                    width: 200,
                    validate: {
                        message: "Please enter company name!",
                        validator: function(value) {
                            return value.length > 0;
                        }
                    }
                },
                {
                    title: "Recieved From",
                    name: "CHEQUE_RECIEVED_FROM",
                    type: "text",
                    width: 200,
                    validate: {
                        message: "Please enter cheque recieved from!",
                        validator: function(value) {
                            return value.length > 0;
                        }
                    }
                },
                                {
                    title: "Phone No",
                    name: "PHONE_NO",
                    type: "text",
                    width: 200,
                    filtering: false,
                    validate: {
                        message: "Please enter cheque phone no!",
                        validator: function(value) {
                            return value.length > 0;
                        }
                    }
                },
                {
                    title: "Cheque Date",
                    name: "CHEQUE_DATE",
                    type: "myDateField",
                  editing:true,
                  inserting:true,
                    width: 100,
                    validate: {
                        message: "Please enter cheque date",
                        validator: function(value) {
                            return value.length > 0;
                        }
                    }
                }, {
                    title: "Amount",
                    name: "AMOUNT",
                    type: "text",
                    validate: {
                        message: "Please enter amount",
                        validator: function(value) {
                            return value.length > 0;
                        }
                    }
                }, {
                    title: "Bank Name",
                    name: "BANKNAME",
                    type: "text",
                    validate: {
                        message: "Please enter bank name",
                        validator: function(value) {
                            return value.length > 0;
                        }
                    }
                }, {
                    title: "Remarks",
                    name: "FIELD1",
                    type: "text"
 
                },

                {
                    type: "control"
                }
            ]
            ,
            rowClick: function(args) {
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
        allChequeAutoCompleteData=selectedItems;
	loadChequeDisburseDetailsGrid();
        
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
        allChequeAutoCompleteData=selectedItems;
        loadChequeDisburseDetailsGrid();
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
          allChequeAutoCompleteData=selectedItems;
    });

  /*  $(".config-panel input[type=checkbox]").on("click", function() {
        var $cb = $(this);
        $("#jsGrid").jsGrid("option", $cb.attr("id"), $cb.is(":checked"));
    });*/









});


function loadChequeDisburseDetailsGrid(){

       
    $("#jsGrid2").jsGrid({
        width: "100%",
        filtering: true,
        editing: true,
        inserting: true,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 5,
            pageButtonCount: 5,
            pagerContainer: "#externalPager2",
            pagerFormat: " Total Pages: {pageCount} &nbsp;&nbsp; {first} {prev} {pages} {next} {last}   &nbsp;&nbsp;  Total Records: {itemCount}",
            pagePrevText: "<",
            pageNextText: ">",
            pageFirstText: "<<",
            pageLastText: ">>",
            pageNavigatorNextText: "&#8230;",
            pageNavigatorPrevText: "&#8230;",
            deleteConfirm: "Do you really want to delete the record?",
        controller: {

                loadData: function(filter) {
 					var save_dataObj = "{}";
                    if (selectedItems.length > 0) {
                        filter.SELECTED_CHEQUE_NO = selectedItems.toString();
                    $.ajax({
                        type: "POST",
                        url: "getSelectedChequeDisburseDetails",
                        async: false,
                        data: filter,
                        success: function(resultData) {
                            save_dataObj = resultData;
                             $("#fieldset_jsGrid2").show();
                        }
                    });
                    } else {
                    
                    		$("#fieldset_jsGrid2").hide();
                    }

                    return JSON.parse(save_dataObj)
                },

                insertItem: function(item) {
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: "saveChequeDisburseDetails",
                        data: item,
                        cache:false,
                        success: function(resultData) {
                           $("#jsGrid2").jsGrid("deleteItem", item);
                           alert(resultData);
                           
                        }
                    });

                    // return JSON.parse(dataObj);
                },

                updateItem: function(item) {
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: "updateChequeDisburseDetails",
                        data: item,
                        success: function(resultData) {
                            alert(resultData);
                             $("#jsGrid2").jsGrid("loadData");

                        }
                    });


                },

                deleteItem: function(item) {
                    $.ajax({
                        type: "POST",
                        async: false,
                        url: "deleteChequeDesburseDetails",
                        data: item,
                        success: function(resultData) {
                            alert(resultData);
                            $("#jsGrid2").jsGrid("loadData");
                        }
                    });

                },

            },//contorller ends
           // CHEQUE_NO, MEMEBER_ID,BANK_ACC_NO,AMOUNT
        fields: [
        
              {
        		title:"Record Id",
                name: "CHQ_DSB_ID",
                type: "text",
                editing: false ,
                inserting: false,
               visible: false,
                 },
 
                {
        		title:"Cheque No",
                name: "CHEQUE_NO",
                type: "myTagField",
                editing: true ,
                inserting: true,
                width: 150 },
            {
            	title:"Department",
                name: "DEPT_ID",
                type: "select",
    			items: deptIdsDropDownArr,
    			validate: {
                        message: "Please select proper department!",
                        validator: function(value) {
                        if(value=='ALL')
                        return false;
                        else return true;
                           
                        }
                    },
    			valueField: "Id",
                textField: "Name"
                
            },
             {
            	title:"Card No",
                name: "CARD_NO",
                type: "cardNoAutoComplete",
            },
             {
            	title:"Name",
                name: "FIRST_NAME",
                editing: false ,
                inserting: false,
                filtering: false,
                type: "text"
             
            },

         {
            	title:"Bank Account No",
                name: "BANK_ACC_NO",
                editing: false ,
                inserting: false,
                filtering: false,
                type: "text"
            
            },
            
             
             	{title:"Amount",
                name: "AMOUNT",
                type: "text"
               
                
            },  
             	{title:"Our Cheque",
                name: "PAID_CHQ_NO",
                type: "text"
               
                
            },{
                type: "control"
            }
        ] 
                    ,
            rowClick: function(args) {
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
}
function exportToExcel(exprotFrameid,exportType) {
    var selectedCheques = selectedItems.toString();
    if(selectedCheques==null || selectedCheques==''){
    alert("Please select atleast one record!");
    $("#downLoadTypeSelect")[0].selectedIndex=0;
    return false;
    }
    if(document.getElementById(exprotFrameid)!=null)
    document.getElementById(exprotFrameid).src = context + "/exportToExcel?ExcelType="+exportType+"&SELECTED_ITEMS=" + selectedCheques;
    else{
    alert('Error while occured while downloading...');
    $("#downLoadTypeSelect")[0].selectedIndex=0;
    }
}

function exportAsText(exprotFrameid,exportType) {
    var selectedCheques = selectedItems.toString();
       if(selectedCheques==null || selectedCheques==''){
    alert("Please select atleast one record!");
    $("#downLoadTypeSelect")[0].selectedIndex=0;
    return false;
    }
    if(document.getElementById(exprotFrameid)!=null)
    document.getElementById(exprotFrameid).src = context + "/download?ExcelType="+exportType+"&SELECTED_ITEMS=" + selectedCheques;
    else{
    alert('Error while occured while downloading...');
    $("#downLoadTypeSelect")[0].selectedIndex=0;
    }
}

function downloadBySelection(){

//CHEQUEDISBURSEDETAILS,CHEQUEDETAILS,CHEQUEDETAILS
var downLoadType=$("#downLoadTypeSelect option").filter(":selected").val();
if(downLoadType=='ChequeDetails'){
exportToExcel('exportToExcelFrame','CHEQUEDETAILS');
}
else if(downLoadType=='ChequeDisburseDetails'){
exportToExcel('exportToExcelFrame2','CHEQUEDISBURSEDETAILS');
}
else if(downLoadType=='BankFormat'){
	var data = $("#jsGrid2").jsGrid("option", "data");
	if(!(data.length>0)){
	alert("No records found to download!");
	$("#downLoadTypeSelect")[0].selectedIndex=0;
	return false;
	}else
	exportAsText('exportAsTextFrame','CHEQUEDISBURSEDETAILS');
}else
alert("Select Download Type!");





//var data = $("#"+gridId).jsGrid("option", "data");



}


function deleteSelectedCeques() {

    if (selectedItems.length > 0) {

        $.ajax({
            type: "POST",
            async: false,
            url: "deleteCheques",
            data: {
                cheques: selectedItems.toString()

            },
            success: function(resultData) {
                alert(resultData);
                $("#jsGrid").jsGrid("refresh");
            }
        });
    } else
        alert("Please select atleast one cheque to delete!");

}



//
function getMemberDetailsByDeptAndCardNo(deptId,cardNo){
var resultData={};
$.ajax({
    type: "POST",
    async: false,
    url: "getMemberDetailsByDeptAndCardNo",
    data:{"CARD_NO":cardNo,"DEPT_ID":deptId} ,
    success: function(data) {
resultData=JSON.parse(data);
    }
});
return resultData;
}
function getCardNosByDeptId(deptId){
var resultData={};
$.ajax({
    type: "POST",
    async: false,
    url: "getCardNosByDeptId",
    data:{"DEPT_ID":deptId} ,
    success: function(data) {
    console.log("getCardNosByDeptId ------ "+ data);
resultData=JSON.parse(data);
    }
});
return resultData;
}
function getChecqueData() {
    $("#jsGrid").jsGrid("loadData");
}