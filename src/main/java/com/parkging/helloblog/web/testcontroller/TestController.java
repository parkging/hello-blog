package com.parkging.helloblog.web.testcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/")
public class TestController {

    @GetMapping("table")
    public String table() {
        log.info("TestController.table");
        return "/html/table";
    }

    @GetMapping("semantic")
    public String semantic() {
        log.info("TestController.semantic");
        return "/html/semantic";
    }

    @GetMapping("form")
    public String form() {
        log.info("TestController.form");
        return "/html/form";
    }

    @GetMapping("position")
    public String position() {
        log.info("TestController.position");
        return "/html/position";
    }
    @GetMapping("bootstrap-test")
    public String bootstrapTest() {
        log.info("TestController.bootstrap-test");
        return "/html/bootstrap-test";
    }

    @GetMapping("login-test")
    public String loginTest() {
        log.info("TestController.login-test");
        return "/html/login-test";
    }

    @GetMapping("tui-editor-test")
    public String tuiEditortest() {
        log.info("TestController.tui-editor-test");
        return "/html/tui-editor-test";
    }

    @GetMapping("form-test")
    public String formTest() {
        log.info("TestController.form-test");
        return "/html/form-test";
    }



}
