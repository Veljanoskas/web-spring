package mk.ukim.finki.webspring.web;

import mk.ukim.finki.webspring.model.Role;
import mk.ukim.finki.webspring.model.User;
import mk.ukim.finki.webspring.model.Vendor;
import mk.ukim.finki.webspring.service.UserService;
import mk.ukim.finki.webspring.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    private UserService userService;
    private VendorService vendorService;

    public AdminController(UserService us, VendorService vs)
    {
        userService=us;
        vendorService=vs;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<String> greetAdmin(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Admin access: %s", name));
    }

    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public Flux<User> findAllUsers() {
        Flux<User> users = userService.getAllUsers().filter(user -> user.getRole()!= Role.ROLE_ADMIN);
        return users;
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(id);
    }


    @GetMapping("/vendors")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public Flux<Vendor> findAllVendors() {
        Flux<Vendor> vendors = vendorService.getAllVendors();
        return vendors;
    }

    @PostMapping("/addVendor")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Vendor> addVendor(@RequestBody Vendor vendor){
        return vendorService.saveVendor(vendor);

    }

    @GetMapping("/vendor/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<Vendor>> getVendor(@PathVariable String id){
        return vendorService.getVendorById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/deleteVendor/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return vendorService.deleteVendor(id);
    }
}
