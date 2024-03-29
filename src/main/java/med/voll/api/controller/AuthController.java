package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.LoginRequest;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        if (request == null || request.login() == null || request.password() == null)  // preverification
            return ResponseEntity.badRequest().build();


        var token = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        System.out.println("password = " + request.login());
        var authentication = this.authManager.authenticate(token);

        return ResponseEntity.ok(this.tokenService.generateToken((User) authentication.getPrincipal()));
    }

}
