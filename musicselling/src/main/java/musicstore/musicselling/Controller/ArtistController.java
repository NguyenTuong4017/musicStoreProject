package musicstore.musicselling.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import musicstore.musicselling.Entity.Album;
import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Entity.Cart;
import musicstore.musicselling.Entity.CartItem;
import musicstore.musicselling.Entity.Genre;
import musicstore.musicselling.Entity.Song;
import musicstore.musicselling.Entity.UserEntity;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.CartRepository;
import musicstore.musicselling.Repository.GenreRepository;
import musicstore.musicselling.Repository.SongRepository;
import musicstore.musicselling.Repository.UserRepository;
import musicstore.musicselling.Service.CustomUserDetail;

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

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    private Cart getOrCreateCart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        Long userId = userDetail.getId();

        Cart cart = cartRepository.findCartByUserUserId(userId);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(userRepository.findByUserId(userId));
            cartRepository.save(cart);
        }

        return cart;
    }

    @GetMapping("/artist")
    public String showAllArtist(Model model) {
        List<Artist> artistList = artistRepository.findAll();
        List<Genre> genreList = genreRepository.findAll();
        List<Long> itemId = new ArrayList<>();
        boolean isLoggedIn = false;
        Cart cart = getOrCreateCart();

        if (cart != null) {
            isLoggedIn = true;
            UserEntity user = userRepository.findByUserId(cart.getUser().getUserId());
            model.addAttribute("user", user);

            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getSong() != null) {
                    itemId.add(cartItem.getSong().getSongId());
                }

            }
            model.addAttribute("itemId", itemId);
        }
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("artistList", artistList);
        model.addAttribute("genreList", genreList);
        return "artist-page";
    }

    @GetMapping("/artist/songs")
    public String showAllSongByArtist(Model model, Long artistId) {
        Artist artist = artistRepository.findByArtistId(artistId);
        Set<Song> songList = artist.getSongs();

        List<Genre> genreList = genreRepository.findAll();

        List<Long> itemId = new ArrayList<>();
        boolean isLoggedIn = false;
        Cart cart = getOrCreateCart();

        if (cart != null) {
            isLoggedIn = true;
            UserEntity user = userRepository.findByUserId(cart.getUser().getUserId());
            model.addAttribute("user", user);

            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getSong() != null) {
                    itemId.add(cartItem.getSong().getSongId());
                }

            }
            model.addAttribute("itemId", itemId);
        }
        model.addAttribute("isLoggedIn", isLoggedIn);

        model.addAttribute("artist", artist);
        model.addAttribute("genreList", genreList);
        model.addAttribute("songList", songList);

        return "song-of-artist";
    }

}
