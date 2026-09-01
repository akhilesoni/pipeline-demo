package com.test.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping( produces = MediaType.TEXT_HTML_VALUE)
    public String returnHtmlSnippet() {
        return "<html><body><h1>Hello, World!</h1><p>Served from RestController.</p></body></html>";
    }

    @GetMapping( value = "/about",produces = MediaType.TEXT_HTML_VALUE)
    public String about() {
        return "<html><body><h1>About!</h1><p>Served from RestController.</p></body></html>";
    }

    @GetMapping( value = "/contact",produces = MediaType.TEXT_HTML_VALUE)
    public String contact() {
        return "<html><body><h1>Contact!</h1><p>Served from RestController.</p></body></html>";
    }


}
