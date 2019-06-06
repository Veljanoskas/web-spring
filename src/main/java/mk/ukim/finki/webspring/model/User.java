package mk.ukim.finki.webspring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Flux;

import java.util.List;

@Document
public class User {
    @Id
    private String id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private Role role;
    private List<LoyalCard> loyalCardList;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<LoyalCard> getLoyalCardList() {
        return loyalCardList;
    }

    public void setLoyalCardList(List<LoyalCard> loyalCardList) {
        this.loyalCardList = loyalCardList;
    }
}
