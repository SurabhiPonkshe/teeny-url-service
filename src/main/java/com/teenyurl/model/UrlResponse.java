package com.teenyurl.model;

import java.util.Objects;

public class UrlResponse {
	
	@Override
	public int hashCode() {
		return Objects.hash(resultMessage, resultUrl);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrlResponse other = (UrlResponse) obj;
		return Objects.equals(resultMessage, other.resultMessage) && Objects.equals(resultUrl, other.resultUrl);
	}
	String resultUrl;
	String resultMessage;
	
	public String getResultUrl() {
		return resultUrl;
	}
	public void setResultUrl(String shortUrl) {
		this.resultUrl = shortUrl;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
