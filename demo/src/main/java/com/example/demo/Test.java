package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by evan.qi on 6/7/2017.
 */

@RestController
public class Test {

    @Autowired
    private Girl girl;

    private String data = "11";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static void say(String data) {
        System.out.println(data);
    }


    @RequestMapping("/say")
    public String test(@RequestParam("data") String data) throws Exception{



        return girl.toString();
    }

    public static void main(String[] args) {
        String demo = "1,2,3";
        String[] ary = StringUtils.commaDelimitedListToStringArray(demo);
        Arrays.stream(ary).forEach(item-> System.out.println(item));
    }
}
