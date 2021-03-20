package com.wx.diary.controller;

import com.wx.diary.iservice.IStuService;
import com.wx.diary.pojo.Stu;
import com.wx.diary.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private IStuService stuService;

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
    @RequestMapping("/ask1")
    public Map<String,Object> ask1( String name){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Stu> lists = stuService.getAll();
        resultMap.put("lists",lists);
        return resultMap;
    }
}
