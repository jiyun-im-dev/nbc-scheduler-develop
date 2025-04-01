package com.jiyun.nbcschedulerdevelop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/", "/login", "/signup", "/logout"};

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();

        log.info("로그인 필터 로직 실행");

        // 화이트 리스트에 포함되지 않으면 로그인을 체크해야 하는 URL 이므로 로직 실행
        if (!isWhiteList(requestURI)) {
            // 로그인하면 세션에 값이 저장되어 있다는 전제 하에 로그인을 체크한다.
            // 세션이 존재하면 가져오고 없으면 null
            HttpSession session = request.getSession(false);// create=false

            // 로그인하지 않은 사용자의 경우
            if (session == null || session.getAttribute("sessionKey") == null) {
                throw new RuntimeException("로그인이 필요합니다.");
            }
        }

        // 다음 필터가 있으면 호출하고 없으면 Servlet -> Controller 호출
        filterChain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}
