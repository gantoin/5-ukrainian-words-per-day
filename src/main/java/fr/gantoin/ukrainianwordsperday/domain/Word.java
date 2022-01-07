package fr.gantoin.ukrainianwordsperday.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Word {

    private int number;
    private String ukrainian;
    private String english;

}
