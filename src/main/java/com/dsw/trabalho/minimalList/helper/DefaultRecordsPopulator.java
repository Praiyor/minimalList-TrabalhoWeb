package com.dsw.trabalho.minimalList.helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dsw.trabalho.minimalList.model.Content;
import com.dsw.trabalho.minimalList.repository.ContentRepository;
import com.dsw.trabalho.minimalList.service.ApiService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DefaultRecordsPopulator implements CommandLineRunner {

    private ContentRepository contentRepository;
    private ApiService apiService;

    @Override
    public void run(String... args) {
        // Check if the table is empty

        if (contentRepository.findAll().isEmpty()) {
            // String response =
            // apiService.fetchGetDataApi("https://api.jikan.moe/v4/top/anime");
            String[] namesContents = {
                    "Breaking Bad",
                    "Game of Thrones",
                    "Friends",
                    "The Office",
                    "Stranger Things",
                    "The Crown",
                    "The Big Bang Theory",
                    "The Simpsons",
                    "The Walking Dead",
                    "Westworld",
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

            String[] genresContents = {
                    "Drama",
                    "Fantasy",
                    "Comedy",
                    "Comedy",
                    "Drama/Fantasy",
                    "Drama",
                    "Comedy",
                    "Animation/Comedy",
                    "Horror/Drama",
                    "Sci-Fi/Western"
            };

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

            String[] durationsContents = {
                    "45 min", "60 min", "22 min", "30 min", "50 min", "60 min", "20 min", "25 min", "40 min",
                    "55 min"
            };

            String[] imagesContents = {
                "http://localhost:8080/assets/images/content/breaking.jpeg",
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

            List<Content> contents = new ArrayList<Content>(10);

            for (int i = 0; i < namesContents.length; i++) {
                Content content = new Content();
                content.setName(namesContents[i]);
                content.setDate(productionDateContent[i]);
                content.setTitle(namesContents[i]);
                content.setSeason(seasonsContents[i]);
                content.setDescription(descriptionsContents[i]);
                content.setImage(imagesContents[i]);
                content.setDuration(durationsContents[i]);
                contents.add(content);
            }

            contentRepository.saveAllAndFlush(contents);
        }
    }
}
