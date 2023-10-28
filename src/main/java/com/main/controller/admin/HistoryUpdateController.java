package com.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quan-tri/lich-su-cap-nhat-san-pham")
public class HistoryUpdateController {

    @GetMapping
    public String history_update_product() {
        return "views/admin/page/views/historys-list";
    }
}
