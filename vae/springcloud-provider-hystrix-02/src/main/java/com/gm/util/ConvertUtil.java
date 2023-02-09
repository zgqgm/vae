package com.gm.util;

import feign.Response;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

public class ConvertUtil {
    @SneakyThrows
    public void feignResp2ServletResp(Response feignResponse, HttpServletResponse response) {
        Response.Body body = feignResponse.body();
        try (InputStream inputStream = body.asInputStream(); OutputStream outputStream = response.getOutputStream())
        {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, feignResponse.headers().get(HttpHeaders.CONTENT_DISPOSITION).stream().findFirst().get());
            response.setContentType(feignResponse.headers().get(HttpHeaders.CONTENT_TYPE).stream().findFirst().get());
            IOUtils.copy(inputStream, outputStream);
        }
    }
}
