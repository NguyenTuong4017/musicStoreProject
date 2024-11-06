package musicstore.musicselling.Controller;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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

    public static String covertToString(String value) {
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/song-list")
    public String showAllSong(Model model) {
        List<Song> songList = songRepository.findAll();
        model.addAttribute("songList", songList);
        return "song-list";

    }

    @PostMapping("/find")
    public String searchResult(Model model, String searchString) {
        List<Song> songList = songRepository.findByArtists_ArtistNameContainingIgnoreCase(searchString);
        if (!songList.isEmpty()) {
            model.addAttribute("songList", songList);
            return "search-result";
        }
        List<Song> songList2 = songRepository.findBySongNameContaining(searchString);
        if (!songList2.isEmpty()) {
            model.addAttribute("songList", songList2);
            return "search-result";
        }
        model.addAttribute("message", "No result.");
        return "error";

    }

}
