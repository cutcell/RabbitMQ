package com.github.cutcell.workers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@ConfigurationProperties("app")
@Getter
@Setter
@Validated
public class AppProps {

    @NotBlank
    private String queueName;

    @Min(1)
    private int emitNum;

    @Min(100)
    @Max(30000)
    private long workMaxDuration;
}
