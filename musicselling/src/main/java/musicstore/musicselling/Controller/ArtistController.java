package musicstore.musicselling.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import musicstore.musicselling.Entity.Album;
import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Entity.Genre;
import musicstore.musicselling.Entity.Song;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.GenreRepository;
import musicstore.musicselling.Repository.SongRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/artist")
    public String showAllArtist(Model model) {
        List<Artist> artistList = artistRepository.findAll();
        List<Genre> genreList = genreRepository.findAll();
        model.addAttribute("artistList", artistList);
        model.addAttribute("genreList", genreList);
        return "artist-page";
    }

    @GetMapping("/artist/songs")
    public String showAllSongByArtist(Model model, Long artistId) {
        Artist artist = artistRepository.findByArtistId(artistId);
        Set<Song> songList = artist.getSongs();

        List<Genre> genreList = genreRepository.findAll();

        model.addAttribute("artist", artist);
        model.addAttribute("genreList", genreList);
        model.addAttribute("songList", songList);

        return "song-of-artist";
    }

}
