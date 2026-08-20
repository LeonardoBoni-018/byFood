package com.byfood.api.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginRateLimitFilter extends OncePerRequestFilter {

    static final int MAX_ATTEMPTS = 5;
    static final long WINDOW_MS = 60_000L;

    private final int maxAttempts;
    private final long windowMs;
    private final Map<String, Deque<Long>> attempts = new ConcurrentHashMap<>();

    public LoginRateLimitFilter() {
        this(MAX_ATTEMPTS, WINDOW_MS);
    }

    LoginRateLimitFilter(int maxAttempts, long windowMs) {
        this.maxAttempts = maxAttempts;
        this.windowMs = windowMs;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !("/auth/login".equals(request.getRequestURI())
                && "POST".equalsIgnoreCase(request.getMethod()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        long now = System.currentTimeMillis();

        Deque<Long> times = attempts.computeIfAbsent(ip, k -> new ArrayDeque<>());
        synchronized (times) {
            while (!times.isEmpty() && now - times.peekFirst() > windowMs) {
                times.removeFirst();
            }
            if (times.size() >= maxAttempts) {
                response.setStatus(429);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter()
                        .write("{\"detail\":\"Muitas tentativas de login. Tente novamente em 1 minuto.\"}");
                return;
            }
            times.addLast(now);
        }
        filterChain.doFilter(request, response);
    }
}