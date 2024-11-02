package musicstore.musicselling.Entity;

import java.util.Set;
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
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;
    private String artistName;
    private String artistBio;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "artist_song", joinColumns = {
            @JoinColumn(name = "artist_id", referencedColumnName = "artistId") }, inverseJoinColumns = {
                    @JoinColumn(name = "song_id", referencedColumnName = "songId") })
    private Set<Song> songs = new HashSet<>();

    public Artist() {

    }

    public Artist(String artistName, String artistBio) {
        this.artistName = artistName;
        this.artistBio = artistBio;
    }

    public Long getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistBio() {
        return artistBio;
    }

    public void setArtistBio(String artistBio) {
        this.artistBio = artistBio;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;

    }

    public void addSong(Song song) {
        this.songs.add(song);
        song.getArtists().add(this);
    }

}
