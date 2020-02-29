/*
package com.zy.learning.annotation.demotest.dao;

import com.zy.learning.annotation.demotest.model.TEmployee;
import org.apache.ibatis.jdbc.SQL;

public class TEmployeeSqlProvider {

    public String insertSelective(TEmployee record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_EMPLOYEE");
        
        if (record.getEmpNo() != null) {
            sql.VALUES("EMP_NO", "#{empNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgNo() != null) {
            sql.VALUES("ORG_NO", "#{orgNo,jdbcType=VARCHAR}");
        }
        
        if (record.getEmpName() != null) {
            sql.VALUES("EMP_NAME", "#{empName,jdbcType=VARCHAR}");
        }
        
        if (record.getEmpPass() != null) {
            sql.VALUES("EMP_PASS", "#{empPass,jdbcType=VARCHAR}");
        }
        
        if (record.getPhnCoding() != null) {
            sql.VALUES("PHN_CODING", "#{phnCoding,jdbcType=VARCHAR}");
        }
        
        if (record.getEmpStsCode() != null) {
            sql.VALUES("EMP_STS_CODE", "#{empStsCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRegDate() != null) {
            sql.VALUES("REG_DATE", "#{regDate,jdbcType=DATE}");
        }
        
        if (record.getLeaveDate() != null) {
            sql.VALUES("LEAVE_DATE", "#{leaveDate,jdbcType=DATE}");
        }
        
        if (record.getComPost() != null) {
            sql.VALUES("COM_POST", "#{comPost,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartType() != null) {
            sql.VALUES("DEPART_TYPE", "#{departType,jdbcType=VARCHAR}");
        }
        
        if (record.getCredentialsTypeCode() != null) {
            sql.VALUES("CREDENTIALS_TYPE_CODE", "#{credentialsTypeCode,jdbcType=VARCHAR}");
        }
        
        if (record.getIdNo() != null) {
            sql.VALUES("ID_NO", "#{idNo,jdbcType=VARCHAR}");
        }
        
        if (record.getGenderCode() != null) {
            sql.VALUES("GENDER_CODE", "#{genderCode,jdbcType=VARCHAR}");
        }
        
        if (record.getNationCode() != null) {
            sql.VALUES("NATION_CODE", "#{nationCode,jdbcType=VARCHAR}");
        }
        
        if (record.getDiplomaCode() != null) {
            sql.VALUES("DIPLOMA_CODE", "#{diplomaCode,jdbcType=VARCHAR}");
        }
        
        if (record.getFaxNum() != null) {
            sql.VALUES("FAX_NUM", "#{faxNum,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.VALUES("EMAIL", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getMaritalStsCode() != null) {
            sql.VALUES("MARITAL_STS_CODE", "#{maritalStsCode,jdbcType=VARCHAR}");
        }
        
        if (record.getIsAppraise() != null) {
            sql.VALUES("IS_APPRAISE", "#{isAppraise,jdbcType=VARCHAR}");
        }
        
        if (record.getDataSources() != null) {
            sql.VALUES("DATA_SOURCES", "#{dataSources,jdbcType=VARCHAR}");
        }
        
        if (record.getAdAcct() != null) {
            sql.VALUES("AD_ACCT", "#{adAcct,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("REMARK", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TEmployee record) {
        SQL sql = new SQL();
        sql.UPDATE("T_EMPLOYEE");
        
        if (record.getOrgNo() != null) {
            sql.SET("ORG_NO = #{orgNo,jdbcType=VARCHAR}");
        }
        
        if (record.getEmpName() != null) {
            sql.SET("EMP_NAME = #{empName,jdbcType=VARCHAR}");
        }
        
        if (record.getEmpPass() != null) {
            sql.SET("EMP_PASS = #{empPass,jdbcType=VARCHAR}");
        }
        
        if (record.getPhnCoding() != null) {
            sql.SET("PHN_CODING = #{phnCoding,jdbcType=VARCHAR}");
        }
        
        if (record.getEmpStsCode() != null) {
            sql.SET("EMP_STS_CODE = #{empStsCode,jdbcType=VARCHAR}");
        }
        
        if (record.getRegDate() != null) {
            sql.SET("REG_DATE = #{regDate,jdbcType=DATE}");
        }
        
        if (record.getLeaveDate() != null) {
            sql.SET("LEAVE_DATE = #{leaveDate,jdbcType=DATE}");
        }
        
        if (record.getComPost() != null) {
            sql.SET("COM_POST = #{comPost,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartType() != null) {
            sql.SET("DEPART_TYPE = #{departType,jdbcType=VARCHAR}");
        }
        
        if (record.getCredentialsTypeCode() != null) {
            sql.SET("CREDENTIALS_TYPE_CODE = #{credentialsTypeCode,jdbcType=VARCHAR}");
        }
        
        if (record.getIdNo() != null) {
            sql.SET("ID_NO = #{idNo,jdbcType=VARCHAR}");
        }
        
        if (record.getGenderCode() != null) {
            sql.SET("GENDER_CODE = #{genderCode,jdbcType=VARCHAR}");
        }
        
        if (record.getNationCode() != null) {
            sql.SET("NATION_CODE = #{nationCode,jdbcType=VARCHAR}");
        }
        
        if (record.getDiplomaCode() != null) {
            sql.SET("DIPLOMA_CODE = #{diplomaCode,jdbcType=VARCHAR}");
        }
        
        if (record.getFaxNum() != null) {
            sql.SET("FAX_NUM = #{faxNum,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("EMAIL = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getMaritalStsCode() != null) {
            sql.SET("MARITAL_STS_CODE = #{maritalStsCode,jdbcType=VARCHAR}");
        }
        
        if (record.getIsAppraise() != null) {
            sql.SET("IS_APPRAISE = #{isAppraise,jdbcType=VARCHAR}");
        }
        
        if (record.getDataSources() != null) {
            sql.SET("DATA_SOURCES = #{dataSources,jdbcType=VARCHAR}");
        }
        
        if (record.getAdAcct() != null) {
            sql.SET("AD_ACCT = #{adAcct,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("REMARK = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("EMP_NO = #{empNo,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}*/
