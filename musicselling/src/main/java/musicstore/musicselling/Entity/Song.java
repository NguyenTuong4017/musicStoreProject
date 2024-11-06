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
import jakarta.persistence.Table;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;
    private String songName;
    private long songStream;
    private String songImage;

    @ManyToMany(mappedBy = "songs", fetch = FetchType.LAZY)
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "song_genre", joinColumns = {
            @JoinColumn(name = "song_id", referencedColumnName = "songId") }, inverseJoinColumns = {
                    @JoinColumn(name = "genre_id", referencedColumnName = "genreId") })
    private Set<Genre> genres = new HashSet<>();

    public Song() {

    }

    public Song(String songName, long songStream, String songImage) {
        this.songName = songName;
        this.songStream = songStream;

        this.songImage = songImage;
    }

    public Long getSongId() {
        return songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public long getSongStream() {
        return songStream;
    }

    public void setSongStream(long songStream) {
        this.songStream = songStream;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<Genre> getGenres() {
        return this.genres;
    }

    public void addGenres(Genre genre) {
        this.genres.add(genre);
        genre.getSongs().add(this);
    }

    public String getArtistName() {
        return artists.stream()
                .map(artist -> artist.getArtistName()) // Convert each Artist to their name
                .collect(Collectors.joining(", ")); // Join names with commas
    }

}
