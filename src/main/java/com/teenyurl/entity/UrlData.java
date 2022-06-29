package com.teenyurl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
@Table(name = "urldata")
public class UrlData {
	
	@Id
	@Column
    private int id;

    @Column
    @NotNull(message="{NotNull.Url.originalUrl}")
    private String originalUrl;
    
    @Column
    @NotNull(message="{NotNull.Url.shortenedUrl}")
    private String shortenedUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortenedUrl() {
		return shortenedUrl;
	}

	public void setShortenedUrl(String shortenedUrl) {
		this.shortenedUrl = shortenedUrl;
	}
    
}
