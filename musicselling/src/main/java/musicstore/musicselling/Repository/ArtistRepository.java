package musicstore.musicselling.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Entity.Song;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByArtistName(String artistName);

    Artist findByArtistNameIgnoreCase(String artistName);

}
