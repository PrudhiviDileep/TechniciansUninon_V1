/*    */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.math.BigInteger;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Embeddable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Embeddable
/*    */ public class NumberGenerationPK
/*    */   implements Serializable
/*    */ {
/*    */   @Basic(optional = false)
/*    */   @Column(name = "S_NO")
/*    */   private int sNo;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "SEQ_TXT")
/*    */   private String seqTxt;
/*    */   @Basic(optional = false)
/*    */   @Column(name = "STRT_UNIQUE_SEQ")
/*    */   private BigInteger strtUniqueSeq;
/*    */   
/*    */   public NumberGenerationPK() {}
/*    */   
/*    */   public NumberGenerationPK(int sNo, String seqTxt, BigInteger strtUniqueSeq) {
/* 35 */     this.sNo = sNo;
/* 36 */     this.seqTxt = seqTxt;
/* 37 */     this.strtUniqueSeq = strtUniqueSeq;
/*    */   }
/*    */   
/*    */   public int getSNo() {
/* 41 */     return this.sNo;
/*    */   }
/*    */   
/*    */   public void setSNo(int sNo) {
/* 45 */     this.sNo = sNo;
/*    */   }
/*    */   
/*    */   public String getSeqTxt() {
/* 49 */     return this.seqTxt;
/*    */   }
/*    */   
/*    */   public void setSeqTxt(String seqTxt) {
/* 53 */     this.seqTxt = seqTxt;
/*    */   }
/*    */   
/*    */   public BigInteger getStrtUniqueSeq() {
/* 57 */     return this.strtUniqueSeq;
/*    */   }
/*    */   
/*    */   public void setStrtUniqueSeq(BigInteger strtUniqueSeq) {
/* 61 */     this.strtUniqueSeq = strtUniqueSeq;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 66 */     int hash = 0;
/* 67 */     hash += this.sNo;
/* 68 */     hash += (this.seqTxt != null) ? this.seqTxt.hashCode() : 0;
/* 69 */     return (this.strtUniqueSeq != null) ? this.strtUniqueSeq.hashCode() : 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 76 */     if (!(object instanceof NumberGenerationPK)) {
/* 77 */       return false;
/*    */     }
/* 79 */     NumberGenerationPK other = (NumberGenerationPK)object;
/* 80 */     if (this.sNo != other.sNo) {
/* 81 */       return false;
/*    */     }
/* 83 */     if ((this.seqTxt == null && other.seqTxt != null) || (this.seqTxt != null && !this.seqTxt.equals(other.seqTxt))) {
/* 84 */       return false;
/*    */     }
/* 86 */     if ((this.strtUniqueSeq == null && other.strtUniqueSeq != null) || (this.strtUniqueSeq != null && !this.strtUniqueSeq.equals(other.strtUniqueSeq))) {
/* 87 */       return false;
/*    */     }
/* 89 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 94 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.NumberGenerationPK[ sNo=" + this.sNo + ", seqTxt=" + this.seqTxt + ", strtUniqueSeq=" + this.strtUniqueSeq + " ]";
/*    */   }
/*    */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\NumberGenerationPK.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */