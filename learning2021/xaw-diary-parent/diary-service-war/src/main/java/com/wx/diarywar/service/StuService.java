package com.wx.diarywar.service;

import com.wx.diary.iservice.IStuService;
import com.wx.diary.pojo.Stu;
import com.wx.diarywar.dao.StuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StuService implements IStuService {
    @Autowired
    private StuMapper stuMapper;
    @Override
    public List<Stu> getAll() {
        List<Stu> stus = new ArrayList<>();
        Stu stu = stuMapper.selectByPrimaryKey("20210314135102100");
        stus.add(stu);
        return stus;
    }
}
