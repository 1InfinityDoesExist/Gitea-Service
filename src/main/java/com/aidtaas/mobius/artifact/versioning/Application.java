package com.aidtaas.mobius.artifact.versioning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * sudo cat /var/snap/gitea/common/conf/app.ini
 * 
 * 
 * [webhook] 
 * ALLOWED_HOST_LIST = localhost,127.0.0.1,::1
 * 
 * 
 */


@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
