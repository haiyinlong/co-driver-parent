package com.leo.ad.codriver.clean;

import com.leo.ad.codriver.clean.handler.CleanHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * CleanChainConfig
 *
 * @author HaiYinLong
 * @version 2024/07/12 18:17
 **/
@Configuration
@RequiredArgsConstructor
public class CleanHandlerChainConfig {
    private final List<CleanHandler> cleanHandlers;

    @Bean
    public CleanHandler cleanHandlerChain() {
        if (cleanHandlers.isEmpty()) {
            throw new IllegalArgumentException("Handler list is empty");
        }
        CleanHandler cleanHandler;
        for (int i = 0; i < cleanHandlers.size() - 1; i++) {
            cleanHandler = cleanHandlers.get(i);
            cleanHandler.setNextHandler(cleanHandlers.get(i + 1));
        }
        return cleanHandlers.get(0);
    }
}
