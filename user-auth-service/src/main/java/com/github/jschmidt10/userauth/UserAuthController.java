package com.github.jschmidt10.userauth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserAuthController {

    private UserAuthService service;

    public UserAuthController(UserAuthService service) {
        this.service = service;
    }

    @PostMapping("create")
    public String create(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        try {
            service.create(username, password);
            return sendResponse(response, HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public String authenticate(@RequestParam String username, @RequestParam String password,
                               HttpServletResponse response) {
        try {
            if (service.authenticate(username, password)) {
                return sendResponse(response, HttpStatus.OK);
            } else {
                return sendResponse(response, HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return sendResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String sendResponse(HttpServletResponse response, HttpStatus status) {
        response.setStatus(status.value());
        return status.name();
    }
}
