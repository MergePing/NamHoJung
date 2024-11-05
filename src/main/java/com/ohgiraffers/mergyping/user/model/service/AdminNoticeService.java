package com.ohgiraffers.mergyping.user.model.service;

import com.ohgiraffers.mergyping.notice.model.dao.NoticeMapper;
import com.ohgiraffers.mergyping.user.model.dao.AdminNoticeMapper;
import com.ohgiraffers.mergyping.user.model.dto.AdminNoticeDTO;
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




}


