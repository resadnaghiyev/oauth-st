package com.rashad.sociallogin;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    String baseUrl = System.getenv("STADIUM_BASE_URL");

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/user/login/google")
    public ResponseEntity<?> login(@AuthenticationPrincipal OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        String firstName = oauth2User.getAttribute("given_name");
        String lastName = oauth2User.getAttribute("family_name");

        String resourceUrl = baseUrl + "/api/v1/user/google/login";
        HttpEntity<GoogleLogin> request = new HttpEntity<>(new GoogleLogin(email, firstName, lastName));

        return restTemplate.exchange(resourceUrl, HttpMethod.POST, request, CustomResponse.class);
    }

    @GetMapping
    public Map<String, Object> google(@AuthenticationPrincipal OAuth2User oauth2User) {
        return oauth2User.getAttributes();
    }
}
