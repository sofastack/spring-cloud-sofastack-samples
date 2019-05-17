package io.sofastack.cloud.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

/**
 * 简单的鉴权过滤器实现
 * @author: guolei.sgl (guolei.sgl@antfin.com) 19/1/29 上午11:27
 * @since:
 **/
@Component
public class AuthGatewayFilter implements GlobalFilter, Ordered {

    @Value("${sofastack.auth.server.url}")
    private String              authServerUrl;

    private static final String STATIC_RESOURCE_PATH = "/static/";

    private static final Logger LOGGER               = LoggerFactory
                                                         .getLogger(AuthGatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("code");
        URI uri = exchange.getRequest().getURI();
        String backUrl = "";
        String redirectUrl;
        if (uri != null) {
            backUrl = uri.toString();
        }
        String urlPath = exchange.getRequest().getURI().getPath();
        if (StringUtils.isEmpty(token) && !urlPath.contains(STATIC_RESOURCE_PATH)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.SEE_OTHER);
            try {
                redirectUrl = authServerUrl + "?backUrl=" + URLEncoder.encode(backUrl, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Error to encode url");
                redirectUrl = authServerUrl + "?backUrl=" + backUrl;
            }
            response.getHeaders().set("Location", redirectUrl);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
