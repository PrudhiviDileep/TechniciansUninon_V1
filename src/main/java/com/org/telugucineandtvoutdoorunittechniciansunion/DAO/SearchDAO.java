package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Repository
public class SearchDAO {
  @Autowired
  public DataAccess dataAccess;
  
  @Autowired
  public TeluguCineAndTVOutDoorUnitTechniciansUnionDAO registerDAO;
  
  @Autowired
  public MiscellaneousDAO miscDAO;
  
  @Autowired
  public LoanDAO loanDAO;
  
  @Autowired
  ServletContext context;
  
  Utils utils = new Utils();
  
  private static final String UPLOADED_FOLDER = "\\AppFiles\\Uploads";
  
  private static final String DOWNLOAD_FOLDER = "\\AppFiles\\Downloads";
  
  public String search(HttpServletRequest request, Map<String, Object> model) {
    return "search";
  }
  
  @Transactional
  public JSONObject getCommonSearchResults(HttpServletRequest request) {
    JSONObject finalResultObj = new JSONObject();
    JSONObject otherPanelResultObj = new JSONObject();
    JSONObject topPanelResultObj = new JSONObject();
    String pageId = request.getParameter("pageId");
    String cardNo = request.getParameter("cardNo");
    String deptId = request.getParameter("deptId");
    try {
      int cardNumber = Integer.parseInt(cardNo);
      if (pageId != null && !"".equalsIgnoreCase(pageId) && cardNo != null && !"".equalsIgnoreCase(cardNo)) {
        pageId = pageId.trim();
        finalResultObj.put("PAGE_ID", pageId);
        Registration registredMember = this.miscDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
        if (!"UPDATE_MEMBER_DETAILS".equalsIgnoreCase(pageId)) {
          otherPanelResultObj = new JSONObject();
          topPanelResultObj = new JSONObject();
          topPanelResultObj = this.miscDAO.getTopPanel(cardNumber, deptId, pageId);
          String topPanelResultCode = (String)topPanelResultObj.get("TOP_PANEL_RESULT_CODE");
          if (topPanelResultCode != null && !"".equalsIgnoreCase(topPanelResultCode))
            if (topPanelResultCode.equalsIgnoreCase("200") || topPanelResultCode.equalsIgnoreCase("300")) {
              finalResultObj.put("FINAL_RESULT_CODE", topPanelResultCode);
              finalResultObj.put("ERROR_MSG", topPanelResultObj.get("TOP_PANEL_ERROR_MSG"));
              finalResultObj.put("TOP_PANEL_DETAILS", topPanelResultObj);
            } else if (topPanelResultCode.equalsIgnoreCase("400")) {
              JSONObject obj = this.utils.requestParamsToJSON((ServletRequest)request);
              if ("CARD_BALANCE_PAYMENT".equalsIgnoreCase(pageId)) {
                otherPanelResultObj = (JSONObject)JSONValue.parse(getDetialsByPageId(obj));
                finalResultObj.put("MEMBERSHIP_AMOUNT", Integer.valueOf(registredMember.getMembershipAmount()));
                finalResultObj.put("PAID_AMOUNT", Integer.valueOf(registredMember.getPaidAmount()));
                finalResultObj.put("BALANCE_AMOUNT", registredMember.getCardBalance());
                finalResultObj.put("RECEIPT_NO", registredMember.getReceiptNo());
                finalResultObj.put("MEMBER_ID", registredMember.getRegistrationPK().getMemberId());
                finalResultObj.put("PAYMENT_CONF_ID", this.utils.convertPaymentConfigDetailsToHTMLSelect(
                      this.miscDAO.getPaymentConfigDetialsForSelect(deptId, "REGISTRATION")));
              } else if ("PAY_SUBSCRIPTION_AMOUNT".equalsIgnoreCase(pageId) || 
                "GET_SCUBSRIPTION_DETAILS".equalsIgnoreCase(pageId)) {
                otherPanelResultObj = (JSONObject)JSONValue.parse(getDetialsByPageId(obj));
                if ("PAY_SUBSCRIPTION_AMOUNT".equalsIgnoreCase(pageId)) {
                  finalResultObj.put("SUBSCRIPTION_YEAR", 
                      this.utils.setSubscribedYearsSelectedToCurrentYear());
                  finalResultObj.put("PAYMENT_CONF_ID", this.utils.convertPaymentConfigDetailsToHTMLSelect(
                        this.miscDAO.getPaymentConfigDetialsForSelect(deptId, "SUBSCRIPTION")));
                } 
              } else if ("LOAN_SANCTION".equalsIgnoreCase(pageId)) {
                otherPanelResultObj = (JSONObject)JSONValue.parse(getDetialsByPageId(obj));
              } else if ("LOAN_PAYMENT".equalsIgnoreCase(pageId)) {
                otherPanelResultObj = (JSONObject)JSONValue.parse(getDetialsByPageId(obj));
              } else if ("LOAN_SUMMARY".equalsIgnoreCase(pageId)) {
                otherPanelResultObj = (JSONObject)JSONValue.parse(getDetialsByPageId(obj));
              } 
              if (otherPanelResultObj != null && !otherPanelResultObj.isEmpty() && 
                otherPanelResultObj.get("OTHER_PANEL_RESULT_CODE") != null) {
                String otherPanelResultCode = (String)otherPanelResultObj
                  .get("OTHER_PANEL_RESULT_CODE");
                finalResultObj.put("TOP_PANEL_DETAILS", topPanelResultObj);
                if (otherPanelResultCode.equalsIgnoreCase("300")) {
                  finalResultObj.put("ERROR_MSG", topPanelResultObj.get("OTHER_PANEL_ERROR_MSG"));
                } else if (otherPanelResultCode.equalsIgnoreCase("200") || 
                  otherPanelResultCode.equalsIgnoreCase("400")) {
                  finalResultObj.put(pageId, otherPanelResultObj.get("DATA_DETAILS"));
                  if ("LOAN_SUMMARY".equalsIgnoreCase(pageId))
                    finalResultObj.put("LOAN_DETAILS", otherPanelResultObj.get("DATA_DETAILS1")); 
                } 
                finalResultObj.put("FINAL_RESULT_CODE", otherPanelResultCode);
              } else {
                finalResultObj.put("FINAL_RESULT_CODE", "400");
                finalResultObj.put("TOP_PANEL_DETAILS", topPanelResultObj);
                finalResultObj.put(pageId, "");
              } 
            }  
        } else if ("UPDATE_MEMBER_DETAILS".equalsIgnoreCase(pageId)) {
          otherPanelResultObj = new JSONObject();
          if (registredMember != null) {
            otherPanelResultObj = this.registerDAO
              .getMemberDetails(registredMember.getRegistrationPK().getMemberId());
            String otherPanelResultCode = (String)otherPanelResultObj.get("FINAL_RESULT_CODE");
            finalResultObj.put("FINAL_RESULT_CODE", otherPanelResultCode);
            if (otherPanelResultCode.equalsIgnoreCase("200") || 
              otherPanelResultCode.equalsIgnoreCase("300")) {
              finalResultObj.put("ERROR_MSG", topPanelResultObj.get("ERROR_MSG"));
            } else if (otherPanelResultCode.equalsIgnoreCase("400")) {
              finalResultObj.put(pageId, otherPanelResultObj.get("DATA_DETAILS"));
            } 
          } else {
            finalResultObj.put("FINAL_RESULT_CODE", "200");
            finalResultObj.put("ERROR_MSG", 
                "No registered member found with  deptId =" + deptId + " and  cardNo =" + cardNo);
            finalResultObj.put("PAGE_ID", pageId);
          } 
        } else {
          finalResultObj.put("FINAL_RESULT_CODE", "300");
          finalResultObj.put("ERROR_MSG", 
              "Unable to perform any action due to 'OUT OF SCOPE OPERATION REQUEST FROM >> '" + pageId);
          finalResultObj.put("PAGE_ID", pageId);
        } 
      } else {
        finalResultObj.put("FINAL_RESULT_CODE", "300");
        finalResultObj.put("ERROR_MSG", " No Operation specified ! ");
        finalResultObj.put("PAGE_ID", pageId);
      } 
    } catch (Exception e) {
      finalResultObj.put("FINAL_RESULT_CODE", "300");
      finalResultObj.put("ERROR_MSG", 
          "Technical error occured while performing action, the detials are " + e.getMessage());
      finalResultObj.put("PAGE_ID", pageId);
      e.printStackTrace();
    } 
    return finalResultObj;
  }
  
  @Transactional
  public String getDetialsByPageId(JSONObject items) {
    JSONArray resutlJsnArr = new JSONArray();
    JSONObject resultObj = new JSONObject();
    int colCount = 0;
    String table = "";
    table = "<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'>";
    table = String.valueOf(String.valueOf(table)) + "<thead><tr><th>DATE</th><th>NAME</th><th>FATHER NAME</th><th>DEPT ID</th><th>CARD NO</th><th>AMOUNT</th><th>Update</th><th>Delete</th></tr></thead>";
    String theadStr = "<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'><thead><tr><th align='center'>SNo</th>";
    String tbody = "<tbody>";
    List list = null;
    SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
    try {
      String pageId = (String)items.get("pageId");
      String cardNo = (String)items.get("cardNo");
      String deptId = (String)items.get("deptId");
      Registration registeredMember = this.miscDAO.getMemberDetailsByDeptIdAndCardNo(deptId, cardNo);
      String memberId = registeredMember.getRegistrationPK().getMemberId();
      String query = " from ";
      Map<String, Object> parametersMap = new HashMap<String, Object>();
      parametersMap.put("memberId", memberId);
      if ("CARD_BALANCE_PAYMENT".equalsIgnoreCase(pageId)) {
        colCount = 7;
        theadStr = String.valueOf(String.valueOf(theadStr)) + "<th align='left'>Paid Amt</th><th align='left'>Reciept No</th><th align='left'>Date of Payment</th><th align='left'>Remarks</th><th align='left'>Update</th><th align='left'>Delete</th>";
        query = String.valueOf(String.valueOf(query)) + " MembershipPayments where membershipPaymentsPK.memberId=:memberId order by paidDate ";
      } else if ("PAY_SUBSCRIPTION_AMOUNT".equalsIgnoreCase(pageId) || 
        "GET_SCUBSRIPTION_DETAILS".equalsIgnoreCase(pageId)) {
        colCount = 10;
        theadStr = String.valueOf(String.valueOf(theadStr)) + "<th align='left'>Description</th><th align='left'>Subscriptoin Amt</th><th align='left'>Paid Amt</th><th align='left'>Receipt No</th><th align='left'>Year</th><th align='left'>Status</th><th align='left'>Paid Date</th>";
        theadStr = String.valueOf(String.valueOf(theadStr)) + "<th align='left'>Update</th><th align='left'>Delete</th>";
        query = String.valueOf(String.valueOf(query)) + " SubscriptionPayments where memberId=:memberId and category='SUBSCRIPTION' order by paidDate ";
      } else if ("LOAN_PAYMENT".equalsIgnoreCase(pageId) || "LOAN_SUMMARY".equalsIgnoreCase(pageId)) {
        colCount = 8;
        theadStr = String.valueOf(String.valueOf(theadStr)) + "<th align='left'>Receipt No</th><th align='left'>Date of Payment</th><th align='left'>Paid Amount</th><th align='left'>Particulars</th><th>Update</th><th>Delete</th>";
        String loanStatus = registeredMember.getCurrentLoanStatus();
        String str = this.loanDAO.getLoanDetails(memberId);
        JSONObject loandetails = new JSONObject();
        if (str != null && !"".equalsIgnoreCase(str)) {
          loandetails = (JSONObject)JSONValue.parse(str);
          String lonDetilsResultcode = (String)loandetails.get("LOAN_DETAILS_RESULT_CODE");
          if (lonDetilsResultcode != null && !"".equalsIgnoreCase(lonDetilsResultcode))
            if (lonDetilsResultcode.equalsIgnoreCase("200") || 
              lonDetilsResultcode.equalsIgnoreCase("300")) {
              tbody = String.valueOf(String.valueOf(tbody)) + "<tr><td colspan='" + colCount + "' align='center'> No data found !</td></tr>";
              tbody = String.valueOf(String.valueOf(tbody)) + "</tbody>";
              resultObj.put("OTHER_PANEL_RESULT_CODE", lonDetilsResultcode);
              resultObj.put("OTHER_PANEL_ERROR_MSG", loandetails.get("LOAN_DETAILS_ERROR_MSG"));
              resultObj.put("DATA_DETAILS", String.valueOf(String.valueOf(theadStr)) + tbody + "</table>");
            } else if (lonDetilsResultcode.equalsIgnoreCase("400")) {
              JSONArray jsnArry = (JSONArray)loandetails.get("LOAN_RECOVERY_DETAILS");
              JSONArray loanDetailsArr = (JSONArray)loandetails.get("LOAN_DETAILS");
              if (loanDetailsArr != null && loanDetailsArr.size() > 0) {
                table = String.valueOf(String.valueOf(table)) + "<tbody>";
                for (int i = 0; i < loanDetailsArr.size(); i++) {
                  JSONObject oobj = (JSONObject)loanDetailsArr.get(i);
                  String ccc = this.utils.convertNullToEmptyString(oobj.get("LOAN_SANCTIONED_DATE"));
                  String[] date = ccc.split("-");
                  table = String.valueOf(String.valueOf(table)) + "<tr>" + 
                    "<td>" + date[2] + "/" + date[1] + "/" + date[0] + "</td>" + 
                    "<td>" + this.utils.convertNullToEmptyString(oobj.get("FIRST_NAME")) + "</td>" + 
                    "<td>" + this.utils.convertNullToEmptyString(oobj.get("FATHER_NAME")) + "</td>" + 
                    "<td>" + this.utils.convertNullToEmptyString(oobj.get("DEPT_ID")) + "</td>" + 
                    "<td>" + this.utils.convertNullToEmptyString(oobj.get("CARD_NO")) + "</td>" + 
                    "<td>" + this.utils.convertNullToEmptyString(oobj.get("LOAN_AMOUNT")) + "</td>";
                  table = String.valueOf(String.valueOf(table)) + "<td align='left'><form action='updateLoanSanctionDetailsForm' method='POST'  ><input type='hidden' name='update_LoanSanctionDetails_memberId' id='del_LoanSancitonDetails_memberId' value='" + 
                    
                    memberId + "'>" + 
                    
                    "<input type='hidden' name='update_LoanSanctionDetails_loanId' id='del_LoanSancitonDetails_loanId'" + 
                    " value='" + this.utils.convertNullToEmptyString(oobj.get("LOAN_ID")) + "' >" + 
                    "<input type='hidden' name='update_LoanSanctionDetails_loanSanctionDate' id='del_LoanSancitonDetails_loanSanctionDate'" + 
                    " value='" + date[2] + "/" + date[1] + "/" + date[0] + "'>" + 
                    
                    "<input type='hidden' name='update_LoanSanctionDetails_cardNo' id='del_LoanSancitonDetails_cardNo' value='" + 
                    cardNo + "'>" + 
                    
                    "<input type='hidden' name='update_LoanSanctionDetails_deptId' id='del_LoanSancitonDetails_deptId' value='" + 
                    this.utils.convertNullToEmptyString(oobj.get("DEPT_ID")) + "'>" + 
                    
                    "<input type='hidden' name='update_LoanSanctionDetails_loanAmount' id='del_LoanSancitonDetails_loanAmount' " + 
                    "value='" + this.utils.convertNullToEmptyString(oobj.get("LOAN_AMOUNT")) + "'>" + 
                    "<input type='hidden' name='update_LoanSanctionDetails_fatherName' id='del_LoanSancitonDetails_fatherName' " + 
                    " value='" + this.utils.convertNullToEmptyString(oobj.get("FATHER_NAME")) + "'>" + 
                    "<input type='hidden' name='update_LoanSanctionDetails_pageId' id='del_LoanSancitonDetails_pageId' " + 
                    "value='" + "UPDATE_LOAN_SANCTION" + "' >" + "<input type='submit' value='Update'>" + 
                    "</form></td>";
                  table = String.valueOf(String.valueOf(table)) + "<td align='left'><form action='deleteLoanSanctionDetailsForm' method='POST'  ><input type='hidden' name='update_LoanSanctionDetails_memberId' id='del_LoanSancitonDetails_memberId' value='" + 
                    
                    memberId + "'>" + 
                    
                    "<input type='hidden' name='update_LoanSanctionDetails_loanId' id='del_LoanSancitonDetails_loanId'" + 
                    " value='" + this.utils.convertNullToEmptyString(oobj.get("LOAN_ID")) + "' >" + 
                    "<input type='hidden' name='update_LoanSanctionDetails_loanSanctionDate' id='del_LoanSancitonDetails_loanSanctionDate'" + 
                    " value='" + date[2] + "/" + date[1] + "/" + date[0] + "'>" + 
                    
                    "<input type='hidden' name='delete_LoanRecoveryDetails_cardNo' id='del_LoanSancitonDetails_cardNo' value='" + 
                    cardNo + "'>" + 
                    
                    "<input type='hidden' name='delete_LoanRecoveryDetails_deptId' id='del_LoanSancitonDetails_deptId' value='" + 
                    this.utils.convertNullToEmptyString(oobj.get("DEPT_ID")) + "'>" + 
                    
                    "<input type='hidden' name='update_LoanSanctionDetails_loanAmount' id='del_LoanSancitonDetails_loanAmount' " + 
                    "value='" + this.utils.convertNullToEmptyString(oobj.get("LOAN_AMOUNT")) + "'>" + 
                    "<input type='hidden' name='update_LoanSanctionDetails_fatherName' id='del_LoanSancitonDetails_fatherName' " + 
                    " value='" + this.utils.convertNullToEmptyString(oobj.get("FATHER_NAME")) + "'>" + 
                    "<input type='hidden' name='delete_LoanRecoveryDetails_pageId' id='del_LoanSancitonDetails_pageId' " + 
                    "value='" + "DELETE_LOAN_SANCTION" + "' >" + "<input type='submit' value='Delete'>" + 
                    "</form></td>";
                  table = String.valueOf(String.valueOf(table)) + "</tr>";
                } 
                table = String.valueOf(String.valueOf(table)) + "</tbody>";
              } else {
                table = String.valueOf(String.valueOf(table)) + "<tbody><tr><td colspan='6' align='center'> No Data found</td></tr></tbody>";
              } 
              table = String.valueOf(String.valueOf(table)) + "</table>";
              int totalPaidAmount = 0;
              int totalLoanAmount = this.miscDAO.getTotalLoanAmount(memberId);
              if (jsnArry != null && jsnArry.size() > 0) {
                int old_bal = totalLoanAmount;
                for (int i = 0; i < jsnArry.size(); i++) {
                  JSONObject jsnObj = (JSONObject)jsnArry.get(i);
                  int paidAmount = Integer.parseInt((String)jsnObj.get("PAID_AMOUNT"));
                  old_bal -= paidAmount;
                  tbody = String.valueOf(String.valueOf(tbody)) + "<tr><td>" + (i + 1) + "</td>";
                  tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(jsnObj.get("RECEIPT_NO")) + "</td>";
                  tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + (String)jsnObj.get("PAID_DATE") + "</td>";
                  tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(jsnObj.get("PAID_AMOUNT")) + "</td>";
                  tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(jsnObj.get("REMARKS")) + "</td>";
                  tbody = String.valueOf(String.valueOf(tbody)) + "<td align='left'><form action='updateLoanRecoveryDetailsForm' method='POST'  ><input type='hidden' name='update_LoanRecoveryDetails_memberId' id='upd_LoanRecoveryDetails_memberId' value='" + 
                    
                    this.utils.convertNullToEmptyString(jsnObj.get("MEMBER_ID")) + "'>" + 
                    "<input type='hidden' name='update_LoanRecoveryDetails_transId' id='upd_LoanRecoveryDetails_transId' value='" + 
                    this.utils.convertNullToEmptyString(jsnObj.get("TRANSACTION_ID")) + "' >" + 
                    "<input type='hidden' name='update_LoanRecoveryDetails_loanId' id='upd_LoanRecoveryDetails_loanId' value='" + 
                    this.utils.convertNullToEmptyString(jsnObj.get("LOAN_ID")) + "' >" + 
                    "<input type='hidden' name='update_LoanRecoveryDetails_cardNo' id='upd_LoanRecoveryDetails_cardNo' value='" + 
                    cardNo + "'>" + 
                    "<input type='hidden' name='update_LoanRecoveryDetails_deptId' id='upd_LoanRecoveryDetails_deptId' value='" + 
                    deptId + "'>" + 
                    "<input type='hidden' name='update_LoanRecoveryDetails_pageId' id='upd_LoanRecoveryDetails_pageId' value='" + 
                    pageId + "' >" + "<input type='submit' value='Update'>" + 
                    "</form></td>";
                  tbody = String.valueOf(String.valueOf(tbody)) + "<td align='left'><form action='deleteLoanRecoveryDetailsForm' method='POST'  ><input type='hidden' name='delete_LoanRecoveryDetails_memberId' id='del_LoanRecoveryDetails_memberId' value='" + 
                    
                    this.utils.convertNullToEmptyString(jsnObj.get("MEMBER_ID")) + "'>" + 
                    "<input type='hidden' name='delete_LoanRecoveryDetails_transId' id='del_LoanRecoveryDetails_transId' value='" + 
                    this.utils.convertNullToEmptyString(jsnObj.get("TRANSACTION_ID")) + "' >" + 
                    "<input type='hidden' name='delete_LoanRecoveryDetails_loanId' id='del_LoanRecoveryDetails_loanId' value='" + 
                    this.utils.convertNullToEmptyString(jsnObj.get("LOAN_ID")) + "' >" + 
                    "<input type='hidden' name='delete_LoanRecoveryDetails_cardNo' id='del_LoanRecoveryDetails_cardNo' value='" + 
                    cardNo + "'>" + 
                    "<input type='hidden' name='delete_LoanRecoveryDetails_deptId' id='del_LoanRecoveryDetails_deptId' value='" + 
                    deptId + "'>" + 
                    "<input type='hidden' name='delete_LoanRecoveryDetails_pageId' id='del_LoanRecoveryDetails_pageId' value='" + 
                    pageId + "' >" + "<input type='submit' value='Delete'>" + 
                    "</form></td></tr>";
                  totalPaidAmount += Integer.parseInt(this.utils.convertNullToEmptyString(jsnObj.get("PAID_AMOUNT")));
                } 
                theadStr = String.valueOf(String.valueOf(theadStr)) + "</tr></thead>";
                resultObj.put("OTHER_PANEL_RESULT_CODE", "400");
                resultObj.put("DATA_DETAILS", String.valueOf(String.valueOf(theadStr)) + tbody + "</table>");
                table = String.valueOf(String.valueOf(table)) + "<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'>";
                table = String.valueOf(String.valueOf(table)) + "<thead><tr><th>TOTAL LOAN AMOUNT</th><th>TOTAL PAID AMOUNT</th><th>BALANCE</th></tr></thead>";
                table = String.valueOf(String.valueOf(table)) + "<tbody><tr><td align='center'>" + totalLoanAmount + "</td><td align='center'>" + totalPaidAmount + "</td><td align='center'>" + (totalLoanAmount - totalPaidAmount) + "</td></tr></tbody></table>";
                resultObj.put("DATA_DETAILS1", table);
              } else {
                tbody = String.valueOf(String.valueOf(tbody)) + "<tr><td colspan='" + (colCount + 2) + 
                  "' align='center'> No data found !</td></tr>";
                tbody = String.valueOf(String.valueOf(tbody)) + "</tbody>";
                resultObj.put("OTHER_PANEL_RESULT_CODE", "200");
                resultObj.put("OTHER_PANEL_ERROR_MSG", "No data found !");
                resultObj.put("DATA_DETAILS", String.valueOf(String.valueOf(theadStr)) + tbody + "</table>");
                table = String.valueOf(String.valueOf(table)) + "<table border='1' cellspacing='0' cellpadding='5' style='border-color: #EEE'>";
                table = String.valueOf(String.valueOf(table)) + "<thead><tr><th>TOTAL LOAN AMOUNT</th><th>TOTAL PAID AMOUNT</th><th>BALANCE</th></tr></thead>";
                table = String.valueOf(String.valueOf(table)) + "<tbody><tr><td align='center'>" + totalLoanAmount + "</td><td align='center'>" + totalPaidAmount + "</td><td align='center'>" + (totalLoanAmount - totalPaidAmount) + "</td></tr></tbody></table>";
                resultObj.put("DATA_DETAILS1", table);
              } 
            }  
        } else {
          tbody = String.valueOf(String.valueOf(tbody)) + "<tr><td colspan='" + colCount + "' align='center'> No data found !</td></tr>";
          tbody = String.valueOf(String.valueOf(tbody)) + "</tbody>";
          resultObj.put("OTHER_PANEL_RESULT_CODE", "200");
          resultObj.put("OTHER_PANEL_ERROR_MSG", "No data found !");
          resultObj.put("DATA_DETAILS", String.valueOf(String.valueOf(theadStr)) + tbody + "</table>");
          table = String.valueOf(String.valueOf(table)) + "<tbody><tr><td colspan='6' align='center'> No Data found</td></tr></tbody></table>";
          resultObj.put("DATA_DETAILS1", table);
        } 
      } 
      if (!"LOAN_PAYMENT".equalsIgnoreCase(pageId) && !"LOAN_SANCTION".equalsIgnoreCase(pageId) && !"LOAN_SUMMARY".equalsIgnoreCase(pageId)) {
        list = this.dataAccess.queryWithParams(query, parametersMap);
        theadStr = String.valueOf(String.valueOf(theadStr)) + "</tr></thead>";
        if (list != null && list.size() > 0) {
          int k = 1;
          for (int j = 0; j < list.size(); j++) {
            JSONObject detailsObj = new JSONObject();
            tbody = String.valueOf(String.valueOf(tbody)) + "<tr><td align='center' >" + k + "</td>";
            if (pageId.equalsIgnoreCase("CARD_BALANCE_PAYMENT")) {
              MembershipPayments membershipPayments = (MembershipPayments)list.get(j);
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(Integer.valueOf(membershipPayments.getPaidAmount())) + 
                "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(membershipPayments.getReceiptNo()) + 
                "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(
                  dateFormater.format(membershipPayments.getPaidDate())) + "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(membershipPayments.getRemarks()) + "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td align='left'><form action='updateCardBalanceForm' method='POST'  ><input type='hidden' name='update_cardBalance_memberId' id='upd_cardBalance_memberId' value='" + 
                
                membershipPayments.getMembershipPaymentsPK().getMemberId() + "'>" + 
                "<input type='hidden' name='update_cardBalance_transId' id='upd_cardBalance_transId' value='" + 
                membershipPayments.getMembershipPaymentsPK().getTransactionId() + "' >" + 
                "<input type='hidden' name='update_cardBalance_cardNo' id='upd_cardBalance_cardNo' value='" + 
                cardNo + "'>" + 
                "<input type='hidden' name='update_cardBalance_deptId' id='upd_cardBalance_deptId' value='" + 
                deptId + "'>" + 
                "<input type='hidden' name='update_cardBalance_pageId' id='upd_cardBalance_pageId' value='" + 
                pageId + "' >" + 
                "<input type='submit' value='Update'>" + "</form></td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td align='left'><form action='deleteCardBalanceForm' method='POST'  ><input type='hidden' name='delete_cardBalance_memberId' id='del_cardBalance_memberId' value='" + 
                
                membershipPayments.getMembershipPaymentsPK().getMemberId() + "'>" + 
                "<input type='hidden' name='delete_cardBalance_transId' id='del_cardBalance_transId' value='" + 
                membershipPayments.getMembershipPaymentsPK().getTransactionId() + "' >" + 
                "<input type='hidden' name='delete_cardBalance_cardNo' id='del_cardBalance_cardNo' value='" + 
                cardNo + "'>" + 
                "<input type='hidden' name='delete_cardBalance_deptId' id='del_cardBalance_deptId' value='" + 
                deptId + "'>" + 
                "<input type='hidden' name='delete_cardBalance_pageId' id='del_cardBalance_pageId' value='" + 
                pageId + "' >" + 
                
                "<input type='submit' value='Delete'>" + "</form></td>";
            } else if (pageId.equalsIgnoreCase("PAY_SUBSCRIPTION_AMOUNT") || 
              pageId.equalsIgnoreCase("GET_SCUBSRIPTION_DETAILS")) {
              SubscriptionPayments subscriptionPayments = (SubscriptionPayments)list.get(j);
              String subscriptionStringFormat = "Rs." + subscriptionPayments.getSubscriptionAmount() + "/" + 
                "Rec No." + subscriptionPayments.getSubscriptionPaymentsPK().getReceiptNo() + "@" + 
                dateFormater.format(subscriptionPayments.getPaidDate());
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(subscriptionStringFormat) + "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + 
                this.utils.convertNullToEmptyString(Integer.valueOf(subscriptionPayments.getSubscriptionAmount())) + 
                "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + 
                this.utils.convertNullToEmptyString(Integer.valueOf(subscriptionPayments.getSubscriptionAmount())) + 
                "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(
                  subscriptionPayments.getSubscriptionPaymentsPK().getReceiptNo()) + "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + 
                this.utils.convertNullToEmptyString(
                  Integer.valueOf(subscriptionPayments.getSubscriptionPaymentsPK().getSubscriptionYear())) + 
                "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(subscriptionPayments.getPaymentStatus()) + 
                "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td>" + this.utils.convertNullToEmptyString(
                  dateFormater.format(subscriptionPayments.getPaidDate())) + "</td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td align='left'><form action='updateSubcriptionsForm' method='POST'  ><input type='hidden' name='update_subscription_memberId' id='upd_subscrib_memberId' value='" + 
                
                subscriptionPayments.getMemberId() + "'>" + 
                "<input type='hidden' name='update_subscription_transId' id='upd_subscrib_transId' value='" + 
                subscriptionPayments.getTransactionId() + "' >" + 
                "<input type='hidden' name='update_subscription_cardNo' id='upd_subscrib_cardNo' value='" + 
                cardNo + "'>" + 
                "<input type='hidden' name='update_subscription_deptId' id='upd_subscrib_deptId' value='" + 
                deptId + "'>" + 
                "<input type='hidden' name='update_scubscription_pageId' id='upd_subscrib_pageId' value='" + 
                pageId + "' >" + 
                
                "<input type='submit' value='Update'>" + "</form></td>";
              tbody = String.valueOf(String.valueOf(tbody)) + "<td align='left'><form action='deleteSubcriptionsForm' method='POST'  ><input type='hidden' name='delete_subscription_memberId' id='del_subscrib_memberId' value='" + 
                
                subscriptionPayments.getMemberId() + "'>" + 
                "<input type='hidden' name='delete_subscription_transId' id='del_subscrib_transId' value='" + 
                subscriptionPayments.getTransactionId() + "' >" + 
                "<input type='hidden' name='delete_subscription_cardNo' id='del_subscrib_cardNo' value='" + 
                cardNo + "'>" + 
                "<input type='hidden' name='delete_subscription_deptId' id='del_subscrib_deptId' value='" + 
                deptId + "'>" + 
                "<input type='hidden' name='delete_scubscription_pageId' id='del_subscrib_pageId' value='" + 
                pageId + "' >" + 
                "<input type='submit' value='Delete'>" + "</form></td>";
            } 
            tbody = String.valueOf(String.valueOf(tbody)) + "</tr>";
            k++;
          } 
          tbody = String.valueOf(String.valueOf(tbody)) + "</tbody>";
          resultObj.put("OTHER_PANEL_RESULT_CODE", "400");
          resultObj.put("DATA_DETAILS", String.valueOf(String.valueOf(theadStr)) + tbody + "</table>");
        } else {
          tbody = String.valueOf(String.valueOf(tbody)) + "<tr><td colspan='" + colCount + "' align='center'> No data found !</td></tr>";
          tbody = String.valueOf(String.valueOf(tbody)) + "</tbody>";
          resultObj.put("OTHER_PANEL_RESULT_CODE", "200");
          resultObj.put("OTHER_PANEL_ERROR_MSG", "No data found !");
          resultObj.put("DATA_DETAILS", String.valueOf(String.valueOf(theadStr)) + tbody + "</table>");
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
      tbody = String.valueOf(String.valueOf(tbody)) + "<tr><td colspan='" + colCount + "' align='center'> No data found !</td></tr>";
      tbody = String.valueOf(String.valueOf(tbody)) + "</tbody>";
      resultObj.put("OTHER_PANEL_RESULT_CODE", "300");
      resultObj.put("OTHER_PANEL_ERROR_MSG", e.getMessage());
      tbody = String.valueOf(String.valueOf(tbody)) + "<tr><td colspan='" + colCount + "' align='center'>Unable to fetch records!</td></tr>";
      tbody = String.valueOf(String.valueOf(tbody)) + "</tbody>";
      resultObj.put("DATA_DETAILS", String.valueOf(String.valueOf(theadStr)) + tbody + "</table>");
    } 
    return resultObj.toJSONString();
  }
  
  @Transactional
  public List searchDetails(String deptId) {
    List searchDetailsList = null;
    try {
      String getRegistrationQuery = "from Registration where registrationPK.deptId =:deptId ";
      Map<String, Object> parametersMap = new HashMap<String, Object>();
      parametersMap.put("deptId", deptId);
      searchDetailsList = this.dataAccess.queryWithParams(getRegistrationQuery, parametersMap);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return searchDetailsList;
  }
}
