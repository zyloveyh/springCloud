package com.zy.learning.annotation.demotest.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Table(name = "T_EMPLOYEE")
@ApiModel(value = "TEmployee")
public class TEmployee {

    @NotNull
    private List<String> sL;

    public List<String> getsL() {
        return sL;
    }

    public void setsL(List<String> sL) {
        this.sL = sL;
    }
/*
    @JSONField(name="EmpNo")
    @Column(name="empNo",length=10)
//    @Size(message="empNo max size is 10",max=10)
    @ApiModelProperty(required = true)
    @NotBlank(message="empNo is required not empty!")
    private String empNo;

    @JSONField(name="OrgNo")
    @Column(name="orgNo",length=30)
    @Size(message="orgNo max size is 30",max=30)
    private String orgNo;

    @JSONField(name="EmpName")
    @Column(name="empName",length=40)
    @Size(message="empName max size is 40",max=40)
    @ApiModelProperty(required = true)
    @NotBlank(message="empName is required not empty!")
    private String empName;

    @JSONField(name="EmpPass")
    @Column(name="empPass",length=32)
    @Size(message="empPass max size is 32",max=32)
    private String empPass;

    @JSONField(name="PhnCoding")
    @Column(name="phnCoding",length=20)
    @Size(message="phnCoding max size is 20",max=20)
    private String phnCoding;

    @JSONField(name="EmpStsCode")
    @Column(name="empStsCode",length=10)
    @Size(message="empStsCode max size is 10",max=10)
    @ApiModelProperty(required = true)
    @NotBlank(message="empStsCode is required not empty!")
    private String empStsCode;

    @JSONField(name="RegDate")
    @Column(name="regDate",length=10)
    @Size(message="regDate max size is 10",max=10)
    private Date regDate;

    @JSONField(name="LeaveDate")
    @Column(name="leaveDate",length=10)
    @Size(message="leaveDate max size is 10",max=10)
    private Date leaveDate;

    @JSONField(name="ComPost")
    @Column(name="comPost",length=20)
    @Size(message="comPost max size is 20",max=20)
    private String comPost;

    @JSONField(name="DepartType")
    @Column(name="departType",length=20)
    @Size(message="departType max size is 20",max=20)
    private String departType;

    @JSONField(name="CredentialsTypeCode")
    @Column(name="credentialsTypeCode",length=3)
    @Size(message="credentialsTypeCode max size is 3",max=3)
    private String credentialsTypeCode;

    @JSONField(name="IdNo")
    @Column(name="idNo",length=30)
    @Size(message="idNo max size is 30",max=30)
    private String idNo;

    @JSONField(name="GenderCode")
    @Column(name="genderCode",length=6)
    @Size(message="genderCode max size is 6",max=6)
    private String genderCode;

    @JSONField(name="NationCode")
    @Column(name="nationCode",length=20)
    @Size(message="nationCode max size is 20",max=20)
    private String nationCode;

    @JSONField(name="DiplomaCode")
    @Column(name="diplomaCode",length=6)
    @Size(message="diplomaCode max size is 6",max=6)
    private String diplomaCode;

    @JSONField(name="FaxNum")
    @Column(name="faxNum",length=30)
    @Size(message="faxNum max size is 30",max=30)
    private String faxNum;

    @JSONField(name="Email")
    @Column(name="email",length=100)
    @Size(message="email max size is 100",max=100)
    private String email;

    @JSONField(name="MaritalStsCode")
    @Column(name="maritalStsCode",length=20)
    @Size(message="maritalStsCode max size is 20",max=20)
    private String maritalStsCode;

    @JSONField(name="IsAppraise")
    @Column(name="isAppraise",length=20)
    @Size(message="isAppraise max size is 20",max=20)
    private String isAppraise;

    @JSONField(name="DataSources")
    @Column(name="dataSources",length=6)
    @Size(message="dataSources max size is 6",max=6)
    private String dataSources;

    @JSONField(name="AdAcct")
    @Column(name="adAcct",length=300)
    @Size(message="adAcct max size is 300",max=300)
    private String adAcct;

    @JSONField(name="Remark")
    @Column(name="remark",length=200)
    @Size(message="remark max size is 200",max=200)
    private String remark;

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass == null ? null : empPass.trim();
    }

    public String getPhnCoding() {
        return phnCoding;
    }

    public void setPhnCoding(String phnCoding) {
        this.phnCoding = phnCoding == null ? null : phnCoding.trim();
    }

    public String getEmpStsCode() {
        return empStsCode;
    }

    public void setEmpStsCode(String empStsCode) {
        this.empStsCode = empStsCode == null ? null : empStsCode.trim();
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getComPost() {
        return comPost;
    }

    public void setComPost(String comPost) {
        this.comPost = comPost == null ? null : comPost.trim();
    }

    public String getDepartType() {
        return departType;
    }

    public void setDepartType(String departType) {
        this.departType = departType == null ? null : departType.trim();
    }

    public String getCredentialsTypeCode() {
        return credentialsTypeCode;
    }

    public void setCredentialsTypeCode(String credentialsTypeCode) {
        this.credentialsTypeCode = credentialsTypeCode == null ? null : credentialsTypeCode.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode == null ? null : genderCode.trim();
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode == null ? null : nationCode.trim();
    }

    public String getDiplomaCode() {
        return diplomaCode;
    }

    public void setDiplomaCode(String diplomaCode) {
        this.diplomaCode = diplomaCode == null ? null : diplomaCode.trim();
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum == null ? null : faxNum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMaritalStsCode() {
        return maritalStsCode;
    }

    public void setMaritalStsCode(String maritalStsCode) {
        this.maritalStsCode = maritalStsCode == null ? null : maritalStsCode.trim();
    }

    public String getIsAppraise() {
        return isAppraise;
    }

    public void setIsAppraise(String isAppraise) {
        this.isAppraise = isAppraise == null ? null : isAppraise.trim();
    }

    public String getDataSources() {
        return dataSources;
    }

    public void setDataSources(String dataSources) {
        this.dataSources = dataSources == null ? null : dataSources.trim();
    }

    public String getAdAcct() {
        return adAcct;
    }

    public void setAdAcct(String adAcct) {
        this.adAcct = adAcct == null ? null : adAcct.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "TEmployee{" +
                "empNo='" + empNo + '\'' +
                ", orgNo='" + orgNo + '\'' +
                ", empName='" + empName + '\'' +
                ", empPass='" + empPass + '\'' +
                ", phnCoding='" + phnCoding + '\'' +
                ", empStsCode='" + empStsCode + '\'' +
                ", regDate=" + regDate +
                ", leaveDate=" + leaveDate +
                ", comPost='" + comPost + '\'' +
                ", departType='" + departType + '\'' +
                ", credentialsTypeCode='" + credentialsTypeCode + '\'' +
                ", idNo='" + idNo + '\'' +
                ", genderCode='" + genderCode + '\'' +
                ", nationCode='" + nationCode + '\'' +
                ", diplomaCode='" + diplomaCode + '\'' +
                ", faxNum='" + faxNum + '\'' +
                ", email='" + email + '\'' +
                ", maritalStsCode='" + maritalStsCode + '\'' +
                ", isAppraise='" + isAppraise + '\'' +
                ", dataSources='" + dataSources + '\'' +
                ", adAcct='" + adAcct + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }*/
}