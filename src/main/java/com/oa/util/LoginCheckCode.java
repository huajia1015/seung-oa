package com.oa.util;

import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oa.log.SysLog;
import com.oa.util.constants.Constants;

/**
 * 登录验证码
 * @author Dwen
 * @version v 0.1 2013-8-3 下午05:31:41
 */
public class LoginCheckCode extends HttpServlet {
    private static final long serialVersionUID = 8165458985542870320L;

    public void init() throws ServletException {
        super.init();
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        
        ValidateCodeGenerator vCodeGen=new ValidateCodeGenerator(20, 4);
        
        RenderedImage image=vCodeGen.getImage();
        String code=vCodeGen.getCode();
        SysLog.info("code=========="+code);
        HttpSession session = request.getSession(true);
        session.setAttribute(Constants.LOGIN_CHECK_CODE, code); //将得到的检验码放至session中，以备前端校对
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }
}