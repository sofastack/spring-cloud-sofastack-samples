package io.sofastack.cloud.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/28 8:56 PM
 * @since:
 **/
@Controller
public class LoginController {
    private static final String QUESTION_MARK = "?";

    @RequestMapping("login")
    public String login(HttpServletRequest request, ModelMap map)
                                                                 throws UnsupportedEncodingException {
        String originUrl = request.getParameter("backUrl");
        if (StringUtils.isEmpty(originUrl)) {
            return "fail";
        }
        String backUrl = URLDecoder.decode(originUrl, "UTF-8");
        if (backUrl.contains(QUESTION_MARK)) {
            map.addAttribute("linkParam", true);
        } else {
            map.addAttribute("linkParam", false);
        }
        map.addAttribute("backUrl", backUrl);
        return "login";
    }

    @RequestMapping("loginError")
    public String error(HttpServletRequest request, ModelMap map) {
        String errorMsg = "登录失败";
        Object errorMsgObj = request.getSession().getAttribute("errorMsg");
        if (errorMsg != null) {
            errorMsg = errorMsgObj.toString();
        }
        map.addAttribute("errorMsg", errorMsg);
        return "fail";
    }
}
