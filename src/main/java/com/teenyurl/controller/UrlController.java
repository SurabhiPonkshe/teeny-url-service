package com.teenyurl.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teenyurl.model.UrlResponse;
import com.teenyurl.service.UrlService;

@RestController
@RequestMapping("/")
public class UrlController {
	
	@Autowired
	UrlService urlService;
		
	/**
	 * Function to generate a short URL from a given URL
	 * @param originalUrl
	 * @return shortUrl
	 */
	@PostMapping("/getShortUrl")
	public UrlResponse getShortUrl(@RequestParam String originalUrl) {
		//should use HttpResponse or something similar
		UrlResponse response = new UrlResponse();
		try {
        response = urlService.getShortUrl(originalUrl);
        return response;
		}
		catch(Exception e) {
			//should be logged
			System.out.println("Exception occurred in getShortUrl :: Exception - " + e.getMessage());
			response.setResultMessage("Some unknown exception has occurred.We are working to fix the problem.");
			return response;
		}
    }
	
	/**
	 * Function to map the shortened URL to the original URL and redirect to it
	 * @param shortUrl
	 * @param response
	 */
	@GetMapping("/{shortUrl}")
	public UrlResponse getOriginalUrl(@PathVariable String shortUrl){
		UrlResponse response = new UrlResponse();
		try{
			response = urlService.getOriginalUrl(shortUrl);
			return response;
		}
		catch(Exception e) {
			//should be logged
			System.out.println("Exception occurred in getOriginalUrl. Exception - " + e.getMessage());
			response.setResultMessage("Some unknown exception has occurred.We are working to fix the problem.");
			return response;
		}
	}
}
