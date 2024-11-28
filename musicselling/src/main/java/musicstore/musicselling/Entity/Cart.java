package musicstore.musicselling.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    // One-to-Many relationship with CartItem (a cart can have multiple cart items)
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    // One-to-One relationship with UserEntity (each cart is associated with a
    // single user)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity user;

    // Retrieves the unique ID of the cart
    public Long getCartId() {
        return cartId;
    }

    // Retrieves the set of cart items associated with the cart
    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    // Sets the set of cart items for the cart
    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    // Adds a cart item to the cart
    public void addCartItems(CartItem item) {
        // Adds the item to the set of cart items
        cartItems.add(item);
        // Sets this cart as the parent for the cart item
        item.setCart(this);
    }

    // Removes a cart item from the cart
    public void removeCartItems(CartItem item) {
        // Removes the item from the set of cart items
        cartItems.remove(item);
        // Disassociates the item from this cart
        item.setCart(null);
    }

    // Retrieves the user associated with the cart
    public UserEntity getUser() {
        return user;
    }

    // Sets the user for the cart
    public void setUser(UserEntity user) {
        // Links the user to this cart
        this.user = user;
        // Sets this cart as the cart for the user
        user.setCart(this);
    }
}