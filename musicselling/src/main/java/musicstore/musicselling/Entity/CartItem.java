package musicstore.musicselling.Entity;

import jakarta.annotation.Generated;
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

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "songId")
    private Song song;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

    public CartItem() {
    }

    public Long getItemId() {
        return itemId;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getCartItemImage() {
        if (this.getSong() != null) {
            return this.getSong().getSongImage();
        } else if (this.getAlbum() != null) {
            return this.getAlbum().getAlbumImage();
        }
        return null;
    }

    public String getCartItemName() {
        if (this.getSong() != null) {
            return this.getSong().getSongName();
        } else if (this.getAlbum() != null) {
            return this.getAlbum().getAlbumName();
        }
        return null;
    }

    public float getCartItemPrice() {
        if (this.getSong() != null) {
            return this.getSong().getSongPrice();
        } else if (this.getAlbum() != null) {
            return this.getAlbum().getAlbumPrice();
        }
        return 0;
    }

    public Long getCartItemId() {
        if (this.getSong() != null) {
            return this.getSong().getSongId();
        } else if (this.getAlbum() != null) {
            return this.getAlbum().getAlbumId();
        }
        return null;
    }
}
