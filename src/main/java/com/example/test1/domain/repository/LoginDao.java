package com.example.test1.domain.repository;

import com.example.test1.domain.model.Login;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface LoginDao {

    // Loginテーブルの件数を取得.
    public int count() throws DataAccessException;
    // Loginテーブルにデータを1件insert.
    public int insertOne(Login login) throws DataAccessException;
    // Loginテーブルのデータを１件取得
    public Login selectOne(String id) throws DataAccessException;
    // Loginテーブルの全データを取得.
    public List<Login> selectMany() throws DataAccessException;
    // Loginテーブルを１件更新.
    public int updateOne(Login login) throws DataAccessException;
    // Loginテーブルを１件削除.
    public int deleteOne(String id) throws DataAccessException;
}