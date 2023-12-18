package com.example.test1.domain.repository.jdbc;

import com.example.test1.domain.model.Status;
import com.example.test1.domain.repository.StatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("StatusDaoJdbcImpl")
public class StatusDaoJdbcImpl implements StatusDao {
    @Autowired
    JdbcTemplate jdbc;

    // statusテーブルの件数を取得.
    @Override
    public int count() throws DataAccessException {
        //全件取得してカウント
        int count = jdbc.queryForObject("SELECT COUNT(*) FROM attendance " +
                "INNER JOIN work_place ON attendance.a_date = work_place.w_date;", Integer.class);
        return count;
    }
    // statusテーブルにデータを1件insert.
    @Override
    public int insertOne(Status status) throws DataAccessException {
        return 0;
    }
    // statusテーブルのデータを１件取得
    @Override
    public Status selectOne(Integer id) throws DataAccessException {
        return null;
    }
    // statusテーブルの全データを取得.
    @Override
    public List<Status> selectMany() throws DataAccessException {

        // M_statusテーブルのデータを全件取得
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM attendance INNER JOIN work_place " +
                "ON attendance.a_date = work_place.w_date ORDER BY a_date");
        // 結果返却用の変数
        List<Status> statusList = new ArrayList<>();
        // 取得したデータを結果返却用のListに格納していく
        for (Map<String, Object> map : getList) {

            //statusインスタンスの生成
            Status status = new Status();
            // statusインスタンスに取得したデータをセットする
            status.setId((Integer) map.get("id")); 
            status.setA_date((Date) map.get("a_date")); //日付
            status.setW_hours((Date) map.get("working_hours")); //出勤時間
            status.setC_time((Date) map.get("closing_time")); //退勤時間
            status.setW_date((Date) map.get("w_date")); //日付
            status.setPlace((String) map.get("place")); //勤務地

            //結果返却用のListに追加
            statusList.add(status);
        }
        return statusList;
    }
    // statusテーブルを１件更新.
    @Override
    public int updateOne(Status status) throws DataAccessException {
        return 0;
    }
    // statusテーブルを１件削除.
    @Override
    public int deleteOne(Integer id) throws DataAccessException {
        return 0;
    }
}
