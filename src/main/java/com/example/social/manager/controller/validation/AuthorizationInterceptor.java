package com.example.social.manager.controller.validation;

import com.example.social.manager.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AuthorizedRoles authorizedRolesAnnotation = handlerMethod.getMethodAnnotation(AuthorizedRoles.class);
            if (authorizedRolesAnnotation != null) {
                String[] requiredRoles = authorizedRolesAnnotation.value();
                String authorizationHeader = request.getHeader("Authorization");
                if (authorizationHeader != null) {
                    var jwt = authorizationHeader.split(" ")[1];
                    if (!JwtUtil.validateToken(jwt)) {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        return false;
                    }
                    Claims claim = JwtUtil.getClaim(jwt);
                    String userRol = claim.get("rol").toString();
                    boolean authorized = Arrays.asList(requiredRoles).contains(userRol);
                    if (!authorized) {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        return false;
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return false;
                }
            }
        }
        return true;
    }
}
