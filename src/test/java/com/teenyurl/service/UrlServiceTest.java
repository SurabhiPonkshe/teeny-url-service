package com.teenyurl.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teenyurl.constants.ResponseConstants;
import com.teenyurl.entity.UrlData;
import com.teenyurl.model.UrlResponse;

@SpringBootTest
public class UrlServiceTest {
	
	@Autowired
	UrlService urlService;
	
	@Mock
	UrlRepositoryService urlRepoService;
		
	UrlData urlData;
	UrlResponse expectedResponse;
	UrlResponse actualResponse;
	
	@Before
	public void setUp() {
		urlData = new UrlData();
		urlData.setId(1);
		urlData.setOriginalUrl("www.google.com");
		urlData.setShortenedUrl("b");
		expectedResponse = new UrlResponse();
		actualResponse = new UrlResponse();
	}
	
	@Test
	public void getShortUrlTestValidUrl() {
		try {
			expectedResponse.setResultMessage(ResponseConstants.SUCCESS);
			expectedResponse.setResultUrl("c");
			
			when(urlRepoService.getLastRecord()).thenReturn(Optional.of(urlData));
			when(urlRepoService.saveUrlData(Mockito.any())).thenReturn(Mockito.any());
			
			actualResponse = urlService.getShortUrl("www.google.com");
			assertEquals(actualResponse.getResultMessage(),expectedResponse.getResultMessage());
			assertEquals(actualResponse.getResultUrl(),expectedResponse.getResultUrl());
		}
		catch(Exception e) {
			e.printStackTrace(); //log exception
		}
	}
		
	@Test
	public void shortenUrlTestEmptyDBCase() {
		
		try {
			expectedResponse.setResultMessage(ResponseConstants.SUCCESS);
			expectedResponse.setResultUrl("b");
			
			when(urlRepoService.getLastRecord()).thenReturn(Optional.empty());
			when(urlRepoService.saveUrlData(Mockito.any())).thenReturn(Mockito.any());
			
			UrlResponse actualResponse = urlService.shortenUrl("www.google.com");
			assertEquals(actualResponse.getResultMessage(),expectedResponse.getResultMessage());
			assertEquals(actualResponse.getResultUrl(),expectedResponse.getResultUrl());
		}
		catch(Exception e) {
			e.printStackTrace(); //log exception
		}
	}
	
	@Test
	public void shortenUrlTestNonEmptyDBCase() {
		
		try {
			expectedResponse.setResultMessage(ResponseConstants.SUCCESS);
			expectedResponse.setResultUrl("c");
			
			when(urlRepoService.getLastRecord()).thenReturn(Optional.of(urlData));
			when(urlRepoService.saveUrlData(Mockito.any())).thenReturn(Mockito.any());
			
			UrlResponse actualResponse = urlService.shortenUrl("www.google.com");
			assertEquals(actualResponse.getResultMessage(),expectedResponse.getResultMessage());
			assertEquals(actualResponse.getResultUrl(),expectedResponse.getResultUrl());
		}
		catch(Exception e) {
			e.printStackTrace(); //log exception
		}
	}

	@Test
	public void getShortUrlFromIdTest1() {
		int testId = 100;
		String expectedUrl = "bM";
		String result = urlService.getShortUrlFromId(testId);
		assertEquals(expectedUrl,result);
	}
	
	@Test
	public void getIdFromShortUrlTest1() {
		String testUrl = "bM";
		int expectedId = 100;
		int resultId = urlService.getIdFromShortUrl(testUrl);
		assertEquals(expectedId, resultId);
	}
}
