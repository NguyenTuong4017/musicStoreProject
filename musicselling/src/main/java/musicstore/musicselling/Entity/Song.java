package musicstore.musicselling.Entity;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    private String songName; // Name of the song
    private long songStream; // Number of streams for the song
    private String songImage; // Image for the song (URL or file path)
    private float songPrice; // Price of the song
    private int song_order; // Order of the song within an album

    // Many-to-Many relationship with Artist (many songs can have multiple artists)
    @ManyToMany(mappedBy = "songs", fetch = FetchType.LAZY)
    private Set<Artist> artists = new HashSet<>();

    // Many-to-Many relationship with Genre (a song can have multiple genres)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "song_genre", joinColumns = {
            @JoinColumn(name = "song_id", referencedColumnName = "songId") }, inverseJoinColumns = {
                    @JoinColumn(name = "genre_id", referencedColumnName = "genreId") })
    private Set<Genre> genres = new HashSet<>();

    // Many-to-One relationship with Album (many songs belong to one album)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    // Default constructor required by JPA
    public Song() {

    }

    // Constructor to initialize song details
    public Song(String songName, long songStream, String songImage, float songPrice) {
        this.songName = songName;
        this.songStream = songStream;
        this.songPrice = songPrice;
        this.songImage = songImage;
    }

    // Retrieves the unique ID of the song
    public Long getSongId() {
        return songId;
    }

    // Retrieves the name of the song
    public String getSongName() {
        return songName;
    }

    // Sets the name of the song
    public void setSongName(String songName) {
        this.songName = songName;
    }

    // Retrieves the number of streams for the song
    public long getSongStream() {
        return songStream;
    }

    // Sets the number of streams for the song
    public void setSongStream(long songStream) {
        this.songStream = songStream;
    }

    // Retrieves the set of artists associated with the song
    public Set<Artist> getArtists() {
        return artists;
    }

    // Sets the set of artists for the song
    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    // Retrieves the image of the song
    public String getSongImage() {
        return songImage;
    }

    // Sets the image for the song
    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    // Retrieves the set of genres associated with the song
    public Set<Genre> getGenres() {
        return this.genres;
    }

    // Adds a genre to the song and maintains bidirectional relationship
    public void addGenres(Genre genre) {
        this.genres.add(genre);
        genre.getSongs().add(this);
    }

    // Retrieves the price of the song
    public float getSongPrice() {
        return songPrice;
    }

    // Sets the price of the song
    public void setSongPrice(float songPrice) {
        this.songPrice = songPrice;
    }

    // Formats the song price for display (e.g., "€9.99")
    public String formatPrice(float songPrice) {
        return "€" + songPrice;
    }

    // Retrieves the album this song belongs to
    public Album getAlbum() {
        return album;
    }

    // Sets the album for this song
    public void setAlbum(Album album) {
        this.album = album;
    }

    // Retrieves the order of the song within the album
    public int getSong_order() {
        return song_order;
    }

    // Sets the order of the song within the album
    public void setSong_order(int song_order) {
        this.song_order = song_order;
    }

    // Retrieves the names of all artists associated with the song as a
    // comma-separated string
    public String getArtistName() {
        return artists.stream()
                .map(artist -> artist.getArtistName())
                .collect(Collectors.joining(", "));
    }

    // Sets the set of genres for the song
    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    // Retrieves the list of genres as a comma-separated string
    public String genreList() {
        return this.genres.stream()
                .map(genre -> genre.getGenreName())
                .collect(Collectors.joining(" ,"));
    }
}