package mk.ukim.finki.webspring.web;

import mk.ukim.finki.webspring.config.AuthRequest;
import mk.ukim.finki.webspring.config.AuthResponse;
import mk.ukim.finki.webspring.config.JWTUtil;
import mk.ukim.finki.webspring.config.PBKDF2Encoder;
import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.Role;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.service.LoyalCardService;
import mk.ukim.finki.webspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.xml.ws.RespectBinding;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
        return service.getUserByEmail(ar.getUsername()).map((userDetails) -> {
            Boolean passwordsEqual = new BCryptPasswordEncoder().matches(ar.getPassword(),userDetails.getPassword());
            if(passwordsEqual){
                return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    //register
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user){
        String encodedPassword= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        if(user.getRole()==null){
            user.setRole(Role.ROLE_USER);
        }else{
            user.setRole(Role.ROLE_ADMIN);
        }
        service.saveUser(user);
    }
}
