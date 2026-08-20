package com.byfood.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class LoginRateLimitFilterTest {

    private final LoginRateLimitFilter filter = new LoginRateLimitFilter(3, 60_000L);

    private MockHttpServletResponse requestLogin() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/auth/login");
        request.setRemoteAddr("10.0.0.1");
        MockHttpServletResponse response = new MockHttpServletResponse();
        AtomicInteger passed = new AtomicInteger();
        filter.doFilter(request, response,
                (req, res) -> passed.incrementAndGet());
        return response;
    }

    @Test
    void shouldAllowRequestsUnderLimit() throws Exception {
        assertThat(requestLogin().getStatus()).isEqualTo(200);
        assertThat(requestLogin().getStatus()).isEqualTo(200);
        assertThat(requestLogin().getStatus()).isEqualTo(200);
    }

    @Test
    void shouldBlockWhenLimitExceeded() throws Exception {
        requestLogin();
        requestLogin();
        requestLogin();

        MockHttpServletResponse response = requestLogin();

        assertThat(response.getStatus()).isEqualTo(429);
        assertThat(response.getContentAsString()).contains("Muitas tentativas de login");
    }

    @Test
    void shouldNotApplyToOtherRequests() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/public/menu");
        request.setRemoteAddr("10.0.0.2");
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(request, response, (req, res) -> {
        });

        assertThat(response.getStatus()).isEqualTo(200);
    }
}