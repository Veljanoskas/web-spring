package mk.ukim.finki.webspring.service;

import mk.ukim.finki.webspring.model.LoyalCard;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.model.UserDTO;
import mk.ukim.finki.webspring.model.Vendor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VendorService {

    Flux<Vendor> getAllVendors();

    Mono<Vendor> getVendorById(String id);

    Mono<Vendor> saveVendor(Vendor vendor);

    Mono<Void> deleteVendor(String id);

}
