package com.oneencrypt.oneencrypt.central.api;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "api/v1/demo")
public class StartupAPI {
    @GetMapping
    public String getStartupAPI(){
        return "hello";
    }
    @PostMapping(path="addList")
    public String postListDemoAPI(@RequestBody ArrayList<Integer> list){
        return "list val " +list.get(0);
    }
}
