package com.parkging.blog.apiapp.domain.polling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollingController {

    @GetMapping("/")
    public String polling() {
        return "OK";
    }

}
