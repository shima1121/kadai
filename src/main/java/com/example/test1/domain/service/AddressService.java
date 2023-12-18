package com.example.test1.domain.service;

import com.example.test1.domain.model.Address;
import com.example.test1.domain.repository.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressDao addressDao;
    public boolean insert(Address _address) {
        // insert実行
        int rowNumber = addressDao.insertOne(_address);
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
        return addressDao.count();
    }

    //全件取得用メソッド.
    public List<Address> selectMany() {
        // 全件取得
        return addressDao.selectMany();
    }
     // １件取得用メソッド.
    public Address selectOne(String name) {
        // selectOne実行
        return addressDao.selectOne(name);
    }

     //１件更新用メソッド.
    public boolean updateOne(Address address) {
        // 判定用変数
        boolean result = false;
        // １件更新
        int rowNumber = addressDao.updateOne(address);
        if (rowNumber > 0) {
            // update成功
            result = true;
        }
        return result;
    }

    // １件削除用メソッド.
    public boolean deleteOne(String name) {

        // １件削除
        int rowNumber = addressDao.deleteOne(name);
        // 判定用変数
        boolean result = false;
        if (rowNumber > 0) {
            // delete成功
            result = true;
        }
        return result;
    }
}



