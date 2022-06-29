package com.teenyurl.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenyurl.constants.ResponseConstants;
import com.teenyurl.entity.UrlData;
import com.teenyurl.model.UrlResponse;
import com.teenyurl.util.Validators;


@Service
public class UrlService {
	
	@Autowired
	UrlRepositoryService urlRepoService;
	
	public static int counter = 0;

	public UrlResponse getShortUrl(String originalUrl) {
		
		UrlResponse response = new UrlResponse();
		
		//check if URL is valid - currently not checking this
		if(!Validators.isValidUrl(originalUrl)) {
			response.setResultMessage(ResponseConstants.INVALID_URL_ERROR);
			return response;			
		}
		
		response = shortenUrl(originalUrl);
		
		return response;
	}
	
	public UrlResponse shortenUrl(String originalUrl) {
		
		UrlResponse response = new UrlResponse();		
		
		//logic to get the current counter
		//finds out the last record in the database and increments by 1.
		Optional<UrlData> lastRecord = urlRepoService.getLastRecord();
		
		if(counter == 0) {
			if(lastRecord.isEmpty()) {
				counter = 1;
			}
			else {
			UrlData rec = lastRecord.get();
			counter = rec.getId() + 1;
			}
		}
		
		try { // get short URL, create a new record with original URL and short URL
			String shortUrl = getShortUrlFromId(counter);
			response.setResultMessage(ResponseConstants.SUCCESS);
			response.setResultUrl(shortUrl);
			
			UrlData newRecord = new UrlData();
			
			newRecord.setId(counter);
			newRecord.setOriginalUrl(originalUrl);
			newRecord.setShortenedUrl(shortUrl);
			
			urlRepoService.saveUrlData(newRecord);
			
			counter = counter + 1;
		}
		catch(Exception e) {
			response.setResultMessage(ResponseConstants.OTHER_ERROR);
			System.out.println("Exception in shortenUrl :: Exception - " + e.getMessage());
		}
		
		return response;
		
	}
	
	// Function to generate a short url from integer ID
    public String getShortUrlFromId(int n)
    {
        // Map to store 62 possible characters
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
     
        StringBuffer shorturl = new StringBuffer();
     
        // Convert given integer id to a base 62 number
        while (n > 0)
        {
            // use above map to store actual character
            // in short url
            shorturl.append(map[n % 62]);
            n = n / 62;
        }
     
        // Reverse shortURL to complete base conversion
        return shorturl.reverse().toString();
    }
    
    public UrlResponse getOriginalUrl(String shortUrl) {
    	UrlResponse response = new UrlResponse();
    	try {
    	int id = getIdFromShortUrl(shortUrl);
    	Optional<UrlData> urlData = urlRepoService.getOriginalUrlRecord(id);
    	if(urlData.isEmpty()) {
    		response.setResultMessage(ResponseConstants.URL_NOT_FOUND);
    		return response;
    	}
    	UrlData record = urlData.get();
    	response.setResultMessage(ResponseConstants.SUCCESS);
    	response.setResultUrl(record.getOriginalUrl());
    	}
    	catch(Exception e) {
    		response.setResultMessage(ResponseConstants.OTHER_ERROR);
			System.out.println("Exception in getOriginalUrl :: Exception - " + e.getMessage());
    	}
    	return response;
    }
     
    // Function to get integer ID back from a short url
    public int getIdFromShortUrl(String shortURL)
    {
        int id = 0; // initialize result
     
        // Base conversion logic
        for (int i = 0; i < shortURL.length(); i++)
        {
            if ('a' <= shortURL.charAt(i) &&
                       shortURL.charAt(i) <= 'z')
            id = id * 62 + shortURL.charAt(i) - 'a';
            if ('A' <= shortURL.charAt(i) &&
                       shortURL.charAt(i) <= 'Z')
            id = id * 62 + shortURL.charAt(i) - 'A' + 26;
            if ('0' <= shortURL.charAt(i) &&
                       shortURL.charAt(i) <= '9')
            id = id * 62 + shortURL.charAt(i) - '0' + 52;
        }
        return id;
    }	
	
}
