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
public class GiteaWebhookPayload implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("ref")
  private String ref;

  @JsonProperty("before")
  private String before;

  @JsonProperty("after")
  private String after;

  @JsonProperty("compare_url")
  private String compareUrl;

  @JsonProperty("commits")
  private List<Commit> commits;

  @JsonProperty("total_commits")
  private int totalCommits;

  @JsonProperty("head_commit")
  private Commit headCommit;

  @JsonProperty("repository")
  private Repository repository;

  @JsonProperty("pusher")
  private User pusher;

  @JsonProperty("sender")
  private User sender;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
