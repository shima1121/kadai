package com.example.test1.domain.service;

import com.example.test1.domain.model.Address;
import com.example.test1.domain.model.Login;
import com.example.test1.domain.repository.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginDao loginDao;

    public boolean insert(Login login) {
        // insert実行
        int rowNumber = loginDao.insertOne(login);
        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // insert成功
            result = true;
        }
        return result;
    }
    // １件取得用メソッド.
    public Login selectOne(String id) {
        // selectOne実行
        return loginDao.selectOne(id);
    }
}

