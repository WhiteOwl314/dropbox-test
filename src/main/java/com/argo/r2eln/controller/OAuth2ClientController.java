package com.argo.r2eln.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2ClientController {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping("/oAuth2AuthorizedClientService")
    public String index() {
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient("dropbox", "tester");

        OAuth2AccessToken accessToken = client.getAccessToken();

        return "index";
    }
}
