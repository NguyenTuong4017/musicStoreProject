package musicstore.musicselling.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // Unique ID for the user

    private String username; // Username of the user
    private String password; // Password of the user
    private String role; // Role of the user (e.g., "admin", "user")

    // One-to-One relationship with Cart (a user can have one cart)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    // Default constructor required by JPA
    public UserEntity() {

    }

    // Constructor to initialize user details
    public UserEntity(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Retrieves the unique ID of the user
    public Long getUserId() {
        return userId;
    }

    // Retrieves the username of the user
    public String getUsername() {
        return username;
    }

    // Sets or updates the username of the user
    public void setUsername(String username) {
        this.username = username;
    }

    // Retrieves the password of the user
    public String getPassword() {
        return password;
    }

    // Sets or updates the password of the user
    public void setPassword(String password) {
        this.password = password;
    }

    // Retrieves the role of the user
    public String getRole() {
        return role;
    }

    // Sets or updates the role of the user
    public void setRole(String role) {
        this.role = role;
    }

    // Retrieves the cart associated with the user
    public Cart getCart() {
        return cart;
    }

    // Associates a cart with the user
    public void setCart(Cart cart) {
        this.cart = cart;
    }
}