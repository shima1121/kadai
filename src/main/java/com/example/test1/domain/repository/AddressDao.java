package com.example.test1.domain.repository;

import com.example.test1.domain.model.Address;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AddressDao {
    // Addressテーブルの件数を取得.
    public int count() throws DataAccessException;
    // Addressテーブルにデータを1件insert.
    public int insertOne(Address address) throws DataAccessException;
    // Addressテーブルのデータを１件取得
    public Address selectOne(String name) throws DataAccessException;
    // Addressテーブルの全データを取得.
    public List<Address> selectMany() throws DataAccessException;
    // Addressテーブルを１件更新.
    public int updateOne(Address address) throws DataAccessException;
    // Addressテーブルを１件削除.
    public int deleteOne(String name) throws DataAccessException;
}

