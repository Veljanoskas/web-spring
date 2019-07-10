package mk.ukim.finki.webspring.service;

import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.model.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {

    Flux<User> getAllUsers();

    Mono<User> getUserByEmail(String email);

    Mono<User> getUserById(String id);

    void saveUser(User user);

    Mono<User> updateUser(String email, UserDTO userDTO);/*String email, String password, String name, String surname);*/

    Mono<Void> deleteUser(String id);

    Flux<LoyalCard> getUserCards(String email);

    Mono<User> addNewLoyalCard(String email, LoyalCard loyalCard);

   Mono<User> deleteUserCard(String email,String barcode);

}
