package musicstore.musicselling.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import musicstore.musicselling.Entity.Song;
import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.SongRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SongController {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/song-list")
    public String showAllSong(Model model) {
        List<Song> songList = songRepository.findAll();
        model.addAttribute("songList", songList);
        return "song-list";

    }

    @PostMapping("/song-by-artist")
    public String findSongByArtistName(Model model, String name) {
        Artist artist = artistRepository.findByArtistName(name);
        Set<Song> songList = artist.getSongs();
        model.addAttribute("songList", songList);
        return "/song-of-artist";

    }

}
