package com.aidtaas.mobius.artifact.versioning.config;

import java.io.Serializable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "gitea.prop", ignoreInvalidFields = true,
    ignoreUnknownFields = true)
public class GiteaProperties implements Serializable {

  private static final long serialVersionUID = -4712667681045354609L;
  private String key;
  private String basePath;
}
