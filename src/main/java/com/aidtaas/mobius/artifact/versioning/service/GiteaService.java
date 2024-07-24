package com.aidtaas.mobius.artifact.versioning.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import org.springframework.stereotype.Component;
import com.aidtaas.mobius.artifact.versioning.config.GiteaConfiguration;
import com.aidtaas.mobius.artifact.versioning.model.create.CreateRepository;
import com.aidtaas.mobius.artifact.versioning.model.create.RepoRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.gitea.ApiException;
import io.gitea.api.RepositoryApi;
import io.gitea.model.ContentsResponse;
import io.gitea.model.CreateFileOptions;
import io.gitea.model.CreateHookOption;
import io.gitea.model.CreateHookOptionConfig;
import io.gitea.model.CreateRepoOption;
import io.gitea.model.DeleteFileOptions;
import io.gitea.model.FileDeleteResponse;
import io.gitea.model.FileResponse;
import io.gitea.model.Hook;
import io.gitea.model.Repository;
import io.gitea.model.UpdateFileOptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class GiteaService {

  private final GiteaConfiguration giteaConfiguration;

  public String createRepository(CreateRepository createRepository) {
    RepositoryApi repositoryApi = giteaConfiguration.getGiteaRepositoryClient();

    CreateRepoOption createRepoOption = new CreateRepoOption();
    createRepoOption.setName(createRepository.getName());
    createRepoOption.setDescription(createRepository.getDescription());
    createRepoOption.setPrivate(createRepository.isPrivateRepository());

    try {
      Repository repository = repositoryApi.createCurrentUserRepo(createRepoOption);
      log.info("Repository created successfully: {}", repository.getFullName());

      return repository.getFullName();

    } catch (ApiException e) {
      e.printStackTrace();
    }
    return "";
  }

  // create a file in repo
  public Object createFileInRepo(RepoRequest repoRequest) throws ApiException {
    RepositoryApi repositoryApi = giteaConfiguration.getGiteaRepositoryClient();
    CreateFileOptions body = new CreateFileOptions();
    try {
      FileResponse result = repositoryApi.repoCreateFile(repoRequest.getOwner(),
          repoRequest.getRepo(), repoRequest.getFilepath(), body);

      log.info("File Created in Repo : {}, Result : {}", repoRequest.getRepo(), result);

      return result;
    } catch (ApiException e) {
      log.error("Exception when calling RepositoryApi#repoCreateFile");
      e.printStackTrace();
    }

    return "Something went wrong";
  }

  // update file in repo
  public Object updateFileInRepo(RepoRequest repoRequest) throws ApiException {
    RepositoryApi repositoryApi = giteaConfiguration.getGiteaRepositoryClient();


    String sha = getFileSha(repositoryApi, repoRequest.getOwner(), repoRequest.getRepo(),
        repoRequest.getFilepath(), repoRequest.getBranch());

    if (sha == null) {
      log.error("File not found or unable to fetch SHA.");
      return "Sha is null.";
    }
    String newContent = "{\"name\": \"Avinash Patel 2\"}";


    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String formattedContent = gson.toJson(gson.fromJson(newContent, Object.class));

    String encodedContent = Base64.getEncoder().encodeToString(formattedContent.getBytes());
    UpdateFileOptions body = new UpdateFileOptions();
    body.setContent(encodedContent);
    body.setSha(sha);
    body.setBranch(repoRequest.getBranch());
    body.setMessage("Updating t_constuct_t5.json");
    try {
      FileResponse result = repositoryApi.repoUpdateFile(repoRequest.getOwner(),
          repoRequest.getRepo(), repoRequest.getFilepath(), body);
      log.info("File updated successfully: {}", result);

      return result;
    } catch (ApiException e) {
      log.error("Exception when calling RepositoryApi#repoUpdateFile");
      e.printStackTrace();
      log.error("Response body: " + e.getResponseBody());
    }

    return "Something went wrong";
  }

  // delete a file in repo
  public Object deleteFileInRepo(RepoRequest repoRequest) throws ApiException {
    RepositoryApi repositoryApi = giteaConfiguration.getGiteaRepositoryClient();

    String sha = getFileSha(repositoryApi, repoRequest.getOwner(), repoRequest.getRepo(),
        repoRequest.getFilepath(), repoRequest.getBranch());

    DeleteFileOptions body = new DeleteFileOptions();
    body.setSha(sha);
    body.setBranch(repoRequest.getBranch());
    body.setMessage("Deletion Successfull");
    try {
      FileDeleteResponse result = repositoryApi.repoDeleteFile(repoRequest.getOwner(),
          repoRequest.getRepo(), repoRequest.getFilepath(), body);
      log.info("Result : {}", result);

      return result;
    } catch (ApiException e) {
      log.error("Exception when calling RepositoryApi#repoDeleteFile");
      e.printStackTrace();
    }

    return "Something went wrong";
  }

  private String getFileSha(RepositoryApi apiInstance, String owner, String repo, String filepath,
      String branch) throws ApiException {
    String sha = null;
    try {
      sha = apiInstance.repoGetContents(owner, repo, filepath, branch).getSha();
    } catch (ApiException e) {
      log.error("when calling RepositoryApi#repoGetContents to fetch SHA");
      e.printStackTrace();
      throw e;
    }
    return sha;
  }


  // get a content of file in repo
  public Object getFileContentInRepo(RepoRequest repoRequest) throws ApiException {
    RepositoryApi repositoryApi = giteaConfiguration.getGiteaRepositoryClient();

    try {
      ContentsResponse result = repositoryApi.repoGetContents(repoRequest.getOwner(),
          repoRequest.getRepo(), repoRequest.getFilepath(), repoRequest.getRef());

      byte[] decodedBytes = Base64.getDecoder().decode(result.getContent());
      String decodedStr = new String(decodedBytes);
      log.info("------decodedStr :{}", decodedStr);

      return decodedStr;
    } catch (ApiException e) {
      log.error("Exception when calling RepositoryApi#repoGetContents");
      e.printStackTrace();
    }

    return "Something went wrong";
  }

  // get list of file in repo
  public Object getFileListInRepo(RepoRequest repoRequest) throws ApiException {
    RepositoryApi repositoryApi = giteaConfiguration.getGiteaRepositoryClient();
    try {
      List<ContentsResponse> result = repositoryApi.repoGetContentsList(repoRequest.getOwner(),
          repoRequest.getRepo(), repoRequest.getRef());
      log.info("-----Result : {}", result);

      return result;
    } catch (ApiException e) {
      log.error("Exception when calling RepositoryApi#repoGetContentsList");
      e.printStackTrace();
    }
    return "Something went wrong";
  }



  // Create Webhook
  public Object createRepoWebhook(RepoRequest repoRequest) throws ApiException {
    RepositoryApi repositoryApi = giteaConfiguration.getGiteaRepositoryClient();

    // Create a webhook configuration
    CreateHookOption hookOption = new CreateHookOption();
    hookOption.setType(CreateHookOption.TypeEnum.GITEA);
    hookOption.setActive(true);
    hookOption.setEvents(Arrays.asList("push", "pull_request"));

    log.info("Hook option: {}", hookOption.toString());
    CreateHookOptionConfig config = new CreateHookOptionConfig();
    config.put("url", "http://localhost:9898/gitea/webhook");
    config.put("content_type", "json");
    hookOption.setConfig(config);

    try {
      Hook result =
          repositoryApi.repoCreateHook(repoRequest.getOwner(), repoRequest.getRepo(), hookOption);
      log.info("-----Result : {}", result);

      return result;
    } catch (ApiException e) {
      log.error("Exception when calling RepositoryApi#repoCreateHook");
      e.printStackTrace();
      log.error("Response body: " + e.getResponseBody());
    }

    return "Something went wrong";
  }

  // Test Created Webhook
  public void testCreatedWebhook(RepoRequest repoRequest) throws ApiException {
    RepositoryApi repositoryApi = giteaConfiguration.getGiteaRepositoryClient();
    try {
      repositoryApi.repoTestHook(repoRequest.getOwner(), repoRequest.getRepo(), repoRequest.getId(),
          repoRequest.getRef());

    } catch (ApiException e) {
      log.error("Exception when calling RepositoryApi#repoTestHook");
      e.printStackTrace();
    }
  }

}
