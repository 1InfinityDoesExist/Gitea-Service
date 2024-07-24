package com.aidtaas.mobius.artifact.versioning.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.aidtaas.mobius.artifact.versioning.model.create.CreateRepository;
import com.aidtaas.mobius.artifact.versioning.model.create.RepoRequest;
import com.aidtaas.mobius.artifact.versioning.service.GiteaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gitea.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GiteaController {

  private final GiteaService giteaService;

  @PostMapping("/create-repo")
  public ResponseEntity<?> createRepository(@RequestBody CreateRepository createRepository) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ModelMap().addAttribute("msg", giteaService.createRepository(createRepository)));
  }

  @PostMapping("/create-file")
  public ResponseEntity<?> createFile(@RequestBody RepoRequest repoRequest) throws ApiException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ModelMap().addAttribute("msg", giteaService.createFileInRepo(repoRequest)));
  }

  @PostMapping("/update-file")
  public ResponseEntity<?> updateFile(@RequestBody RepoRequest repoRequest) throws ApiException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ModelMap().addAttribute("msg", giteaService.updateFileInRepo(repoRequest)));
  }

  @PostMapping("/delete-file")
  public ResponseEntity<?> deleteFile(@RequestBody RepoRequest repoRequest) throws ApiException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ModelMap().addAttribute("msg", giteaService.deleteFileInRepo(repoRequest)));
  }

  @PostMapping("/file-content")
  public ResponseEntity<?> getFileContent(@RequestBody RepoRequest repoRequest)
      throws ApiException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ModelMap().addAttribute("msg", giteaService.getFileContentInRepo(repoRequest)));
  }

  @PostMapping("/file-list")
  public ResponseEntity<?> getFileList(@RequestBody RepoRequest repoRequest) throws ApiException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ModelMap().addAttribute("msg", giteaService.getFileListInRepo(repoRequest)));
  }

  @PostMapping("/test-webhook")
  public ResponseEntity<?> testCreatedWebhook(@RequestBody RepoRequest repoRequest)
      throws ApiException {

    giteaService.testCreatedWebhook(repoRequest);
    return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("msg", "Success"));
  }

  @PostMapping("/create-webhook")
  public ResponseEntity<?> createRepoWebhook(@RequestBody RepoRequest repoRequest)
      throws ApiException {

    return ResponseEntity.status(HttpStatus.OK)
        .body(new ModelMap().addAttribute("msg", giteaService.createRepoWebhook(repoRequest)));
  }

  @PostMapping(path = "/gitea/webhook", produces = "application/json",
      consumes = "application/json")
  public ResponseEntity<?> webhook(@RequestBody JsonNode payload) {
    ObjectMapper objectMapper = new ObjectMapper();
    String prettyPayload = null;
    try {

      prettyPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
      log.info(prettyPayload);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ModelMap().addAttribute("msg", prettyPayload));
  }
}


