package musicstore.musicselling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import musicstore.musicselling.Entity.Song;
import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByArtists_ArtistNameContainingIgnoreCase(String searchName);

    List<Song> findBySongNameContaining(String searchName);
}
