package com.example.cms.service;

import com.example.cms.entity.Content;
import com.example.cms.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    public Content getContentById(Long id) {
        return contentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Content not found with id " + id));
    }

    public Content addContent(Content content) {
        return contentRepository.save(content);
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

	public Content updateContent(Long id, Content content) {
		Content existingContent = getContentById(id);
		existingContent.setTitle(content.getTitle());
		existingContent.setDescription(content.getDescription());
		existingContent.setContent(content.getContent());
		existingContent.setAuthor(content.getAuthor());
		return contentRepository.save(existingContent);
	}
}