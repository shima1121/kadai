package com.example.test1.domain.repository;

import com.example.test1.domain.model.Place;
import lombok.Data;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

public interface PlaceDao {
    // Placeテーブルの件数を取得.
    public int count() throws DataAccessException;
    // Placeテーブルにデータを1件insert.
    public int insertOne(Place place) throws DataAccessException;
    // Placeテーブルのデータを１件取得
    public Place selectOne(Integer id) throws DataAccessException;
    // Placeテーブルの全データを取得.
    public List<Place> selectMany() throws DataAccessException;
    // Placeテーブルを１件更新.
    public int updateOne(Place place) throws DataAccessException;
    // Placeテーブルを１件削除.
    public int deleteOne(Date w_date) throws DataAccessException;
}

