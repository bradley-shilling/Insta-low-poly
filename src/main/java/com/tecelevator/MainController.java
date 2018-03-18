package com.tecelevator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jinstagram.Instagram;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tecelevator.instagram.Constants;
import com.tecelevator.model.MyImage;
import com.tecelevator.model.MyImageDAO;





@Controller 
public class MainController {
	@Autowired
	MyImageDAO myImageDAO;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String displayHomepage() throws IOException {
		//List<MediaFeedData> mediaList = instagram.getUserRecentMedia().getData();
	return "lowPoly";
	}
	
	// not needed
	@RequestMapping(path="/i", method=RequestMethod.GET)
	public String displayIndex() throws IOException {
	return "index";
	}
	@RequestMapping(path="/profile", method=RequestMethod.GET)
	public String displayProfile() throws IOException {
	return "profile";
	}
	@RequestMapping(path="/gallery", method=RequestMethod.GET)
	public String displayGallery() throws IOException {
	return "gallery";
	}
	
	
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public String sendAccountToLowPoly(@RequestParam String instagramAccount, ModelMap modelHolder, HttpSession session) throws IOException {
		String urlOut = "redirect:/" + instagramAccount ;
	return urlOut;
	}
	
	/*
	 * Display default page w/ first image
	 */
	@RequestMapping(path="/displayLowPoly", method=RequestMethod.GET)
	public String displayLowPoly(ModelMap modelHolder, 
			HttpSession session, 
			//@PathVariable String instagramAccount, 
			@RequestParam(value="pagination", required=false) String pagination) throws IOException {
	
		
		Object objInstagram = session.getAttribute(Constants.INSTAGRAM_OBJECT);
	    Instagram instagram = null;
	    List<MyImage> myImages;
	    MyImage currentImage;
	    
	    if (objInstagram != null) { // check for existance of instagram object
	        instagram = (Instagram) objInstagram;
	        System.out.println("instagram found");
	   
	        myImages = myImageDAO.getAllMyImages(instagram);
	        // set current image to index 0 if not present
			currentImage = myImages.get(0); 
			
			modelHolder.put("currentImage", currentImage); // add current image to model holder
			modelHolder.put("images", myImages); // add images to model holder
	        
			//String userName = instagram.getCurrentUserInfo().getData().getUsername();

	        //UserInfo userInfo = instagram.getCurrentUserInfo();
	        //UserInfoData userData = userInfo.getData();
	        //System.out.println("id : " + userData.getId());
	        //System.out.println("username : " + userData.getUsername());
	        //System.out.println(myImageDAO.getAllMyImages(instagram));
	        //List<MediaFeedData> mediaList = instagram.getUserRecentMedia().getData(); // get a list of all media items for current user
	        //System.out.println("media size : " + mediaList.size()); // get size of all media items
	        //System.out.println("media 0 type: " +mediaList.get(0).getType());// get type of first media item
	        //System.out.println("media 0 thumb url " +mediaList.get(0).getImages().getThumbnail().getImageUrl());
	        //System.out.println("media 0 low url" + mediaList.get(0).getImages().getLowResolution().getImageUrl()); // first image url low
	        //System.out.println("media 0  standard url " + mediaList.get(0).getImages().getStandardResolution().getImageUrl()); // first image standard url
	        /*
	         * magic happens here
	         */
	        //String url640 = mediaList.get(0).getImages().getStandardResolution().getImageUrl(); // store original image as string
	        //String largestImage = url640.replaceAll("s640x640/", "s1080x1080/").replaceAll("/vp\\/[^\\/]*/", "/hphotos-xfp1/"); // hack to get higher resolution images not accessible in API
	        //System.out.println("new url:" + largestImage); //final hacked large url  

	        //System.out.println("media 0 link " + mediaList.get(0).getLink()); // first image link
//	        System.out.println("media 0 date " + mediaList.get(0).getCreatedTime()); // first image time
//	        java.util.Date javaTime=new java.util.Date(Long.parseLong(mediaList.get(0).getCreatedTime())*1000); // this gives a date java can understand from the unix timestamp returned by the instagram api
//	        System.out.println("media 0 date " + javaTime);
//	        LocalDateTime ldt = LocalDateTime.ofInstant(javaTime.toInstant(), ZoneId.systemDefault()); // convert to local date
//	        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//	        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("h:mm a");
//	        System.out.println(ldt.format(formatterDate));
//	        System.out.println(ldt.format(formatterTime));
  
	        
	    } else {
	    		System.out.println("instagram  not found");
	    		return "lowPoly"; // in no instagram object is in session redirect to homepage
	    }

		
	    /*
	     * set up menu
	     */
		//TODO finish integrating menu
		int menuSize = 8; // set number of items to show in menu
		int menuIndex; //set starting point for menu
		if(session.getAttribute("menuIndex") != null) { // check if menu index exists
			if(pagination != null && pagination == "+" && ((int) session.getAttribute("menuIndex")) + menuSize <= myImages.size()) {
				menuIndex = ((int) session.getAttribute("menuIndex"))+menuSize ; // move index ahead 4 spaces
			} else if (pagination != null && pagination == "-" && ((int) session.getAttribute("menuIndex"))-menuSize >= 0) {
				menuIndex = ((int) session.getAttribute("menuIndex"))-menuSize ; // move index ahead 4 spaces
			} else {
				menuIndex = (int) session.getAttribute("menuIndex");
			}
		} else  {
			menuIndex = 1; // if menu index  does not exist set to 1
		}
		session.setAttribute("menuIndex", menuIndex); // set menu index
	return "displayLowPoly";
	}
	
	
	
	
	/*
	 * display new image
	 */
	@RequestMapping(path="/displayLowPoly/{selectedImage}", method=RequestMethod.GET)
	public String displayNewLowPoly(ModelMap modelHolder, 
			HttpSession session, 
			@PathVariable int selectedImage, 
			@RequestParam(value="pagination", required=false) String pagination) throws IOException {
		Object objInstagram = session.getAttribute(Constants.INSTAGRAM_OBJECT);
	    Instagram instagram = null;
	    List<MyImage> myImages;
	    MyImage currentImage;
	    
	    if (objInstagram != null) { // check for existance of instagram object
	        instagram = (Instagram) objInstagram;
	        System.out.println("instagram found");
	   
	        myImages = myImageDAO.getAllMyImages(instagram);
	        // set current image to index 0 if not present
			currentImage = myImages.get(selectedImage); 
			
			modelHolder.put("currentImage", currentImage); // add current image to model holder
			modelHolder.put("images", myImages); // add images to model holder
			
	    } else {
	    		System.out.println("instagram  not found");
	    		return "lowPoly"; // in no instagram object is in session redirect to homepage
	    }

		
	    /*
	     * set up menu
	     */
		//TODO finish integrating menu
		int menuSize = 8; // set number of items to show in menu
		int menuIndex; //set starting point for menu
		if(session.getAttribute("menuIndex") != null) { // check if menu index exists
			if(pagination != null && pagination == "+" && ((int) session.getAttribute("menuIndex")) + menuSize <= myImages.size()) {
				menuIndex = ((int) session.getAttribute("menuIndex"))+menuSize ; // move index ahead 4 spaces
			} else if (pagination != null && pagination == "-" && ((int) session.getAttribute("menuIndex"))-menuSize >= 0) {
				menuIndex = ((int) session.getAttribute("menuIndex"))-menuSize ; // move index ahead 4 spaces
			} else {
				menuIndex = (int) session.getAttribute("menuIndex");
			}
		} else  {
			menuIndex = 1; // if menu index  does not exist set to 1
		}
		session.setAttribute("menuIndex", menuIndex); // set menu index
	return "displayLowPoly";
	}
	
	
	
	/*
	 * Show all view
	 */
	@RequestMapping(path="/showAll", method=RequestMethod.GET)
	public String showAll(ModelMap modelHolder, 
			HttpSession session) throws IOException {
	
		
		Object objInstagram = session.getAttribute(Constants.INSTAGRAM_OBJECT);
	    Instagram instagram = null;
	    List<MyImage> myImages;
	    
	    if (objInstagram != null) { // check for existance of instagram object
	        instagram = (Instagram) objInstagram;
	        System.out.println("instagram found");
	        myImages = myImageDAO.getAllMyImages(instagram);
	        modelHolder.put("userName", instagram.getCurrentUserInfo().getData().getUsername());
			modelHolder.put("images", myImages); // add images to model holder  
	    } else {
	    		System.out.println("instagram  not found");
	    		return "lowPoly"; // in no instagram object is in session redirect to homepage
	    }

		
	return "showAll";
	}
	
	
	
	
	
}
