package musicstore.musicselling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.transaction.Transactional;
import musicstore.musicselling.Entity.Artist;
import musicstore.musicselling.Entity.Song;
import musicstore.musicselling.Repository.ArtistRepository;
import musicstore.musicselling.Repository.SongRepository;

@SpringBootApplication
public class MusicsellingApplication {
	@Autowired
	private SongRepository songRepository;

	@Autowired
	private ArtistRepository artistRepository;

	public static void main(String[] args) {
		SpringApplication.run(MusicsellingApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner commandLineRunner() {
		return args -> {
			// create artists
			Artist artist1 = new Artist("Sơn Tùng M-TP", "Vietnamese singer-songwriter and actor.");
			Artist artist2 = new Artist("tlinh", "tlinh is a Vietnamese independent singer and rapper.");
			Artist artist3 = new Artist("RPT MCK", "RPT MCK is a Vietnamese singer and rapper based in Ha Noi.");

			// create songs
			Song song1 = new Song("nếu lúc đó", 3794587,
					"/static/song-image/neu-luc-do.jpg");
			Song song2 = new Song("Chạy Ngay Đi", 234879825,
					"/static/song-image/chay-ngay-di.jpg");
			Song song3 = new Song("Hãy Trao Cho Anh", 2387532,
					"/static/song-image/hay-trao-cho-anh.jpg");
			Song song4 = new Song("Chỉ Một Đêm Nữa Thôi (feat. tlinh)", 782643,
					"/static/song-image/99-phan-tram.jpg");
			Song song5 = new Song("Em Là Châu Báu", 298374,
					"/static/song-image/em-la-chau-bau.jpg");
			/*
			 * // save songs
			 * songRepository.save(song1);
			 * songRepository.save(song2);
			 * songRepository.save(song3);
			 * songRepository.save(song4);
			 * songRepository.save(song5);
			 */
			artist1.addSong(song2);
			artist1.addSong(song3);

			artist2.addSong(song1);
			artist2.addSong(song4);
			artist2.addSong(song5);

			artist3.addSong(song4);
			artist3.addSong(song5);
			/*
			 * // Save artists, which will cascade and save songs as well
			 * artistRepository.save(artist1);
			 * artistRepository.save(artist2);
			 * artistRepository.save(artist3);
			 */
		};
	}
}
