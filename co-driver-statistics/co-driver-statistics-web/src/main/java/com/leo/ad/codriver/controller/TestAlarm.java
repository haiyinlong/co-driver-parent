package com.leo.ad.codriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestAlarm
 *
 * @author HaiYinLong
 * @version 2024/07/01 16:36
 **/
@RestController
@RequestMapping("/testAlarm")
public class TestAlarm {
    @PostMapping("/demo")
    public void testAlarm(String msg) {
        System.out.println(msg);
    }

    @GetMapping("/try")
    public void tryAlarm() {
        try {
            Thread.sleep(2000);
            System.out.println("休息一次");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
