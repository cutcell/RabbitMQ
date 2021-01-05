package com.github.cutcell.exchange;

import lombok.Getter;
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
@Getter
@Validated
public class AppProps {

    @NotBlank
    private final String fanoutName;

    @Min(1)
    private final int emitsNum;

    @NotNull
    @DurationUnit(ChronoUnit.MILLIS)
    private final Duration workMaxDuration;

}
