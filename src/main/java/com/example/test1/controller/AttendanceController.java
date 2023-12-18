package com.example.test1.controller;

import com.example.test1.domain.model.Attendance;
import com.example.test1.domain.model.AttendanceForm;
import com.example.test1.domain.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @GetMapping("/attendance")
    public String getAttendance(@ModelAttribute AttendanceForm form, Model model) {
        // attendance.htmlに画面遷移
        return "login/attendance/attendance";
    }
    @GetMapping("/attendanceList")
    public String getAttendanceList(Model model) {

        //一覧の生成
        List<Attendance> attendanceList = attendanceService.selectMany();
        //Modelにattendanceリストを登録
        model.addAttribute("attendanceList", attendanceList);
        //デ-タ件数を取得
        int count = attendanceService.count();
        model.addAttribute("ListCount", count);
        return "login/attendance/attendanceList";
    }
    //詳細画面のGETメソッド用処理.

    @GetMapping("/attendanceDetail/{id:.+}")
    public String getAttendanceDetail(@ModelAttribute AttendanceForm form,
                                   Model model,
                                   @PathVariable("id") Integer id) {
        // ID確認
        System.out.println("id = " + id);

        // IDのチェック
        if (id != null) {
            // 情報を取得
            Attendance attendance= attendanceService.selectOne(id);
            // Addressクラスをフォムクラスに変換
            form.setId(attendance.getId());
            form.setA_date(attendance.getA_date()); //日付
            form.setW_hours(attendance.getW_hours()); //出勤時間
            form.setC_time(attendance.getC_time()); //退勤時間

            // Modelに登録
            model.addAttribute("attendanceForm", form);
        }
        return "login/attendance/attendanceDetail";
    }

    @PostMapping(value ="/attendance", params = "start")
    public String postAttendance(@ModelAttribute @Validated AttendanceForm form,
                             BindingResult bindingResult,
                             Model model) {

        // 入力チェックに引っかかった場合、ホーム登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、ホーム登録画面に戻ります
            return getAttendance(form, model);
        }

        // formの中身をコンソ-ルに出して確認します
        System.out.println(form);

        // insert用変数
        Date now = new Date();

        Attendance attendance = new Attendance();
        attendance.setA_date(form.getA_date()); //日付
        attendance.setW_hours(now); //出勤時間
        // 勤怠入力処理
        boolean result = attendanceService.insert(attendance);

        // 勤怠入力結果の判定
        if (result == true) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }
        return getAttendanceList(model);
    }
    @PostMapping(value = "/attendance", params = "end")
    public String postAttendanceUpdate(@ModelAttribute @Validated AttendanceForm form,
                                 Model model) {
        System.out.println("退勤ボタンの処理");

        Date now = new Date();
        //Attendanceインスタンスの生成
        Attendance attendance = new Attendance();
        //フォムクラスをAttendanceクラスに変換
        attendance.setA_date(form.getA_date());
        attendance.setC_time(now); //退勤時間
        //更新実行
        boolean result = attendanceService.updateOne(attendance);

        if (result == true) {
            model.addAttribute("result", "更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
        }
        //一覧画面を表示
        return getAttendanceList(model);
    }
    // 削除用処理.
    @PostMapping(value = "/attendanceDetail", params = "delete")
    public String postAttendanceDetailDelete(@ModelAttribute AttendanceForm form, Model model) {

        System.out.println("削除ボタンの処理");
        //削除実行
        boolean result = attendanceService.deleteOne(form.getA_date());
        if (result == true) {
            model.addAttribute("result", "削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
        }

        //一覧画面を表示
        return getAttendanceList(model);
    }

}

