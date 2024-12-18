package com.example.cms.service;

import com.example.cms.entity.Notice;
import com.example.cms.exception.ResourceNotFoundException;
import com.example.cms.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice getNoticeByIdWithoutIncrement(Long id) {
        Notice notice = noticeRepository.findById(id).orElse(null);
        if (notice == null) {
            throw new ResourceNotFoundException("Notice not found with id: " + id);
        }
        return notice;
    }

    public Notice incrementHits(Long id) {
        Notice notice = getNoticeByIdWithoutIncrement(id);
        notice.setHits(notice.getHits() + 1);
        return noticeRepository.save(notice);
    }

	public Notice getNoticeById(Long id) {
		return noticeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
	}
    
    public Notice addNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public Notice updateNotice(Long id, Notice updatedNotice) {
        Notice existingNotice = getNoticeByIdWithoutIncrement(id);
        existingNotice.setTitle(updatedNotice.getTitle());
        existingNotice.setContent(updatedNotice.getContent());
        existingNotice.setUpdatedAt(updatedNotice.getUpdatedAt());
        return noticeRepository.save(existingNotice);
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}