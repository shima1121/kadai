package com.example.test1.domain.repository.jdbc;

import com.example.test1.domain.model.Address;
import com.example.test1.domain.repository.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("AddressDaoJdbcImpl")
public class AddressDaoJdbcImpl implements AddressDao {
    @Autowired
    JdbcTemplate jdbc;

    // CONTACT_ADDRESSテーブルの件数を取得.
    @Override
    public int count() throws DataAccessException {
        //全権取得してカウント
        int count = jdbc.queryForObject("SELECT COUNT(*) FROM CONTACT_ADDRESS", Integer.class);
        return count;
    }

    // CONTACT_ADDRESSテーブルにデータを1件insert.
    @Override
    public int insertOne(Address address) throws DataAccessException {
        String sql = "INSERT INTO CONTACT_ADDRESS(name, phone, address) VALUES(?,?,?)";
        int rowNumber = jdbc.update(sql, address.getName(), address.getPhone(), address.getAddress());
        return rowNumber;
    }

    // CONTACT_ADDRESSテーブルのデータを１件取得
    @Override
    public Address selectOne(String name) throws DataAccessException {
        // １件取得
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM CONTACT_ADDRESS"
                + " WHERE name = ?", name);
        // 結果返却用の変数
        Address address = new Address();

        // 取得したデータを結果返却用の変数にセットしていく
        address.setName((String) map.get("name")); //名前
        address.setPhone((String) map.get("phone")); //電話番号
        address.setAddress((String) map.get("address")); //Eメールアドレス
        return address;
    }

    // CONTACT_ADDRESSテーブルの全データを取得.
    @Override
    public List<Address> selectMany() throws DataAccessException {
        // CONTACT_ADDRESSテーブルのデータを全件取得
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM CONTACT_ADDRESS");
        // 結果返却用の変数
        List<Address> addressesList = new ArrayList<>();
        // 取得したデータを結果返却用のListに格納していく
        for (Map<String, Object> map : getList) {

            //Addressインスタンスの生成
            Address address = new Address();
            // Addressインスタンスに取得したデータをセットする
            address.setName((String) map.get("name")); //名前
            address.setPhone((String) map.get("phone")); //電話番号
            address.setAddress((String) map.get("address")); //Eメールアドレス
            //結果返却用のListに追加
            addressesList.add(address);
        }
        return addressesList;
    }

    // CONTACT_ADDRESSテーブルを１件更新.
    @Override
    public int updateOne(Address address) throws DataAccessException {
        //１件更新
        int rowNumber = jdbc.update("UPDATE CONTACT_ADDRESS" + " SET" + " phone = ?,"
                        + " address = ?" + " WHERE name = ?",
                address.getPhone(), address.getAddress(), address.getName());

        return rowNumber;
    }

    // CONTACT_ADDRESSテーブルを１件削除.
    @Override
    public int deleteOne(String name) throws DataAccessException {
        //１件削除
        int rowNumber = jdbc.update("DELETE FROM CONTACT_ADDRESS WHERE name = ?", name);

        return rowNumber;
    }
}