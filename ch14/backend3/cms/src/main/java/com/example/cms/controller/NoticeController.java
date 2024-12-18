package com.example.cms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.entity.Notice;
import com.example.cms.exception.ResourceNotFoundException;
import com.example.cms.service.NoticeService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
 
	
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    public List<Notice> getAllNotices() {
        return noticeService.getAllNotices();
    }

    /*
    @GetMapping("/{id}")
    public Notice getNoticeById(@PathVariable("id") Long id) {
    	log.info("Conrtoller : "+id);
    	Notice noti = noticeService.getNoticeById(id);
    	log.info("Response :"+noti.toString());
        return noti;
	}
    */

    @GetMapping("/{id}")
    public Notice getNoticeById(
            @PathVariable("id") Long id, 
            HttpServletRequest request, 
            HttpServletResponse response) {

        String cookieName = "notice_" + id;
        boolean isViewed = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    isViewed = true;
                    break;
                }
            }
        }

        Notice notice;
        if (!isViewed) {
            notice = noticeService.incrementHits(id);
            Cookie newCookie = new Cookie(cookieName, "viewed");
            newCookie.setMaxAge(24 * 60 * 60); 
            newCookie.setPath("/");
            response.addCookie(newCookie);
        } else {
            notice = noticeService.getNoticeByIdWithoutIncrement(id);
        }

        if (notice == null) {
            throw new ResourceNotFoundException("Notice not found with id: " + id);
        }
        log.info("Controller : "+notice);
        return notice;
    }
	
    		
    @PostMapping
    public Notice addNotice(@RequestBody Notice notice) {
        return noticeService.addNotice(notice);
    }

    @PutMapping("/{id}")
    public Notice updateNotice(@PathVariable("id") Long id, @RequestBody Notice notice) {
        return noticeService.updateNotice(id, notice);
    }

    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable("id") Long id) {
        noticeService.deleteNotice(id);
    }
}