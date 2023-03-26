package com.parkging.helloblog.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestApiController {
    @GetMapping("hello-get-map")
    public Map hello() {
        Map helloMap = new HashMap<String, String>();
        helloMap.put("message1", "안녕하세요 서버 데이터 입니다!~!");
        return helloMap;
    }
}
