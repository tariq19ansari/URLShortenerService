package com.springboot.demo.service;


import com.springboot.demo.exception.UrlNotFoundException;
import com.springboot.demo.model.Url;
import com.springboot.demo.model.UrlDto;
import com.springboot.demo.repository.UrlRepository;
import com.springboot.demo.service.UrlServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class UrlServiceImplTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlServiceImpl urlService;

    private static final Logger logger = Logger.getLogger(UrlServiceImplTest.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateShortLinkAndRedirect() throws Exception {
        // Given
        UrlDto urlDto = new UrlDto();
        urlDto.setUrl("https://www.google.com");

        // Mock the behavior of the repository
        when(urlRepository.save(any())).thenAnswer(invocation -> {
            Url savedUrl = invocation.getArgument(0);
            savedUrl.setId(1L); // Simulating the saved URL with an ID
            return savedUrl;
        });

        // When
        Url generatedUrl = urlService.generateShortLink(urlDto);

        // Then
        // Ensure the URL is generated successfully
        assert generatedUrl != null;
        String shortLink = generatedUrl.getShortLink();
        assert shortLink != null && !shortLink.isEmpty();

        // Log the generated URL
        logger.info("Generated Short Link: " + shortLink);

        // Verify that the URL is saved in the repository
        verify(urlRepository, times(1)).save(any(Url.class));

    }

}
