package musicstore.musicselling.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long genreId;

    private String genreName;

    // Many-to-Many relationship with Song (a genre can have multiple songs)
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Song> songs = new HashSet<>();

    // Default constructor required by JPA
    public Genre() {

    }

    // Constructor with parameter to initialize genre name
    public Genre(String genreName) {
        this.genreName = genreName;
    }

    // Retrieves the name of the genre
    public String getGenreName() {
        return genreName;
    }

    // Sets the name of the genre
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    // Retrieves the set of songs associated with the genre
    public Set<Song> getSongs() {
        return this.songs;
    }

    // Retrieves the unique ID of the genre
    public long getGenreId() {
        return genreId;
    }
}