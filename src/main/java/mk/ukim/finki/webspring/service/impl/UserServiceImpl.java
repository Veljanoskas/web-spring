package mk.ukim.finki.webspring.service.impl;

import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.repository.UserRepository;
import mk.ukim.finki.webspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Flux<User> getAllUsers() {
        return null;
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
    public Mono<User> updateUser(String email, String password, String name, String surname) {
        Mono<User> existingUser=userRepository.findUserByEmail(email);
        return existingUser.flatMap(user->{
            if(password!=null){
                user.setPassword(password);
            }
            if(name!=null)
            {
                user.setName(name);
            }
            if(surname!=null){
                user.setSurname(surname);
            }
           return userRepository.save(user);
        });
    }

    @Override
    public Mono<Void> deleteUser(String email) {
        return userRepository.deleteByEmail(email);
    }

    @Override
    public Flux<LoyalCard> getUserCards(String email) {
        return null;
    }
}
