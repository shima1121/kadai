package com.example.test1.domain.service;

import com.example.test1.domain.model.Attendance;
import com.example.test1.domain.repository.AttendanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    AttendanceDao attendanceDao;

    public boolean insert(Attendance attendance) {
        // insert実行
        int rowNumber = attendanceDao.insertOne(attendance);
        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // insert成功
            result = true;
        }
        return result;
    }

    //カウント用メソッド.
    public int count() {
        return attendanceDao.count();
    }

    //全件取得用メソッド.
    public List<Attendance> selectMany() {
        // 全件取得
        return attendanceDao.selectMany();
    }
     //１件取得用メソッド.
    public Attendance selectOne(Integer id) {
        // selectOne実行
        return attendanceDao.selectOne(id);
    }
    //１件更新用メソッド.
    public boolean updateOne(Attendance attendance) {
        // 判定用変数
        boolean result = false;
        // １件更新
        int rowNumber = attendanceDao.updateOne(attendance);
        if (rowNumber > 0) {
            // update成功
            result = true;
        }
        return result;
    }
    // １件削除用メソッド.
    public boolean deleteOne(Date a_date) {

        // １件削除
        int rowNumber = attendanceDao.deleteOne(a_date);
        // 判定用変数
        boolean result = false;
        if (rowNumber > 0) {
            // delete成功
            result = true;
        }
        return result;
    }
}



