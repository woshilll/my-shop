package com.li.my.shop.web.ui.web.controller;

import com.li.my.shop.web.ui.api.ContentsApi;
import com.li.my.shop.web.ui.dto.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-28 11:11
 */
@Controller
public class IndexController {


    @RequestMapping(value = {"" , "index"} , method = RequestMethod.GET)
    public String index(Model model){
        requestContentsPPT(model);
        return "index";
    }

    /**
     * 请求幻灯片
     *
     * @param model
     */
    private void requestContentsPPT(Model model) {
        List<TbContent> tbContents = ContentsApi.ppt();
        for (TbContent tbContent : tbContents) {
            System.out.println(tbContent);
        }
        model.addAttribute("ppt", tbContents);
    }
}
