package clubedolivro.api.controller;

import clubedolivro.api.domain.user.AuthenticationData;
import clubedolivro.api.domain.user.User;
import clubedolivro.api.infra.security.TokenJWTData;
import clubedolivro.api.infra.security.TokenService;
import jakarta.validation.Valid;
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
public class AuthenticationController {

    //CLASSE DO SPRING QUE DISPARA O PROCESSO DE AUTENTICAÇÃO
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationData data) {
        //OBJETO AUTHENTICATION PARA PASSAR NO MÉTODO AUTHENTICATE
        var authenticationObj = new UsernamePasswordAuthenticationToken(data.username(), data.password());

        //CHAMA O MÉTODO DO SERVICE
        var authentication = manager.authenticate(authenticationObj);

        //GERANDO O TOKENJWT PARA O USUARIO AUTENTICADO
        var tokenJWT = tokenService.getToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }
}
