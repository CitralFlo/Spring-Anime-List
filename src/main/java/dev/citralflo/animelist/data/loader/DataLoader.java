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

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
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

    private void loadData() throws IOException {

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
        NGE.setImageUrl("https://w0.peakpx.com/wallpaper/40/143/HD-wallpaper-eva-01-evangelion-neon-genesis-evangelion-shinji.jpg");
        NGE.setNote(new Note(NGE, "I hate the main character, Shinji Ikari. He's a whiny little brat."));

        try {
            NGE.setImageFromUrl("https://w0.peakpx.com/wallpaper/40/143/HD-wallpaper-eva-01-evangelion-neon-genesis-evangelion-shinji.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        NGE.addCharacter(new Character("Shinji Ikari", "https://i1.sndcdn.com/artworks-000545105238-g2du6q-t500x500.jpg", voiceActorOptional3.get()));
        NGE.addCharacter(new Character("Rei Ayanami", "https://miro.medium.com/v2/resize:fit:1400/1*TVebZE0MHzu7mpFw_bCsrQ.jpeg", voiceActorOptional2.get()));
        NGE.addCharacter(new Character("Asuka Langley Soryu", "https://avatarfiles.alphacoders.com/292/thumb-1920-292454.png", voiceActorOptional1.get()));

        Set<Genre> ngeGenres = NGE.getGenres();

        ngeGenres.add(genreOptional1.get());
        ngeGenres.add(genreOptional2.get());
        ngeGenres.add(genreOptional6.get());

        NGE.setGenres(ngeGenres);

        this.seriesRepository.save(NGE);

        Series CB = new Series();
        CB.setTitle("Cowboy Bebop");
        CB.setDescription("A space western that follows the story of Spike Spiegel.");
        CB.setRating(Rating.MASTERPIECE);
        CB.setUrl("https://www.netflix.com/title/70204970");
        CB.setImageUrl("https://w0.peakpx.com/wallpaper/699/834/HD-wallpaper-cowboy-bebop-2-bepop.jpg");
        CB.setNote(new Note(CB, "I love the music in this anime."));

        try {
            CB.setImageFromUrl("https://w0.peakpx.com/wallpaper/699/834/HD-wallpaper-cowboy-bebop-2-bepop.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CB.addCharacter(new Character("Spike Spiegel", "https://gifdb.com/images/thumbnail/cowboy-bebop-spike-spiegel-smoking-q4dyq11ymucdxhvz.gif", voiceActorOptional4.get()));
        CB.addCharacter(new Character("Faye Valentine", "https://upload.wikimedia.org/wikipedia/it/thumb/b/bc/Faye_Valentine.jpg/1200px-Faye_Valentine.jpg", voiceActorOptional2.get()));
        CB.addCharacter(new Character("Ed", "https://i.pinimg.com/originals/cd/5f/06/cd5f06b30f601abd994edbb23e1fbf0a.png", voiceActorOptional5.get()));

        CB.getGenres().add(genreOptional1.get());
        CB.getGenres().add(genreOptional2.get());

        this.seriesRepository.save(CB);

        Series HxH = new Series();
        HxH.setTitle("Hunter x Hunter");
        HxH.setDescription("An adventure anime that follows the story of Gon Freecss.");
        HxH.setRating(Rating.MASTERPIECE);
        HxH.setUrl("https://www.netflix.com/title/80000063");
        HxH.setImageUrl("https://wallpapers.com/images/hd/hunter-x-hunter-iphone-hv7a5cubr60hyllh.jpg");
        HxH.setNote(new Note(HxH, "I love the power system in this anime."));

        try {
            HxH.setImageFromUrl("https://wallpapers.com/images/hd/hunter-x-hunter-iphone-hv7a5cubr60hyllh.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        HxH.addCharacter(new Character("Killua Zoldyck", "https://miro.medium.com/v2/resize:fit:700/1*7Qy6qbDkrIQ_7qPrnSqkiA.png", voiceActorOptional6.get()));
        HxH.addCharacter(new Character("Kurapika", "https://i.pinimg.com/736x/a3/ec/14/a3ec14fd55bae7683c5fa01a7acb7d77.jpg", voiceActorOptional7.get()));
        HxH.addCharacter(new Character("Gon Freecss", "https://wallpapers-clan.com/wp-content/uploads/2022/02/hxh-gon-and-killua-matching-pfp-17.jpg", voiceActorOptional8.get()));

        HxH.getGenres().add(genreOptional1.get());
        HxH.getGenres().add(genreOptional4.get());

        this.seriesRepository.save(HxH);

        Series JJK = new Series();
        JJK.setTitle("Jujustu Kaisen");
        JJK.setDescription("A supernatural anime that follows the story of Yuji Itadori.");
        JJK.setRating(Rating.MASTERPIECE);
        JJK.setUrl("https://www.netflix.com/title/81054889");
        JJK.setImageUrl("https://static.tnn.in/photo/104569494/104569494.jpg");

        try {
            JJK.setImageFromUrl("https://static.tnn.in/photo/104569494/104569494.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JJK.setNote(new Note(JJK, "I love the fight scenes in this anime."));

        JJK.addCharacter(new Character("Gojo Satoru", "https://avatarfiles.alphacoders.com/368/thumb-1920-368124.png", voiceActorOptional9.get()));
        JJK.addCharacter(new Character("Yuji Itadori", "https://i.pinimg.com/736x/c8/6e/82/c86e8278d5d8bb515457fa7ee3bb092d.jpg", voiceActorOptional10.get()));
        JJK.addCharacter(new Character("Megumi Fushiguro", "https://i.pinimg.com/736x/61/b9/a2/61b9a282a293eb330301d505875410cb.jpg", voiceActorOptional11.get()));

        JJK.getGenres().add(genreOptional1.get());

        this.seriesRepository.save(JJK);

        Series CSM = new Series();
        CSM.setTitle("Chainsaw man");
        CSM.setDescription("A supernatural anime that follows the story of Denji.");
        CSM.setRating(Rating.MASTERPIECE);
        CSM.setUrl("https://www.netflix.com/title/81054889");
        CSM.setImageUrl("https://m.media-amazon.com/images/I/81fsv1zxpqL._AC_UF894,1000_QL80_.jpg");
        CSM.setNote(new Note(CSM, "I love the fight scenes in this anime."));

        try {
            CSM.setImageFromUrl("https://m.media-amazon.com/images/I/81fsv1zxpqL._AC_UF894,1000_QL80_.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CSM.addCharacter(new Character("Makima", "https://i.redd.it/what-makes-makima-so-recognizable-and-stand-out-so-much-v0-m8foctf67rlc1.jpg?width=720&format=pjpg&auto=webp&s=add6b0bdac38ec70f0a39c3ce6b4685f1fba5570", voiceActorOptional12.get()));
        CSM.addCharacter(new Character("Power", "https://i.pinimg.com/736x/61/c5/6c/61c56c1fe22b5031736a410d35b3da85.jpg", voiceActorOptional13.get()));
        CSM.addCharacter(new Character("Denji", "https://wallpapers-clan.com/wp-content/uploads/2023/01/chainsaw-man-denji-pfp-29.jpg", voiceActorOptional14.get()));

        CSM.getGenres().add(genreOptional1.get());
        CSM.getGenres().add(genreOptional4.get());

        this.seriesRepository.save(CSM);

        Series CE = new Series();
        CE.setTitle("Cyberpunk Edgerunners");
        CE.setDescription("A cyberpunk anime that follows the story of a street kid.");
        CE.setRating(Rating.MASTERPIECE);
        CE.setUrl("https://www.netflix.com/title/81054889");
        CE.setImageUrl("https://i.pinimg.com/736x/dc/f7/74/dcf774b17773434a34193160811b9f5a.jpg");
        CE.setNote(new Note(CE, "The graphics in this anime are amazing."));

        try {
            CE.setImageFromUrl("https://i.pinimg.com/736x/dc/f7/74/dcf774b17773434a34193160811b9f5a.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CE.addCharacter(new Character("Lucy", "https://wallpapers-clan.com/wp-content/uploads/2022/11/cyberpunk-edgerunners-lucy-pfp-21.jpg", voiceActorOptional15.get()));
        CE.addCharacter(new Character("David Martivez", "https://i.pinimg.com/736x/6c/1d/5a/6c1d5a989a84900ae0c488b9e0c504f1.jpg", voiceActorOptional16.get()));
        CE.addCharacter(new Character("Rebecca", "https://i.pinimg.com/736x/c1/ee/f8/c1eef8ca624e401963ad76365efef100.jpg", voiceActorOptional17.get()));

        CE.getGenres().add(genreOptional2.get());
        CE.getGenres().add(genreOptional6.get());

        this.seriesRepository.save(CE);

        Series HMC = new Series();
        HMC.setTitle("Howl's Moving Castle");
        HMC.setDescription("A fantasy anime that follows the story of Sophie.");
        HMC.setRating(Rating.THRILLING);
        HMC.setUrl("https://www.netflix.com/title/81054889");
        HMC.setImageUrl("https://m.media-amazon.com/images/I/51LcirCWCTL._AC_UF894,1000_QL80_.jpg");
        HMC.setNote(new Note(HMC, "Couldn't figure out the plot of this anime."));

        try {
            HMC.setImageFromUrl("https://m.media-amazon.com/images/I/51LcirCWCTL._AC_UF894,1000_QL80_.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        HMC.addCharacter(new Character("Howl", "https://i.etsystatic.com/35550803/r/il/03d72d/3846156466/il_570xN.3846156466_qdfe.jpg", voiceActorOptional18.get()));
        HMC.addCharacter(new Character("Sophie", "https://i.pinimg.com/564x/40/67/03/4067035ae50d142e575e4abe8ae4f5f4.jpg", voiceActorOptional19.get()));
        HMC.addCharacter(new Character("Calcifer", "https://i.pinimg.com/originals/53/da/12/53da12c9d8241d9ccd9db0233e484bf8.jpg", voiceActorOptional20.get()));

        HMC.getGenres().add(genreOptional4.get());
        HMC.getGenres().add(genreOptional6.get());

        this.seriesRepository.save(HMC);

        Series PB = new Series();
        PB.setTitle("Perfect Blue");
        PB.setDescription("A psychological anime that follows the story of Mima.");
        PB.setRating(Rating.OKAY);
        PB.setUrl("https://www.netflix.com/title/81054889");
        PB.setImageUrl("https://i.pinimg.com/originals/7f/d3/47/7fd347ae3a0cc930984a3d427fb6def5.jpg");
        PB.setNote(new Note(PB, "The twist in this anime was pretty good."));

        try {
            PB.setImageFromUrl("https://i.pinimg.com/originals/7f/d3/47/7fd347ae3a0cc930984a3d427fb6def5.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PB.addCharacter(new Character("Mima Kirigoe", "https://64.media.tumblr.com/326dc4490714fb6ce04a5e938604450e/tumblr_p6rhig5j841wr60xso1_400.jpg", voiceActorOptional21.get()));
        PB.addCharacter(new Character("Uchida Mamoru", "https://static1.personality-database.com/profile_images/2f8ce2c0f1ea448d89b91040f059114b.png", voiceActorOptional22.get()));
        PB.addCharacter(new Character("Rumi Hidaka", "https://thicc-af.mywaifulist.moe/waifus/2954/c2f73d1c8af1f444b2f5e77f315908296438adf54519789c0e2297d78271a045_thumb.jpeg", voiceActorOptional23.get()));

        PB.getGenres().add(genreOptional6.get());

        this.seriesRepository.save(PB);

        Series BG = new Series();
        BG.setTitle("Bucchigire!");
        BG.setDescription("A fantasy anime that follows the story of a demon lord.");
        BG.setRating(Rating.BAD);
        BG.setUrl("https://www.netflix.com/title/81054889");
        BG.setImageUrl("https://cdn.myanimelist.net/images/anime/1092/125295.jpg");
        BG.setNote(new Note(BG, "The plot of this anime was all over the place."));

        try {
            BG.setImageFromUrl("https://cdn.myanimelist.net/images/anime/1092/125295.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BG.addCharacter(new Character("Akira", "https://i.pinimg.com/originals/e2/2b/ad/e22bad8495a4845e78ae41541fa84f53.jpg", voiceActorOptional24.get()));
        BG.addCharacter(new Character("Sakuya", "https://i.pinimg.com/originals/a1/2e/f3/a12ef3e9327c2521fcddf0c4dd8918d6.jpg", voiceActorOptional25.get()));
        BG.addCharacter(new Character("Suzuran", "https://i.pinimg.com/736x/75/b3/b7/75b3b772f90f2c2a9f19eba0680adfd0.jpg", voiceActorOptional26.get()));

        BG.getGenres().add(genreOptional4.get());

        this.seriesRepository.save(BG);

        Series N = new Series();
        N.setTitle("Naruto");
        N.setDescription("A ninja anime that follows the story of Naruto Uzumaki.");
        N.setRating(Rating.OKAY);
        N.setUrl("https://www.netflix.com/title/81054889");
        N.setImageUrl("https://m.media-amazon.com/images/I/71WECnGLtIL._AC_UF894,1000_QL80_.jpg");
        N.setNote(new Note(N, "Pretty long anime. The fillers in this anime were annoying."));

        try {
            N.setImageFromUrl("https://m.media-amazon.com/images/I/71WECnGLtIL._AC_UF894,1000_QL80_.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        N.addCharacter(new Character("Naruto Uzumaki", "https://i.pinimg.com/originals/ee/61/37/ee61374e60f036d0d605c37b3a7bee8a.jpg", voiceActorOptional27.get()));
        N.addCharacter(new Character("Sasuke Uchiha", "https://i.pinimg.com/564x/fd/d5/3b/fdd53bf7804d79e947641a46b7a6ae6a.jpg", voiceActorOptional28.get()));
        N.addCharacter(new Character("Hatake Kakashi", "https://res.cloudinary.com/dcydcwulw/images/f_auto,q_auto/v1686768534/Kakashi-Hatake-PFP-16_2323b4274/Kakashi-Hatake-PFP-16_2323b4274.jpg?_i=AA", voiceActorOptional29.get()));

        N.getGenres().add(genreOptional1.get());
        N.getGenres().add(genreOptional3.get());

        this.seriesRepository.save(N);

    }
}
