package com.example.myproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myproject.entity.Notice;
import com.example.myproject.exception.ResourceNotFoundException;
import com.example.myproject.repository.NoticeRepository;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }
    
    public Notice updateNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

	public Notice getNoticeById(Long id) {
		return noticeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
	}
}