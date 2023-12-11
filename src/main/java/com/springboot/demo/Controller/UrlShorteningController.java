package com.springboot.demo.Controller;

import com.springboot.demo.model.Url;
import com.springboot.demo.model.UrlDto;
import com.springboot.demo.model.UrlErrorResponseDto;
import com.springboot.demo.model.UrlResponseDto;
import com.springboot.demo.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
//import javax.servlet.http.HttpServletResponse;

@Controller
public class UrlShorteningController {

    @Autowired
    private UrlService urlService;

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto)
    {
        Url urlToRet = urlService.generateShortLink(urlDto);

        if(urlToRet != null)
    {
        UrlResponseDto urlResponseDto = new UrlResponseDto();
        urlResponseDto.setOriginalUrl(urlToRet.getOriginalUrl());
        urlResponseDto.setShortLink(urlToRet.getShortLink());
        return new ResponseEntity<String>(urlResponseDto.getShortLink(), HttpStatus.OK);
    }
        UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
        urlErrorResponseDto.setStatus("404");
        urlErrorResponseDto.setError(" Error processing your request. Please try again.");
        return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto,HttpStatus.OK);

}
@GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws IOException {

        if(StringUtils.isEmpty(shortLink))
        {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setError("This is a Invalid Url");
            urlErrorResponseDto.setStatus("400");
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto,HttpStatus.OK);
        }
        Url urlToRet = urlService.getEncodedUrl(shortLink);
    System.out.print(urlToRet);
    System.out.print(urlToRet.originalUrl);
    System.out.print(urlToRet.getOriginalUrl());
    System.out.print("   url..");

        if(urlToRet == null)
        {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setError("Url does not exist!");
            urlErrorResponseDto.setStatus("400");
            return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto,HttpStatus.OK);
        }
       String urlString = urlToRet.getOriginalUrl();
//
//        if (!urlString.matches("https(.*)")){
//            urlString = "https://" + urlString;
//        }
        response.sendRedirect(urlString);
      //  response.sendRedirect(urlToRet.getOriginalUrl());
        return null;

//    if (urlToRet != null) {
//        String urlString = urlToRet.getOriginalUrl();
//        if (!urlString.startsWith("http")) {
//            urlString = "https://" + urlString; // Adjust protocol if necessary
//        }
//        return "redirect:" + urlString;
//    } else {
//        attributes.addFlashAttribute("error", "Url does not exist!");
//        return "redirect:/"; // Redirect to some other page
//    }

    }
}