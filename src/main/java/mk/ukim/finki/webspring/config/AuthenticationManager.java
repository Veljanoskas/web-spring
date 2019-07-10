package mk.ukim.finki.webspring.config;

import io.jsonwebtoken.Claims;
import mk.ukim.finki.webspring.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        String username;
        try {
            username = jwtUtil.getUsernameFromToken(authToken);
        } catch (Exception e) {
            username = null;
        }
        if (username != null && jwtUtil.validateToken(authToken)) {
            Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
            String roleName = claims.get("role", String.class);
            Set<GrantedAuthority> authoritySet = new HashSet<>();
            authoritySet.add(new SimpleGrantedAuthority(roleName));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authoritySet
            );
            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}
