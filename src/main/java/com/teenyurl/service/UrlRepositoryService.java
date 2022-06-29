package com.teenyurl.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenyurl.entity.UrlData;
import com.teenyurl.repository.UrlRepository;

@Service
public class UrlRepositoryService {
	
	@Autowired 
	UrlRepository urlRepository;
	
	public UrlData saveUrlData(UrlData urlData) {
    	return urlRepository.save(urlData);
    }
	
	public Optional<UrlData> getOriginalUrlRecord(int urlId) {
        return urlRepository.findById(urlId);
    }
	
	public Optional<UrlData> getLastRecord(){
		return urlRepository.findTopByOrderByIdDesc();
	}
	    
}
