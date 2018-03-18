package com.tecelevator.model;

import java.util.List;

import org.jinstagram.Instagram;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;

public interface MyImageDAO {
	public List<MyImage> getAllMyImages(Instagram instagram) throws InstagramException;

}
