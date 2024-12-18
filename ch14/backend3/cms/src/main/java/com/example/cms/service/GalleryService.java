package com.example.cms.service;

import com.example.cms.entity.Gallery;
import com.example.cms.repository.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public GalleryService(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    public List<Gallery> getAllGalleries() {
        return galleryRepository.findAll();
    }

    public Gallery getGalleryById(Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gallery not found with id " + id));
    }

    public Gallery addGallery(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    public Gallery updateGallery(Long id, Gallery updatedGallery) {
        Gallery existingGallery = getGalleryById(id);
        existingGallery.setFilePath(updatedGallery.getFilePath());
        existingGallery.setFileType(updatedGallery.getFileType());
        return galleryRepository.save(existingGallery);
    }

    public void deleteGallery(Long id) {
        galleryRepository.deleteById(id);
    }

	public List<Gallery> getGalleryByContentId(Long contentId) {
		return galleryRepository.findAllByContentId(contentId);
	}
}