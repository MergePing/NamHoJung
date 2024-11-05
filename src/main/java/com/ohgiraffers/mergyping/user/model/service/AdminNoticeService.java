package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.user.model.dao.AdminNoticeMapper;
import com.ohgiraffers.mergyping.user.model.dao.AdminNoticeDetailMapper;
import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDTO;
import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminNoticeService {

    private final AdminNoticeMapper adminNoticeMapper;
    private final AdminNoticeDetailMapper adminNoticeDetailMapper;

    @Autowired
    public AdminNoticeService(AdminNoticeMapper adminNoticeMapper, AdminNoticeDetailMapper adminNoticeDetailMapper) {
        this.adminNoticeMapper = adminNoticeMapper;
        this.adminNoticeDetailMapper = adminNoticeDetailMapper;
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
        return adminNoticeDetailMapper.selectNoticeDetailByNo(noticeNo);
    }
}