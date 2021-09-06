package com.example.sogbackend.controller.accountController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.sogbackend.config.security.JWTUtil;
import com.example.sogbackend.model.AppUser;
import com.example.sogbackend.responce.UserResponse;
import com.example.sogbackend.services.accountService.IAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping(path = JWTUtil.ENDPOINTS)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String authToken = request.getHeader(JWTUtil.AUTH_HEADER);
      if (authToken != null && authToken.startsWith(JWTUtil.PREFIX)) {
        try {
          String jwt = authToken.substring(JWTUtil.PREFIX_LENGTH);
          Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
          JWTVerifier jwtVerifier = JWT.require(algorithm).build();
          DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
          String username = decodedJWT.getSubject();
          AppUser appUser = accountService.loadUserByUsername(username);
          String jwtAccessToken = JWT.create()
            .withSubject(appUser.getEmail())
            .withExpiresAt(new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
            .withIssuer(request.getRequestURL().toString())
            .withClaim("roles", appUser.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toList()))
            .sign(algorithm);
          Map<String, String> idToken = new HashMap<>();
          idToken.put("jwt", jwtAccessToken);
          idToken.put("refreshToken", jwt);
          response.setContentType("application/json");
          new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        } catch (Exception e) {
          throw e;
        }
      } else {
        throw new RuntimeException("Refresh token required");
      }
    }

    @GetMapping(path = "/profile")
    public UserResponse profile(Principal principal) {
      AppUser appUser = accountService.loadUserByUsername(principal.getName());

        UserResponse userResponse = new UserResponse();

        userResponse.setUserId(appUser.getUserId());
        userResponse.setRoles(appUser.getRoles());
        userResponse.setUserName(appUser.getFirstName() + " " + appUser.getLastName());
        userResponse.setEmailVerificationStatus(appUser.getEmailVerificationStatus());

      return userResponse;
    }

}
