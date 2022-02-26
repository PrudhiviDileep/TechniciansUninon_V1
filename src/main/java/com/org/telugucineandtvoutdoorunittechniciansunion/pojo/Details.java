/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.Table;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "details")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Details.findAll", query = "SELECT d FROM Details d"), @NamedQuery(name = "Details.findByDetailsId", query = "SELECT d FROM Details d WHERE d.detailsId = :detailsId"), @NamedQuery(name = "Details.findByMname", query = "SELECT d FROM Details d WHERE d.mname = :mname"), @NamedQuery(name = "Details.findByMfname", query = "SELECT d FROM Details d WHERE d.mfname = :mfname"), @NamedQuery(name = "Details.findByMdob", query = "SELECT d FROM Details d WHERE d.mdob = :mdob"), @NamedQuery(name = "Details.findByMqual", query = "SELECT d FROM Details d WHERE d.mqual = :mqual"), @NamedQuery(name = "Details.findByMmt", query = "SELECT d FROM Details d WHERE d.mmt = :mmt"), @NamedQuery(name = "Details.findByMlang", query = "SELECT d FROM Details d WHERE d.mlang = :mlang"), @NamedQuery(name = "Details.findByBlood", query = "SELECT d FROM Details d WHERE d.blood = :blood"), @NamedQuery(name = "Details.findByNominee", query = "SELECT d FROM Details d WHERE d.nominee = :nominee"), @NamedQuery(name = "Details.findByRel", query = "SELECT d FROM Details d WHERE d.rel = :rel"), @NamedQuery(name = "Details.findBySelect1", query = "SELECT d FROM Details d WHERE d.select1 = :select1"), @NamedQuery(name = "Details.findByMdoj", query = "SELECT d FROM Details d WHERE d.mdoj = :mdoj"), @NamedQuery(name = "Details.findByMnow", query = "SELECT d FROM Details d WHERE d.mnow = :mnow"), @NamedQuery(name = "Details.findByMnoc", query = "SELECT d FROM Details d WHERE d.mnoc = :mnoc"), @NamedQuery(name = "Details.findByMpre", query = "SELECT d FROM Details d WHERE d.mpre = :mpre"), @NamedQuery(name = "Details.findByMprecontact", query = "SELECT d FROM Details d WHERE d.mprecontact = :mprecontact"), @NamedQuery(name = "Details.findByMper", query = "SELECT d FROM Details d WHERE d.mper = :mper"), @NamedQuery(name = "Details.findByMpercontact", query = "SELECT d FROM Details d WHERE d.mpercontact = :mpercontact"), @NamedQuery(name = "Details.findByR1name", query = "SELECT d FROM Details d WHERE d.r1name = :r1name"), @NamedQuery(name = "Details.findByR1cardno", query = "SELECT d FROM Details d WHERE d.r1cardno = :r1cardno"), @NamedQuery(name = "Details.findByR1deptname", query = "SELECT d FROM Details d WHERE d.r1deptname = :r1deptname"), @NamedQuery(name = "Details.findByR1work", query = "SELECT d FROM Details d WHERE d.r1work = :r1work"), @NamedQuery(name = "Details.findByR2name", query = "SELECT d FROM Details d WHERE d.r2name = :r2name"), @NamedQuery(name = "Details.findByR2cardno", query = "SELECT d FROM Details d WHERE d.r2cardno = :r2cardno"), @NamedQuery(name = "Details.findByR2deptname", query = "SELECT d FROM Details d WHERE d.r2deptname = :r2deptname"), @NamedQuery(name = "Details.findByR2work", query = "SELECT d FROM Details d WHERE d.r2work = :r2work"), @NamedQuery(name = "Details.findByMsno", query = "SELECT d FROM Details d WHERE d.msno = :msno"), @NamedQuery(name = "Details.findByMcardno", query = "SELECT d FROM Details d WHERE d.mcardno = :mcardno"), @NamedQuery(name = "Details.findByMdeptname", query = "SELECT d FROM Details d WHERE d.mdeptname = :mdeptname"), @NamedQuery(name = "Details.findByCardamnt", query = "SELECT d FROM Details d WHERE d.cardamnt = :cardamnt"), @NamedQuery(name = "Details.findByAmount", query = "SELECT d FROM Details d WHERE d.amount = :amount"), @NamedQuery(name = "Details.findByRecptno", query = "SELECT d FROM Details d WHERE d.recptno = :recptno"), @NamedQuery(name = "Details.findByAppdate", query = "SELECT d FROM Details d WHERE d.appdate = :appdate")})
/*     */ public class Details
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   @Basic(optional = false)
/*     */   @Column(name = "details_id")
/*     */   private Integer detailsId;
/*     */   @Column(name = "mname")
/*     */   private String mname;
/*     */   @Column(name = "mfname")
/*     */   private String mfname;
/*     */   @Column(name = "mdob")
/*     */   private String mdob;
/*     */   @Column(name = "mqual")
/*     */   private String mqual;
/*     */   @Column(name = "mmt")
/*     */   private String mmt;
/*     */   @Column(name = "mlang")
/*     */   private String mlang;
/*     */   @Column(name = "blood")
/*     */   private String blood;
/*     */   @Column(name = "nominee")
/*     */   private String nominee;
/*     */   @Column(name = "rel")
/*     */   private String rel;
/*     */   @Column(name = "select1")
/*     */   private String select1;
/*     */   @Column(name = "mdoj")
/*     */   private String mdoj;
/*     */   @Column(name = "mnow")
/*     */   private String mnow;
/*     */   @Column(name = "mnoc")
/*     */   private String mnoc;
/*     */   @Column(name = "mpre")
/*     */   private String mpre;
/*     */   @Column(name = "mprecontact")
/*     */   private String mprecontact;
/*     */   @Column(name = "mper")
/*     */   private String mper;
/*     */   @Column(name = "mpercontact")
/*     */   private String mpercontact;
/*     */   @Column(name = "r1name")
/*     */   private String r1name;
/*     */   @Column(name = "r1cardno")
/*     */   private String r1cardno;
/*     */   @Column(name = "r1deptname")
/*     */   private String r1deptname;
/*     */   @Column(name = "r1work")
/*     */   private String r1work;
/*     */   @Column(name = "r2name")
/*     */   private String r2name;
/*     */   @Column(name = "r2cardno")
/*     */   private String r2cardno;
/*     */   @Column(name = "r2deptname")
/*     */   private String r2deptname;
/*     */   @Column(name = "r2work")
/*     */   private String r2work;
/*     */   @Column(name = "msno")
/*     */   private String msno;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "mcardno")
/*     */   private int mcardno;
/*     */   @Column(name = "mdeptname")
/*     */   private String mdeptname;
/*     */   @Column(name = "cardamnt")
/*     */   private String cardamnt;
/*     */   @Column(name = "amount")
/*     */   private String amount;
/*     */   @Column(name = "recptno")
/*     */   private String recptno;
/*     */   @Column(name = "appdate")
/*     */   private String appdate;
/*     */   
/*     */   public Details() {}
/*     */   
/*     */   public Details(Integer detailsId) {
/* 140 */     this.detailsId = detailsId;
/*     */   }
/*     */   
/*     */   public Details(Integer detailsId, int mcardno) {
/* 144 */     this.detailsId = detailsId;
/* 145 */     this.mcardno = mcardno;
/*     */   }
/*     */   
/*     */   public Integer getDetailsId() {
/* 149 */     return this.detailsId;
/*     */   }
/*     */   
/*     */   public void setDetailsId(Integer detailsId) {
/* 153 */     this.detailsId = detailsId;
/*     */   }
/*     */   
/*     */   public String getMname() {
/* 157 */     return this.mname;
/*     */   }
/*     */   
/*     */   public void setMname(String mname) {
/* 161 */     this.mname = mname;
/*     */   }
/*     */   
/*     */   public String getMfname() {
/* 165 */     return this.mfname;
/*     */   }
/*     */   
/*     */   public void setMfname(String mfname) {
/* 169 */     this.mfname = mfname;
/*     */   }
/*     */   
/*     */   public String getMdob() {
/* 173 */     return this.mdob;
/*     */   }
/*     */   
/*     */   public void setMdob(String mdob) {
/* 177 */     this.mdob = mdob;
/*     */   }
/*     */   
/*     */   public String getMqual() {
/* 181 */     return this.mqual;
/*     */   }
/*     */   
/*     */   public void setMqual(String mqual) {
/* 185 */     this.mqual = mqual;
/*     */   }
/*     */   
/*     */   public String getMmt() {
/* 189 */     return this.mmt;
/*     */   }
/*     */   
/*     */   public void setMmt(String mmt) {
/* 193 */     this.mmt = mmt;
/*     */   }
/*     */   
/*     */   public String getMlang() {
/* 197 */     return this.mlang;
/*     */   }
/*     */   
/*     */   public void setMlang(String mlang) {
/* 201 */     this.mlang = mlang;
/*     */   }
/*     */   
/*     */   public String getBlood() {
/* 205 */     return this.blood;
/*     */   }
/*     */   
/*     */   public void setBlood(String blood) {
/* 209 */     this.blood = blood;
/*     */   }
/*     */   
/*     */   public String getNominee() {
/* 213 */     return this.nominee;
/*     */   }
/*     */   
/*     */   public void setNominee(String nominee) {
/* 217 */     this.nominee = nominee;
/*     */   }
/*     */   
/*     */   public String getRel() {
/* 221 */     return this.rel;
/*     */   }
/*     */   
/*     */   public void setRel(String rel) {
/* 225 */     this.rel = rel;
/*     */   }
/*     */   
/*     */   public String getSelect1() {
/* 229 */     return this.select1;
/*     */   }
/*     */   
/*     */   public void setSelect1(String select1) {
/* 233 */     this.select1 = select1;
/*     */   }
/*     */   
/*     */   public String getMdoj() {
/* 237 */     return this.mdoj;
/*     */   }
/*     */   
/*     */   public void setMdoj(String mdoj) {
/* 241 */     this.mdoj = mdoj;
/*     */   }
/*     */   
/*     */   public String getMnow() {
/* 245 */     return this.mnow;
/*     */   }
/*     */   
/*     */   public void setMnow(String mnow) {
/* 249 */     this.mnow = mnow;
/*     */   }
/*     */   
/*     */   public String getMnoc() {
/* 253 */     return this.mnoc;
/*     */   }
/*     */   
/*     */   public void setMnoc(String mnoc) {
/* 257 */     this.mnoc = mnoc;
/*     */   }
/*     */   
/*     */   public String getMpre() {
/* 261 */     return this.mpre;
/*     */   }
/*     */   
/*     */   public void setMpre(String mpre) {
/* 265 */     this.mpre = mpre;
/*     */   }
/*     */   
/*     */   public String getMprecontact() {
/* 269 */     return this.mprecontact;
/*     */   }
/*     */   
/*     */   public void setMprecontact(String mprecontact) {
/* 273 */     this.mprecontact = mprecontact;
/*     */   }
/*     */   
/*     */   public String getMper() {
/* 277 */     return this.mper;
/*     */   }
/*     */   
/*     */   public void setMper(String mper) {
/* 281 */     this.mper = mper;
/*     */   }
/*     */   
/*     */   public String getMpercontact() {
/* 285 */     return this.mpercontact;
/*     */   }
/*     */   
/*     */   public void setMpercontact(String mpercontact) {
/* 289 */     this.mpercontact = mpercontact;
/*     */   }
/*     */   
/*     */   public String getR1name() {
/* 293 */     return this.r1name;
/*     */   }
/*     */   
/*     */   public void setR1name(String r1name) {
/* 297 */     this.r1name = r1name;
/*     */   }
/*     */   
/*     */   public String getR1cardno() {
/* 301 */     return this.r1cardno;
/*     */   }
/*     */   
/*     */   public void setR1cardno(String r1cardno) {
/* 305 */     this.r1cardno = r1cardno;
/*     */   }
/*     */   
/*     */   public String getR1deptname() {
/* 309 */     return this.r1deptname;
/*     */   }
/*     */   
/*     */   public void setR1deptname(String r1deptname) {
/* 313 */     this.r1deptname = r1deptname;
/*     */   }
/*     */   
/*     */   public String getR1work() {
/* 317 */     return this.r1work;
/*     */   }
/*     */   
/*     */   public void setR1work(String r1work) {
/* 321 */     this.r1work = r1work;
/*     */   }
/*     */   
/*     */   public String getR2name() {
/* 325 */     return this.r2name;
/*     */   }
/*     */   
/*     */   public void setR2name(String r2name) {
/* 329 */     this.r2name = r2name;
/*     */   }
/*     */   
/*     */   public String getR2cardno() {
/* 333 */     return this.r2cardno;
/*     */   }
/*     */   
/*     */   public void setR2cardno(String r2cardno) {
/* 337 */     this.r2cardno = r2cardno;
/*     */   }
/*     */   
/*     */   public String getR2deptname() {
/* 341 */     return this.r2deptname;
/*     */   }
/*     */   
/*     */   public void setR2deptname(String r2deptname) {
/* 345 */     this.r2deptname = r2deptname;
/*     */   }
/*     */   
/*     */   public String getR2work() {
/* 349 */     return this.r2work;
/*     */   }
/*     */   
/*     */   public void setR2work(String r2work) {
/* 353 */     this.r2work = r2work;
/*     */   }
/*     */   
/*     */   public String getMsno() {
/* 357 */     return this.msno;
/*     */   }
/*     */   
/*     */   public void setMsno(String msno) {
/* 361 */     this.msno = msno;
/*     */   }
/*     */   
/*     */   public int getMcardno() {
/* 365 */     return this.mcardno;
/*     */   }
/*     */   
/*     */   public void setMcardno(int mcardno) {
/* 369 */     this.mcardno = mcardno;
/*     */   }
/*     */   
/*     */   public String getMdeptname() {
/* 373 */     return this.mdeptname;
/*     */   }
/*     */   
/*     */   public void setMdeptname(String mdeptname) {
/* 377 */     this.mdeptname = mdeptname;
/*     */   }
/*     */   
/*     */   public String getCardamnt() {
/* 381 */     return this.cardamnt;
/*     */   }
/*     */   
/*     */   public void setCardamnt(String cardamnt) {
/* 385 */     this.cardamnt = cardamnt;
/*     */   }
/*     */   
/*     */   public String getAmount() {
/* 389 */     return this.amount;
/*     */   }
/*     */   
/*     */   public void setAmount(String amount) {
/* 393 */     this.amount = amount;
/*     */   }
/*     */   
/*     */   public String getRecptno() {
/* 397 */     return this.recptno;
/*     */   }
/*     */   
/*     */   public void setRecptno(String recptno) {
/* 401 */     this.recptno = recptno;
/*     */   }
/*     */   
/*     */   public String getAppdate() {
/* 405 */     return this.appdate;
/*     */   }
/*     */   
/*     */   public void setAppdate(String appdate) {
/* 409 */     this.appdate = appdate;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 414 */     int hash = 0;
/* 415 */     return (this.detailsId != null) ? this.detailsId.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 422 */     if (!(object instanceof Details)) {
/* 423 */       return false;
/*     */     }
/* 425 */     Details other = (Details)object;
/* 426 */     if ((this.detailsId == null && other.detailsId != null) || (this.detailsId != null && !this.detailsId.equals(other.detailsId))) {
/* 427 */       return false;
/*     */     }
/* 429 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 434 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.Details[ detailsId=" + this.detailsId + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\Details.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */