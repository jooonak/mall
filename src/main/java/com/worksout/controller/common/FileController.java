package com.worksout.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileController {

    @GetMapping("/img-upload")
    public String imgUpload(String workType) {
        return "/common/img-upload";
    }

}
