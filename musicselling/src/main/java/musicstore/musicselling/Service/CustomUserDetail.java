package musicstore.musicselling.Service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import musicstore.musicselling.Entity.UserEntity;

public class CustomUserDetail implements UserDetails {

    private Long id; // Unique ID of the user
    private String username; // Username for authentication
    private String password; // Password for authentication
    private String role; // Role of the user (e.g., "ROLE_USER", "ROLE_ADMIN")

    // Constructor that initializes the custom user details with a UserEntity.
    public CustomUserDetail(UserEntity user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    // Returns the authorities granted to the user.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Returns a list containing the user's role as a GrantedAuthority
        return List.of(() -> role);
    }

    // Retrieves the unique ID of the user.
    public Long getId() {
        return id;
    }

    // Retrieves the password used to authenticate the user.

    @Override
    public String getPassword() {
        return password;
    }

    // Retrieves the username used to authenticate the user.
    @Override
    public String getUsername() {
        return username;
    }

}