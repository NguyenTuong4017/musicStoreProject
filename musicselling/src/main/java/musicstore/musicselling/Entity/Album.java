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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "album", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @OrderColumn(name = "song_order")
    private List<Song> songs = new ArrayList<>();

    public Album() {

    }

    public Album(String albumName, String albumImage, float albumPrice) {
        this.albumName = albumName;
        this.albumImage = albumImage;
        this.albumPrice = albumPrice;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public float getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(float albumPrice) {
        this.albumPrice = albumPrice;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        song.setSong_order(this.songs.size());
        this.songs.add(song);
        song.setAlbum(this);
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getArtistName() {
        return this.artist.getArtistName();
    }

    public String formatPrice(float albumPrice) {
        return "€" + albumPrice;
    }

    public int albumQuantity() {
        return this.songs.size();
    }

}