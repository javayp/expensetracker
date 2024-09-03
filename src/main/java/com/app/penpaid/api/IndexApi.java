package com.app.penpaid.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class IndexApi {

    @GetMapping
    public String index() {
        return "Hello World!";
    }
}
