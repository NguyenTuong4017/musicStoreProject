package musicstore.musicselling.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import musicstore.musicselling.Entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByGenreId(Long id);
}
