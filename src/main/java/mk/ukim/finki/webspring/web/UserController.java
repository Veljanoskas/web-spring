package mk.ukim.finki.webspring.web;

import jdk.nashorn.internal.runtime.logging.Logger;
import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.model.UserDTO;
import mk.ukim.finki.webspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Flux<User> findAll() {
        Flux<User> users = service.getAllUsers();
        return users;
    }

    @RequestMapping(value="/cards",method = RequestMethod.GET)
    @ResponseBody
    public Flux<LoyalCard> findUserCards() {
        Flux<LoyalCard> cards = service.getUserCards("gordesssv@yahoo.com");
        return cards;
    }

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

    @PutMapping("{email}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> update(@PathVariable String email, @RequestBody UserDTO userDTO) {
        return service.updateUser(email,userDTO);
    }

    @RequestMapping(value="/cards",method = RequestMethod.POST)
    public Mono<User> addNewLoyalCard(@RequestBody LoyalCard loyalCard) {
        return service.addNewLoyalCard("gordesssv@yahoo.com",loyalCard);
    }

    @RequestMapping(value = "/cards/{barcode}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> deleteCard(@PathVariable("barcode") String barcode) {
        return service.deleteUserCard("gordesssv@yahoo.com",barcode);
    }
}
