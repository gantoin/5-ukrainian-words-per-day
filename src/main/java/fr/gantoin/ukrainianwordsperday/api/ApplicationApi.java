package fr.gantoin.ukrainianwordsperday.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

import fr.gantoin.ukrainianwordsperday.service.CsvReader;

@RequiredArgsConstructor
@Controller
public class ApplicationApi {

    private final CsvReader csvReader;

    @GetMapping(path = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("words", csvReader.pickFiveWords());
        return "index";
    }
}
