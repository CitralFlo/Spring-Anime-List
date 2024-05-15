package dev.citralflo.animelist.data.loader;

import dev.citralflo.animelist.model.Character;
import dev.citralflo.animelist.model.Genre;
import dev.citralflo.animelist.model.Note;
import dev.citralflo.animelist.model.Rating;
import dev.citralflo.animelist.model.Series;
import dev.citralflo.animelist.model.VoiceActor;
import dev.citralflo.animelist.repositories.GenreRepository;
import dev.citralflo.animelist.repositories.SeriesRepository;
import dev.citralflo.animelist.repositories.VoiceActorRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final SeriesRepository seriesRepository;
    private final VoiceActorRepository voiceActorRepository;
    private final GenreRepository genreRepository;

    public DataLoader(SeriesRepository seriesRepository, VoiceActorRepository voiceActorRepository, GenreRepository genreRepository) {
        this.seriesRepository = seriesRepository;
        this.voiceActorRepository = voiceActorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        this.loadData();
        log.debug("DataLoader.run");
    }

    private void loadData() {

        Optional<Genre> genreOptional1 = this.genreRepository.findByName("Action");
        Optional<Genre> genreOptional2 = this.genreRepository.findByName("Sci-Fi");
        Optional<Genre> genreOptional3 = this.genreRepository.findByName("Adventure");
        Optional<Genre> genreOptional4 = this.genreRepository.findByName("Fantasy");
        Optional<Genre> genreOptional5 = this.genreRepository.findByName("Comedy");
        Optional<Genre> genreOptional6 = this.genreRepository.findByName("Drama");


        // Neon Genesis Evangelion
        Optional<VoiceActor> voiceActorOptional1 = this.voiceActorRepository.findByName("Miyamura, Yuko");
        Optional<VoiceActor> voiceActorOptional2 = this.voiceActorRepository.findByName("Hayashibara, Megumi");
        Optional<VoiceActor> voiceActorOptional3 = this.voiceActorRepository.findByName("Ogata, Megumi");
        // Cowboy Bebop
        Optional<VoiceActor> voiceActorOptional4 = this.voiceActorRepository.findByName("Yamadera, Kouichi");
        Optional<VoiceActor> voiceActorOptional5 = this.voiceActorRepository.findByName("Tada, Aoi");
        // Hunter x Hunter
        Optional<VoiceActor> voiceActorOptional6 = this.voiceActorRepository.findByName("Ise, Mariya");
        Optional<VoiceActor> voiceActorOptional7 = this.voiceActorRepository.findByName("Sawashiro, Miyuki");
        Optional<VoiceActor> voiceActorOptional8 = this.voiceActorRepository.findByName("Han, Megumi");
        // Jujustu Kaisen
        Optional<VoiceActor> voiceActorOptional9 = this.voiceActorRepository.findByName("Nakamura, Yuuichi");
        Optional<VoiceActor> voiceActorOptional10 = this.voiceActorRepository.findByName("Enoki, Junya");
        Optional<VoiceActor> voiceActorOptional11 = this.voiceActorRepository.findByName("Uchida, Yuuma");
        // Chainsaw man
        Optional<VoiceActor> voiceActorOptional12 = this.voiceActorRepository.findByName("Kusunoki, Tomori");
        Optional<VoiceActor> voiceActorOptional13 = this.voiceActorRepository.findByName("Fairouz Ai");
        Optional<VoiceActor> voiceActorOptional14 = this.voiceActorRepository.findByName("Toya, Kikunosuke");
        // Cyberpunk Edgerunners
        Optional<VoiceActor> voiceActorOptional15 = this.voiceActorRepository.findByName("Yuuki, Aoi");
        Optional<VoiceActor> voiceActorOptional16 = this.voiceActorRepository.findByName("KENN");
        Optional<VoiceActor> voiceActorOptional17 = this.voiceActorRepository.findByName("Kurosawa, Tomoyo");
        // Howl's Moving Castle
        Optional<VoiceActor> voiceActorOptional18 = this.voiceActorRepository.findByName("Kimura, Takuya");
        Optional<VoiceActor> voiceActorOptional19 = this.voiceActorRepository.findByName("Baishou, Chieko");
        Optional<VoiceActor> voiceActorOptional20 = this.voiceActorRepository.findByName("Gashuin, Tatsuya");
        // Perfect Blue
        Optional<VoiceActor> voiceActorOptional21 = this.voiceActorRepository.findByName("Iwao, Junko");
        Optional<VoiceActor> voiceActorOptional22 = this.voiceActorRepository.findByName("Ookura, Masaaki");
        Optional<VoiceActor> voiceActorOptional23 = this.voiceActorRepository.findByName("Matsumoto, Rica");
        // Bucchigire!
        Optional<VoiceActor> voiceActorOptional24 = this.voiceActorRepository.findByName("Uesaka, Sumire");
        Optional<VoiceActor> voiceActorOptional25 = this.voiceActorRepository.findByName("Toki, Shunichi");
        Optional<VoiceActor> voiceActorOptional26 = this.voiceActorRepository.findByName("Uemura, Yuuto");
        // Naruto
        Optional<VoiceActor> voiceActorOptional27 = this.voiceActorRepository.findByName("Takeuchi, Junko");
        Optional<VoiceActor> voiceActorOptional28 = this.voiceActorRepository.findByName("Sugiyama, Noriaki");
        Optional<VoiceActor> voiceActorOptional29 = this.voiceActorRepository.findByName("Inoue, Kazuhiko");



        Series NGE = new Series();
        NGE.setTitle("Neon Genesis Evangelion");
        NGE.setDescription("A mecha anime that follows the story of Shinji Ikari.");
        NGE.setRating(Rating.BORING);
        NGE.setUrl("https://www.netflix.com/title/81033445");
        NGE.setImageUrl("");
        NGE.setNote(new Note(NGE, "I hate the main character, Shinji Ikari. He's a whiny little brat."));

        NGE.addCharacter(new Character("Shinji Ikari", "", voiceActorOptional3.get()));
        NGE.addCharacter(new Character("Rei Ayanami", "", voiceActorOptional2.get()));
        NGE.addCharacter(new Character("Asuka Langley Soryu", "", voiceActorOptional1.get()));

        NGE.getGenres().add(genreOptional1.get());
        NGE.getGenres().add(genreOptional2.get());
        NGE.getGenres().add(genreOptional6.get());

        this.seriesRepository.save(NGE);

        Series CB = new Series();
        CB.setTitle("Cowboy Bebop");
        CB.setDescription("A space western that follows the story of Spike Spiegel.");
        CB.setRating(Rating.MASTERPIECE);
        CB.setUrl("https://www.netflix.com/title/70204970");
        CB.setImageUrl("");
        CB.setNote(new Note(CB, "I love the music in this anime."));

        CB.addCharacter(new Character("Spike Spiegel", "", voiceActorOptional4.get()));
        CB.addCharacter(new Character("Faye Valentine", "", voiceActorOptional2.get()));
        CB.addCharacter(new Character("Ed", "", voiceActorOptional5.get()));

        CB.getGenres().add(genreOptional1.get());
        CB.getGenres().add(genreOptional2.get());

        this.seriesRepository.save(CB);

        Series HxH = new Series();
        HxH.setTitle("Hunter x Hunter");
        HxH.setDescription("An adventure anime that follows the story of Gon Freecss.");
        HxH.setRating(Rating.MASTERPIECE);
        HxH.setUrl("https://www.netflix.com/title/80000063");
        HxH.setImageUrl("");
        HxH.setNote(new Note(HxH, "I love the power system in this anime."));

        HxH.addCharacter(new Character("Killua Zoldyck", "", voiceActorOptional6.get()));
        HxH.addCharacter(new Character("Kurapika", "", voiceActorOptional7.get()));
        HxH.addCharacter(new Character("Gon Freecss", "", voiceActorOptional8.get()));

        HxH.getGenres().add(genreOptional1.get());
        HxH.getGenres().add(genreOptional4.get());

        this.seriesRepository.save(HxH);

        Series JJK = new Series();
        JJK.setTitle("Jujustu Kaisen");
        JJK.setDescription("A supernatural anime that follows the story of Yuji Itadori.");
        JJK.setRating(Rating.MASTERPIECE);
        JJK.setUrl("https://www.netflix.com/title/81054889");
        JJK.setImageUrl("");
        JJK.setNote(new Note(JJK, "I love the fight scenes in this anime."));

        JJK.addCharacter(new Character("Gojo Satoru", "", voiceActorOptional9.get()));
        JJK.addCharacter(new Character("Yuji Itadori", "", voiceActorOptional10.get()));
        JJK.addCharacter(new Character("Megumi Fushiguro", "", voiceActorOptional11.get()));

        JJK.getGenres().add(genreOptional1.get());

        this.seriesRepository.save(JJK);

        Series CSM = new Series();
        CSM.setTitle("Chainsaw man");
        CSM.setDescription("A supernatural anime that follows the story of Denji.");
        CSM.setRating(Rating.MASTERPIECE);
        CSM.setUrl("https://www.netflix.com/title/81054889");
        CSM.setImageUrl("");
        CSM.setNote(new Note(CSM, "I love the fight scenes in this anime."));

        CSM.addCharacter(new Character("Makima", "", voiceActorOptional12.get()));
        CSM.addCharacter(new Character("Power", "", voiceActorOptional13.get()));
        CSM.addCharacter(new Character("Denji", "", voiceActorOptional14.get()));

        CSM.getGenres().add(genreOptional1.get());
        CSM.getGenres().add(genreOptional4.get());

        this.seriesRepository.save(CSM);

        Series CE = new Series();
        CE.setTitle("Cyberpunk Edgerunners");
        CE.setDescription("A cyberpunk anime that follows the story of a street kid.");
        CE.setRating(Rating.MASTERPIECE);
        CE.setUrl("https://www.netflix.com/title/81054889");
        CE.setImageUrl("");
        CE.setNote(new Note(CE, "The graphics in this anime are amazing."));

        CE.addCharacter(new Character("Lucy", "", voiceActorOptional15.get()));
        CE.addCharacter(new Character("David Martivez", "", voiceActorOptional16.get()));
        CE.addCharacter(new Character("Rebecca", "", voiceActorOptional17.get()));

        CE.getGenres().add(genreOptional2.get());
        CE.getGenres().add(genreOptional6.get());

        this.seriesRepository.save(CE);

        Series HMC = new Series();
        HMC.setTitle("Howl's Moving Castle");
        HMC.setDescription("A fantasy anime that follows the story of Sophie.");
        HMC.setRating(Rating.THRILLING);
        HMC.setUrl("https://www.netflix.com/title/81054889");
        HMC.setImageUrl("");
        HMC.setNote(new Note(HMC, "Couldn't figure out the plot of this anime."));

        HMC.addCharacter(new Character("Howl", "", voiceActorOptional18.get()));
        HMC.addCharacter(new Character("Sophie", "", voiceActorOptional19.get()));
        HMC.addCharacter(new Character("Calcifer", "", voiceActorOptional20.get()));

        HMC.getGenres().add(genreOptional4.get());
        HMC.getGenres().add(genreOptional6.get());

        this.seriesRepository.save(HMC);

        Series PB = new Series();
        PB.setTitle("Perfect Blue");
        PB.setDescription("A psychological anime that follows the story of Mima.");
        PB.setRating(Rating.OKAY);
        PB.setUrl("https://www.netflix.com/title/81054889");
        PB.setImageUrl("");
        PB.setNote(new Note(PB, "The twist in this anime was pretty good."));

        PB.addCharacter(new Character("Mima Kirigoe", "", voiceActorOptional21.get()));
        PB.addCharacter(new Character("Uchida Mamoru", "", voiceActorOptional22.get()));
        PB.addCharacter(new Character("Rumi Hidaka", "", voiceActorOptional23.get()));

        PB.getGenres().add(genreOptional6.get());

        this.seriesRepository.save(PB);

        Series BG = new Series();
        BG.setTitle("Bucchigire!");
        BG.setDescription("A fantasy anime that follows the story of a demon lord.");
        BG.setRating(Rating.BAD);
        BG.setUrl("https://www.netflix.com/title/81054889");
        BG.setImageUrl("");
        BG.setNote(new Note(BG, "The plot of this anime was all over the place."));

        BG.addCharacter(new Character("Akira", "", voiceActorOptional24.get()));
        BG.addCharacter(new Character("Sakuya", "", voiceActorOptional25.get()));
        BG.addCharacter(new Character("Suzuran", "", voiceActorOptional26.get()));

        BG.getGenres().add(genreOptional4.get());

        this.seriesRepository.save(BG);

        Series N = new Series();
        N.setTitle("Naruto");
        N.setDescription("A ninja anime that follows the story of Naruto Uzumaki.");
        N.setRating(Rating.OKAY);
        N.setUrl("https://www.netflix.com/title/81054889");
        N.setImageUrl("");
        N.setNote(new Note(N, "Pretty long anime. The fillers in this anime were annoying."));

        N.addCharacter(new Character("Naruto Uzumaki", "", voiceActorOptional27.get()));
        N.addCharacter(new Character("Sasuke Uchiha", "", voiceActorOptional28.get()));
        N.addCharacter(new Character("Hatake Kakashi", "", voiceActorOptional29.get()));

        N.getGenres().add(genreOptional1.get());
        N.getGenres().add(genreOptional3.get());

        this.seriesRepository.save(N);

    }
}
