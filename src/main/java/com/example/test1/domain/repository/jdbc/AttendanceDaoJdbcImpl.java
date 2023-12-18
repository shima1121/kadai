package com.example.test1.domain.repository.jdbc;

import com.example.test1.domain.model.Attendance;
import com.example.test1.domain.repository.AttendanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("AttendanceDaoJdbcImpl")
public class AttendanceDaoJdbcImpl implements AttendanceDao {
    @Autowired
    JdbcTemplate jdbc;

    // ATTENDANCEテーブルの件数を取得.
    @Override
    public int count() throws DataAccessException {
        //全権取得してカウント
        int count = jdbc.queryForObject("SELECT COUNT(*) FROM ATTENDANCE", Integer.class);
        return count;
    }
    // ATTENDANCEテーブルにデータを1件insert.
    @Override
    public int insertOne(Attendance attendance) throws DataAccessException {
        String sql = "INSERT INTO ATTENDANCE(a_date, working_hours, closing_time) VALUES(?,?,?)";
        int rowNumber = jdbc.update(sql, attendance.getA_date(), attendance.getW_hours(), attendance.getC_time());
        return rowNumber;
    }
    // ATTENDANCEテーブルのデータを１件取得
    @Override
    public Attendance selectOne(Integer id) throws DataAccessException {

        // １件取得
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM ATTENDANCE"
                + " WHERE id = ?", id);

        // 結果返却用の変数
        Attendance attendance= new Attendance();

        // 取得したデータを結果返却用の変数にセットしていく
        attendance.setId((Integer) map.get("id"));
        attendance.setA_date((Date) map.get("a_date")); //日付
        attendance.setW_hours((Date) map.get("working_hours")); //出勤時間
        attendance.setC_time((Date) map.get("closing_time")); //退勤時間


        return attendance;
    }
    // ATTENDANCEテーブルの全データを取得.
    @Override
    public List<Attendance> selectMany() throws DataAccessException {
        // ATTENDANCEテーブルのデータを全件取得
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM ATTENDANCE ORDER BY a_date");
        // 結果返却用の変数
        List<Attendance> attendanceList = new ArrayList<>();
        // 取得したデータを結果返却用のListに格納していく
        for (Map<String, Object> map : getList) {

            //Attendanceインスタンスの生成
            Attendance attendance = new Attendance();
            // Attendanceインスタンスに取得したデータをセットする
            attendance.setId((Integer) map.get("id"));
            attendance.setA_date((Date) map.get("a_date")); //日付
            attendance.setW_hours((Date) map.get("working_hours")); //出勤時間
            attendance.setC_time((Date) map.get("closing_time")); //退勤時間
            //結果返却用のListに追加
            attendanceList.add(attendance);
        }
        return attendanceList;
    }
    // ATTENDANCEテーブルを１件更新.
    @Override
    public int updateOne(Attendance attendance) throws DataAccessException {
        //１件更新
        int rowNumber = jdbc.update("UPDATE ATTENDANCE" +" SET" + " closing_time = ?" + " WHERE a_date = ?",
                attendance.getC_time(), attendance.getA_date());
            return rowNumber;
    }
    // ATTENDANCEテーブルを１件削除.
    @Override
    public int deleteOne(Date a_date) throws DataAccessException {
        //１件削除
        int rowNumber = jdbc.update("DELETE FROM ATTENDANCE WHERE a_date = ?", a_date);

        return rowNumber;
    }
}
