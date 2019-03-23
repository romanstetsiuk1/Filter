package com.romanstetsiuk;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Logger;

@WebFilter(urlPatterns = {"/index.jsp"})
public class BaseFilter implements Filter {

    private static final Logger FILTER_LOGGER = Logger.getLogger(BaseFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        Enumeration<String> headerNames = req.getHeaderNames();

        FILTER_LOGGER.info(req.getRequestURI());
        FILTER_LOGGER.info(req.getParameter("name"));

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                FILTER_LOGGER.info("Header: " + req.getHeader(headerNames.nextElement()));
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

        req.setCharacterEncoding("ISO-8859-1");
    }

    @Override
    public void destroy() {

    }
}
