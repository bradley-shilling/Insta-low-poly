package com.tecelevator;

import java.io.IOException;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.net.URL;



import me.postaddict.instagram.scraper.Instagram;
import me.postaddict.instagram.scraper.cookie.CookieHashSet;
import me.postaddict.instagram.scraper.cookie.DefaultCookieJar;
import me.postaddict.instagram.scraper.interceptor.ErrorInterceptor;
import me.postaddict.instagram.scraper.model.Account;
import me.postaddict.instagram.scraper.model.Media;
import me.postaddict.instagram.scraper.model.PageObject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Controller 
public class MainController {
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String displayHomepage() throws IOException {
	return "lowPoly";
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public String sendAccountToLowPoly(@RequestParam String instagramAccount, ModelMap modelHolder, HttpSession session) throws IOException {
		String urlOut = "redirect:/" + instagramAccount ;
	return urlOut;
	}
	
	/*
	 * Display default page w/ first image
	 */
	@RequestMapping(path="/{instagramAccount}", method=RequestMethod.GET)
	public String displayLowPoly(ModelMap modelHolder, 
			HttpSession session, 
			@PathVariable String instagramAccount, 
			@RequestParam(value="pagination", required=false) String pagination) throws IOException {
		/*
		 * new http logger
		 */
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		OkHttpClient httpClient = new OkHttpClient.Builder()
		        .addNetworkInterceptor(loggingInterceptor)
		        .addInterceptor(new ErrorInterceptor())
		        .cookieJar(new DefaultCookieJar(new CookieHashSet()))
		        .build();
		/*
		 * instagram object creation
		 */
		Instagram instagram = new Instagram(httpClient); // create new instagram object
		Account account = instagram.getAccountByUsername(instagramAccount); // create new account object
		PageObject<Media> medias = instagram.getMedias(instagramAccount, (account.getMedia().getCount())); // get all media associated with instagram account 
		
		ArrayList<Media> photos = new ArrayList<Media>(); // return a list of only the Images in the account
		for (int i = 0; i < medias.getCount(); i++) {
			if (medias.getNodes().get(i).getIsVideo()) { // check if video
			} else if (medias.getNodes().get(i).getIsAdvertising() != null && medias.getNodes().get(i).getIsAdvertising()) { // check if advertisement
			}
			else { // get only photos
				photos.add(medias.getNodes().get(i)); // if not video add media short code to list
			}
		}
		
		int menuSize = 8; // set number of items to show in menu
		int menuIndex; //set starting point for menu
		if(session.getAttribute("menuIndex") != null) { // check if menu index exists
			if(pagination != null && pagination == "+" && ((int) session.getAttribute("menuIndex"))+menuSize <= photos.size()) {
				menuIndex = ((int) session.getAttribute("menuIndex"))+menuSize ; // move index ahead 4 spaces
			} else if (pagination != null && pagination == "-" && ((int) session.getAttribute("menuIndex"))-menuSize >= 0) {
				menuIndex = ((int) session.getAttribute("menuIndex"))-menuSize ; // move index ahead 4 spaces
			} else {
				menuIndex = (int) session.getAttribute("menuIndex");
			}
		} else  {
			menuIndex = 1; // if menu index  does not exist set to 1
		}
		
		
		/*
		 * debuggin lines
		 */
		Media currentMedia = photos.get(0); // get media to convert to lowpoly this will be a var
		String currentPhotoUrl = currentMedia.getDisplayUrl(); // get url to convert to low poly used for debugging
		
		
		System.out.println("Total Media: " + account.getMedia().getCount()); // get total media for account
		System.out.println("Photos: " + photos.size()); // total photos for account
		System.out.println(photos); // photos
		System.out.println(currentPhotoUrl); // main image
		System.out.println(currentMedia.getCreated()); // display upload date - works
		System.out.println(currentMedia.getLikeCount()); //display total likes
		System.out.println(currentMedia.getFirstComments()); //display first comment
		System.out.println(currentMedia.getShortcode()); // display shortcode
		account.getUsername();
		
		
        
        
		
		
		
		modelHolder.put("account", account);
		modelHolder.put("photos", photos);
		modelHolder.put("currentMedia", currentMedia); // add current photo to model map
		session.setAttribute("menuIndex", menuIndex); // set menu index
		
	return "displayLowPoly";
	}
	
	
	
	
	/*
	 * display new image
	 */
	@RequestMapping(path="/{instagramAccount}/{imageShortcode}", method=RequestMethod.GET)
	public String displayNewLowPoly(ModelMap modelHolder, 
			HttpSession session, 
			@PathVariable String instagramAccount, 
			@PathVariable String imageShortcode, 
			@RequestParam(value="pagination", required=false) String pagination) throws IOException {
		/*
		 * new http logger
		 */
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		OkHttpClient httpClient = new OkHttpClient.Builder()
		        .addNetworkInterceptor(loggingInterceptor)
		        .addInterceptor(new ErrorInterceptor())
		        .cookieJar(new DefaultCookieJar(new CookieHashSet()))
		        .build();
		/*
		 * instagram object creation
		 */
		Instagram instagram = new Instagram(httpClient); // create new instagram object
		Account account = instagram.getAccountByUsername(instagramAccount); // create new account object
		PageObject<Media> medias = instagram.getMedias(instagramAccount, (account.getMedia().getCount())); // get all media associated with instagram account 
		
		ArrayList<Media> photos = new ArrayList<Media>(); // return a list of only the Images in the account
		for (int i = 0; i < medias.getCount(); i++) {
			if (medias.getNodes().get(i).getIsVideo()) { // check if video
			} else if (medias.getNodes().get(i).getIsAdvertising() != null && medias.getNodes().get(i).getIsAdvertising()) { // check if advertisement
			} else { // return only photos
				photos.add(medias.getNodes().get(i)); // if not video add media short code to list
			}
		}
		
		int menuSize = 8; // set number of items to show in menu
		int menuIndex; //set starting point for menu
		if(session.getAttribute("menuIndex") != null) { // check if menu index exists
			if(pagination != null && pagination == "+" && ((int) session.getAttribute("menuIndex"))+menuSize <= photos.size()) {
				menuIndex = ((int) session.getAttribute("menuIndex"))+menuSize ; // move index ahead 4 spaces
			} else if (pagination != null && pagination == "-" && ((int) session.getAttribute("menuIndex"))-menuSize >= 0) {
				menuIndex = ((int) session.getAttribute("menuIndex"))-menuSize ; // move index ahead 4 spaces
			} else {
				menuIndex = (int) session.getAttribute("menuIndex");
			}
		} else  {
			menuIndex = 1; // if menu index  does not exist set to 1
		}
		
		Media currentMedia = instagram.getMediaByCode(imageShortcode); // get media to convert to lowpoly this will be a var
		String currentPhotoUrl = currentMedia.getDisplayUrl(); // get url to convert to low poly used for debugging
		
		
		
		modelHolder.put("account", account);
		modelHolder.put("photos", photos);
		modelHolder.put("currentMedia", currentMedia); // add current photo to model map
		session.setAttribute("menuIndex", menuIndex); // set menu index
		
	return "displayLowPoly";
	}
	
	
	
	
	
}
