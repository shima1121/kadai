package com.example.test1.domain.service;

import com.example.test1.domain.model.Place;
import com.example.test1.domain.repository.PlaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlaceService {
    @Autowired
    PlaceDao placeDao;

    public boolean insert(Place place) {
        // insert実行
        int rowNumber = placeDao.insertOne(place);
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
        return placeDao.count();
    }
    //全件取得用メソッド.
    public List<Place> selectMany() {
        // 全件取得
        return placeDao.selectMany();
    }
     //１件取得用メソッド.
    public Place selectOne(Integer id) {
        // selectOne実行
        return placeDao.selectOne(id);
    }
    //１件更新用メソッド.
    public boolean updateOne(Place place) {
        // 判定用変数
        boolean result = false;
        // １件更新
        int rowNumber = placeDao.updateOne(place);
        if (rowNumber > 0) {
            // update成功
            result = true;
        }
        return result;
    }
     // １件削除用メソッド.
    public boolean deleteOne(Date w_date) {

        // １件削除
        int rowNumber = placeDao.deleteOne(w_date);
        // 判定用変数
        boolean result = false;
        if (rowNumber > 0) {
            // delete成功
            result = true;
        }
        return result;
    }
}


