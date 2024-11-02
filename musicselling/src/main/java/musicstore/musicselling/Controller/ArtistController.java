package musicstore.musicselling.Controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Entity.Song;
import musicstore.musicselling.Repository.ArtistRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/artist/{id}")
    public String findArtistName(@PathVariable Long id, Model model) {
        Optional<Artist> artist = artistRepository.findById(id);
        model.addAttribute("artist", artist.get());
        return "artist";
    }

    @GetMapping("/artist/{id}/songs")
    public String findSongByArtistId(@PathVariable Long id, Model model) {
        Optional<Artist> ref = artistRepository.findById(id);
        Artist artist = ref.get();
        Set<Song> songListByArtist = artist.getSongs();
        model.addAttribute("songListByArtist", songListByArtist);
        return "artist-song";
    }

}
