package mk.ukim.finki.webspring.repository;

import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findUserByEmail(String email);
    Mono<Void> deleteByEmail(String email);

}
