package com.gm.interceptor;

import feign.Response;
import feign.codec.Decoder;
import org.apache.commons.io.IOUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * feign返回值拦截
 * @author liufei
 */
public final class ResultStatusDecoder implements Decoder {
    public static final String CONTENT_KEY = "content";
    final Decoder delegate;

    public ResultStatusDecoder(Decoder delegate) {
        Objects.requireNonNull(delegate, "Decoder must not be null. ");
        this.delegate = delegate;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {

        //获取Request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletResponse httpServletResponse = attributes.getResponse();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        //将feignResponse转存到httpServletResponse中
        Map<String, Collection<String>> headers = response.headers();
        int i = 0;
        for(String name : headers.keySet()){
            for (String s : headers.get(name)) {

                System.out.println(i++);
                if (name.equals("connection")){
                    continue;
                }
                /*if (name.equals("set-cookie")){
                    String[] COOKIE = s.split(";");
                    String[] JSESSIONID = COOKIE[0].split("=");
                    Cookie cookie = new Cookie("JSESSIONID", JSESSIONID[1]);
                    //httpServletResponse.addCookie(cookie);
                    httpServletResponse.setHeader("Set-Cookie","JSESSIONID="+JSESSIONID[1]);
                }else {
                    httpServletResponse.setHeader(name,s);
                }*/
                httpServletResponse.setHeader(name,s);
                System.out.println("name:"+name+"\tvalues:"+s);
            }
        }
        httpServletResponse.setHeader("Access-Control-Allow-Origin",attributes.getRequest().getHeader("Origin"));
        // 判断是否返回参数是否是异常
        String resultStr = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
        // 拿到返回值，进行自定义逻辑处理
        //System.out.println("o business ,result msg ->"+resultStr);
        // 回写body,因为response的流数据只能读一次，这里回写后重新生成response
        return delegate.decode(response.toBuilder().body(resultStr, StandardCharsets.UTF_8).build(), type);
    }
}

