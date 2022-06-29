package com.teenyurl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teenyurl.model.UrlResponse;
import com.teenyurl.service.UrlService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AppController {
	
	@Autowired
	UrlService urlService;
	
	@RequestMapping({"/"})
	public String loadUI() {
		return "forward:/index.html";
	}
	
	@PostMapping("/{shortUrl}")
	public ResponseEntity<?> getOriginalUrl(@PathVariable String shortUrl,HttpServletResponse response){
		UrlResponse urlResponse = urlService.getOriginalUrl(shortUrl);
		String originalUrl = urlResponse.getResultUrl();
		if(originalUrl != null) {
			try {
				response.sendRedirect(originalUrl);
				return ResponseEntity.ok(urlResponse);
			} catch (IOException e) {
				e.printStackTrace(); //log the exception.
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
