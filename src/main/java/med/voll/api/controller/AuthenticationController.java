package med.voll.api.controller;

import med.voll.api.domain.user.User;
import med.voll.api.domain.user.UserAuthenticationData;
import med.voll.api.infra.security.jwtTokenData;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody UserAuthenticationData userAuthenticationData) {
        Authentication AuthToken = new UsernamePasswordAuthenticationToken(
                userAuthenticationData.login(),
                userAuthenticationData.key()
        );
        var authenticatedUser = authenticationManager.authenticate(AuthToken);
        String jwtToken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new jwtTokenData(jwtToken));
    }
}
