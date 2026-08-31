package com.test.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping( produces = MediaType.TEXT_HTML_VALUE)
    public String returnHtmlSnippet() {
        return "<html><body><h1>Hello, World!</h1><p>Served from RestController.</p></body></html>";
    }
}
