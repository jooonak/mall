package com.worksout.controller.admin;

import com.worksout.dto.goods.Goods;
import com.worksout.dto.goods.GoodsSeeker;
import com.worksout.service.goods.GoodsService;
import com.worksout.service.settings.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SeasonService seasonService;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("goodsList", goodsService.getGoodsList());
        return "/admin/goods/main";
    }

    @GetMapping("/list")
    public void goodsList(Model model, @ModelAttribute("seeker") GoodsSeeker seeker) {
        model.addAttribute("goodsList", goodsService.getGoodsList(seeker));
        model.addAttribute("seasons", seasonService.getSeasons());
        model.addAttribute("categories", goodsService.getCategoryList());
    }

    @GetMapping("/register")
    public void goodsRegistrationPage (Model model, Authentication auth) {
        model.addAttribute("seasons", seasonService.getSeasons());
        model.addAttribute("categories", goodsService.getCategoryList());
        model.addAttribute("sizeGroup", goodsService.getSizeGroup());
    }

    @PostMapping("/register")
    public String goodsRegistration (RedirectAttributes rttr, Goods goods) {
        try {
            goodsService.registerGoods(goods);
            rttr.addFlashAttribute("regResult", true);
        } catch (Exception e) {
            rttr.addFlashAttribute("regResult", false);
        }
        return "redirect:/admin/goods/list";
    }

    @GetMapping("/{goodsNo}")
    public String goodsDetailPage (@PathVariable("goodsNo") String goodsNo, Model model) {
        model.addAttribute("goods", goodsService.getGoods(goodsNo));
        return "/admin/goods/detail";
    }

}
