package com.example.test1.domain.repository;

import com.example.test1.domain.model.Attendance;

import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

public interface AttendanceDao {
    // Attendanceテーブルの件数を取得.
    public int count() throws DataAccessException;
    // Attendanceテーブルにデータを1件insert.
    public int insertOne(Attendance attendance) throws DataAccessException;
    // Attendanceテーブルのデータを１件取得
    public Attendance selectOne(Integer id) throws DataAccessException;
    // Attendanceテーブルの全データを取得.
    public List<Attendance> selectMany() throws DataAccessException;
    // Attendanceテーブルを１件更新.
    public int updateOne(Attendance attendance) throws DataAccessException;
    // Attendanceテーブルを１件削除.
    public int deleteOne(Date a_date) throws DataAccessException;
}

