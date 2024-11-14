package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.AdminNoticeMapper;
import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDTO;
import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminNoticeService {

    private final AdminNoticeMapper adminNoticeMapper;

    @Autowired
    public AdminNoticeService(AdminNoticeMapper adminNoticeMapper) {
        this.adminNoticeMapper = adminNoticeMapper;
    }

    public List<AdminNoticeDTO> getNoticesByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return adminNoticeMapper.selectNoticesByPage(offset, pageSize);
    }

    public int countNotices() {
        return adminNoticeMapper.countNotices();
    }

    // 공지사항 상세 조회 기능
    public AdminNoticeDetailDTO getNoticeDetail(String noticeNo) {
        return adminNoticeMapper.selectNoticeDetail(noticeNo);
    }

    // 공지사항 수정 기능
    public boolean updateNotice(AdminNoticeDetailDTO noticeDetailDTO) {

        if (noticeDetailDTO.getTitle() == null || noticeDetailDTO.getContent() == null || noticeDetailDTO.getCategory() == null) {
            throw new IllegalArgumentException("필수 입력값이 누락되었습니다.");
        }


        int rowsUpdated = adminNoticeMapper.updateNotice(noticeDetailDTO);
        return rowsUpdated > 0;
    }

    // 공지사항 삭제 기능
    public boolean deleteNotice(String noticeNo) {
        return adminNoticeMapper.deleteNotice(noticeNo) > 0;
    }

    // 공지사항 추가 기능
    public boolean addNotice(AdminNoticeDTO noticeDTO) {
        return adminNoticeMapper.insertNotice(noticeDTO) > 0;
    }



    // 공지사항 검색 기능
    public List<AdminNoticeDTO> searchNoticesByTitle(String keyword, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return adminNoticeMapper.searchNoticesByTitle(keyword, offset, pageSize);
    }

    public int countNoticesByKeyword(String keyword) {
        return adminNoticeMapper.countNoticesByKeyword(keyword);
    }
}