package fr.gantoin.ukrainianwordsperday.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.Getter;

import fr.gantoin.ukrainianwordsperday.domain.Word;

@Service
public class CsvReader {

    @Getter
    private final List<Word> words = new ArrayList<>();

    public CsvReader() throws URISyntaxException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("static/words.csv");
        Path path = Paths.get(Objects.requireNonNull(url).toURI());
        CsvReader.readFile(new File(path.toString())).forEach(line -> {
            String[] strings = line.split(",");
            Word word = new Word(Integer.parseInt(strings[0]), strings[1], strings[2]);
            words.add(word);
        });
    }

    public static List<String> readFile(File file) throws IOException {
        List<String> result = new ArrayList<>();

        FileReader fr = new FileReader(file, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fr);

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            result.add(line);
        }

        br.close();
        fr.close();

        return result;
    }

    public List<Word> pickFiveWords() {
        List<Word> result = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int randomIndex = rand.nextInt(words.size());
            result.add(words.get(randomIndex));
        }
        return result;
    }
}
