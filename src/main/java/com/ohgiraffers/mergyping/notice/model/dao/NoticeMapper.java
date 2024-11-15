package com.ohgiraffers.mergyping.notice.model.dao;

import com.ohgiraffers.mergyping.notice.model.dto.NoticeDTO;
import com.ohgiraffers.mergyping.notice.model.dto.NoticeDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {


    List<NoticeDTO> selectNoticesByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);


    int countNotices();


    NoticeDetailDTO selectNoticeDetail(@Param("noticeNo") int noticeNo);
}