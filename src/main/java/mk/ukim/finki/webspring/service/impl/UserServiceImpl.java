package mk.ukim.finki.webspring.service.impl;

import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.model.UserDTO;
import mk.ukim.finki.webspring.model.exceptions.UserNotFoundException;
import mk.ukim.finki.webspring.repository.UserRepository;
import mk.ukim.finki.webspring.repository.VendorRepository;
import mk.ukim.finki.webspring.service.LoyalCardService;
import mk.ukim.finki.webspring.service.UserService;
import mk.ukim.finki.webspring.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoyalCardService loyalCardService;

    @Autowired private VendorService vs;

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void saveUser(User user) {
         userRepository.save(user).subscribe();
    }

    @Override
    public Mono<User> updateUser(String email, UserDTO userDTO){
        Mono<User> user = this.userRepository.findUserByEmail(email);
        return user.flatMap(value -> {
            if(!userDTO.name.isEmpty()) {
                value.setName(userDTO.name);
            }
            if(!userDTO.surname.isEmpty()) {
                value.setSurname(userDTO.surname);
            }
            if(!userDTO.password.isEmpty()){
                value.setPassword(new BCryptPasswordEncoder().encode(userDTO.password));
            }
            return Mono.just(value); // Can this be done cleaner?
        }).flatMap(this.userRepository::save);
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Flux<LoyalCard> getUserCards(String email) {
        Mono<User> user=userRepository.findUserByEmail(email);
        return user.map(u->u.getLoyalCardList().stream()).flatMapMany(Flux::fromStream);

    }

    @Override
    public Mono<User> addNewLoyalCard(String email, LoyalCard loyalCard) {

        Mono<User> user = this.userRepository.findUserByEmail(email);
        this.loyalCardService.saveLoyalCard(loyalCard);
        return user.flatMap(value -> {
            value.getLoyalCardList().add(loyalCard);
            return Mono.just(value); // Can this be done cleaner?
        }).flatMap(this.userRepository::save);
    }

    @Override
    public Mono<User> deleteUserCard(String email,String barcode) {
        Mono<User> user = this.userRepository.findUserByEmail(email);
        this.loyalCardService.deleteLoyalCard(barcode);
          return user.flatMap(value -> {
            value.getLoyalCardList().removeIf(loyalCard -> loyalCard.barcode.equals(barcode));
            return Mono.just(value); // Can this be done cleaner?
        }).flatMap(this.userRepository::save);
    }
}
