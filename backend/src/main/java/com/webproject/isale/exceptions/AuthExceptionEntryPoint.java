package com.webproject.isale.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException
    {
        final Map<String, Object> mapBodyException = new HashMap<>() ;

        mapBodyException.put("error", "InvalidAuthTokenException");
        mapBodyException.put("message", arg2.getLocalizedMessage());
        mapBodyException.put("status", HttpServletResponse.SC_UNAUTHORIZED) ;
        mapBodyException.put("timestamp", (new Date()).getTime()) ;

        response.setContentType(MediaType.APPLICATION_JSON_VALUE) ;
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR) ;
        final ObjectMapper mapper = new ObjectMapper() ;
        mapper.writeValue(response.getOutputStream(), mapBodyException) ;
        response.flushBuffer();
    }
}
