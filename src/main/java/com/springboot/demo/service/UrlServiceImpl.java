package com.springboot.demo.service;

import com.springboot.demo.model.Url;
import com.springboot.demo.model.UrlDto;
import com.springboot.demo.repository.UrlRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlServiceImpl implements UrlService{

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url generateShortLink(UrlDto urlDto) {
        System.out.println("Going in this method" +333333);
        if(StringUtils.isNotEmpty(urlDto.getUrl()))
        {
            String encodedUrl = encodeUrl(urlDto.getUrl());
            Url urlToPersist = new Url();

            urlToPersist.setOriginalUrl(urlDto.getUrl());
            urlToPersist.setShortLink(encodedUrl);
            Url urlToRet = persistShortLink(urlToPersist);

            if(urlToRet != null)
                return urlToRet;

            return null;
        }
        return null;
    }

    private String encodeUrl(String url) {

        return url;
    }

    @Override
    public Url persistShortLink(Url url) {
        Url urlToRet = urlRepository.save(url);
        return null;
    }

    @Override
    public Url getEncodedUrl(String url) {
        Url urlToRet = urlRepository.findByShortLink(url);
        return null;
    }

    @Override
    public void deleteShortLink(Url url) {

        urlRepository.delete(url);
    }
}
