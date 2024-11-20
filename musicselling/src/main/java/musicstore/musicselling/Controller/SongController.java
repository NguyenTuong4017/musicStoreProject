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
import musicstore.musicselling.Entity.Album;
import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Entity.Genre;
import musicstore.musicselling.Repository.AlbumRepository;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.GenreRepository;
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

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

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
        List<Genre> genreList = genreRepository.findAll();
        List<Artist> artistList = artistRepository.findAll();
        model.addAttribute("artistList", artistList);
        model.addAttribute("songList", songList);
        model.addAttribute("genreList", genreList);
        return "song-list";

    }

    @PostMapping("/find")
    public String searchResult(Model model, String searchString) {
        List<Song> songList = songRepository.findByArtists_ArtistNameContainingIgnoreCase(searchString);
        List<Genre> genreList = genreRepository.findAll();
        if (!songList.isEmpty()) {
            model.addAttribute("songList", songList);
            model.addAttribute("genreList", genreList);
            return "search-result";
        }
        List<Song> songList2 = songRepository.findBySongNameContaining(searchString);
        if (!songList2.isEmpty()) {
            model.addAttribute("songList", songList2);
            model.addAttribute("genreList", genreList);
            return "search-result";
        }
        model.addAttribute("genreList", genreList);
        model.addAttribute("message", "No result.");
        return "error";

    }

    @GetMapping("/song-genre")
    public String songOfGenre(Model model, Long genreId) {
        List<Genre> genreList = genreRepository.findAll();
        Genre genre = genreRepository.findByGenreId(genreId);
        Set<Song> songList = genre.getSongs();
        System.out.println("Genre: " + genre);
        System.out.print("Song List: ");
        for (Song song : songList) {
            System.out.print(song.getSongName() + ", ");
        }
        model.addAttribute("songList", songList);
        model.addAttribute("genreList", genreList);
        return "song-of-genre";
    }

    @GetMapping("/album")
    public String showAllAlbum(Model model) {
        List<Album> albumList = albumRepository.findAll();
        List<Genre> genreList = genreRepository.findAll();
        model.addAttribute("albumList", albumList);
        model.addAttribute("genreList", genreList);
        return "album";
    }

    @GetMapping("/album/songs")
    public String showAllSongsOfAlbum(Model model, Long albumId) {
        Album album = albumRepository.findByAlbumId(albumId);
        List<Song> songList = album.getSongs();
        List<Genre> genreList = genreRepository.findAll();

        model.addAttribute("album", album);
        model.addAttribute("songList", songList);
        model.addAttribute("genreList", genreList);

        return "song-of-album";
    }

    @GetMapping("/song-page")
    public String songPage(Model model, Long songId) {
        List<Genre> genreList = genreRepository.findAll();

        Song song = songRepository.findBySongId(songId);

        model.addAttribute("song", song);
        model.addAttribute("genreList", genreList);

        return "song-page";
    }

}