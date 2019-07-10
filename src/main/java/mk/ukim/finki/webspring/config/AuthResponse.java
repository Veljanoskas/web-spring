package mk.ukim.finki.webspring.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {
    public String token;

    public AuthResponse(String token){
        this.token=token;
    }
}