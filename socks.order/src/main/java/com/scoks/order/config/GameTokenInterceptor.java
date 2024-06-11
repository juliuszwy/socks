package com.scoks.order.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GameTokenInterceptor implements HandlerInterceptor {
    private String secretKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        boolean pass = false;
//        String token = request.getParameter("token");
//        if (!StringUtils.isEmpty(token)) {
//            try {
//
//                String decryption = HexUtil.decryption(secretKey, token);
//                GameUser gameUser = JSONObject.parseObject(decryption, GameUser.class);
//                if (gameUser != null) {
//                    pass = true;
//                    request.setAttribute("userInfo", gameUser);
//                }
//            } catch (Exception e) {
//            }
//        }
//        if (!pass) {
//            response.setContentType("application/json; charset=utf-8");
//            try (PrintWriter out = response.getWriter()) {
//                out.append(JSONObject.toJSONString(Result.error(ResultStatus.TOKEN_ERR)));
//            } catch (IOException e) {
//            }
//        }
//        return pass;
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
