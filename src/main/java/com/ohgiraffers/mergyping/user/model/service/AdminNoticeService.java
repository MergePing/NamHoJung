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
        int rowsAffected = adminNoticeMapper.updateNotice(noticeDetailDTO);
        return rowsAffected > 0; // 업데이트 성공 여부 반환
    }
}