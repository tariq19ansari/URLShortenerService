package com.springboot.demo.service;

import com.google.common.hash.Hashing;
import com.springboot.demo.model.Url;
import com.springboot.demo.model.UrlDto;
import com.springboot.demo.repository.UrlRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Component
public class UrlServiceImpl implements UrlService {
    private final Map<String, String> urlMap = new HashMap<>();
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlDto urlDto) {
        if (StringUtils.isNotEmpty(urlDto.getUrl())) {
            String originalUrl = urlDto.getUrl();

            // Check if the URL is already shortened
            if (urlMap.containsKey(originalUrl)) {
                String existingShortenedUrl = urlMap.get(originalUrl);
                // Retrieve the existing shortened URL and return
                Url url = new Url(originalUrl, existingShortenedUrl);
                return url;
            } else {
                String encodedUrl = encodeUrl(urlDto.getUrl());
                Url urlToPersist = new Url();

                urlToPersist.setOriginalUrl(originalUrl);
                urlToPersist.setShortLink(encodedUrl);

                Url urlToRet = persistShortLink(urlToPersist);

                if (urlToRet != null) {
                    // Store the mapping in the map
                    urlMap.put(originalUrl, encodedUrl);
                    return urlToRet;
                }
            }
        }
        return null;
    }

    private String encodeUrl(String url) {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return encodedUrl;
    }

    @Override
    public Url persistShortLink(Url url) {
        Url urlToRet = urlRepository.save(url);
        return urlToRet;
    }

    @Override
    public Url getEncodedUrl(String url) {
        Url urlToRet = urlRepository.findByShortLink(url);
        return urlToRet;
    }

    @Override
    public void deleteShortLink(Url url) {

        urlRepository.delete(url);
    }
}
