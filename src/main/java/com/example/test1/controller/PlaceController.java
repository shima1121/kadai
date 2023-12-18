package com.example.test1.controller;

import com.example.test1.domain.model.Place;
import com.example.test1.domain.model.PlaceForm;
import com.example.test1.domain.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PlaceController {
    @Autowired
    private PlaceService placeService;

    //ラジオボタン用変数
    private Map<String, String> radioPlace;
    private Map<String, String> initRadioPlace() {
        Map<String, String> radio = new LinkedHashMap<>();
        // 勤務地をMapに格納
        radio.put("自宅", "自宅");
        radio.put("本社", "本社");
        radio.put("営業先", "営業先");
        return radio;
    }

    @GetMapping("/place")
    public String getPlace(@ModelAttribute PlaceForm form, Model model) {
        // ラジオボタンの初期化メソッド呼び出し
        radioPlace = initRadioPlace();
        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioPlace", radioPlace);
        // place.htmlに画面遷移
        return "login/place/place";
    }
    @GetMapping("/placeList")
    public String getPlaceList(Model model) {
        //一覧の生成
        List<Place> placeList = placeService.selectMany();
        //Modelにplaceリストを登録
        model.addAttribute("placeList", placeList);
        //データ件数を取得
        int count = placeService.count();
        model.addAttribute("ListCount", count);
        return "login/place/placeList";
    }
     //詳細画面のGETメソッド用処理.
    @GetMapping("/placeDetail/{id:.+}")
    public String getPlaceDetail(@ModelAttribute PlaceForm form,
                                Model model, @PathVariable("id")Integer id) {
        // ID確認
        System.out.println("id = " + id);
        // 勤務地用ラジオボタンの初期化
        radioPlace = initRadioPlace();
        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioPlace", radioPlace);

        // IDのチェック
        if (id != null) {
            // 情報を取得
            Place place = placeService.selectOne(id);
            // Placeクラスをフォームクラスに変換
            form.setId(place.getId());
            form.setW_date(place.getW_date()); //日付
            form.setPlace(place.getPlace()); //勤務地

            // Modelに登録
            model.addAttribute("placeForm", form);
        }
        return "login/place/placeDetail";
    }

    @PostMapping("/place")
    public String postPlace(@ModelAttribute @Validated PlaceForm form,
                              BindingResult bindingResult,
                              Model model) {

        // 入力チェックに引っかかった場合、登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、登録画面に戻ります
            return getPlace(form, model);
        }

        // formの中身をコンソールに出して確認します
        System.out.println(form);

        // insert用変数
        Place place = new Place();
        place.setW_date(form.getW_date()); //日付
        place.setPlace(form.getPlace()); //勤務地
        //勤務地入力処理
        boolean result = placeService.insert(place);

        // 勤務地入力結果の判定
        if (result == true) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }
        // home.htmlにリダイレクト
        return "redirect:/home";
    }
    //更新用処理.
    @PostMapping(value = "/placeDetail", params = "update")
    public String postAddressDetailUpdate(@ModelAttribute PlaceForm form, Model model) {

        System.out.println("更新ボタンの処理");

        //addressインスタンスの生成
        Place place = new Place();
        //フォームクラスをaddressクラスに変換
        place.setW_date(form.getW_date());
        place.setPlace(form.getPlace());
        //更新実行
        boolean result = placeService.updateOne(place);

        if (result == true) {
            model.addAttribute("result", "更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
        }
        //一覧画面を表示
        return getPlaceList(model);
    }
    // 削除用処理.
    @PostMapping(value = "/placeDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute PlaceForm form, Model model) {

        System.out.println("削除ボタンの処理");
        //削除実行
        boolean result = placeService.deleteOne(form.getW_date());
        if (result == true) {
            model.addAttribute("result", "削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
        }

        //一覧画面を表示
        return getPlaceList(model);
    }

}





