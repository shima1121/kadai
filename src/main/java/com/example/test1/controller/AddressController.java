package com.example.test1.controller;

import com.example.test1.domain.model.Address;
import com.example.test1.domain.model.AddressForm;
import com.example.test1.domain.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping("/address")
    public String getAddress(@ModelAttribute AddressForm form, Model model) {
        // address.htmlに画面遷移
        return "login/address/address";
    }
    @GetMapping("/addressList")
    public String getAddressList(Model model) {

        //address一覧の生成
        List<Address> addressList = addressService.selectMany();
        //Modelにaddressリストを登録
        model.addAttribute("addressList", addressList);
        //データ件数を取得
        int count = addressService.count();
        model.addAttribute("ListCount", count);
        return "login/address/addressList";
    }
    //連絡先詳細画面のGETメソッド用処理.
    @GetMapping("/addressDetail/{name:.+}")
    public String getAddressDetail(@ModelAttribute AddressForm form,
                                Model model,
                                @PathVariable("name") String name) {
        // name確認
        System.out.println("name = " + name);

        // nameのチェック
        if (name != null && name.length() > 0) {
            // 連絡先情報を取得
            Address address = addressService.selectOne(name);
            // Addressクラスをフォームクラスに変換
            form.setName(address.getName()); //名前
            form.setPhone(address.getPhone()); //電話番号
            form.setAddress(address.getAddress()); //Eメールアドレス

            // Modelに登録
            model.addAttribute("addressForm", form);
        }
        return "login/address/addressDetail";
    }

    @PostMapping("/address")
    public String postAddress(@ModelAttribute @Validated AddressForm form,
                                 BindingResult bindingResult,
                                 Model model) {

        // 入力チェックに引っかかった場合、登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、登録画面に戻ります
            return getAddress(form, model);
        }

        // formの中身をコンソールに出して確認します
        System.out.println(form);

        // insert用変数
        Address address = new Address();
        address.setName(form.getName()); //名前
        address.setPhone(form.getPhone()); //電話番号
        address.setAddress(form.getAddress()); //Eメール
         //連絡先入力処理
        boolean result = addressService.insert(address);

        // 連絡先入力結果の判定
        if (result == true) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }
        // home.htmlにリダイレクト
        return "redirect:/home";
    }
    //更新用処理.
    @PostMapping(value = "/addressDetail", params = "update")
    public String postAddressDetailUpdate(@ModelAttribute AddressForm form, Model model) {

        System.out.println("更新ボタンの処理");

        //addressインスタンスの生成
        Address address= new Address();
        //フォームクラスをaddressクラスに変換
        address.setName(form.getName());
        address.setPhone(form.getPhone());
        address.setAddress(form.getAddress());
        //更新実行
        boolean result = addressService.updateOne(address);

        if (result == true) {
            model.addAttribute("result", "更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
        }
        //一覧画面を表示
        return getAddressList(model);
    }

     // 削除用処理.
    @PostMapping(value = "/addressDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute AddressForm form, Model model) {

        System.out.println("削除ボタンの処理");
        //削除実行
        boolean result = addressService.deleteOne(form.getName());
        if (result == true) {
            model.addAttribute("result", "削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
        }

        //一覧画面を表示
        return getAddressList(model);
    }

}



