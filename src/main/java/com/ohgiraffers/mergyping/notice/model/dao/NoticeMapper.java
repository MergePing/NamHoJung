package com.ohgiraffers.mergyping.notice.model.dao;

import com.ohgiraffers.mergyping.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 페이징된 공지사항 리스트 가져오기
    List<NoticeDTO> selectNoticesByPage(int offset, int pageSize);

    // 전체 공지사항 수 가져오기
    int countNotices();
}
