package com.access.controller;

import com.access.service.UserAccessServe;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/useraccess", produces = { "application/json;charset=utf-8" })
public class userAccessController {
//    @Autowired
//    UserAccessServe userAccessServe;

    @GetMapping("hello")
    public String sayHello(){
//        UserAccessServe.main();
        return "hello Beijing";
    }
}
