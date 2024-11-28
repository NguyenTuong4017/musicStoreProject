package musicstore.musicselling.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    // Many-to-One relationship with Cart (a cart can have multiple cart items)
    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    // Many-to-One relationship with Song (a cart item can reference a single song)
    @ManyToOne
    @JoinColumn(name = "songId")
    private Song song;

    // Many-to-One relationship with Album (a cart item can reference a single
    // album)
    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

    // Default constructor required by JPA
    public CartItem() {
    }

    // Retrieves the unique ID of the cart item
    public Long getItemId() {
        return itemId;
    }

    // Retrieves the song associated with this cart item
    public Song getSong() {
        return song;
    }

    // Sets the song for this cart item
    public void setSong(Song song) {
        this.song = song;
    }

    // Retrieves the album associated with this cart item
    public Album getAlbum() {
        return album;
    }

    // Sets the album for this cart item
    public void setAlbum(Album album) {
        this.album = album;
    }

    // Retrieves the cart this cart item belongs to
    public Cart getCart() {
        return cart;
    }

    // Sets the cart for this cart item
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // Retrieves the image for the cart item (either song image or album image)
    public String getCartItemImage() {
        if (this.getSong() != null) {
            // If the cart item references a song, return the song's image
            return this.getSong().getSongImage();
        } else if (this.getAlbum() != null) {
            // If the cart item references an album, return the album's image
            return this.getAlbum().getAlbumImage();
        }
        // Return null if neither song nor album is associated
        return null;
    }

    // Retrieves the name of the cart item (either song name or album name)
    public String getCartItemName() {
        if (this.getSong() != null) {
            // If the cart item references a song, return the song's name
            return this.getSong().getSongName();
        } else if (this.getAlbum() != null) {
            // If the cart item references an album, return the album's name
            return this.getAlbum().getAlbumName();
        }
        // Return null if neither song nor album is associated
        return null;
    }

    // Retrieves the price of the cart item (either song price or album price)
    public float getCartItemPrice() {
        if (this.getSong() != null) {
            // If the cart item references a song, return the song's price
            return this.getSong().getSongPrice();
        } else if (this.getAlbum() != null) {
            // If the cart item references an album, return the album's price
            return this.getAlbum().getAlbumPrice();
        }
        // Return 0 if neither song nor album is associated
        return 0;
    }

    // Retrieves the unique ID of the cart item content (either song ID or album ID)
    public Long getCartItemId() {
        if (this.getSong() != null) {
            // If the cart item references a song, return the song's ID
            return this.getSong().getSongId();
        } else if (this.getAlbum() != null) {
            // If the cart item references an album, return the album's ID
            return this.getAlbum().getAlbumId();
        }
        // Return null if neither song nor album is associated
        return null;
    }
}