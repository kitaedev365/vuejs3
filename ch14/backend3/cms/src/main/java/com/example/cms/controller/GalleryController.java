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

import com.example.cms.domain.RequestGalleryDto;
import com.example.cms.entity.Content;
import com.example.cms.entity.Gallery;
import com.example.cms.service.GalleryService;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {

	private static final Logger log = LoggerFactory.getLogger(GalleryController.class);
	
    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping
    public List<Gallery> getAllGalleries() {
        return galleryService.getAllGalleries();
    }

    @GetMapping("/content/{contentId}")
    public List<Gallery> getGalleryById(@PathVariable("contentId") Long contentId) {
        return galleryService.getGalleryByContentId(contentId);
    }

    @GetMapping("/{id}")
    public Gallery getGalleryByContentId(@PathVariable("id") Long id) {
        return galleryService.getGalleryById(id);
    }
    
    @PostMapping("/add")
    public Gallery addGallery(@RequestBody RequestGalleryDto gallery) {
    	Gallery gal = new Gallery();
    	Content con = new Content();
    	con.setId(gallery.getContentId());
    	gal.setContent(con);
    	gal.setFilePath(gallery.getFilePath());
    	gal.setFileType(gallery.getFileType());
    	log.info("Request data: "+gallery.getContentId());
        return galleryService.addGallery(gal);
    }

    @PutMapping("/{id}")
    public Gallery updateGallery(@PathVariable("id") Long id, @RequestBody Gallery gallery) {
        return galleryService.updateGallery(id, gallery);
    }

    @DeleteMapping("/{id}")
    public void deleteGallery(@PathVariable("id") Long id) {
        galleryService.deleteGallery(id);
    }
}