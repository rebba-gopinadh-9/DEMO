package com.example.demo.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.RandomAccessFile;

@RestController
@RequestMapping("/videos")
public class VideoStreamController {

    private final ResourceLoader resourceLoader;

    public VideoStreamController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping(value = "/sample", produces = "video/mp4")
    public ResponseEntity<byte[]> streamVideo(
            @RequestHeader HttpHeaders headers) throws IOException {
    	System.out.println("------------------$-------------------");

    	Resource video = resourceLoader.getResource("classpath:/static/videos/OneSendAcrossNetwork.mp4");

        RandomAccessFile videoFile = new RandomAccessFile(video.getFile(), "r");
        long fileLength = videoFile.length();

        HttpRange range = headers.getRange().isEmpty() ? null : headers.getRange().get(0);
        long start = 0, end = fileLength - 1;

        if (range != null) {
            start = range.getRangeStart(fileLength);
            end = range.getRangeEnd(fileLength);
        }

        int chunkSize = (int) (end - start + 1);
        byte[] buffer = new byte[chunkSize];
        videoFile.seek(start);
        videoFile.read(buffer, 0, chunkSize);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Range", "bytes " + start + "-" + end + "/" + fileLength);
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentLength(chunkSize);

        return new ResponseEntity<>(buffer, responseHeaders, HttpStatus.PARTIAL_CONTENT);
    }
}
