package com.example.IssueTrackingSystem.web.issue;

import com.example.IssueTrackingSystem.domain.issue.IssueEntity;
import com.example.IssueTrackingSystem.domain.issue.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/issues") // @GetMappingの共通部分を削除
@RequiredArgsConstructor
public class issueController {

    //@RequiredArgsConstructor が finalかつ初期化されていないフィールドを初期化するコンストラクタを自動生成してくれるので = new IssueServiceはいらない
    private final IssueService issueService;
    @GetMapping
    public String showList(Model model){
        //addAttributeで タイムリーフにオブジェクトを渡すことができる
        // 第一引数は タイムリーフで参照するときのキー、第二引数はタイムリーフに渡したいオブジェクト
        model.addAttribute("issueList", issueService.findAll());
        return "issues/list"; //resources/templates　からの相対パスで指定する
    }

    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute IssueForm form){ // add.addAttributeするのと同じ newもしてくれる
        //model.addAttribute("issueFrom", new IssueForm());
        return "issues/creationForm";
    }

    @PostMapping
    //IssueFrom の後に BindingResult は書く。BindingResultがあることでバリデーションがあるかをチェックできるようになってあれば作成画面に戻す処理ができる
    public String create(@Validated IssueForm form, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return showCreationForm(form);
        }
        //TODO データの永続化をおこなう
        issueService.create(form.getSummary(), form.getDescription());
        return "redirect:/issues";
    }

    @GetMapping("/{issueId}")
    public String showDetail(@PathVariable("issueId") long issueId, Model model){
        model.addAttribute("issue", issueService.findById(issueId));
        return "issues/detail";
    }
}
