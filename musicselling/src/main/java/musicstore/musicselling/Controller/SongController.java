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
import musicstore.musicselling.Entity.Cart;
import musicstore.musicselling.Entity.CartItem;
import musicstore.musicselling.Entity.Genre;
import musicstore.musicselling.Repository.AlbumRepository;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.CartItemRepository;
import musicstore.musicselling.Repository.CartRepository;
import musicstore.musicselling.Repository.GenreRepository;
import musicstore.musicselling.Repository.SongRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

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

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private Cart getOrCreateCart() {
        if (cartRepository.findByCartId(1L) == null) {
            Cart cart = new Cart();
            return cartRepository.save(cart);
        }
        return cartRepository.findByCartId(1L);

    }

    @GetMapping("/song-list")
    public String showAllSong(Model model) {
        List<Song> songList = songRepository.findAll();
        List<Genre> genreList = genreRepository.findAll();
        List<Artist> artistList = artistRepository.findAll();
        List<Long> itemId = new ArrayList<>();
        Cart cart = getOrCreateCart();

        for (CartItem cartItem : cart.getCartItems()) {
            itemId.add(cartItem.getSong().getSongId());
        }

        model.addAttribute("itemId", itemId);
        model.addAttribute("artistList", artistList);
        model.addAttribute("songList", songList);
        model.addAttribute("genreList", genreList);
        return "song-list";

    }

    @GetMapping("/find")
    public String searchResult(@RequestParam(value = "searchString", required = false) String searchString, Model model,
            HttpServletRequest request) {
        List<Song> songList = songRepository.findByArtists_ArtistNameContainingIgnoreCase(searchString);
        List<Genre> genreList = genreRepository.findAll();
        Cart cart = getOrCreateCart();
        List<Long> itemId = new ArrayList<>();
        String pageDomain = request.getHeader("Referer");

        for (CartItem cartItem : cart.getCartItems()) {
            itemId.add(cartItem.getSong().getSongId());
        }

        if (!songList.isEmpty()) {
            System.out.println(pageDomain);
            model.addAttribute("itemId", itemId);
            model.addAttribute("songList", songList);
            model.addAttribute("genreList", genreList);
            return "search-result";
        }

        List<Song> songList2 = songRepository.findBySongNameContaining(searchString);
        if (!songList2.isEmpty()) {
            System.out.println(pageDomain);
            model.addAttribute("itemId", itemId);
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
        List<Long> itemId = new ArrayList<>();
        Cart cart = getOrCreateCart();

        for (CartItem cartItem : cart.getCartItems()) {
            itemId.add(cartItem.getSong().getSongId());
        }
        model.addAttribute("itemId", itemId);
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