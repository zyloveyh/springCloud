package com.zy.schoolrelation.filter;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * 修改请求路径,且所有的参数都跟随
 */
@WebFilter("/*")
public class PathFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter start");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
        System.out.println(httpRequest.getRequestURI());
        String path=httpRequest.getRequestURI();
        //path的写法根据需要修改
        if(path.indexOf("/api/")<0){
            path="/api"+path;
            System.out.println(path);
            httpRequest.getRequestDispatcher(path).forward(request,response);
        }
        else {
            chain.doFilter(request,response);
        }
        return;
    }

    @Override
    public void destroy() {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("classpath:spring.xml"));
    }
}


