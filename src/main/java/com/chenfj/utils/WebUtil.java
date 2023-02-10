package com.chenfj.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: feiju.chen
 * @Date: 2023/2/10 12:44
 * @Description:
 * @version: 1.0
 */
public class WebUtil {
    public static String renderString(HttpServletResponse response,String json){
        try {
            response.setStatus(100);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
