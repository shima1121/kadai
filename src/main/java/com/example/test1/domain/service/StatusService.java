package com.example.test1.domain.service;

import com.example.test1.domain.model.Status;
import com.example.test1.domain.repository.StatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusDao statusDao;
    //カウント用メソッド.
    public int count() {
        return statusDao.count();
    }

    //全件取得用メソッド.
    public List<Status> selectMany() {
        // 全件取得
        return statusDao.selectMany();
    }
}
