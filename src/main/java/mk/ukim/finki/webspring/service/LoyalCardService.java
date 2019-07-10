package mk.ukim.finki.webspring.service;

import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.Vendor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoyalCardService {

    Flux<LoyalCard> getAllLoyalCards();

    Mono<LoyalCard> getLoyalCardById(String id);

    void saveLoyalCard(LoyalCard loyalCard);

    Mono<Void> deleteLoyalCard(String id);
}
