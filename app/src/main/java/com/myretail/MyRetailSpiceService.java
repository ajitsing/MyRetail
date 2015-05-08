package com.myretail;


import android.app.Application;

import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.jackson.JacksonObjectPersisterFactory;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MyRetailSpiceService extends SpringAndroidSpiceService {

    @Override
    public CacheManager createCacheManager( Application application ) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        JacksonObjectPersisterFactory jacksonObjectPersisterFactory = new JacksonObjectPersisterFactory( application );
        cacheManager.addPersister( jacksonObjectPersisterFactory );
        return cacheManager;
    }

    @Override
    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        final List<HttpMessageConverter< ? >> listHttpMessageConverters = restTemplate.getMessageConverters();

        listHttpMessageConverters.add( jsonConverter );
        listHttpMessageConverters.add( formHttpMessageConverter );
        listHttpMessageConverters.add( stringHttpMessageConverter );
        restTemplate.setMessageConverters( listHttpMessageConverters );
        return restTemplate;
    }
}
