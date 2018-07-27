package com.worksout.controller.admin;

import com.worksout.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("goodsList", goodsService.selectGoodsList());
        return "admin/goods/list";
    }
}
