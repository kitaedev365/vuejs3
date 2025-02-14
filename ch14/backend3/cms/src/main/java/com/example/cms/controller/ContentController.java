package com.example.cms.controller;

import com.example.cms.entity.Content;
import com.example.cms.service.ContentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public List<Content> getAllContents() {
        return contentService.getAllContents();
    }

    @GetMapping("/{id}")
    public Content getContentById(@PathVariable("id") Long id) {
        return contentService.getContentById(id);
    }

    @PutMapping("/{id}")
    public Content updateContent(@PathVariable("id") Long id, @RequestBody Content content) {
        return contentService.updateContent(id, content);
    }
    
    @PostMapping
    public Content addContent(@RequestBody Content content) {
        return contentService.addContent(content);
    }

    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable("id") Long id) {
        contentService.deleteContent(id);
    }
}