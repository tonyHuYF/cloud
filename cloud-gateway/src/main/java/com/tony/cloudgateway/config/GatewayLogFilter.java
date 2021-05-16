package com.tony.cloudgateway.config;

import cn.hutool.core.date.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class GatewayLogFilter implements GlobalFilter, Ordered {

    private static final String BEGIN_TIME = "beginTime";

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, GatewayFilterChain chain) {

        exchange.getAttributes().put(BEGIN_TIME, DateUtil.current());

        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(BEGIN_TIME);
                    if (startTime != null) {
                        log.info("=============================Gateway打印日志开始===============================");
                        log.info("访问接口host: " + exchange.getRequest().getURI().getHost());
                        log.info("访问接口端口: " + exchange.getRequest().getURI().getPort());
                        log.info("访问接口URL: " + exchange.getRequest().getURI().getPath());
                        log.info("访问接口URL参数: " + exchange.getRequest().getURI().getRawQuery());
                        log.info("访问接口时间: " + (DateUtil.current() - startTime) + "ms");
                        log.info("=============================Gateway打印日志结束===============================");
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return -10000;
    }
}
