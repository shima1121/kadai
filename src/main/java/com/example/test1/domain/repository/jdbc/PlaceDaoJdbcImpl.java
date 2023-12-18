package com.example.test1.domain.repository.jdbc;

import com.example.test1.domain.model.Place;
import com.example.test1.domain.repository.PlaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("PlaceDaoJdbcImpl")
public class PlaceDaoJdbcImpl implements PlaceDao {
    @Autowired
    JdbcTemplate jdbc;

    // WORK_PLACEテーブルの件数を取得.
    @Override
    public int count() throws DataAccessException {
        //全権取得してカウント
        int count = jdbc.queryForObject("SELECT COUNT(*) FROM WORK_PLACE", Integer.class);
        return count;
    }
    // WORK_PLACEテーブルにデータを1件insert.
    @Override
    public int insertOne(Place place) throws DataAccessException {
        String sql = "INSERT INTO WORK_PLACE(w_date, place) VALUES(?,?)";
        int rowNumber = jdbc.update(sql, place.getW_date(), place.getPlace());
        return rowNumber;
    }
    // WORK_PLACEテーブルのデータを１件取得
    @Override
    public Place selectOne(Integer id) throws DataAccessException {
        // １件取得
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM WORK_PLACE"
                + " WHERE id = ?", id);

        // 結果返却用の変数
        Place place = new Place();

        // 取得したデータを結果返却用の変数にセットしていく
        place.setId((Integer) map.get("id"));
        place.setW_date((Date) map.get("w_date"));//日付
        place.setPlace((String) map.get("place"));//勤務地
        return place;
    }
    // WORK_PLACEテーブルの全データを取得.
    @Override
    public List<Place> selectMany() throws DataAccessException {
        // WORK_PLACEテーブルのデータを全件取得
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM WORK_PLACE ORDER BY w_date");
        // 結果返却用の変数
        List<Place> placeList = new ArrayList<>();
        // 取得したデータを結果返却用のListに格納していく
        for (Map<String, Object> map : getList) {

            //Placeインスタンスの生成
            Place place = new Place();
            // placeインスタンスに取得したデータをセットする
            place.setId((Integer) map.get("id"));
            place.setW_date((Date) map.get("w_date"));//日付
            place.setPlace((String) map.get("place"));//勤務地
            //結果返却用のListに追加
            placeList.add(place);
        }
        return placeList;
    }
    // WORK_PLACEテーブルを１件更新.
    @Override
    public int updateOne(Place place) throws DataAccessException {
        //１件更新
        int rowNumber = jdbc.update("UPDATE WORK_PLACE" +" SET" + " place = ?" + " WHERE w_date = ?",
                        place.getPlace(), place.getW_date());
        return rowNumber;
    }
    // WORK_PLACEテーブルを１件削除.
    @Override
    public int deleteOne(Date w_date) throws DataAccessException {
        //１件削除
        int rowNumber = jdbc.update("DELETE FROM WORK_PLACE WHERE w_date = ?", w_date);

        return rowNumber;
    }
}
