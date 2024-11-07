package com.ohgiraffers.mergyping.user.model.dao;

import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDTO;
import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminNoticeMapper {

    // 공지사항 목록 페이징 조회
    List<AdminNoticeDTO> selectNoticesByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 전체 공지사항 개수 조회
    int countNotices();

    // 공지사항 상세 조회
    AdminNoticeDetailDTO selectNoticeDetail(@Param("noticeNo") String noticeNo);

    // 공지사항 수정
    int updateNotice(AdminNoticeDetailDTO noticeDetailDTO);

    // 공지사항 삭제
    int deleteNotice(@Param("noticeNo") String noticeNo);
}