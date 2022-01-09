package fr.gantoin.ukrainianwordsperday.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.Getter;

import fr.gantoin.ukrainianwordsperday.domain.Word;

@Service
public class CsvReader {

    @Getter
    private final List<Word> words = new ArrayList<>();

    public CsvReader() throws URISyntaxException, IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/words.csv");
        CsvReader.readFile(resourceAsStream).forEach(line -> {
            String[] strings = line.split(",");
            Word word = new Word(Integer.parseInt(strings[0]), strings[1], strings[2]);
            words.add(word);
        });
    }

    public static List<String> readFile(InputStream resourceAsStream) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"));

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            result.add(line);
        }

        br.close();
        resourceAsStream.close();

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
