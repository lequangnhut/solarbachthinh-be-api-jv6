package com.main.controller.admin;

import com.main.dto.BannerWordsDto;
import com.main.entity.BannerWords;
import com.main.service.BannerWordService;
import com.main.utils.EntityDtoUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("quan-tri/quan-ly-tu-cam")
public class BannerWordControllerAD {

    @Autowired
    BannerWordService bannerWordService;

    @Autowired
    HttpSession session;

    @GetMapping
    public String showListBannerWord(@ModelAttribute BannerWordsDto bannerWordsDto, Model model) {
        model.addAttribute("bannerWordsDto", bannerWordsDto);
        model.addAttribute("listBannerWord", bannerWordService.findAll());
        return "views/admin/page/views/banner-word-list";
    }

    @PostMapping("save")
    public String save(@Validated @ModelAttribute BannerWordsDto bannerWordsDto, BindingResult bindingResult, Model model) {
        List<BannerWords> bannerWords = bannerWordService.findByWord(bannerWordsDto.getWord());
        if (bindingResult.hasErrors()) {
            if(bannerWords.isEmpty()){
                BannerWords save = EntityDtoUtils.convertToEntity(bannerWordsDto, BannerWords.class);
                bannerWordService.save(save);

                model.addAttribute("bannerWordsDto", bannerWordsDto);
                model.addAttribute("listBannerWord", bannerWordService.findAll());

                session.setAttribute("toastSuccess", "Thêm từ cấm thành công !");
                return "redirect:/quan-tri/quan-ly-tu-cam";
            }else{
                model.addAttribute("bannerWordsDto", bannerWordsDto);
                model.addAttribute("listBannerWord", bannerWordService.findAll());
                session.setAttribute("toastSuccess", "Từ cấm đã tồn tại!");
                return "redirect:/quan-tri/quan-ly-tu-cam";
            }

        }else{
            model.addAttribute("bannerWordsDto", bannerWordsDto);
            model.addAttribute("listBannerWord", bannerWordService.findAll());
            return "views/admin/page/views/banner-word-list";
        }

    }

    @PostMapping("update/{bannerWordId}")
    public String update(@PathVariable("bannerWordId") Integer bannerWordId, Model model) {
        Optional<BannerWords> bannerWords = bannerWordService.findById(bannerWordId);
        if(bannerWords.isPresent()){
            BannerWords save = EntityDtoUtils.convertToEntity(bannerWords.get(), BannerWords.class);
            bannerWordService.save(save);

            model.addAttribute("bannerWordsDto", bannerWords.get());
            model.addAttribute("listBannerWord", bannerWordService.findAll());
            return "redirect:/quan-tri/quan-ly-tu-cam";
        }else{
            return "redirect:/quan-tri/quan-ly-tu-cam";
        }

    }

    @GetMapping("delete/{bannerWordId}")
    public String delete(@PathVariable("bannerWordId") Integer bannerWordId, Model model) {

        Optional<BannerWords> bannerWords = bannerWordService.findById(bannerWordId);
        if(bannerWords.isPresent()){
            bannerWordService.delete(bannerWords.get());

            model.addAttribute("bannerWordsDto", bannerWords.get());
            model.addAttribute("listBannerWord", bannerWordService.findAll());
            session.setAttribute("toastSuccess", "Xóa từ cấm thành công!");
            return "redirect:/quan-tri/quan-ly-tu-cam";
        }else{
            model.addAttribute("bannerWordsDto", bannerWords.get());
            model.addAttribute("listBannerWord", bannerWordService.findAll());
            session.setAttribute("toastFailed", "Xóa từ cấm thất bại vui lòng kiểm tra lại!");
            return "redirect:/quan-tri/quan-ly-tu-cam";
        }
    }
}
