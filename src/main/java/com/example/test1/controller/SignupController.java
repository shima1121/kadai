package com.example.test1.controller;

import com.example.test1.domain.model.Login;
import com.example.test1.domain.model.SignupForm;
import com.example.test1.domain.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @Autowired
    private LoginService loginService;
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {
        // signup.htmlに画面遷移
        return "login/signup";
    }

    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated SignupForm form,
                             BindingResult bindingResult,
                             Model model) {

        // 入力チェックに引っかかった場合、ユーザー登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、ユーザー登録画面に戻ります
            return getSignUp(form, model);
        }

        // formの中身をコンソールに出して確認します
        System.out.println(form);

        // insert用変数
        Login login = new Login();
        login.setId(form.getId()); //ユーザーID
        login.setPassword(form.getPassword()); //パスワード
        // ユーザー登録処理
        boolean result = loginService.insert(login);

        // ユーザー登録結果の判定
        if (result == true) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }
        // login.htmlにリダイレクト
        return "redirect:/login";
    }
}