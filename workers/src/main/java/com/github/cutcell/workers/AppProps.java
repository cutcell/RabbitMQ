package com.github.cutcell.workers;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationProperties("app")
@ConstructorBinding
@RequiredArgsConstructor
@Validated
public class AppProps {

    @NotBlank
    private final String queueName;

    @Min(1)
    private final int emitNum;

    @NotNull
    @DurationUnit(ChronoUnit.MILLIS)
    private final Duration workMaxDuration;

    public String getQueueName() {
        return queueName;
    }

    public int getEmitNum() {
        return emitNum;
    }

    public Duration getWorkMaxDuration() {
        return workMaxDuration;
    }
}
