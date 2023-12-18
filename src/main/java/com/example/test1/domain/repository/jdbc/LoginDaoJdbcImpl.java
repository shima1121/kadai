package com.example.test1.domain.repository.jdbc;

import com.example.test1.domain.model.Login;
import com.example.test1.domain.repository.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("LoginDaoJdbcImpl")
public class LoginDaoJdbcImpl implements LoginDao {
    @Autowired
    JdbcTemplate jdbc;

    // LOGINテーブルの件数を取得.
    @Override
    public int count() throws DataAccessException {
        return 0;
    }
    // LOGINテーブルにデータを1件insert.
    @Override
    public int insertOne(Login login) throws DataAccessException {
        String sql = "INSERT INTO LOGIN(id, password) VALUES(?,?)";
        int rowNumber = jdbc.update(sql, login.getId(),login.getPassword());
        return rowNumber;
    }
    // LOGINテーブルのデータを１件取得
    @Override
    public Login selectOne(String id) throws DataAccessException {
        return null;
    }
    // LOGINテーブルの全データを取得.
    @Override
    public List<Login> selectMany() throws DataAccessException {
        return null;
    }
    // LOGINテーブルを１件更新.
    @Override
    public int updateOne(Login login) throws DataAccessException {
        return 0;
    }
    // LOGINテーブルを１件削除.
    @Override
    public int deleteOne(String id) throws DataAccessException {
        return 0;
    }
}