package com.springboot.demo.model;

public class UrlResponseDto
{
    private String originalUrl;
    private String shortLink;

    public UrlResponseDto(String originalUrl, String shortLink) {
        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
    }

    public UrlResponseDto() {
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    @Override
    public String toString() {
        return "UrlResponseDto{" +
                "originalUrl='" + originalUrl + '\'' +
                ", shortLink='" + shortLink + '\'' +
                '}';
    }
}
