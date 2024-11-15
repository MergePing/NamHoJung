package com.ohgiraffers.mergyping.notice.model.service;

import com.ohgiraffers.mergyping.notice.model.dao.NoticeMapper;
import com.ohgiraffers.mergyping.notice.model.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public List<NoticeDTO> getNoticesByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return noticeMapper.selectNoticesByPage(offset, pageSize);
    }

    public int countNotices() {
        return noticeMapper.countNotices();
    }
}
