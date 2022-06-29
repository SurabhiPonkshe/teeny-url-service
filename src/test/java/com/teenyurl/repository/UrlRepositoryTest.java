package com.teenyurl.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.teenyurl.entity.UrlData;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UrlRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	UrlRepository urlRepository;
		
	UrlData urlData1;
	UrlData urlData2;
	
	@BeforeEach
	public void setUp() {
		urlData1 = new UrlData();
		urlData2 = new UrlData();
		
		urlData1.setId(1);
		urlData1.setOriginalUrl("www.gmail.com");
		urlData1.setShortenedUrl("b");
		
		urlData2.setId(2);
		urlData2.setOriginalUrl("www.yahoo.com");
		urlData2.setShortenedUrl("c");
	}
	
	@Test
	public void saveUrlDataTest() {
				
		UrlData saved = entityManager.persistAndFlush(urlData1);
		
		assertEquals(urlData1.getId(),saved.getId());
		assertEquals(urlData1.getOriginalUrl(),saved.getOriginalUrl());
		assertEquals(urlData1.getShortenedUrl(),saved.getShortenedUrl());
	}
	
	@Test
	public void getOriginalUrlRecordTest() {
		
		entityManager.persistAndFlush(urlData1);
		Optional<UrlData> found = urlRepository.findById(1);
		UrlData foundRecord = found.get();
		
		assertEquals(urlData1.getId(),foundRecord.getId());
		assertEquals(urlData1.getOriginalUrl(),foundRecord.getOriginalUrl());
		assertEquals(urlData1.getShortenedUrl(),foundRecord.getShortenedUrl());
	}
	
	@Test
	public void getLastRecordTest() {
		
		entityManager.persistAndFlush(urlData1);
		entityManager.persistAndFlush(urlData2);
		
		Optional<UrlData> found = urlRepository.findTopByOrderByIdDesc();
		UrlData lastRecord = found.get();
		
		assertEquals(urlData2.getId(),lastRecord.getId());
		assertEquals(urlData2.getOriginalUrl(),lastRecord.getOriginalUrl());
		assertEquals(urlData2.getShortenedUrl(),lastRecord.getShortenedUrl());		
	}

}
