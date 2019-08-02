package com.li.my.shop.web.ui.api;

import com.li.my.shop.commons.utils.HttpClientUtils;
import com.li.my.shop.commons.utils.MapperUtils;
import com.li.my.shop.web.ui.dto.TbContent;
import org.apache.http.impl.client.HttpClients;

import java.util.List;

/**
 * @author 李洋
 * @date 2019-07-28 16:43
 */
public class ContentsApi {

    public static List<TbContent> ppt(){
        List<TbContent> tbContents = null;
        String result = HttpClientUtils.doGet(Api.API_CONTENTS_PPT+"89");
        try {
            tbContents = MapperUtils.json2listByTree(result , "data" , TbContent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }
}
