package com.chatRobot.utils;

import org.apache.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {


    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpServletRequest hreq = (HttpServletRequest) servletRequest;

        String origin = servletRequest.getRemoteHost() + ":" + servletRequest.getRemotePort();

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");

//        response.setHeader("Access-Control-Allow-Headers", "Authentication");

        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
        response.setHeader("Access-Control-Max-Age", "3600");

        response.setHeader("Access-Control-Allow-Credentials", "true");

        if (hreq.getMethod().equals("OPTIONS")) {

            response.setStatus(HttpStatus.SC_OK);

            // hresp.setContentLength(0);

            response.getWriter().write("OPTIONS returns OK");

            return;

        }

        filterChain.doFilter(hreq, servletResponse);

    }

    public void destroy() {


    }

}