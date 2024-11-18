package com.ohgiraffers.mergyping.report.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReportMapper {
    int insertReport(@Param("reportReason") String reportReason,
                     @Param("userNo") int userNo,
                     @Param("thisName") String thisName,
                     @Param("postNo") Integer postNo,
                     @Param("commentNo") Integer commentNo);
}