package com.example.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.entity.Notice;
import com.example.shop.exception.ResourceNotFoundException;
import com.example.shop.repository.NoticeRepository;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> findAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice findNoticeById(Long id) {
        return noticeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notice not found"));
    }

    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Notice updateNotice(Long id, Notice notice) {
        Notice existingNotice = findNoticeById(id);
        existingNotice.setTitle(notice.getTitle());
        existingNotice.setContent(notice.getContent());
        return noticeRepository.save(existingNotice);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}