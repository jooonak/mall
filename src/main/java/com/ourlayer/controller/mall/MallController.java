package com.ourlayer.controller.mall;

import com.ourlayer.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MallController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("goods", goodsService.getGoodsList());
        return "/mall/index";
    }

    @GetMapping("/mall")
    public String mall (Model model) {
        model.addAttribute("goods", goodsService.getGoodsList());
        return "/mall/main";
    }

    @GetMapping("/ref")
    public String refPage() {
        return "/index";
    }

}
