package com.app.penpaid.api;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class IndexApi {

    public String index() {
        return "Hello World!";
    }
}
