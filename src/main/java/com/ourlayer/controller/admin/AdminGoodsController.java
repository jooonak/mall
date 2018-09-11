package com.ourlayer.controller.admin;

import com.ourlayer.dto.goods.Goods;
import com.ourlayer.dto.goods.GoodsSeeker;
import com.ourlayer.service.goods.GoodsService;
import com.ourlayer.service.settings.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/goods")
public class AdminGoodsController {

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
    public void goodsList (Model model, @ModelAttribute("seeker") GoodsSeeker seeker) {
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
    public String goodsDetailPage (@PathVariable("goodsNo") String goodsNo, Model model, HttpServletRequest req) {
        String prev = req.getHeader("Referer");
        System.out.println(prev);

        model.addAttribute("seasons", seasonService.getSeasons());
        model.addAttribute("categories", goodsService.getCategoryList());
        model.addAttribute("sizeGroup", goodsService.getSizeGroup());
        model.addAttribute("goods", goodsService.getGoods(goodsNo));
        return "/admin/goods/detail";
    }

    @PostMapping("/update")
    public String updateGoods (Goods goods, RedirectAttributes rttr) {
        try {
            System.out.println(goods);
            goodsService.updateGoods(goods);
            rttr.addFlashAttribute("updateResult", "Update Success!");
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("updateResult", "Update Fail...");
        }
        return "redirect:/admin/goods/list";
    }

}
