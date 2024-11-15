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

    @OneToMany(mappedBy = "artist", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Album> albums = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "artist_song", joinColumns = {
            @JoinColumn(name = "artist_id", referencedColumnName = "artistId") }, inverseJoinColumns = {
                    @JoinColumn(name = "song_id", referencedColumnName = "songId") })
    private Set<Song> songs = new HashSet<>();

    @ManyToMany(mappedBy = "featuredArtist")
    private Set<Song> featuredInSongs = new HashSet<>();

    public Set<Song> getFeaturedInSongs() {
        return featuredInSongs;
    }

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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;

    }

    public void addSong(Song song) {
        this.songs.add(song);
        song.getArtists().add(this);
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
        album.setArtist(this);
    }

    public void setFeaturedInSongs(Set<Song> featuredInSongs) {
        this.featuredInSongs = featuredInSongs;
    }

}
