package com.oceanebelle.javasamplers.camel.routes.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {


    @GetMapping("/api/large")
    public ResponseEntity<InputStreamResource> fetchData() {
        return streamFile("large.txt");
    }

    /**
     * Streams the supplied classpath file
     * @param classPathFile
     * @return
     */
    private ResponseEntity<InputStreamResource> streamFile(String classPathFile) {
        InputStreamResource resource = new InputStreamResource(ClassLoader.getSystemResourceAsStream(classPathFile));
        LinkedMultiValueMap headers = new LinkedMultiValueMap();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=large.txt");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return new ResponseEntity(resource, headers, HttpStatus.OK);
    }
}
