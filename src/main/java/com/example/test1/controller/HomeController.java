package com.example.test1.controller;

import com.example.test1.domain.model.Status;
import com.example.test1.domain.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    StatusService statusService;

    @GetMapping("/home")
    public String getHome(Model model) {
        return "login/home";
    }

    @PostMapping("/logout")
    public String postLogout() {
        //ログイン画面にリダイレクト
        return "redirect:/login";
    }
    @GetMapping("/allList")
    public String getUserList(Model model) {
        //status一覧の生成
        List<Status> statusList = statusService.selectMany();
        //Modelにallリストを登録
        model.addAttribute("allList", statusList);
        //データ件数を取得
        int count = statusService.count();
        model.addAttribute("userListCount", count);
        return "login/allList";
    }
}
