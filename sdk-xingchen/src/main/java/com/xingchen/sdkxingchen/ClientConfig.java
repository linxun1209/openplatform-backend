package com.xingchen.sdkxingchen;

import com.xingchen.sdkxingchen.clinet.ClientXingchen;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingchen
 * @version V1.0
 * @Package com.xingchen.sdkxingchen
 * @date 2022/11/12 16:25
 */
@Configuration
@ConfigurationProperties("xingchen.client")
@Data
@ComponentScan
public class ClientConfig {
    private String accessKey;

    private String secretKey;

    @Bean
    public ClientXingchen yuApiClient() {
        return new ClientXingchen(accessKey, secretKey);
    }
}
