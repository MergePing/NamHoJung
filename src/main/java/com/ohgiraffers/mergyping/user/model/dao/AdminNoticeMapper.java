package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminNoticeMapper {

    // 페이징 처리를 위한 공지사항 목록 조회
    List<AdminNoticeDTO> selectNoticesByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 전체 공지사항 개수 조회
    int countNotices();
}
