package com.example.test1.domain.repository;

import com.example.test1.domain.model.Status;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface StatusDao {
    // statusテーブルの件数を取得.
    public int count() throws DataAccessException;
    // statusテーブルにデータを1件insert.
    public int insertOne(Status status) throws DataAccessException;
    // statusテーブルのデータを１件取得
    public Status selectOne(Integer id) throws DataAccessException;
    // statusテーブルの全データを取得.
    public List<Status> selectMany() throws DataAccessException;
    // statusテーブルを１件更新.
    public int updateOne(Status status) throws DataAccessException;
    // statusテーブルを１件削除.
    public int deleteOne(Integer id) throws DataAccessException;
}
