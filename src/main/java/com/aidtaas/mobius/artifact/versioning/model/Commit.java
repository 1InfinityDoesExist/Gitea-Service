package com.aidtaas.mobius.artifact.versioning.model;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.gitea.model.User;
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
public class Commit implements Serializable {

  private static final long serialVersionUID = 5461828855343352L;

  @JsonProperty("id")
  private String id;

  @JsonProperty("message")
  private String message;

  @JsonProperty("url")
  private String url;

  @JsonProperty("author")
  private User author;

  @JsonProperty("committer")
  private User committer;

  @JsonProperty("verification")
  private Verification verification;

  @JsonProperty("timestamp")
  private String timestamp;

  @JsonProperty("added")
  private List<String> added;

  @JsonProperty("removed")
  private List<String> removed;

  @JsonProperty("modified")
  private List<String> modified;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
