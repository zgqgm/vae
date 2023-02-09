package com.gm.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * feign请求拦截
 * 自定义feign注解
 * @desc 解决feign丢失header问题
 * @data 2023-1-6 16:29
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //获取Request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取header
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            //遍历
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                System.out.println("name:"+name+";--------value:"+values);
                //设置请求头
                requestTemplate.header(name, values);
            }
           // System.out.println("==================================================================");
            //HttpServletResponse response = attributes.getResponse();
//            for (String headerName : response.getHeaderNames()) {
//                String header = response.getHeader(headerName);
//                System.out.println("name:"+headerName+";--------value:"+header);
//            }

        }
    }
}
