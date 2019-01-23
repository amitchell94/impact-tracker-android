package com.codingnomads.impacttracker.data;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriMaker {
    public static URI getUri(String path) {
        return UriComponentsBuilder.fromUriString("http:///18.220.98.185/")
                .path(path)
                .build()
                .encode()
                .toUri();
    }

    public static URI getUri(String path, String value) {
        return UriComponentsBuilder.fromUriString("http://18.220.98.185/")
                .path(path)
                .queryParam("token",value)
                .build()
                .encode()
                .toUri();
    }
}
