package com.aidtaas.mobius.artifact.versioning.model;

import java.io.Serializable;
import java.security.Permissions;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.gitea.model.InternalTracker;
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
public class Repository implements Serializable {

  private static final long serialVersionUID = -5799867212368595445L;

  @JsonProperty("id")
  private int id;

  @JsonProperty("owner")
  private User owner;

  @JsonProperty("name")
  private String name;

  @JsonProperty("full_name")
  private String fullName;

  @JsonProperty("description")
  private String description;

  @JsonProperty("empty")
  private boolean empty;

  @JsonProperty("private")
  private boolean isPrivate;

  @JsonProperty("fork")
  private boolean fork;

  @JsonProperty("template")
  private boolean template;

  @JsonProperty("parent")
  private Object parent;

  @JsonProperty("mirror")
  private boolean mirror;

  @JsonProperty("size")
  private int size;

  @JsonProperty("language")
  private String language;

  @JsonProperty("languages_url")
  private String languagesUrl;

  @JsonProperty("html_url")
  private String htmlUrl;

  @JsonProperty("url")
  private String url;

  @JsonProperty("ssh_url")
  private String sshUrl;

  @JsonProperty("clone_url")
  private String cloneUrl;

  @JsonProperty("original_url")
  private String originalUrl;

  @JsonProperty("website")
  private String website;

  @JsonProperty("stars_count")
  private int starsCount;

  @JsonProperty("forks_count")
  private int forksCount;

  @JsonProperty("watchers_count")
  private int watchersCount;

  @JsonProperty("open_issues_count")
  private int openIssuesCount;

  @JsonProperty("open_pr_counter")
  private int openPrCounter;

  @JsonProperty("release_counter")
  private int releaseCounter;

  @JsonProperty("default_branch")
  private String defaultBranch;

  @JsonProperty("archived")
  private boolean archived;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("updated_at")
  private String updatedAt;

  @JsonProperty("archived_at")
  private String archivedAt;

  @JsonProperty("permissions")
  private Permissions permissions;

  @JsonProperty("has_issues")
  private boolean hasIssues;

  @JsonProperty("internal_tracker")
  private InternalTracker internalTracker;

  @JsonProperty("has_wiki")
  private boolean hasWiki;

  @JsonProperty("has_pull_requests")
  private boolean hasPullRequests;

  @JsonProperty("has_projects")
  private boolean hasProjects;

  @JsonProperty("projects_mode")
  private String projectsMode;

  @JsonProperty("has_releases")
  private boolean hasReleases;

  @JsonProperty("has_packages")
  private boolean hasPackages;

  @JsonProperty("has_actions")
  private boolean hasActions;

  @JsonProperty("ignore_whitespace_conflicts")
  private boolean ignoreWhitespaceConflicts;

  @JsonProperty("allow_merge_commits")
  private boolean allowMergeCommits;

  @JsonProperty("allow_rebase")
  private boolean allowRebase;

  @JsonProperty("allow_rebase_explicit")
  private boolean allowRebaseExplicit;

  @JsonProperty("allow_squash_merge")
  private boolean allowSquashMerge;

  @JsonProperty("allow_fast_forward_only_merge")
  private boolean allowFastForwardOnlyMerge;

  @JsonProperty("allow_rebase_update")
  private boolean allowRebaseUpdate;

  @JsonProperty("default_delete_branch_after_merge")
  private boolean defaultDeleteBranchAfterMerge;

  @JsonProperty("default_merge_style")
  private String defaultMergeStyle;

  @JsonProperty("default_allow_maintainer_edit")
  private boolean defaultAllowMaintainerEdit;

  @JsonProperty("avatar_url")
  private String avatarUrl;

  @JsonProperty("internal")
  private boolean internal;

  @JsonProperty("mirror_interval")
  private String mirrorInterval;

  @JsonProperty("object_format_name")
  private String objectFormatName;

  @JsonProperty("mirror_updated")
  private String mirrorUpdated;

  @JsonProperty("repo_transfer")
  private Object repoTransfer;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
