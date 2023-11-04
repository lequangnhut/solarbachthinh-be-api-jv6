package com.main.controller.admin;

import com.main.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/binh-luan")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public String product_comment(Model model) {
        model.addAttribute("Comments", commentService.findAll());
        return "views/admin/page/views/comment-list";

    }
}
