package com.wx.diary.controller;

import com.wx.diary.pojo.Stu;
import com.wx.log.LogUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/sayhello")
    public String sayHello() {
        return "Hello,World!";
    }
    @RequestMapping("/ask")
    public Map<String,Object> ask(@RequestBody String name){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Stu> stus = new ArrayList<Stu>();
        for (int i = 0; i < 3; i++) {
            Stu stu = new Stu("" + i, name + i);
            stus.add(stu);
        }
        resultMap.put("lists",stus);
        return resultMap;
    }
}
