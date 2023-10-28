package com.main.controller.admin;

import com.main.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/quan-ly-lich-su")
public class HistoryUpdateController {

    @Autowired
    HistoryService historyService;

    @GetMapping
    public String history_update_product(Model model) {
        model.addAttribute("histories", historyService.findAll());
        return "views/admin/page/views/historys-list";
    }
}
