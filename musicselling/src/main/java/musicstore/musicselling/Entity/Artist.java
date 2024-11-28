package musicstore.musicselling.Entity;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;
    private String artistName;
    private String artistBio;
    private String artistImage;

    // One-to-Many relationship with Album (an artist can have multiple albums)
    @OneToMany(mappedBy = "artist", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Album> albums = new ArrayList<>();

    // Many-to-Many relationship with Song (an artist can have multiple songs and
    // vice versa)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "artist_song", joinColumns = {
            @JoinColumn(name = "artist_id", referencedColumnName = "artistId") }, inverseJoinColumns = {
                    @JoinColumn(name = "song_id", referencedColumnName = "songId") })
    private Set<Song> songs = new HashSet<>();

    // Default constructor
    public Artist() {

    }

    // Constructor with parameters
    public Artist(String artistName, String artistBio, String artistImage) {
        this.artistName = artistName;
        this.artistBio = artistBio;
        this.artistImage = artistImage;
    }

    // Retrieves the unique ID of the artist
    public Long getArtistId() {
        return artistId;
    }

    // Gets the name of the artist
    public String getArtistName() {
        return artistName;
    }

    // Sets the name of the artist
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    // Gets the biography of the artist
    public String getArtistBio() {
        return artistBio;
    }

    // Sets the biography of the artist
    public void setArtistBio(String artistBio) {
        this.artistBio = artistBio;
    }

    // Gets the image associated with the artist
    public String getArtistImage() {
        return artistImage;
    }

    // Sets the image for the artist
    public void setArtistImage(String artistImage) {
        this.artistImage = artistImage;
    }

    // Retrieves the set of songs associated with the artist
    public Set<Song> getSongs() {
        return songs;
    }

    // Sets the set of songs for the artist
    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    // Retrieves the list of albums associated with the artist
    public List<Album> getAlbums() {
        return albums;
    }

    // Sets the list of albums for the artist
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    // Adds a song to the artist's song set
    public void addSong(Song song) {
        // Adds the song to the artist's set of songs
        this.songs.add(song);
        // Adds this artist to the song's set of artists
        song.getArtists().add(this);
    }

    // Adds an album to the artist's album list
    public void addAlbum(Album album) {
        // Adds the album to the artist's list of albums
        this.albums.add(album);
        // Sets this artist as the artist of the album
        album.setArtist(this);
    }
}