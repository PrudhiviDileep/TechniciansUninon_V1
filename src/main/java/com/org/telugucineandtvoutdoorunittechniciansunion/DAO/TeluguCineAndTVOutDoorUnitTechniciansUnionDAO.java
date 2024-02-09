package com.org.telugucineandtvoutdoorunittechniciansunion.DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.org.telugucineandtvoutdoorunittechniciansunion.init.ApplicationUtilities;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.DataAccess;
import com.org.telugucineandtvoutdoorunittechniciansunion.init.IdGenerator;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.AdminfeePayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.AdminfeePaymentsPK;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.CardNubers;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.CardNubersPK;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Departments;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.MembershipPaymentsPK;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.PaymentConfigurations;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.RecommendationDetails;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Registration;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.RegistrationPK;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPayments;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.SubscriptionPaymentsPK;
import com.org.telugucineandtvoutdoorunittechniciansunion.pojo.Units;
import com.org.telugucineandtvoutdoorunittechniciansunion.utils.Utils;

@Repository
public class TeluguCineAndTVOutDoorUnitTechniciansUnionDAO {
	@Autowired
	private DataAccess dataAccess;
	@Autowired
	public MiscellaneousDAO miscellaneousDAO;
	@Autowired
	GenericCRUDOperationsDAO genericCRUDOperationsDAO;
	@Autowired
	private IdGenerator idGenerator;
	Utils utils = new Utils();

	@Autowired
	ServletContext context;

	@Autowired
	LoanDAO loanDAO;

	public static final String FINAL_RESULT_CODE = "FINAL_RESULT_CODE";
	public static final String PAID_AMOUNT = "PAID_AMOUNT";
	public static final String RESULT_CODE_200 = "200";
	public static final String RESULT_CODE_300 = "300";
	public static final String ERROR_MSG = "ERROR_MSG";
	public static final String RESULT_CODE_400 = "400";
	public static final String RECEIPT_NO = "RECEIPT_NO";
	public static final String MEMBER_ID = "MEMBER_ID";
	public static final String PAYMENT_CONF_ID = "PAYMENT_CONF_ID";
	public static final String LOAN_SUMMARY = "LOAN_SUMMARY";
	public static final String DATA_DETAILS = "DATA_DETAILS";
	public static final String FATHER_NAME = "FATHER_NAME";
	public static final String DEPT_ID = "DEPT_ID";
	public static final String LOAN_AMOUNT = "LOAN_AMOUNT";

	public String login(HttpServletRequest request, Map<String, Object> model) {
		return "";
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Transactional
	public String registration(JSONObject items, MultipartFile[] file) {
		JSONObject finalResult = new JSONObject();
		JSONObject resultObj = new JSONObject();
		int insertedImages = 0;
		int insertedDefaultImages = 0;

		try {
			if (items != null) {

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String category = "REGISTRATION";
				String paymentStatus = "Yes";
				String remarks = "Registration";
				String firstName = ((String) items.get("memberName") != null) ? (String) items.get("memberName") : "";
				String fatherName = ((String) items.get("fatherName") != null) ? (String) items.get("fatherName") : "";
				String dateOfBirth = ((String) items.get("dateOfBirth") != null) ? (String) items.get("dateOfBirth")
						: "";
				String eduQualification = ((String) items.get("eduQualification") != null)
						? (String) items.get("eduQualification")
						: "";
				String motherTongue = ((String) items.get("motherTongue") != null) ? (String) items.get("motherTongue")
						: "";
				String knownLanguages = ((String) items.get("knownLang") != null) ? (String) items.get("knownLang")
						: "";
				String bloodGroup = ((String) items.get("bloodGroup") != null) ? (String) items.get("bloodGroup") : "";
				String fefsiMember = ((String) items.get("workDetails_fefsiNumber") != null)
						? (String) items.get("workDetails_fefsiNumber")
						: "";
				String registeredDate = ((String) items.get("workDetails_dateOfJoining") != null)
						? (String) items.get("workDetails_dateOfJoining")
						: "";

				String nameOfcompany = ((String) items.get("workDetails_placeOfWork") != null)
						? (String) items.get("workDetails_placeOfWork")
						: "";
				String natureOfWork = ((String) items.get("workDetails_deptName") != null)
						? (String) items.get("workDetails_deptName")
						: "";

				String deptName = ((String) items.get("deptName") != null) ? (String) items.get("deptName") : "";
				String deptId = (String) items.get("deptId");
				String receiptNo = ((String) items.get("regPaymentDetails_receiptNo") != null)
						? (String) items.get("regPaymentDetails_receiptNo")
						: "";
				String appliedDatae = ((String) items.get("approvedDetails_appliedDate") != null)
						? (String) items.get("approvedDetails_appliedDate")
						: "";
				String unitId = ((String) items.get("approvedDetails_unitId") != null)
						? (String) items.get("approvedDetails_unitId")
						: "";
				String unitName = ((String) items.get("approvedDetails_placeOfWork") != null)
						? (String) items.get("approvedDetails_placeOfWork")
						: "";
				String presentAddress = ((String) items.get("presentAddress") != null)
						? (String) items.get("presentAddress")
						: "";
				String phoneNo1 = ((String) items.get("phoneNo1") != null) ? (String) items.get("phoneNo1") : "";
				String perminentAddress = ((String) items.get("perminentAddress") != null)
						? (String) items.get("perminentAddress")
						: "";
				String phoneNo2 = ((String) items.get("phoneNo2") != null) ? (String) items.get("phoneNo2") : "";
				String nominie = ((String) items.get("nomineeDetails_name") != null)
						? (String) items.get("nomineeDetails_name")
						: "";
				String relationWithNominie = ((String) items.get("nomineeDetails_relation") != null)
						? (String) items.get("nomineeDetails_relation")
						: "";
				String recommend1DeptName = ((String) items.get("recommand1_deptName") != null)
						? (String) items.get("recommand1_deptName")
						: "";
				String recommend1DeptId = ((String) items.get("recommand1_deptId") != null)
						? (String) items.get("recommand1_deptId")
						: "";
				String recommand1cardNo = ((String) items.get("recommand1_cardNo") != null)
						? (String) items.get("recommand1_cardNo")
						: "";
				String recommend1Name = ((String) items.get("recommand1_name") != null)
						? (String) items.get("recommand1_name")
						: "";
				String recommend1UnitId = ((String) items.get("recommand1_unitId") != null)
						? (String) items.get("recommand1_unitId")
						: "";
				String recommend1WorkingPlace = ((String) items.get("recommand1_placeOfWork") != null)
						? (String) items.get("recommand1_placeOfWork")
						: "";
				String recommend2DeptName = ((String) items.get("recommand2_deptName") != null)
						? (String) items.get("recommand2_deptName")
						: "";

				String approvedDetails_unitId = ((String) items.get("approvedDetails_unitId") != null)
						? (String) items.get("approvedDetails_unitId")
						: "";

				String approvedDetails_placeOfWork = ((String) items.get("approvedDetails_placeOfWork") != null)
						? (String) items.get("approvedDetails_placeOfWork")
						: "";

				String recommend2DeptId = ((String) items.get("recommand2_deptId") != null)
						? (String) items.get("recommand2_deptId")
						: "";
				String recommand2cardNo = ((String) items.get("recommand2_cardNo") != null)
						? (String) items.get("recommand2_cardNo")
						: "";
				String recommend2Name = ((String) items.get("recommand2_name") != null)
						? (String) items.get("recommand2_name")
						: "";
				String recommend2UnitId = ((String) items.get("recommand2_unitId") != null)
						? (String) items.get("recommand2_unitId")
						: "";
				String recommend2WorkingPlace = ((String) items.get("recommand2_placeOfWork") != null)
						? (String) items.get("recommand2_placeOfWork")
						: "";

				String aadhar = ((String) items.get("aadharCardNo") != null) ? (String) items.get("aadharCardNo") : "";

				String bankname = ((String) items.get("bankDetails_bankName") != null)
						? (String) items.get("bankDetails_bankName")
						: "";
				String bankAccHolderName = ((String) items.get("bankDetails_accHolderName") != null)
						? (String) items.get("bankDetails_accHolderName")
						: "";
				String bankAccNo = ((String) items.get("bankDetails_accNumber") != null)
						? (String) items.get("bankDetails_accNumber")
						: "";

				String bankBranch = ((String) items.get("bankDetails_branch") != null)
						? (String) items.get("bankDetails_branch")
						: "";

				String bankIfscCode = ((String) items.get("bankDetails_ifsc") != null)
						? (String) items.get("bankDetails_ifsc")
						: "";

				String regPaymentDetails_cardAmount = ((String) items.get("regPaymentDetails_cardAmount") != null)
						? (String) items.get("regPaymentDetails_cardAmount")
						: "";

				String regPaymentDetails_paymentType = ((String) items
						.get("regPaymentDetails_paymentTypeOption") != null)
								? (String) items.get("regPaymentDetails_paymentTypeOption")
								: "";

				String regPaymentDetails_paymentMode = ((String) items.get("regPaymentDetails_paymentMode") != null)
						? (String) items.get("regPaymentDetails_paymentMode")
						: "";

				String regPaymentDetails_ddNo = ((String) items.get("regPaymentDetails_ddNo") != null)
						? (String) items.get("regPaymentDetails_ddNo")
						: "";

				String regPaymentDetails_paidDate = ((String) items.get("regPaymentDetails_paidDate") != null)
						? (String) items.get("regPaymentDetails_paidDate")
						: "";
				String regPaymentDetails_remarks = ((String) items.get("regPaymentDetails_remarks") != null)
						? (String) items.get("regPaymentDetails_remarks")
						: "";
				JSONObject validateObj = new JSONObject();
				validateObj.put("MEMBER_DOB", dateOfBirth);
				validateObj.put("MEMBER_APPLIED_DATE", appliedDatae);
				validateObj.put("MEMBER_REGISTERED_DATE", registeredDate);
				validateObj.put("MEMBER_DEPT_ID", deptId);
				validateObj.put("MEMBER_CARD_NO",
						(items.get("approvedDetails_cardNo") != null) ? items.get("approvedDetails_cardNo") : "");
				validateObj.put("RECOMOND1_CARD_NO", recommand1cardNo);
				validateObj.put("RECOMOND1_DEPT_ID", recommend1DeptId);
				validateObj.put("RECOMOND2_CARD_NO", recommand2cardNo);
				validateObj.put("RECOMOND2_DEPT_ID", recommend2DeptId);
				validateObj.put("NOMINEE_NAME", nominie);
				validateObj.put("NOMINEE_RELATION", relationWithNominie);
				validateObj.put("AADHAR_CARD_NO", aadhar);
				//receiptNo= this.idGenerator.get(RECEIPT_NO, RECEIPT_NO);
				validateObj.put(RECEIPT_NO, receiptNo);

				String validationResult = registrationValidation(validateObj, "REGISTRATION");

				if (!"".equals(validationResult) && "SUCCESS".equalsIgnoreCase(validationResult)) {
					int cardNo = Integer.parseInt(((String) items.get("approvedDetails_cardNo") != null)
							? (String) items.get("approvedDetails_cardNo")
							: "");
					int paidAmount = Integer.parseInt(((String) items.get("regPaymentDetails_paidAmmount") != null)
							? (String) items.get("regPaymentDetails_paidAmmount")
							: "");
					PaymentConfigurations paymentConfigurations = this.miscellaneousDAO
							.getPaymentConfigurationsDetailsById(regPaymentDetails_paymentType);

					int adminFeeAmount = paymentConfigurations.getAdminAmount();
					int subscriptionAmount = paymentConfigurations.getSubscriptionAmount();
					int membershipAmount = paymentConfigurations.getMembershipAmount();
					int donationAmount = paidAmount - adminFeeAmount + subscriptionAmount;

					if (paidAmount <= adminFeeAmount + subscriptionAmount) {
						finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_300);
						finalResult.put(ERROR_MSG, "Registration failed for paying very low membership amount !");
						return finalResult.toJSONString();
					}
					if (paidAmount > membershipAmount) {
						finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_300);
						finalResult.put(ERROR_MSG, "Registration failed due to paying more than membership amount !");
						return finalResult.toJSONString();
					}

					int cardBalance = membershipAmount - paidAmount;
					String memberId = this.idGenerator.get("MEM-REG-ID", MEMBER_ID);
					String transactionId = this.idGenerator.get("TRANSACTION_ID", "TRANSACTION_ID");
					RegistrationPK registrationPK = new RegistrationPK();
					Registration registration = new Registration();
					registrationPK.setCardNo(cardNo);
					registrationPK.setDeptId(deptId);
					registrationPK.setMemberId(memberId);
					registration.setUnitId(unitId);

					registration.setAadharCardNo(aadhar);
					registrationPK.setTransactionId(transactionId);
					Units units = new Units();
					units.setUnitId(unitId);
					units.setUnitName(unitName);
					CardNubers cardNubers = new CardNubers();
					CardNubersPK cardNubersPk = new CardNubersPK();
					cardNubersPk.setCardNo(cardNo);
					cardNubersPk.setDeptId(deptId);
					cardNubers.setCardNubersPK(cardNubersPk);
					cardNubers.setCardStatus("ACTIVE");
					Departments departments = new Departments();
					departments.setDeptId(deptId);
					departments.setDeptName(deptName);
					this.dataAccess.save(cardNubers);
					registration.setRegistrationPK(registrationPK);
					registration.setFatherName(fatherName);
					registration.setFirstName(firstName);
					registration.setPhoneNo(phoneNo1);
					registration.setDateOfBirth(formatter.parse(dateOfBirth));
					registration.setAltPhoneNo(phoneNo2);
					registration.setAppliedDatae(formatter.parse(appliedDatae));

					registration.setRegTimeStamp(new Date());
					registration.setBloodGroup(bloodGroup);
					registration.setDeptName(deptName);
					registration.setEduQualification(eduQualification);
					registration.setFefsiMember(fefsiMember);
					registration.setKnownLanguages(knownLanguages);
					registration.setLastName("");
					registration.setMembershipAmount(membershipAmount);
					registration.setMiddleName("");
					registration.setMotherTongue(motherTongue);
					registration.setNameOfCompany(nameOfcompany);
					registration.setNatureOfWork(natureOfWork);
					registration.setNominie(nominie);
					registration.setPaidAmount(paidAmount);
					registration.setReceiptNo(receiptNo);
					registration.setPerminentAddress(perminentAddress);
					registration.setPresentAddress(presentAddress);
					registration.setRegisteredDate(formatter.parse(registeredDate));
					registration.setRelationWithNominie(relationWithNominie);
					registration.setCurrentLoanBalance(0);
					registration.setCurrentLoanIssuedAmount(0);
					registration.setCurrentLoanStatus("LOAN_NOT_SANCTIONED");
					registration.setPaymentConfId(regPaymentDetails_paymentType);
					registration.setCardAmount(membershipAmount);
					registration.setCardBalance(cardBalance);
					registration.setMotherTongue(motherTongue);
					registration.setBankName(bankname);

					RecommendationDetails reccomendDetails = new RecommendationDetails();
					reccomendDetails.setMemberId(memberId);
					reccomendDetails.setRecommend1DeptId(recommend1DeptId);
					reccomendDetails.setRecommend1DeptName(recommend1DeptName);
					reccomendDetails.setRecommend1Name(recommend1Name);
					reccomendDetails.setRecommend1WorkingPlace(recommend1WorkingPlace);
					reccomendDetails.setRecommend1UnitId(recommend1UnitId);
					reccomendDetails.setRecommend1CardNo(Integer.parseInt(recommand1cardNo));
					reccomendDetails.setRecommend1WorkingPlace(recommend1WorkingPlace);
					reccomendDetails.setRecommend2DeptId(recommend2DeptId);
					reccomendDetails.setRecommend2DeptName(recommend2DeptName);
					reccomendDetails.setRecommend2Name(recommend2Name);
					reccomendDetails.setRecommend2WorkingPlace(recommend2WorkingPlace);
					reccomendDetails.setRecommend2CardNo(Integer.parseInt(recommand2cardNo));
					reccomendDetails.setRecommend2UnitId(recommend2UnitId);
					reccomendDetails.setMemberId(memberId);

					this.dataAccess.save(reccomendDetails);

					if (file != null && file.length > 0 && !file[0].isEmpty()) {
						byte[] bytes = file[0].getBytes();
						String filetype = file[0].getOriginalFilename().substring(
								file[0].getOriginalFilename().lastIndexOf(".") + 1,
								file[0].getOriginalFilename().length());

						registration.setFileContent(bytes);
						registration.setFileName(String.valueOf(String.valueOf(memberId)) + "_PROFILE_PIC");
						registration.setFileType(filetype.toUpperCase());
					}

					registration.setBankAccHolderName(bankAccHolderName);
					registration.setBankAccNo(bankAccNo);
					registration.setBankBranch(bankBranch);

					registration.setWorkingPlace(approvedDetails_placeOfWork);
					registration.setBankIfscCode(bankIfscCode);
					registration.setIsActive("Y");
					Date sqlDate = new Date((new Date()).getTime());
					MembershipPayments membershipPayments = new MembershipPayments();
					MembershipPaymentsPK membershipPaymentsPK = new MembershipPaymentsPK();
					membershipPaymentsPK.setMemberId(memberId);
					membershipPaymentsPK.setTransactionId(transactionId);
					membershipPayments.setMembershipPaymentsPK(membershipPaymentsPK);
					membershipPayments.setPaidDate(formatter.parse(regPaymentDetails_paidDate));
					membershipPayments.setPaidAmount(paidAmount);
					membershipPayments.setDonationAmount(donationAmount);
					membershipPayments.setReceiptNo(receiptNo);
					membershipPayments.setRemarks(regPaymentDetails_remarks);
					membershipPayments.setCategory("REGISTRATION");
					membershipPayments.setPaymentConfId(regPaymentDetails_paymentType);
					membershipPayments.setPaymentType(regPaymentDetails_paymentMode);
					membershipPayments.setDdNo(regPaymentDetails_ddNo);
					this.dataAccess.save(membershipPayments);
					SubscriptionPayments subscriptionPayments = new SubscriptionPayments();
					SubscriptionPaymentsPK subscriptionPaymentsPK = new SubscriptionPaymentsPK();
					subscriptionPayments.setMemberId(memberId);
					subscriptionPayments.setTransactionId(transactionId);
					subscriptionPaymentsPK.setDeptId(deptId);
					subscriptionPayments.setSubscriptionPaymentsPK(subscriptionPaymentsPK);
					subscriptionPayments.setCategory(category);
					subscriptionPayments.setSubscriptionAmount(subscriptionAmount);
					subscriptionPayments.setPaidDate(formatter.parse(registeredDate));
					subscriptionPayments.setPaymentConfId(regPaymentDetails_paymentType);
					subscriptionPayments.setPaymentStatus(paymentStatus);
					subscriptionPayments.getSubscriptionPaymentsPK().setReceiptNo(receiptNo);
					subscriptionPayments.setRemarks(remarks);
					int subscriptionYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
					subscriptionPayments.getSubscriptionPaymentsPK().setSubscriptionYear(subscriptionYear);

					this.dataAccess.save(subscriptionPayments);
					AdminfeePayments adminfeePayments = new AdminfeePayments();
					AdminfeePaymentsPK adminfeePaymentsPK = new AdminfeePaymentsPK();
					adminfeePaymentsPK.setMemberId(memberId);
					adminfeePaymentsPK.setTransactionId(transactionId);
					adminfeePayments.setAdminFeeAmount(adminFeeAmount);
					adminfeePayments.setCategory(category);
					adminfeePayments.setPaymentConfId(regPaymentDetails_paymentType);
					adminfeePayments.setPaymentDate(sqlDate);
					adminfeePayments.setRemarks(remarks);
					adminfeePayments.setAdminfeePaymentsPK(adminfeePaymentsPK);
					this.dataAccess.save(adminfeePayments);
					resultObj.put("REGISTRATION_RESULT", "Registered Successfully !");
					resultObj.put("MEBER_ID", memberId);
					resultObj.put("MEBER_DEPT_ID", deptId);
					resultObj.put("MEBER_CARD_NO", Integer.valueOf(cardNo));
					finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_400);
					finalResult.put(DATA_DETAILS, resultObj);
					this.dataAccess.save(registration);
				} else {
					finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_300);
					finalResult.put(ERROR_MSG, validationResult);
				}

			} else {
				finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_300);
				finalResult.put(ERROR_MSG, "No registration form data found !");

			}

		} catch (Exception e) {

			finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			finalResult.put(ERROR_MSG, e.getMessage());
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}
		return finalResult.toJSONString();
	}

	@Transactional
	public String registrationValidation(JSONObject obj, String type) {
		String result = "Sorry Registration Failed !";

		try {
			if (obj != null) {
				String memberDob = (String) obj.get("MEMBER_DOB");
				String memberAppliedDate = (String) obj.get("MEMBER_APPLIED_DATE");
				String memberRegisteredDate = (String) obj.get("MEMBER_REGISTERED_DATE");
				String memberDeptId = (String) obj.get("MEMBER_DEPT_ID");
				String memberCardNo = (String) obj.get("MEMBER_CARD_NO");
				String recomond1CardNo = (String) obj.get("RECOMOND1_CARD_NO");
				String recomond1DeptId = (String) obj.get("RECOMOND1_DEPT_ID");
				String recomond2CardNo = (String) obj.get("RECOMOND2_CARD_NO");
				String recomond2DeptId = (String) obj.get("RECOMOND2_DEPT_ID");
				String nomineeName = (String) obj.get("NOMINEE_NAME");
				String nomineeRelation = (String) obj.get("NOMINEE_RELATION");
				String aadhar = (String) obj.get("AADHAR_CARD_NO");
				///String receiptNo = (String) obj.get(RECEIPT_NO);

				if (memberDob == null || "".equalsIgnoreCase(memberDob)) {
					return "Please Select Date of Birth.";
				}
				if (memberAppliedDate == null || "".equalsIgnoreCase(memberAppliedDate)) {
					return "Please Select Applied Date";
				}
				if (memberRegisteredDate == null || "".equalsIgnoreCase(memberRegisteredDate)) {
					return "Please Select Registered Date.";
				}
				if (memberCardNo == null || "".equalsIgnoreCase(memberCardNo)) {
					return "Please Enter Card No.";
				}
				if (memberDeptId == null || "".equalsIgnoreCase(memberDeptId)
						|| "SELECT".equalsIgnoreCase(memberDeptId)) {
					return "Please select member department.";
				}
//				if ("REGISTRATION".equalsIgnoreCase(type) && (receiptNo == null || "".equalsIgnoreCase(receiptNo))) {
//					return "Please enter receipt no";
//				}

				if (recomond1CardNo == null || "".equalsIgnoreCase(recomond1CardNo)) {
					return "Please enter recommondation1 card no.";
				}
				if (recomond1DeptId == null || "".equalsIgnoreCase(recomond1DeptId)
						|| "SELECT".equalsIgnoreCase(recomond1DeptId)) {
					return "Please select member recommondation1 department.";
				}

				if (recomond2CardNo == null || "".equalsIgnoreCase(recomond2CardNo)) {
					return "Please enter recommondation2 card no.";
				}
				if (recomond2DeptId == null || "".equalsIgnoreCase(recomond2DeptId)
						|| "SELECT".equalsIgnoreCase(recomond2DeptId)) {
					return "Please select member recommondation2 department.";
				}
				if (nomineeName == null || "".equalsIgnoreCase(nomineeName))
					return "Please enter nominee Name.";
				if (nomineeRelation == null || "".equalsIgnoreCase(nomineeRelation)
						|| "SELECT".equalsIgnoreCase(nomineeRelation)) {
					return "Please select nominee relationship.";
				}
				if (aadhar == null || "".equalsIgnoreCase(aadhar)) {
					return "Please Addhar number";
				}

				if (!this.utils.isValidDate(memberDob)) {
					return " Incorrect date format for Date of birth, and entered date  format should be (dd/mm/yyyy)";
				}

				if (!this.utils.isValidDate(memberAppliedDate)) {
					return " Incorrect date format for applied date, and entered date format should be (dd/mm/yyyy)";
				}

				if (!this.utils.isValidDate(memberRegisteredDate)) {
					return " Incorrect date format for registered , and entered date format should be (dd/mm/yyyy)";
				}

				if (type != null && !"".equalsIgnoreCase(type) && "REGISTRATION".equalsIgnoreCase(type)) {
					Registration isMemberExist = this.miscellaneousDAO.getMemberDetailsByDeptIdAndCardNo(memberDeptId,
							memberCardNo);
					if (isMemberExist != null) {
						return " Already member exist with  " + memberCardNo + " in " + memberDeptId + "Department";
					}
				}

				if (type != null && !"".equalsIgnoreCase(type) && "UPDATE".equalsIgnoreCase(type)) {

					if (recomond2CardNo.equalsIgnoreCase(memberCardNo)) {
						return " Recommod2 card number should not be Member card number  ";
					}

					if (recomond1CardNo.equalsIgnoreCase(memberCardNo)) {
						return " Recommod1 card number should not be Member card number  ";
					}
				}

				if (!this.utils.isNumericString(aadhar))
					return "Please enter only numbers in Aadhar";
				if (aadhar.length() != 12) {
					return "Please enter only 12 digit Aadhar number";
				}
				return "SUCCESS";

			}

		} catch (Exception exception) {
			ApplicationUtilities.error(getClass(), exception, "registrationValidation");
		}

		return result;
	}

	@Transactional
	public String viewMemberDetails(HttpServletRequest request) {
		String result = "";
		try {
			String memberId = request.getParameter("memberId");
			result = getMemberDetails(memberId).toJSONString();
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "viewMemberDetails");
			
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public JSONObject getMemberDetails(String memberId) {
		JSONObject Obj = new JSONObject();
		JSONObject finalResult = new JSONObject();
		SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");

		try {
			ApplicationUtilities.debug(getClass(), "getMemberDetails ");

			Registration registerdMember = this.miscellaneousDAO.getMemberDetailsByMemberId(memberId);
			if (registerdMember != null) {
				RegistrationPK registerdMemberPK = registerdMember.getRegistrationPK();

				Obj.put("CARD_NO", Integer.valueOf(registerdMemberPK.getCardNo()));
				Obj.put(DEPT_ID, (registerdMemberPK.getDeptId() != null) ? registerdMemberPK.getDeptId() : "");
				Obj.put(MEMBER_ID, (registerdMemberPK.getMemberId() != null) ? registerdMemberPK.getMemberId() : "");
				Obj.put("AADHAR_CARD_NO",
						(registerdMember.getAadharCardNo() != null) ? registerdMember.getAadharCardNo() : "");
				Obj.put("TRANSACTION_ID",
						(registerdMemberPK.getTransactionId() != null) ? registerdMemberPK.getTransactionId() : "");
				Obj.put("UNIT_ID", (registerdMember.getUnitId() != null) ? registerdMember.getUnitId() : "");
				Obj.put("ALT_PHONE_NO",
						(registerdMember.getAltPhoneNo() != null) ? registerdMember.getAltPhoneNo() : "");
				Obj.put("APPLIED_DATAE", dateFormater.format(registerdMember.getRegisteredDate()));
				Obj.put("BLOOD_GROUP",
						(registerdMember.getBloodGroup() != null) ? registerdMember.getBloodGroup() : "");
				Obj.put("DEPT_NAME", (registerdMember.getDeptName() != null) ? registerdMember.getDeptName() : "");
				Obj.put("EDU_QUALIFICATION",
						(registerdMember.getEduQualification() != null) ? registerdMember.getEduQualification() : "");
				Obj.put("FEFSI_MEMBER",
						(registerdMember.getFefsiMember() != null) ? registerdMember.getFefsiMember() : "");
				Obj.put("FIRST_NAME", (registerdMember.getFirstName() != null) ? registerdMember.getFirstName() : "");
				Obj.put("KNOWN_LANGUAGES",
						(registerdMember.getKnownLanguages() != null) ? registerdMember.getKnownLanguages() : "");
				Obj.put("LAST_NAME", (registerdMember.getLastName() != null) ? registerdMember.getLastName() : "");
				Obj.put("MEMBERSHIP_AMOUNT", Integer.valueOf(registerdMember.getMembershipAmount()));
				Obj.put("MIDDLE_NAME",
						(registerdMember.getMiddleName() != null) ? registerdMember.getMiddleName() : "");
				Obj.put("MOTHER_TONGUE",
						(registerdMember.getMotherTongue() != null) ? registerdMember.getMotherTongue() : "");
				Obj.put("NAME_OF_COMPANY",
						(registerdMember.getNameOfCompany() != null) ? registerdMember.getNameOfCompany() : "");
				Obj.put("NATURE_OF_WORK",
						(registerdMember.getNatureOfWork() != null) ? registerdMember.getNatureOfWork() : "");
				Obj.put("NOMINIE", (registerdMember.getNominie() != null) ? registerdMember.getNominie() : "");
				Obj.put(PAID_AMOUNT, Integer.valueOf(registerdMember.getPaidAmount()));
				Obj.put("PAYMENT_RECEIPT_NO",
						(registerdMember.getReceiptNo() != null) ? registerdMember.getReceiptNo() : "");
				Obj.put("PERMINENT_ADDRESS",
						(registerdMember.getPerminentAddress() != null) ? registerdMember.getPerminentAddress() : "");
				Obj.put("PHONE_NO", (registerdMember.getPhoneNo() != null) ? registerdMember.getPhoneNo() : "");
				Obj.put("PRESENT_ADDRESS",
						(registerdMember.getPresentAddress() != null) ? registerdMember.getPresentAddress() : "");
				Obj.put("REGISTERED_DATE", dateFormater.format(registerdMember.getRegisteredDate()));
				Obj.put("RELATION_WITH_NOMINIE",
						(registerdMember.getRelationWithNominie() != null) ? registerdMember.getRelationWithNominie()
								: "");

				Obj.put(FATHER_NAME, (registerdMember.getFatherName() != null) ? registerdMember.getFatherName() : "");
				Obj.put("DATE_OF_BIRTH", dateFormater.format(registerdMember.getDateOfBirth()));
				Obj.put("CURRENT_LOAN_STATUS",
						(registerdMember.getCurrentLoanStatus() != null) ? registerdMember.getCurrentLoanStatus() : "");
				Obj.put("CURRENT_LOAN_ID",
						(registerdMember.getCurrentLoanId() != null) ? registerdMember.getCurrentLoanId() : "");
				Obj.put("CURRENT_LOAN_BALANCE",
						(registerdMember.getCurrentLoanBalance() != null) ? registerdMember.getCurrentLoanBalance()
								: "");
				Obj.put("CARD_BALANCE",
						(registerdMember.getCardBalance() != null) ? registerdMember.getCardBalance() : "");
				Obj.put("BANK_ACC_HOLDER_NAME",
						(registerdMember.getBankAccHolderName() != null) ? registerdMember.getBankAccHolderName() : "");
				Obj.put("BANK_ACC_NO", (registerdMember.getBankAccNo() != null) ? registerdMember.getBankAccNo() : "");
				Obj.put("BANK_BRANCH",
						(registerdMember.getBankBranch() != null) ? registerdMember.getBankBranch() : "");
				Obj.put("BANK_NAME", (registerdMember.getBankName() != null) ? registerdMember.getBankName() : "");
				Obj.put("BANK_IFSC_CODE",
						(registerdMember.getBankIfscCode() != null) ? registerdMember.getBankIfscCode() : "");
				Obj.put("CARD_AMOUNT",registerdMember.getCardAmount());
				Obj.put("CURRENT_LOAN_ISSUED_AMOUNT",
						(registerdMember.getCurrentLoanIssuedAmount() != null)
								? registerdMember.getCurrentLoanIssuedAmount()
								: "");
				Obj.put(PAYMENT_CONF_ID,
						(registerdMember.getPaymentConfId() != null) ? registerdMember.getPaymentConfId() : "");
				Obj.put(RECEIPT_NO, (registerdMember.getReceiptNo() != null) ? registerdMember.getReceiptNo() : "");

				Obj.put("FILE_CONTENT", this.utils.convertImageToBase64(registerdMember.getFileContent(),
						registerdMember.getFileType()));
				Obj.put("FILE_NAME", (registerdMember.getFileName() != null) ? registerdMember.getFileName() : "");
				Obj.put("FILE_TYPE", (registerdMember.getFileType() != null) ? registerdMember.getFileType() : "");

				RecommendationDetails recommondationDetails = this.miscellaneousDAO
						.getRecommondationDetailsByMemberId(memberId);
				if (recommondationDetails != null) {

					Obj.put("RECOMMEND1_NAME",
							(recommondationDetails.getRecommend1Name() != null)
									? recommondationDetails.getRecommend1Name()
									: "");
					Obj.put("RECOMMEND1_DEPT_NAME",
							(recommondationDetails.getRecommend1DeptName() != null)
									? recommondationDetails.getRecommend1DeptName()
									: "");

					Obj.put("RECOMMEND1_DEPT_ID",
							(recommondationDetails.getRecommend1DeptId() != null)
									? recommondationDetails.getRecommend1DeptId()
									: "");
					Obj.put("RECOMMEND1_WORKING_PLACE",
							(recommondationDetails.getRecommend1WorkingPlace() != null)
									? recommondationDetails.getRecommend1WorkingPlace()
									: "");
					Obj.put("RECOMMEND1_UNIT_ID",
							(recommondationDetails.getRecommend1UnitId() != null)
									? recommondationDetails.getRecommend1UnitId()
									: "");

					Obj.put("RECOMMEND1_CARD_NO", Integer.valueOf(recommondationDetails.getRecommend1CardNo()));

					Obj.put("RECOMMEND2_NAME",
							(recommondationDetails.getRecommend2Name() != null)
									? recommondationDetails.getRecommend2Name()
									: "");

					Obj.put("RECOMMEND2_DEPT_NAME",
							(recommondationDetails.getRecommend2DeptName() != null)
									? recommondationDetails.getRecommend2DeptName()
									: "");
					Obj.put("RECOMMEND2_DEPT_ID",
							(recommondationDetails.getRecommend2DeptId() != null)
									? recommondationDetails.getRecommend2DeptId()
									: "");

					Obj.put("RECOMMEND2_WORKING_PLACE",
							(recommondationDetails.getRecommend2WorkingPlace() != null)
									? recommondationDetails.getRecommend2WorkingPlace()
									: "");
					Obj.put("RECOMMEND2_UNIT_ID",
							(recommondationDetails.getRecommend2UnitId() != null)
									? recommondationDetails.getRecommend2UnitId()
									: "");

					Obj.put("RECOMMEND2_CARD_NO", Integer.valueOf(recommondationDetails.getRecommend2CardNo()));
				}

				Obj.put("BANK_ACC_HOLDER_NAME", registerdMember.getBankAccHolderName());
				Obj.put("BANK_ACC_NO", registerdMember.getBankAccNo());
				Obj.put("BANK_BRANCH", registerdMember.getBankBranch());
				Obj.put("", registerdMember.getBankIfscCode());
				finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_400);
				finalResult.put(DATA_DETAILS, Obj);
			} else {

				finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_300);
				finalResult.put(ERROR_MSG, "No member found with registered no :: " + memberId);
			}

		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "getMemberDetails");
			finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			finalResult.put(ERROR_MSG, e.getMessage());
		}

		return finalResult;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public String updateMemberDetails(JSONObject items, MultipartFile[] file) {
		JSONObject finalResult = new JSONObject();
		JSONObject resultObj = new JSONObject();

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String firstName = ((String) items.get("memberName") != null) ? (String) items.get("memberName") : "";
			String fatherName = ((String) items.get("fatherName") != null) ? (String) items.get("fatherName") : "";
			String dateOfBirth = ((String) items.get("dateOfBirth") != null) ? (String) items.get("dateOfBirth") : "";
			String eduQualification = ((String) items.get("eduQualification") != null)
					? (String) items.get("eduQualification")
					: "";
			String motherTongue = ((String) items.get("motherTongue") != null) ? (String) items.get("motherTongue")
					: "";
			String knownLanguages = ((String) items.get("knownLang") != null) ? (String) items.get("knownLang") : "";
			String bloodGroup = ((String) items.get("bloodGroup") != null) ? (String) items.get("bloodGroup") : "";
			String fefsiMember = ((String) items.get("workDetails_fefsiNumber") != null)
					? (String) items.get("workDetails_fefsiNumber")
					: "";
			String natureOfWork = ((String) items.get("workDetails_deptName") != null)
					? (String) items.get("workDetails_deptName")
					: "";
			String nameOfcompany = ((String) items.get("workDetails_placeOfWork") != null)
					? (String) items.get("workDetails_placeOfWork")
					: "";

			String appliedDatae = ((String) items.get("approvedDetails_appliedDate") != null)
					? (String) items.get("approvedDetails_appliedDate")
					: "";
			String unitId = ((String) items.get("approvedDetails_unitId") != null)
					? (String) items.get("approvedDetails_unitId")
					: "";

			String presentAddress = ((String) items.get("presentAddress") != null)
					? (String) items.get("presentAddress")
					: "";
			String phoneNo1 = ((String) items.get("phoneNo1") != null) ? (String) items.get("phoneNo1") : "";
			String perminentAddress = ((String) items.get("perminentAddress") != null)
					? (String) items.get("perminentAddress")
					: "";
			String phoneNo2 = ((String) items.get("phoneNo2") != null) ? (String) items.get("phoneNo2") : "";
			String nominie = ((String) items.get("nomineeDetails_name") != null)
					? (String) items.get("nomineeDetails_name")
					: "";
			String relationWithNominie = ((String) items.get("nomineeDetails_relation") != null)
					? (String) items.get("nomineeDetails_relation")
					: "";
			String recommend1DeptName = ((String) items.get("recommand1_deptName") != null)
					? (String) items.get("recommand1_deptName")
					: "";
			String recommend1DeptId = ((String) items.get("recommand1_deptId") != null)
					? (String) items.get("recommand1_deptId")
					: "";
			String recommand1cardNo = ((String) items.get("recommand1_cardNo") != null)
					? (String) items.get("recommand1_cardNo")
					: "";
			String recommend1Name = ((String) items.get("recommand1_name") != null)
					? (String) items.get("recommand1_name")
					: "";
			String recommend1UnitId = ((String) items.get("recommand1_unitId") != null)
					? (String) items.get("recommand1_unitId")
					: "";
			String recommend1WorkingPlace = ((String) items.get("recommand1_placeOfWork") != null)
					? (String) items.get("recommand1_placeOfWork")
					: "";
			String recommend2DeptName = ((String) items.get("recommand2_deptName") != null)
					? (String) items.get("recommand2_deptName")
					: "";

			String approvedDetails_placeOfWork = ((String) items.get("approvedDetails_placeOfWork") != null)
					? (String) items.get("approvedDetails_placeOfWork")
					: "";
			String recommend2DeptId = ((String) items.get("recommand2_deptId") != null)
					? (String) items.get("recommand2_deptId")
					: "";
			String recommand2cardNo = ((String) items.get("recommand2_cardNo") != null)
					? (String) items.get("recommand2_cardNo")
					: "";
			String recommend2Name = ((String) items.get("recommand2_name") != null)
					? (String) items.get("recommand2_name")
					: "";
			String recommend2UnitId = ((String) items.get("recommand2_unitId") != null)
					? (String) items.get("recommand2_unitId")
					: "";
			String recommend2WorkingPlace = ((String) items.get("recommand2_placeOfWork") != null)
					? (String) items.get("recommand2_placeOfWork")
					: "";
			String aadhar = ((String) items.get("aadharCardNo") != null) ? (String) items.get("aadharCardNo") : "";
			String bankname = ((String) items.get("bankDetails_bankName") != null)
					? (String) items.get("bankDetails_bankName")
					: "";
			String bankAccHolderName = ((String) items.get("bankDetails_accHolderName") != null)
					? (String) items.get("bankDetails_accHolderName")
					: "";
			String bankAccNo = ((String) items.get("bankDetails_accNumber") != null)
					? (String) items.get("bankDetails_accNumber")
					: "";
			String bankBranch = ((String) items.get("bankDetails_branch") != null)
					? (String) items.get("bankDetails_branch")
					: "";
			String bankIfscCode = ((String) items.get("bankDetails_ifsc") != null)
					? (String) items.get("bankDetails_ifsc")
					: "";

			String memberId = ((String) items.get("memberId") != null) ? (String) items.get("memberId") : "";
			String cardNo = ((String) items.get("approvedDetails_cardNo") != null)
					? (String) items.get("approvedDetails_cardNo")
					: "";

			String deptId = ((String) items.get("deptId") != null) ? (String) items.get("deptId") : "";
			String receiptNo = ((String) items.get(RECEIPT_NO) != null) ? (String) items.get(RECEIPT_NO) : "";

			JSONObject validateObj = new JSONObject();
			validateObj.put("MEMBER_DOB", dateOfBirth);
			validateObj.put("MEMBER_APPLIED_DATE", appliedDatae);
			validateObj.put("MEMBER_REGISTERED_DATE", appliedDatae);
			validateObj.put("MEMBER_DEPT_ID", deptId);
			validateObj.put("MEMBER_CARD_NO", cardNo);
			validateObj.put("RECOMOND1_CARD_NO", recommand1cardNo);
			validateObj.put("RECOMOND1_DEPT_ID", recommend1DeptId);
			validateObj.put("RECOMOND2_CARD_NO", recommand2cardNo);
			validateObj.put("RECOMOND2_DEPT_ID", recommend2DeptId);
			validateObj.put("NOMINEE_NAME", nominie);
			validateObj.put("NOMINEE_RELATION", relationWithNominie);
			validateObj.put("AADHAR_CARD_NO", aadhar);
			
			//receiptNo= this.idGenerator.get(RECEIPT_NO, RECEIPT_NO);
			validateObj.put(RECEIPT_NO, receiptNo);

			String validationResult = registrationValidation(validateObj, "UPDATE");
			if (!"".equals(validationResult) && ("SUCCESS".equalsIgnoreCase(validationResult)
					|| "Duplicate Receipt No, this Recept no is already used.".equalsIgnoreCase(validationResult))) {

				Registration registration = this.miscellaneousDAO.getMemberDetailsByMemberId(memberId);

				registration.setUnitId(unitId);
				registration.setAadharCardNo(aadhar);
				registration.setFatherName(fatherName);
				registration.setFirstName(firstName);
				registration.setDateOfBirth(formatter.parse(dateOfBirth));

				registration.setAppliedDatae(formatter.parse(appliedDatae));

				registration.setBloodGroup(bloodGroup);
				registration.setEduQualification(eduQualification);
				registration.setFefsiMember(fefsiMember);
				registration.setKnownLanguages(knownLanguages);
				registration.setMotherTongue(motherTongue);
				registration.setNameOfCompany(nameOfcompany);
				registration.setNatureOfWork(natureOfWork);
				registration.setNominie(nominie);
				registration.setPerminentAddress(perminentAddress);
				registration.setPresentAddress(presentAddress);
				registration.setRelationWithNominie(relationWithNominie);
				registration.setMotherTongue(motherTongue);
				registration.setBankName(bankname);

				RecommendationDetails reccomendDetails = new RecommendationDetails();
				reccomendDetails.setMemberId(memberId);
				reccomendDetails.setRecommend1DeptId(recommend1DeptId);
				reccomendDetails.setRecommend1DeptName(recommend1DeptName);
				reccomendDetails.setRecommend1Name(recommend1Name);
				reccomendDetails.setRecommend1WorkingPlace(recommend1WorkingPlace);
				reccomendDetails.setRecommend1UnitId(recommend1UnitId);
				reccomendDetails.setRecommend1CardNo(Integer.parseInt(recommand1cardNo));

				reccomendDetails.setRecommend2DeptId(recommend2DeptId);
				reccomendDetails.setRecommend2DeptName(recommend2DeptName);
				reccomendDetails.setRecommend2Name(recommend2Name);
				reccomendDetails.setRecommend2WorkingPlace(recommend2WorkingPlace);
				reccomendDetails.setRecommend2UnitId(recommend2UnitId);
				reccomendDetails.setRecommend2CardNo(Integer.parseInt(recommand2cardNo));

				reccomendDetails.setMemberId(memberId);
				this.dataAccess.update(reccomendDetails);

				if (file != null && file.length > 0 && !file[0].isEmpty()) {
					byte[] bytes = file[0].getBytes();
					String filetype = file[0].getOriginalFilename().substring(
							file[0].getOriginalFilename().lastIndexOf(".") + 1, file[0].getOriginalFilename().length());

					registration.setFileContent(bytes);
					registration.setFileName(String.valueOf(String.valueOf(memberId)) + "_PROFILE_PIC");
					registration.setFileType(filetype.toUpperCase());
				}

				registration.setBankAccHolderName(bankAccHolderName);
				registration.setBankAccNo(bankAccNo);
				registration.setBankBranch(bankBranch);
				registration.setWorkingPlace(approvedDetails_placeOfWork);
				registration.setBankIfscCode(bankIfscCode);

				registration.setAltPhoneNo(phoneNo2);
				registration.setPhoneNo(phoneNo1);
				registration.setRegisteredDate(formatter.parse(appliedDatae));
				this.dataAccess.update(registration);

				resultObj.put("REGISTRATION_RESULT", "Udated Registered Member Details Successfully !");
				resultObj.put("MEBER_ID", memberId);
				resultObj.put("MEBER_DEPT_ID", deptId);
				resultObj.put("MEBER_CARD_NO", cardNo);
				finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_400);
				finalResult.put(DATA_DETAILS, resultObj);
			} else {
				finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_300);
				finalResult.put(ERROR_MSG, validationResult);
			}

		} catch (Exception e) {

			finalResult.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			finalResult.put(ERROR_MSG, e.getMessage());
			ApplicationUtilities.error(this.getClass(),e.getMessage(),e);
		}

		return finalResult.toJSONString();
	}

	public String payMembershipAmount(HttpServletRequest request, Map<String, Object> model) {
		return "payMembershipAmount";
	}

	public String getCardBalance(HttpServletRequest request, Map<String, Object> model) {
		return "getCardBalance";
	}

	public String getMembersDetails(HttpServletRequest request, Map<String, Object> model) {
		return "getCardBalance";
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public String payCardBalance(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {
			String cardNo = request.getParameter("cardNo");
			String deptId = request.getParameter("deptId");
			String memberId = request.getParameter("memberId");
			int cardBalance = this.miscellaneousDAO.caluclateCardBalance(memberId);
			if (cardBalance > 0) {
				String membershipAmount = request.getParameter("membershipAmount");
				String paymentConfId = request.getParameter("paymentConfId");
				String paidAmount = request.getParameter("paidAmount");
				String payingAmount = request.getParameter("payingAmount");
				String receiptNo = request.getParameter("receiptNo");
				String paidDate = request.getParameter("paidDate");
				String remarks = request.getParameter("remarks");
				String paymentMode = request.getParameter("paymentMode");
				String ddNo = request.getParameter("ddNo");

				JSONObject validateJsnObj = new JSONObject();
				validateJsnObj.put(MEMBER_ID, memberId);
				validateJsnObj.put("CARD_NO", cardNo);
				validateJsnObj.put(DEPT_ID, deptId);
				validateJsnObj.put(PAYMENT_CONF_ID, paymentConfId);
				validateJsnObj.put("MEMBERSHIP_AMOUNT", membershipAmount);
				validateJsnObj.put(PAID_AMOUNT, paidAmount);
				validateJsnObj.put("PAYIING_AMOUNT", payingAmount);
				validateJsnObj.put("PAID_DATE", paidDate);
				//receiptNo= this.idGenerator.get(RECEIPT_NO, RECEIPT_NO);
				validateJsnObj.put(RECEIPT_NO, receiptNo);

				MembershipPayments membershipPayments = new MembershipPayments();
				membershipPayments.setPaidDate((new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
				membershipPayments.setPaidAmount(Integer.parseInt(payingAmount));
				membershipPayments.setPaymentConfId(paymentConfId);
				membershipPayments.setRemarks(remarks);
				membershipPayments.setReceiptNo(receiptNo);
				membershipPayments.setCategory("INSTALMENT");
				membershipPayments.setPaymentType(paymentMode);
				membershipPayments.setDdNo(ddNo);
				MembershipPaymentsPK membershipPaymentsPK = new MembershipPaymentsPK();
				String transId=this.idGenerator.get("TRANSACTION_ID", "TRANSACTION_ID");
				membershipPaymentsPK.setTransactionId(transId);
				membershipPaymentsPK.setMemberId(memberId);
				membershipPayments.setMembershipPaymentsPK(membershipPaymentsPK);
				this.dataAccess.save(membershipPayments);
				this.miscellaneousDAO.updateCardBalance(memberId);

				result.put(FINAL_RESULT_CODE, RESULT_CODE_400);
				result.put(DATA_DETAILS, "Membership amount paid sucessfullty!");
				Map<String, String> actionData=new HashMap<>();
				actionData.put("PROC_ID", "SAVE_RECEIPT_DETAILS");
				actionData.put("RECEIPT_TYPE", "MEMBERSHIP_AMOUNT");
				actionData.put("TRANSACTION_ID", transId);
				actionData.put("MEMBER_ID", memberId);
				actionData.put("CREATED_BY", "P.Durga Rao");
				actionData.put("AMOUNT", payingAmount);
				if(actionData.get("REMARKS")!=null)
				actionData.put("REMARKS", "   DD No : "+ddNo);
				else
				actionData.put("REMARKS", "");
				actionData.put(RECEIPT_NO, receiptNo);
				genericCRUDOperationsDAO.doGenericCRUDOpertion(actionData);
				result.put(RECEIPT_NO,receiptNo );

			} else {

				result.put(FINAL_RESULT_CODE, RESULT_CODE_300);
				result.put(ERROR_MSG, "This member paid total membership amount !");
			}

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(getClass(), nfe, "payCardBalance");
			result.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			result.put(ERROR_MSG, "Please provide valid input for PaidAmount/SubscriptionAmount/SubscriptionYear");
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "payCardBalance");
			result.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			result.put(ERROR_MSG, "Unable to Subscibe due to " + e.getMessage());
		}
		return result.toJSONString();
	}

	@Transactional
	public String membershipPaymentsValidation(JSONObject obj) {
		String result = "Sorry Registration Failed !";

		try {
			if (obj != null) {
				String cardNo = (String) obj.get("CARD_NO");
				String deptId = (String) obj.get(DEPT_ID);
				String paymentConfId = (String) obj.get(PAYMENT_CONF_ID);
				String subscriptionAmount = (String) obj.get("SUBSCRIPTION_AMOUNT");
				String subscriptionYear = (String) obj.get("SUBSCRIBING_YEAR");
				String paidAmount = (String) obj.get(PAID_AMOUNT);
				String paidDate = (String) obj.get("PAID_DATE");
				//String receiptNo = (String) obj.get(RECEIPT_NO);
				String paymentStatus = (String) obj.get("PAYMENT_STATUS");

				if (subscriptionYear == null || "".equalsIgnoreCase(subscriptionYear)) {
					return "Please Select Subscription Year.";
				}

				if (paymentStatus == null || "".equalsIgnoreCase(paymentStatus)
						|| "No".equalsIgnoreCase(paymentStatus)) {
					return "Please Select Subscription Payment Status.";
				}
//				if (receiptNo == null || "".equalsIgnoreCase(receiptNo)) {
//					return "Please Enter ReceiptNo ";
//				}
				if (paidDate == null || "".equalsIgnoreCase(paidDate)) {
					return "Please Select Paid Date Date.";
				}
				if (cardNo == null || "".equalsIgnoreCase(cardNo)) {
					return "Please Select Card No.";
				}
				if (deptId == null || "".equalsIgnoreCase(deptId) || "SELECT".equalsIgnoreCase(deptId)) {
					return "Please select member department.";
				}
				if (subscriptionAmount == null || "".equalsIgnoreCase(subscriptionAmount)) {
					return "Please Enter Subscription Amount.";
				}
				if (paymentConfId == null || "".equalsIgnoreCase(paymentConfId)
						|| "SELECT".equalsIgnoreCase(paymentConfId)) {
					return "Please select payment type.";
				}

				if (!this.utils.isValidDate(paidDate)) {
					return " Incorrect date format for Paid Date , and  date format should be (dd/mm/yyyy)";
				}

				if (!this.utils.isNumericString(subscriptionAmount)) {
					ApplicationUtilities.debug(this.getClass(),String.valueOf(String.valueOf(subscriptionAmount)) + "   isNumericString  "
							+ this.utils.isNumericString(subscriptionAmount));
					return "Please enter only numbers in Subscription Amount";
				}

				if (!this.utils.isNumericString(paidAmount)) {
					ApplicationUtilities.debug(this.getClass(),String.valueOf(String.valueOf(paidAmount)) + "   isNumericString  "
							+ this.utils.isNumericString(paidAmount));
					return "Please enter only numbers in PaidAmount";
				}

				return "SUCCESS";

			}

		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "membershipPaymentsValidation");
		}
		return result;
	}

	@Transactional
	public JSONObject updateCardBalanceFormDetails(HttpServletRequest request) {
		JSONObject resultObj = new JSONObject();

		try {
			String cardNo = request.getParameter("update_cardBalance_cardNo");

			int cardNumber = Integer.parseInt(cardNo);
			String deptId = request.getParameter("update_cardBalance_deptId");

			String pageId = request.getParameter("update_cardBalance_pageId");

			resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "updateCardBalanceFormDetails");
		}

		return resultObj;
	}

	@Transactional
	public JSONObject deleteCardBalanceFormDetails(HttpServletRequest request) {
		JSONObject resultObj = new JSONObject();
		try {
			String cardNo = request.getParameter("delete_cardBalance_cardNo");

			int cardNumber = Integer.parseInt(cardNo);
			String deptId = request.getParameter("delete_cardBalance_deptId");

			String pageId = request.getParameter("delete_cardBalance_pageId");

			resultObj = this.miscellaneousDAO.getTopPanel(cardNumber, deptId, pageId);
		} catch (Exception e) {

			ApplicationUtilities.error(getClass(), e, "deleteCardBalanceFormDetails");
		}

		return resultObj;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public String updateMembershipPayments(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {
			String paidAmount = request.getParameter("paidAmount");
			String paymentConfId = request.getParameter("paymentConfId");
			String paidDate = request.getParameter("paidDate");
			String receiptNo = request.getParameter("receiptNo");
			String remarks = request.getParameter("remarks");
			String memberId = request.getParameter("memberId");
			String transactionId = request.getParameter("transactionId");

			if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId)) {

				Map<String, Object> parametersMap = new HashMap<String, Object>();
				parametersMap.put("memberId", memberId);
				parametersMap.put("transactionId", transactionId);
				parametersMap.put("paidDate", (new SimpleDateFormat("dd/MM/yyyy")).parse(paidDate));
				//receiptNo= this.idGenerator.get(RECEIPT_NO, RECEIPT_NO);
				parametersMap.put("receiptNo", receiptNo);
				parametersMap.put("paidAmount", Integer.valueOf(Integer.parseInt(paidAmount)));
				parametersMap.put("paymentConfId", paymentConfId);
				parametersMap.put("remarks", remarks);
				result.put(FINAL_RESULT_CODE, RESULT_CODE_400);
				result.put(DATA_DETAILS, "Upadated  membership payment  sucessfullty!");
				this.miscellaneousDAO.updateCardBalance(memberId);
			} else {
				result.put(FINAL_RESULT_CODE, RESULT_CODE_200);
				result.put(ERROR_MSG, "Wrong memberid and transaction id for updating Membership Paments!");
			}

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(getClass(), nfe, "updateMembershipPayments");
			result.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			result.put(ERROR_MSG, "Please provide valid input for PaidAmount");
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "updateMembershipPayments");
			result.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			result.put(ERROR_MSG, "Please provide valid input for PaidAmount");
		}

		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public String deleteMembershipPayments(HttpServletRequest request) {
		JSONObject result = new JSONObject();

		try {

			String memberId = request.getParameter("memberId");
			String transactionId = request.getParameter("transactionId");

			if (memberId != null && !"".equals(memberId) && transactionId != null && !"".equals(transactionId)) {
				String updateQuery = "delete from  MembershipPayments  where membershipPaymentsPK.transactionId=:transactionId and membershipPaymentsPK.memberId=:memberId ";

				Map<String, Object> parametersMap = new HashMap<String, Object>();
				parametersMap.put("memberId", memberId);
				parametersMap.put("transactionId", transactionId);
				@SuppressWarnings("unused")
				int updatedRecords = this.dataAccess.updateQueryByCount(updateQuery, parametersMap);
				result.put(FINAL_RESULT_CODE, RESULT_CODE_400);
				result.put(DATA_DETAILS, "Upadated  membership payment  sucessfullty!");
				this.miscellaneousDAO.updateCardBalance(memberId);
			} else {

				result.put(FINAL_RESULT_CODE, RESULT_CODE_200);
				result.put(ERROR_MSG, "Wrong memberid and transaction id for updating Membership Paments!");
			}

		} catch (NumberFormatException nfe) {
			ApplicationUtilities.error(getClass(), nfe, "deleteMembershipPayments");
			result.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			result.put(ERROR_MSG, "Please provide valid input for PaidAmount");
		} catch (Exception e) {
			ApplicationUtilities.error(getClass(), e, "deleteMembershipPayments");
			result.put(FINAL_RESULT_CODE, RESULT_CODE_300);
			result.put(ERROR_MSG, "Please provide valid input for PaidAmount");
		}

		return result.toString();
	}
}
