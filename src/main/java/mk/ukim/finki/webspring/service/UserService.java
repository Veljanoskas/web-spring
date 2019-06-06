package mk.ukim.finki.webspring.service;

import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {

    Flux<User> getAllUsers();

    Mono<User> getUserByEmail(String email);

    Mono<User> getUserById(String id);

    void saveUser(User user);

    Mono<User> updateUser(String email, String password, String name, String surname);

    Mono<Void> deleteUser(String email);

    Flux<LoyalCard> getUserCards(String email);



}
