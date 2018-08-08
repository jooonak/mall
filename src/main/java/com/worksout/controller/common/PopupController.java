package com.worksout.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PopupController {

    @GetMapping("/img-upload")
    public String imgUploadPopup(String workType, Model model) {
        model.addAttribute("workType", workType);
        return "/common/img-upload";
    }

}
