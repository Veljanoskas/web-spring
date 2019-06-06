package mk.ukim.finki.webspring.web;

import jdk.nashorn.internal.runtime.logging.Logger;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user){
        service.saveUser(user);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<User>> getUser(@PathVariable String id){
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public Mono<ResponseEntity<User>> getUserByEmail(@PathVariable String email){
        return service.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/delete/{email}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("email") String email) {
        service.deleteUser(email).subscribe();
    }
}
