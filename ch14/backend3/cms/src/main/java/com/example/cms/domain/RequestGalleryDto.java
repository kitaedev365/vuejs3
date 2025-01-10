package com.example.cms.domain;

import com.example.cms.entity.Gallery.FileType;

import lombok.Data;

@Data
public class RequestGalleryDto {
	private Long contentId;
	private String filePath;
	private FileType fileType;
}
