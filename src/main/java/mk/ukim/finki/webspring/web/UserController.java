package mk.ukim.finki.webspring.web;

import jdk.nashorn.internal.runtime.logging.Logger;
import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.model.UserDTO;
import mk.ukim.finki.webspring.model.Vendor;
import mk.ukim.finki.webspring.service.UserService;
import mk.ukim.finki.webspring.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Console;
import java.security.Principal;
import java.util.Collections;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private VendorService vendorService;


    @RequestMapping(value="/cards",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public Flux<LoyalCard> findUserCards(@RequestHeader(value = "Username")String username) {

        Flux<LoyalCard> cards = service.getUserCards(username);

        return cards;
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
    @PreAuthorize("hasRole('USER')")
    public Mono<User> update(@PathVariable String email, @RequestBody UserDTO userDTO) {
        return service.updateUser(email,userDTO);
    }

    @RequestMapping(value="/cards",method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public Mono<User> addNewLoyalCard(@RequestHeader(value = "Username")String username,@RequestBody LoyalCard loyalCard) {
        return service.addNewLoyalCard(username,loyalCard);
    }

    @RequestMapping(value = "/cards/{barcode}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> deleteCard(@RequestHeader(value = "Username")String username,@PathVariable("barcode") String barcode) {
        return service.deleteUserCard(username,barcode);
    }

    @GetMapping("/vendors")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public Flux<Vendor> findAllVendors() {
        Flux<Vendor> vendors = vendorService.getAllVendors();
        return vendors;
    }

}
