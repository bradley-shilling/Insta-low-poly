package com.tecelevator.model;

import org.springframework.stereotype.Component;


public class MyImage {
	private String createdDate;
	private String largeImgURL;
	private String createdTime;
	private String thumbnailURL;
	private int index;

	
	
	public String getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getLargeImgURL() {
		return largeImgURL;
	}
	
	public void setLargeImgURL(String largeImgURL) {
		this.largeImgURL = largeImgURL;
	}
	
	public String getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	public String getThumbnailURL() {
		return thumbnailURL;
	}
	
	public void setThumbnailURL(String thumbnailURL) {
		this.thumbnailURL = thumbnailURL;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


}
