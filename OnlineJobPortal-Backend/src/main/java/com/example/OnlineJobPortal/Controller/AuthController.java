package com.example.OnlineJobPortal.Controller;

import com.example.OnlineJobPortal.dto.LoginResponseDto;
import com.example.OnlineJobPortal.model.Users;
import com.example.OnlineJobPortal.record.LoginResponseRecord;
import com.example.OnlineJobPortal.record.RegisterRecord;
import com.example.OnlineJobPortal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRecord data){
        Object response = service.register(data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Users user){
        String token = service.verify(user);
        //record example
        return ResponseEntity.ok(new LoginResponseRecord(token));

        //dto example
//        LoginResponseDto loginResponseDto = new LoginResponseDto();
//        loginResponseDto.setAccessToken(token);
//        return ResponseEntity.ok(loginResponseDto);
    }
}
