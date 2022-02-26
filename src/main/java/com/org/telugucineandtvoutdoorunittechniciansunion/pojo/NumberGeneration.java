/*     */ package com.org.telugucineandtvoutdoorunittechniciansunion.pojo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigInteger;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.EmbeddedId;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.Lob;
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
/*     */ @Entity
/*     */ @Table(name = "number_generation")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "NumberGeneration.findAll", query = "SELECT n FROM NumberGeneration n"), @NamedQuery(name = "NumberGeneration.findBySNo", query = "SELECT n FROM NumberGeneration n WHERE n.numberGenerationPK.sNo = :sNo"), @NamedQuery(name = "NumberGeneration.findBySeqTxt", query = "SELECT n FROM NumberGeneration n WHERE n.numberGenerationPK.seqTxt = :seqTxt"), @NamedQuery(name = "NumberGeneration.findByStrtUniqueSeq", query = "SELECT n FROM NumberGeneration n WHERE n.numberGenerationPK.strtUniqueSeq = :strtUniqueSeq"), @NamedQuery(name = "NumberGeneration.findByColumnName", query = "SELECT n FROM NumberGeneration n WHERE n.columnName = :columnName"), @NamedQuery(name = "NumberGeneration.findByDescription", query = "SELECT n FROM NumberGeneration n WHERE n.description = :description"), @NamedQuery(name = "NumberGeneration.findByEndUniqueSeq", query = "SELECT n FROM NumberGeneration n WHERE n.endUniqueSeq = :endUniqueSeq"), @NamedQuery(name = "NumberGeneration.findBySeqSize", query = "SELECT n FROM NumberGeneration n WHERE n.seqSize = :seqSize"), @NamedQuery(name = "NumberGeneration.findByType", query = "SELECT n FROM NumberGeneration n WHERE n.type = :type")})
/*     */ public class NumberGeneration
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @EmbeddedId
/*     */   protected NumberGenerationPK numberGenerationPK;
/*     */   @Column(name = "COLUMN_NAME")
/*     */   private String columnName;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "DESCRIPTION")
/*     */   private String description;
/*     */   @Column(name = "END_UNIQUE_SEQ")
/*     */   private BigInteger endUniqueSeq;
/*     */   @Lob
/*     */   @Column(name = "REGEXPR")
/*     */   private String regexpr;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "SEQ_SIZE")
/*     */   private BigInteger seqSize;
/*     */   @Basic(optional = false)
/*     */   @Column(name = "TYPE")
/*     */   private String type;
/*     */   
/*     */   public NumberGeneration() {}
/*     */   
/*     */   public NumberGeneration(NumberGenerationPK numberGenerationPK) {
/*  63 */     this.numberGenerationPK = numberGenerationPK;
/*     */   }
/*     */   
/*     */   public NumberGeneration(NumberGenerationPK numberGenerationPK, String description, BigInteger seqSize, String type) {
/*  67 */     this.numberGenerationPK = numberGenerationPK;
/*  68 */     this.description = description;
/*  69 */     this.seqSize = seqSize;
/*  70 */     this.type = type;
/*     */   }
/*     */   
/*     */   public NumberGeneration(int sNo, String seqTxt, BigInteger strtUniqueSeq) {
/*  74 */     this.numberGenerationPK = new NumberGenerationPK(sNo, seqTxt, strtUniqueSeq);
/*     */   }
/*     */   
/*     */   public NumberGenerationPK getNumberGenerationPK() {
/*  78 */     return this.numberGenerationPK;
/*     */   }
/*     */   
/*     */   public void setNumberGenerationPK(NumberGenerationPK numberGenerationPK) {
/*  82 */     this.numberGenerationPK = numberGenerationPK;
/*     */   }
/*     */   
/*     */   public String getColumnName() {
/*  86 */     return this.columnName;
/*     */   }
/*     */   
/*     */   public void setColumnName(String columnName) {
/*  90 */     this.columnName = columnName;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  94 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  98 */     this.description = description;
/*     */   }
/*     */   
/*     */   public BigInteger getEndUniqueSeq() {
/* 102 */     return this.endUniqueSeq;
/*     */   }
/*     */   
/*     */   public void setEndUniqueSeq(BigInteger endUniqueSeq) {
/* 106 */     this.endUniqueSeq = endUniqueSeq;
/*     */   }
/*     */   
/*     */   public String getRegexpr() {
/* 110 */     return this.regexpr;
/*     */   }
/*     */   
/*     */   public void setRegexpr(String regexpr) {
/* 114 */     this.regexpr = regexpr;
/*     */   }
/*     */   
/*     */   public BigInteger getSeqSize() {
/* 118 */     return this.seqSize;
/*     */   }
/*     */   
/*     */   public void setSeqSize(BigInteger seqSize) {
/* 122 */     this.seqSize = seqSize;
/*     */   }
/*     */   
/*     */   public String getType() {
/* 126 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/* 130 */     this.type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 135 */     int hash = 0;
/* 136 */     return (this.numberGenerationPK != null) ? this.numberGenerationPK.hashCode() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 143 */     if (!(object instanceof NumberGeneration)) {
/* 144 */       return false;
/*     */     }
/* 146 */     NumberGeneration other = (NumberGeneration)object;
/* 147 */     if ((this.numberGenerationPK == null && other.numberGenerationPK != null) || (this.numberGenerationPK != null && !this.numberGenerationPK.equals(other.numberGenerationPK))) {
/* 148 */       return false;
/*     */     }
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 155 */     return "com.telugucineandtvoutdoorunittechniciansunion.application.pojo.NumberGeneration[ numberGenerationPK=" + this.numberGenerationPK + " ]";
/*     */   }
/*     */ }


/* Location:              E:\PRODUCTION\Deployed_24012021\TechniciansUnion\WEB-INF\classes\!\com\org\telugucineandtvoutdoorunittechniciansunion\pojo\NumberGeneration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */