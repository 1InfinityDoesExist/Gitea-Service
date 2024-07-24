package com.aidtaas.mobius.artifact.versioning.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Verification implements Serializable {

  private static final long serialVersionUID = -4383837598476819014L;

  @JsonProperty("verified")
  private boolean verified;

  @JsonProperty("reason")
  private String reason;

  @JsonProperty("signature")
  private String signature;

  @JsonProperty("signer")
  private String signer;

  @JsonProperty("payload")
  private String payload;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
