package com.li.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 李洋
 * @date 2019-07-22 16:54
 */
@Controller
public class UploadController {
    private static final String UPLOAD_PATH="/static/upload/";

    /**
     * 文件上传处理
     * @param dropFile 接收来自dropZone框架传来的文件
     * @param editorFile 接收来自富文本编辑器传来的文件
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public Map<String , Object> uploadPic(MultipartFile dropFile, MultipartFile editorFile , HttpServletRequest request){
        MultipartFile myFile = dropFile == null ? editorFile : dropFile ;
        Map<String , Object> result = new HashMap<>(16);
        String originalFilename = myFile.getOriginalFilename();
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        File file=new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        file = new File(filePath,UUID.randomUUID()+fileSuffix);
        try {
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //添加图片
        if (dropFile != null){
            result.put("fileName" , UPLOAD_PATH+file.getName());
        }
        //富文本编辑器
        else {
            //服务器地址
            String serverPath = request.getScheme() + "://" +request.getServerName() + ":" + request.getServerPort();
            result.put("errno" , 0);
            result.put("data" , new String[]{
                    serverPath + UPLOAD_PATH +file.getName()
            });
        }

        return result;
    }
}
