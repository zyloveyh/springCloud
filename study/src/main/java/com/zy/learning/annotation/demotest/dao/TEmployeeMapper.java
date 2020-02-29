package com.zy.learning.annotation.demotest.dao;

import com.zy.learning.annotation.demotest.model.TEmployee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface TEmployeeMapper {
    @Delete({
        "delete from T_EMPLOYEE",
        "where EMP_NO = #{empNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String empNo);

    @Insert({
        "insert into T_EMPLOYEE (EMP_NO, ORG_NO, ",
        "EMP_NAME, EMP_PASS, ",
        "PHN_CODING, EMP_STS_CODE, ",
        "REG_DATE, LEAVE_DATE, COM_POST, ",
        "DEPART_TYPE, CREDENTIALS_TYPE_CODE, ",
        "ID_NO, GENDER_CODE, ",
        "NATION_CODE, DIPLOMA_CODE, ",
        "FAX_NUM, EMAIL, MARITAL_STS_CODE, ",
        "IS_APPRAISE, DATA_SOURCES, ",
        "AD_ACCT, REMARK)",
        "values (#{empNo,jdbcType=VARCHAR}, #{orgNo,jdbcType=VARCHAR}, ",
        "#{empName,jdbcType=VARCHAR}, #{empPass,jdbcType=VARCHAR}, ",
        "#{phnCoding,jdbcType=VARCHAR}, #{empStsCode,jdbcType=VARCHAR}, ",
        "#{regDate,jdbcType=DATE}, #{leaveDate,jdbcType=DATE}, #{comPost,jdbcType=VARCHAR}, ",
        "#{departType,jdbcType=VARCHAR}, #{credentialsTypeCode,jdbcType=VARCHAR}, ",
        "#{idNo,jdbcType=VARCHAR}, #{genderCode,jdbcType=VARCHAR}, ",
        "#{nationCode,jdbcType=VARCHAR}, #{diplomaCode,jdbcType=VARCHAR}, ",
        "#{faxNum,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{maritalStsCode,jdbcType=VARCHAR}, ",
        "#{isAppraise,jdbcType=VARCHAR}, #{dataSources,jdbcType=VARCHAR}, ",
        "#{adAcct,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(TEmployee record);
/*
    @InsertProvider(type=TEmployeeSqlProvider.class, method="insertSelective")
    int insertSelective(TEmployee record);*/

    @Select({
        "select",
        "EMP_NO, ORG_NO, EMP_NAME, EMP_PASS, PHN_CODING, EMP_STS_CODE, REG_DATE, LEAVE_DATE, ",
        "COM_POST, DEPART_TYPE, CREDENTIALS_TYPE_CODE, ID_NO, GENDER_CODE, NATION_CODE, ",
        "DIPLOMA_CODE, FAX_NUM, EMAIL, MARITAL_STS_CODE, IS_APPRAISE, DATA_SOURCES, AD_ACCT, ",
        "REMARK",
        "from T_EMPLOYEE",
        "where EMP_NO = #{empNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="EMP_NO", property="empNo", jdbcType= JdbcType.VARCHAR, id=true),
        @Result(column="ORG_NO", property="orgNo", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMP_NAME", property="empName", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMP_PASS", property="empPass", jdbcType= JdbcType.VARCHAR),
        @Result(column="PHN_CODING", property="phnCoding", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMP_STS_CODE", property="empStsCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="REG_DATE", property="regDate", jdbcType= JdbcType.DATE),
        @Result(column="LEAVE_DATE", property="leaveDate", jdbcType= JdbcType.DATE),
        @Result(column="COM_POST", property="comPost", jdbcType= JdbcType.VARCHAR),
        @Result(column="DEPART_TYPE", property="departType", jdbcType= JdbcType.VARCHAR),
        @Result(column="CREDENTIALS_TYPE_CODE", property="credentialsTypeCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="ID_NO", property="idNo", jdbcType= JdbcType.VARCHAR),
        @Result(column="GENDER_CODE", property="genderCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="NATION_CODE", property="nationCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="DIPLOMA_CODE", property="diplomaCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="FAX_NUM", property="faxNum", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType= JdbcType.VARCHAR),
        @Result(column="MARITAL_STS_CODE", property="maritalStsCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="IS_APPRAISE", property="isAppraise", jdbcType= JdbcType.VARCHAR),
        @Result(column="DATA_SOURCES", property="dataSources", jdbcType= JdbcType.VARCHAR),
        @Result(column="AD_ACCT", property="adAcct", jdbcType= JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType= JdbcType.VARCHAR)
    })
    TEmployee selectByPrimaryKey(String empNo);

    @Select({
        "select",
        "EMP_NO, ORG_NO, EMP_NAME, EMP_PASS, PHN_CODING, EMP_STS_CODE, REG_DATE, LEAVE_DATE, ",
        "COM_POST, DEPART_TYPE, CREDENTIALS_TYPE_CODE, ID_NO, GENDER_CODE, NATION_CODE, ",
        "DIPLOMA_CODE, FAX_NUM, EMAIL, MARITAL_STS_CODE, IS_APPRAISE, DATA_SOURCES, AD_ACCT, ",
        "REMARK",
        "from T_EMPLOYEE"
    })
    @Results({
        @Result(column="EMP_NO", property="empNo", jdbcType= JdbcType.VARCHAR, id=true),
        @Result(column="ORG_NO", property="orgNo", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMP_NAME", property="empName", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMP_PASS", property="empPass", jdbcType= JdbcType.VARCHAR),
        @Result(column="PHN_CODING", property="phnCoding", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMP_STS_CODE", property="empStsCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="REG_DATE", property="regDate", jdbcType= JdbcType.DATE),
        @Result(column="LEAVE_DATE", property="leaveDate", jdbcType= JdbcType.DATE),
        @Result(column="COM_POST", property="comPost", jdbcType= JdbcType.VARCHAR),
        @Result(column="DEPART_TYPE", property="departType", jdbcType= JdbcType.VARCHAR),
        @Result(column="CREDENTIALS_TYPE_CODE", property="credentialsTypeCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="ID_NO", property="idNo", jdbcType= JdbcType.VARCHAR),
        @Result(column="GENDER_CODE", property="genderCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="NATION_CODE", property="nationCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="DIPLOMA_CODE", property="diplomaCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="FAX_NUM", property="faxNum", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType= JdbcType.VARCHAR),
        @Result(column="MARITAL_STS_CODE", property="maritalStsCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="IS_APPRAISE", property="isAppraise", jdbcType= JdbcType.VARCHAR),
        @Result(column="DATA_SOURCES", property="dataSources", jdbcType= JdbcType.VARCHAR),
        @Result(column="AD_ACCT", property="adAcct", jdbcType= JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType= JdbcType.VARCHAR)
    })
    List<TEmployee> selectAll();

    @Select({
        "<script>","select",
        "EMP_NO, ORG_NO, EMP_NAME, EMP_PASS, PHN_CODING, EMP_STS_CODE, REG_DATE, LEAVE_DATE, ",
        "COM_POST, DEPART_TYPE, CREDENTIALS_TYPE_CODE, ID_NO, GENDER_CODE, NATION_CODE, ",
        "DIPLOMA_CODE, FAX_NUM, EMAIL, MARITAL_STS_CODE, IS_APPRAISE, DATA_SOURCES, AD_ACCT, ",
        "REMARK",
        "from T_EMPLOYEE",
        "where (1=1) ",
        "<if test='entity.empNo != null and entity.empNo !=\"\"'>",
          "and EMP_NO = #{entity.empNo,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.orgNo != null and entity.orgNo !=\"\"'>",
          "and ORG_NO = #{entity.orgNo,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.empName != null and entity.empName !=\"\"'>",
          "and EMP_NAME = #{entity.empName,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.empPass != null and entity.empPass !=\"\"'>",
          "and EMP_PASS = #{entity.empPass,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.phnCoding != null and entity.phnCoding !=\"\"'>",
          "and PHN_CODING = #{entity.phnCoding,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.empStsCode != null and entity.empStsCode !=\"\"'>",
          "and EMP_STS_CODE = #{entity.empStsCode,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.regDate != null and entity.regDate !=\"\"'>",
          "and REG_DATE = #{entity.regDate,jdbcType=DATE}",
        "</if>",
        "<if test='entity.leaveDate != null and entity.leaveDate !=\"\"'>",
          "and LEAVE_DATE = #{entity.leaveDate,jdbcType=DATE}",
        "</if>",
        "<if test='entity.comPost != null and entity.comPost !=\"\"'>",
          "and COM_POST = #{entity.comPost,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.departType != null and entity.departType !=\"\"'>",
          "and DEPART_TYPE = #{entity.departType,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.credentialsTypeCode != null and entity.credentialsTypeCode !=\"\"'>",
          "and CREDENTIALS_TYPE_CODE = #{entity.credentialsTypeCode,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.idNo != null and entity.idNo !=\"\"'>",
          "and ID_NO = #{entity.idNo,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.genderCode != null and entity.genderCode !=\"\"'>",
          "and GENDER_CODE = #{entity.genderCode,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.nationCode != null and entity.nationCode !=\"\"'>",
          "and NATION_CODE = #{entity.nationCode,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.diplomaCode != null and entity.diplomaCode !=\"\"'>",
          "and DIPLOMA_CODE = #{entity.diplomaCode,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.faxNum != null and entity.faxNum !=\"\"'>",
          "and FAX_NUM = #{entity.faxNum,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.email != null and entity.email !=\"\"'>",
          "and EMAIL = #{entity.email,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.maritalStsCode != null and entity.maritalStsCode !=\"\"'>",
          "and MARITAL_STS_CODE = #{entity.maritalStsCode,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.isAppraise != null and entity.isAppraise !=\"\"'>",
          "and IS_APPRAISE = #{entity.isAppraise,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.dataSources != null and entity.dataSources !=\"\"'>",
          "and DATA_SOURCES = #{entity.dataSources,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.adAcct != null and entity.adAcct !=\"\"'>",
          "and AD_ACCT = #{entity.adAcct,jdbcType=VARCHAR}",
        "</if>",
        "<if test='entity.remark != null and entity.remark !=\"\"'>",
          "and REMARK = #{entity.remark,jdbcType=VARCHAR}",
        "</if>",
    "</script>"
    })
    @Results({
        @Result(column="EMP_NO", property="empNo", jdbcType= JdbcType.VARCHAR, id=true),
        @Result(column="ORG_NO", property="orgNo", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMP_NAME", property="empName", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMP_PASS", property="empPass", jdbcType= JdbcType.VARCHAR),
        @Result(column="PHN_CODING", property="phnCoding", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMP_STS_CODE", property="empStsCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="REG_DATE", property="regDate", jdbcType= JdbcType.DATE),
        @Result(column="LEAVE_DATE", property="leaveDate", jdbcType= JdbcType.DATE),
        @Result(column="COM_POST", property="comPost", jdbcType= JdbcType.VARCHAR),
        @Result(column="DEPART_TYPE", property="departType", jdbcType= JdbcType.VARCHAR),
        @Result(column="CREDENTIALS_TYPE_CODE", property="credentialsTypeCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="ID_NO", property="idNo", jdbcType= JdbcType.VARCHAR),
        @Result(column="GENDER_CODE", property="genderCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="NATION_CODE", property="nationCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="DIPLOMA_CODE", property="diplomaCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="FAX_NUM", property="faxNum", jdbcType= JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType= JdbcType.VARCHAR),
        @Result(column="MARITAL_STS_CODE", property="maritalStsCode", jdbcType= JdbcType.VARCHAR),
        @Result(column="IS_APPRAISE", property="isAppraise", jdbcType= JdbcType.VARCHAR),
        @Result(column="DATA_SOURCES", property="dataSources", jdbcType= JdbcType.VARCHAR),
        @Result(column="AD_ACCT", property="adAcct", jdbcType= JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType= JdbcType.VARCHAR)
    })
    List<TEmployee> selectByCondition(@Param("entity") TEmployee entity);

/*    @UpdateProvider(type=TEmployeeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TEmployee record);*/

    @Update({
        "update T_EMPLOYEE",
        "set ORG_NO = #{orgNo,jdbcType=VARCHAR},",
          "EMP_NAME = #{empName,jdbcType=VARCHAR},",
          "EMP_PASS = #{empPass,jdbcType=VARCHAR},",
          "PHN_CODING = #{phnCoding,jdbcType=VARCHAR},",
          "EMP_STS_CODE = #{empStsCode,jdbcType=VARCHAR},",
          "REG_DATE = #{regDate,jdbcType=DATE},",
          "LEAVE_DATE = #{leaveDate,jdbcType=DATE},",
          "COM_POST = #{comPost,jdbcType=VARCHAR},",
          "DEPART_TYPE = #{departType,jdbcType=VARCHAR},",
          "CREDENTIALS_TYPE_CODE = #{credentialsTypeCode,jdbcType=VARCHAR},",
          "ID_NO = #{idNo,jdbcType=VARCHAR},",
          "GENDER_CODE = #{genderCode,jdbcType=VARCHAR},",
          "NATION_CODE = #{nationCode,jdbcType=VARCHAR},",
          "DIPLOMA_CODE = #{diplomaCode,jdbcType=VARCHAR},",
          "FAX_NUM = #{faxNum,jdbcType=VARCHAR},",
          "EMAIL = #{email,jdbcType=VARCHAR},",
          "MARITAL_STS_CODE = #{maritalStsCode,jdbcType=VARCHAR},",
          "IS_APPRAISE = #{isAppraise,jdbcType=VARCHAR},",
          "DATA_SOURCES = #{dataSources,jdbcType=VARCHAR},",
          "AD_ACCT = #{adAcct,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR}",
        "where EMP_NO = #{empNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TEmployee record);
}