package com.github.cutcell.routing.config.props;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("app")
@ConstructorBinding
@AllArgsConstructor
@Getter
@Validated
public class AppProps {

    @NotBlank
    private final String exchangeName;

}
