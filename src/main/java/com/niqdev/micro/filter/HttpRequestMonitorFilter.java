package com.niqdev.micro.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HttpRequestMonitorFilter extends OncePerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger("HTTP_REQUEST");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            MDC.put("method", request.getMethod());
            MDC.put("uri", request.getRequestURI());
            MDC.put("status", String.valueOf(response.getStatus()));
            MDC.put("durationMs", String.valueOf(duration));
            MDC.put("remoteAddr", request.getRemoteAddr());

            log.info("HTTP request monitored");

            MDC.clear();
        }
    }
}
