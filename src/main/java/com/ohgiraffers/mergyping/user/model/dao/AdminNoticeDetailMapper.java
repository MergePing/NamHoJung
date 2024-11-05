package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminNoticeDetailMapper {

    @Select("SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE, NOTICE_CATEGORY FROM TBL_NOTICE WHERE NOTICE_NO = #{noticeNo}")
    AdminNoticeDetailDTO selectNoticeDetailByNo(String noticeNo);
}