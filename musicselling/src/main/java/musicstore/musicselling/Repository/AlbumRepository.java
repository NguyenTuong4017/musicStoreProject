package musicstore.musicselling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import musicstore.musicselling.Entity.Album;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByAlbumId(long albumId);
}
