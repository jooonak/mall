package com.ourlayer.controller.common;

import com.ourlayer.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PopupController {

	@Autowired
	private GoodsService goodsService;
	
    @GetMapping("/img-upload")
    public String imgUploadPopup(String workType, Model model) {
        model.addAttribute("workType", workType);
        return "/common/img-upload";
    }
    
    @GetMapping("/view-images/{goodsNo}")
	public String viewGoodsImages(@PathVariable("goodsNo") String goodsNo, Model model) {
 		model.addAttribute("images", goodsService.getGoodsImages(goodsNo));
 		return "/admin/goods/view-images";
	}

}
