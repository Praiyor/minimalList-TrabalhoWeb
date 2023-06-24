package com.dsw.trabalho.minimalList.helper;

import com.dsw.trabalho.minimalList.model.Category;
import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.model.ContentSeason;
import com.dsw.trabalho.minimalList.repository.CategoryRepository;
import com.dsw.trabalho.minimalList.repository.ContentRepository;
import com.dsw.trabalho.minimalList.repository.ContentSeasonRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultRecordsPopulator implements CommandLineRunner {

    private ContentRepository contentRepository;
    private ContentSeasonRepository contentSeasonRepository;
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        // Check if the table is empty

        if (contentRepository.findAll().isEmpty()) {
            // apiService.fetchGetDataApi("https://api.jikan.moe/v4/top/anime");
            String[] namesContents = {
                    "Breaking Bad", // DRAMA
                    "Game of Thrones", // Fantasy
                    "Friends", // COmey
                    "The Office", // COmey
                    "Stranger Things", // Fantasy
                    "The Crown", // DRama
                    "The Big Bang Theory", // COmey
                    "The Simpsons", // Animation
                    "The Walking Dead", // Horror
                    "Westworld", // Sci-fi
            };

            String[] descriptionsContents = {
                    "A high school chemistry teacher turned methamphetamine manufacturer teams up with a former"
                            + " student to create and sell a high-quality blue meth. As they descend into the"
                            + " criminal underworld, they face numerous challenges and consequences.",
                    "Set in the fictional continents of Westeros and Essos, this epic fantasy series follows"
                            + " the political and military struggles among noble families as they vie for control"
                            + " of the Iron Throne of Westeros. It features a large ensemble cast and is known for"
                            + " its intricate plotlines and unexpected twists.",
                    "This sitcom revolves around a group of six friends living in Manhattan, New York City. The"
                            + " show explores their personal and professional lives, their romantic relationships,"
                            + " and the humorous situations they find themselves in.",
                    "A mockumentary-style sitcom that depicts the everyday lives of office employees working at"
                            + " Dunder Mifflin Paper Company. It showcases the dynamics between the quirky and"
                            + " diverse characters, their professional rivalries, and the humorous incidents that"
                            + " occur in the workplace.",
                    "Set in the 1980s, this sci-fi/horror series follows a group of kids in the town of"
                            + " Hawkins, Indiana, as they encounter supernatural occurrences and government"
                            + " conspiracies. They befriend a mysterious girl with telekinetic powers and face off"
                            + " against a parallel dimension known as the Upside Down.",
                    "This historical drama chronicles the reign of Queen Elizabeth II from her early days as a"
                            + " young princess to the challenges she faces as the Queen of the United Kingdom. It"
                            + " offers insights into the political and personal struggles of the royal family and"
                            + " the changing social landscape of Britain.",
                    "This sitcom revolves around a group of socially awkward but highly intelligent physicists,"
                            + " their romantic interests, and their everyday lives. The show blends geek culture,"
                            + " science, and humor as it explores the dynamics of friendship and relationships.",
                    "An animated sitcom that follows the lives of the Simpson family in the fictional town of"
                            + " Springfield. The show satirizes various aspects of American culture, society, and"
                            + " television, often through the misadventures of the lovable yet dysfunctional"
                            + " Simpson family.",
                    "Based on a comic book series, this post-apocalyptic horror drama series follows a group of"
                            + " survivors in a world overrun by zombies, referred to as 'walkers.' It explores the"
                            + " challenges they face, both from the undead and other human survivors, as they"
                            + " strive to stay alive and find a safe haven.",
                    "A dark science fiction series set in a futuristic theme park where androids, known as"
                            + " hosts, cater to the desires of human guests. It delves into themes of"
                            + " consciousness, morality, and the nature of identity."
            };
            int[] seasonsContents = { 5, 8, 10, 9, 4, 4, 12, 34, 11, 4 };

            String[] genresContents = { "Drama", "Fantasy", "Comedy", "Animation", "Horror", "Sci-Fi" };

            for (String genre : genresContents) {
                if (categoryRepository.findByName(genre) == null) {
                    Category category = new Category();
                    category.setName(genre);
                    categoryRepository.save(category);
                }
            }

            LocalDate[] productionDateContent = {
                    LocalDate.of(2008, 1, 20),
                    LocalDate.of(2011, 4, 17),
                    LocalDate.of(1994, 9, 22),
                    LocalDate.of(2005, 3, 24),
                    LocalDate.of(2016, 7, 15),
                    LocalDate.of(2016, 11, 4),
                    LocalDate.of(2007, 9, 24),
                    LocalDate.of(1989, 12, 17),
                    LocalDate.of(2010, 10, 31),
                    LocalDate.of(2016, 10, 2)
            };

            int[][] durationsContents = {
                    // give me the quantity of ep for seasonContents
                    { 10, 10, 10, 10, 10 }, // 5 seasons
                    { 12, 12, 12, 12, 12, 12, 12, 12 }, // 8 seasons
                    { 15, 15, 15, 15, 15, 15, 15, 15, 15, 15 }, // 10 seasons
                    { 20, 20, 20, 20, 20, 20, 20, 20, 20 }, // 9 seasons
                    { 25, 25, 25, 25 }, // 4 seasons
                    { 30, 30, 30, 30 }, // 4 seasons
                    { 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35 }, // 12 seasons
                    { 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40 }, // 11 seasons
                    { 12, 12, 15, 18, 8, 10, 18, 45, 45, 45 }, // 10 seasons
                    { 10, 12, 20, 15 } // 4 seasons
            };

            String[] imagesContents = {
                    "http://localhost:8080/assets/images/content/breaking.jpg",
                    "http://localhost:8080/assets/images/content/thrones.jpeg",
                    "http://localhost:8080/assets/images/content/friend.jpg",
                    "http://localhost:8080/assets/images/content/the-office.jpeg",
                    "http://localhost:8080/assets/images/content/stranger-things.jpeg",
                    "http://localhost:8080/assets/images/content/crown.jpeg",
                    "http://localhost:8080/assets/images/content/big.jpeg",
                    "http://localhost:8080/assets/images/content/simpsons.jpg",
                    "http://localhost:8080/assets/images/content/walking.jpg",
                    "http://localhost:8080/assets/images/content/westworld.jpeg",
            };

            for (int i = 0; i < namesContents.length; i++) {
                Content content = new Content();
                content.setName(namesContents[i]);
                content.setDate(productionDateContent[i]);
                content.setTitle(namesContents[i]);
                content.setSeason(seasonsContents[i]);
                content.setDescription(descriptionsContents[i]);
                content.setImage(imagesContents[i]);
                content.setCreatedAt(LocalDateTime.now());
                content.setUpdatedAt(LocalDateTime.now());

                Category category;
                if (i == 0 || i == 5) {
                    category = categoryRepository.findByName("Drama");
                } else if (i == 1 || i == 4 || i == 9) {
                    category = categoryRepository.findByName("Fantasy");
                } else if (i == 2 || i == 3 || i == 6) {
                    category = categoryRepository.findByName("Comedy");
                } else if (i == 8) {
                    category = categoryRepository.findByName("Horror");
                } else if (i == 9) {
                    category = categoryRepository.findByName("Sci-Fi");
                } else {
                    category = categoryRepository.findByName("Animation");
                }

                if (category != null) {
                    content.setCategory(category);
                }

                List<ContentSeason> contentSeasons = new ArrayList<ContentSeason>();
                for (int qtdEp : durationsContents[i]) {
                    ContentSeason duration = new ContentSeason();
                    duration.setEpisode(qtdEp);
                    duration.setContent(content);
                    contentSeasons.add(duration);
                }

                contentRepository.saveAndFlush(content);
                contentSeasonRepository.saveAllAndFlush(contentSeasons);
            }

        }
    }
}
