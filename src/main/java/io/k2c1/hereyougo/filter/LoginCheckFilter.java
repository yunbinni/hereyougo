package io.k2c1.hereyougo.filter;

import io.k2c1.hereyougo.constant.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter
{
    private static final String[] whitelist = {"/",
            "/login", "/logout", "/join",
            "/jusoPopup", "/members/join", "/members/login","https://business.juso.go.kr/*",
            "/categories/child", "/posts/filtered",
            "favicon.ico", "/css/*", "/js/*", "/font/*", "/images/*"
    };


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        try
        {
            HttpSession session = httpRequest.getSession(false);

            if(isLoginCheckPath(requestURI))
            {
                // 로그인이 안된 사용자가 접속하는 경우
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null)
                {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }

            chain.doFilter(request, response);
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI)
    {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
