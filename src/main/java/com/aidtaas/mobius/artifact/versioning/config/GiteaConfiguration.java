package com.aidtaas.mobius.artifact.versioning.config;

import org.springframework.stereotype.Component;
import io.gitea.ApiClient;
import io.gitea.Configuration;
import io.gitea.api.RepositoryApi;
import io.gitea.auth.ApiKeyAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiteaConfiguration {

  private final GiteaProperties giteaProperties;

  public RepositoryApi getGiteaRepositoryClient() {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath(giteaProperties.getBasePath());

    ApiKeyAuth accessToken = (ApiKeyAuth) defaultClient.getAuthentication("AccessToken");
    accessToken.setApiKey(giteaProperties.getKey());

    RepositoryApi repositoryApi = new RepositoryApi(defaultClient);
    log.info("-----RepositoryApi :{}", repositoryApi);

    return repositoryApi;
  }
}
