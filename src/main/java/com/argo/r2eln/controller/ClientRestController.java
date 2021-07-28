package com.argo.r2eln.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ClientRestController {

    @Autowired
    @Qualifier("webClient")
    private WebClient webClient;

    @GetMapping("/")
    public String index(
            @RegisteredOAuth2AuthorizedClient("dropbox") OAuth2AuthorizedClient authorizedClient
            , Model model
    ) {

        Map param = new HashMap<String, Object>();
        param.put("query", "foo");

        String body = webClient
                .post()
                .uri("https://api.dropboxapi.com/2/check/app")
                .bodyValue(param)
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        model.addAttribute("result", body);

        return "index";
    }

}
