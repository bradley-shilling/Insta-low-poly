package com.tecelevator.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jinstagram.Instagram;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import org.springframework.stereotype.Component;






@Component
public class MyImageList implements MyImageDAO {

	@Override
	public List<MyImage> getAllMyImages(Instagram instagram) throws InstagramException {
		List<MyImage> formattedImageData = new ArrayList<>();
		List<MediaFeedData> mediaList = instagram.getUserRecentMedia().getData(); // get a list of all media items for current user from instagram api
		int count = 0;
		for (MediaFeedData media :  mediaList) { // loop through returned media list from instagram API
			String type = "image";
			if(media.getType().equals(type)) { // check if media is image
				//System.out.println("image found");
				formattedImageData.add(mapInstagramApiResultsToMyImage(media, count));
				count++;
			}
		}
		
		// TODO Auto-generated method stub
		return formattedImageData;
	}
	
	private MyImage mapInstagramApiResultsToMyImage(MediaFeedData media, int count) {
		MyImage currentImage = new MyImage();
		currentImage.setThumbnailURL(media.getImages().getThumbnail().getImageUrl());
		//magic happens here - hack to get higher resolution images not accessible in Instagram API
		currentImage.setLargeImgURL(media.getImages().getStandardResolution().getImageUrl().replaceAll("s640x640/", "s1080x1080/").replaceAll("/vp\\/[^\\/]*/", "/hphotos-xfp1/"));
		Date javaTime = new Date(Long.parseLong(media.getCreatedTime())*1000); // this gives a date java can understand from the unix timestamp returned by the instagram api
	    LocalDateTime ldt = LocalDateTime.ofInstant(javaTime.toInstant(), ZoneId.systemDefault()); // convert to local date
	    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // format date
	    DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("h:mm a"); // format time
		currentImage.setCreatedDate(ldt.format(formatterDate));// formatted date
		currentImage.setCreatedTime(ldt.format(formatterTime));// formatted time
		currentImage.setIndex(count);
		return currentImage;
	}

}
