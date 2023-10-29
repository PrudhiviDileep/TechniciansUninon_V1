package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;























@Entity
@Table(name = "payment_configurations")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "PaymentConfigurations.findAll", query = "SELECT p FROM PaymentConfigurations p"), @NamedQuery(name = "PaymentConfigurations.findBySNo", query = "SELECT p FROM PaymentConfigurations p WHERE p.sNo = :sNo"), @NamedQuery(name = "PaymentConfigurations.findByPaymentConfId", query = "SELECT p FROM PaymentConfigurations p WHERE p.paymentConfigurationsPK.paymentConfId = :paymentConfId"), @NamedQuery(name = "PaymentConfigurations.findByDeptId", query = "SELECT p FROM PaymentConfigurations p WHERE p.paymentConfigurationsPK.deptId = :deptId"), @NamedQuery(name = "PaymentConfigurations.findByCategory", query = "SELECT p FROM PaymentConfigurations p WHERE p.paymentConfigurationsPK.category = :category"), @NamedQuery(name = "PaymentConfigurations.findByDonationAmount", query = "SELECT p FROM PaymentConfigurations p WHERE p.donationAmount = :donationAmount"), @NamedQuery(name = "PaymentConfigurations.findBySubscriptionAmount", query = "SELECT p FROM PaymentConfigurations p WHERE p.subscriptionAmount = :subscriptionAmount"), @NamedQuery(name = "PaymentConfigurations.findByAdminAmount", query = "SELECT p FROM PaymentConfigurations p WHERE p.adminAmount = :adminAmount"), @NamedQuery(name = "PaymentConfigurations.findByConfiguredDate", query = "SELECT p FROM PaymentConfigurations p WHERE p.configuredDate = :configuredDate"), @NamedQuery(name = "PaymentConfigurations.findByConfiguredBy", query = "SELECT p FROM PaymentConfigurations p WHERE p.configuredBy = :configuredBy"), @NamedQuery(name = "PaymentConfigurations.findByMembershipAmount", query = "SELECT p FROM PaymentConfigurations p WHERE p.membershipAmount = :membershipAmount"), @NamedQuery(name = "PaymentConfigurations.findByRemarks", query = "SELECT p FROM PaymentConfigurations p WHERE p.remarks = :remarks"), @NamedQuery(name = "PaymentConfigurations.findByStatus", query = "SELECT p FROM PaymentConfigurations p WHERE p.status = :status")})
public class PaymentConfigurations
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected PaymentConfigurationsPK paymentConfigurationsPK;
  @Column(name = "S_NO")
  private Integer sNo;
  @Basic(optional = false)
  @Column(name = "DONATION_AMOUNT")
  private int donationAmount;
  @Basic(optional = false)
  @Column(name = "SUBSCRIPTION_AMOUNT")
  private int subscriptionAmount;
  @Basic(optional = false)
  @Column(name = "ADMIN_AMOUNT")
  private int adminAmount;
  @Basic(optional = false)
  @Column(name = "CONFIGURED_DATE")
  @Temporal(TemporalType.DATE)
  private Date configuredDate;
  @Basic(optional = false)
  @Column(name = "CONFIGURED_BY")
  private String configuredBy;
  @Basic(optional = false)
  @Column(name = "MEMBERSHIP_AMOUNT")
  private int membershipAmount;
   @Column(name = "REMARKS")
   private String remarks;
   @Basic(optional = false)
   @Column(name = "STATUS")
   private String status;
   
   public PaymentConfigurations() {}
   
   public PaymentConfigurations(PaymentConfigurationsPK paymentConfigurationsPK) {
     this.paymentConfigurationsPK = paymentConfigurationsPK;
   }
   
   public PaymentConfigurations(PaymentConfigurationsPK paymentConfigurationsPK, int donationAmount, int subscriptionAmount, int adminAmount, Date configuredDate, String configuredBy, int membershipAmount, String status) {
     this.paymentConfigurationsPK = paymentConfigurationsPK;
     this.donationAmount = donationAmount;
     this.subscriptionAmount = subscriptionAmount;
     this.adminAmount = adminAmount;
     this.configuredDate = configuredDate;
     this.configuredBy = configuredBy;
     this.membershipAmount = membershipAmount;
     this.status = status;
   }
   
   public PaymentConfigurations(String paymentConfId, String deptId, String category) {
     this.paymentConfigurationsPK = new PaymentConfigurationsPK(paymentConfId, deptId, category);
   }
   
   public PaymentConfigurationsPK getPaymentConfigurationsPK() {
     return this.paymentConfigurationsPK;
   }
   
   public void setPaymentConfigurationsPK(PaymentConfigurationsPK paymentConfigurationsPK) {
     this.paymentConfigurationsPK = paymentConfigurationsPK;
   }
   
   public Integer getSNo() {
     return this.sNo;
   }
   
   public void setSNo(Integer sNo) {
     this.sNo = sNo;
   }
   
   public int getDonationAmount() {
     return this.donationAmount;
   }
   
   public void setDonationAmount(int donationAmount) {
     this.donationAmount = donationAmount;
   }
   
   public int getSubscriptionAmount() {
     return this.subscriptionAmount;
   }
   
   public void setSubscriptionAmount(int subscriptionAmount) {
     this.subscriptionAmount = subscriptionAmount;
   }
   
   public int getAdminAmount() {
     return this.adminAmount;
   }
   
   public void setAdminAmount(int adminAmount) {
     this.adminAmount = adminAmount;
	}

	public Date getConfiguredDate() {
		return this.configuredDate;
	}

	public void setConfiguredDate(Date configuredDate) {
		this.configuredDate = configuredDate;
	}

	public String getConfiguredBy() {
		return this.configuredBy;
	}

	public void setConfiguredBy(String configuredBy) {
		this.configuredBy = configuredBy;
	}

	public int getMembershipAmount() {
		return this.membershipAmount;
	}

	public void setMembershipAmount(int membershipAmount) {
		this.membershipAmount = membershipAmount;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int hashCode() {
		int hash = 0;
		return (this.paymentConfigurationsPK != null) ? this.paymentConfigurationsPK.hashCode() : 0;
	}

	public boolean equals(Object object) {
		if (!(object instanceof PaymentConfigurations)) {
			return false;
		}
		PaymentConfigurations other = (PaymentConfigurations) object;
		if ((this.paymentConfigurationsPK == null && other.paymentConfigurationsPK != null)
				|| (this.paymentConfigurationsPK != null
						&& !this.paymentConfigurationsPK.equals(other.paymentConfigurationsPK))) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.PaymentConfigurations[ paymentConfigurationsPK="
				+ this.paymentConfigurationsPK + " ]";
	}
 }
