package musicstore.musicselling.Entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long albumId;
    private String albumName;
    private String albumImage;
    private float albumPrice;

    // create many to one relationship with artist(one artist can have many albums)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    // create one to many relationship with songs(one album can have many songs)
    @OneToMany(mappedBy = "album", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @OrderColumn(name = "song_order")
    private List<Song> songs = new ArrayList<>();

    // Empty constructor
    public Album() {

    }

    // Constructor
    public Album(String albumName, String albumImage, float albumPrice) {
        this.albumName = albumName;
        this.albumImage = albumImage;
        this.albumPrice = albumPrice;
    }

    // Returns the name of the album
    public String getAlbumName() {
        return albumName;
    }

    // Sets the name of the album
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    // Retrieves the image associated with the album
    public String getAlbumImage() {
        return albumImage;
    }

    // Sets the image for the album
    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    // Gets the price of the album
    public float getAlbumPrice() {
        return albumPrice;
    }

    // Sets the price of the album
    public void setAlbumPrice(float albumPrice) {
        this.albumPrice = albumPrice;
    }

    // Retrieves the list of songs in the album
    public List<Song> getSongs() {
        return songs;
    }

    // Sets the list of songs for the album
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    // Adds a song to the album's song list
    public void addSong(Song song) {
        // Sets the order of the song based on current list size
        song.setSong_order(this.songs.size());
        // Adds the song to the list
        this.songs.add(song);
        // Associates the song with this album
        song.setAlbum(this);
    }

    // Retrieves the unique ID of the album
    public long getAlbumId() {
        return albumId;
    }

    // Sets the unique ID for the album
    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    // Gets the artist associated with the album
    public Artist getArtist() {
        return artist;
    }

    // Sets the artist for the album
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    // Retrieves the name of the artist
    public String getArtistName() {
        return this.artist.getArtistName();
    }

    // Formats the album price for display (e.g., "€9.99")
    public String formatPrice(float albumPrice) {
        return "€" + albumPrice;
    }

    // Returns the number of songs in the album
    public int albumQuantity() {
        return this.songs.size();
    }

}