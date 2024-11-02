package musicstore.musicselling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import musicstore.musicselling.Entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

}
