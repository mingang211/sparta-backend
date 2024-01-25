package com.sparta.spartacoding.config;

import com.sparta.spartacoding.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthorizationServiceException ex) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, request, response, ex);
        } catch (Exception ex) {
            setErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, response, ex);
        }
    }

    public void setErrorResponse(HttpStatus status, HttpServletRequest request, HttpServletResponse response, Throwable ex) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(
                new ErrorResponse(
                        String.valueOf(System.currentTimeMillis()),
                        status.value(),
                        status.getReasonPhrase(),
                        ex.getMessage(),
                        request.getRequestURI()
                ).convertToJson()
        );
    }
}