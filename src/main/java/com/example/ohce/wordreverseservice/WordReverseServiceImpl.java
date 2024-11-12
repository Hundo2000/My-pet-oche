package com.example.ohce.wordreverseservice;

import java.util.Optional;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WordReverseServiceImpl implements WordReverseService {

    /**
     * Reverse's given word and check's if it is palindrome.
     *
     * @param wordToReverse word to reverse
     */
    private void printReversedString(String wordToReverse) {

        if (StringUtils.isBlank(wordToReverse)) {
            log.warn("requested word must not be empty or null");
        } else {
            log.info(getReversedWord(wordToReverse));
        }
    }

    /**
     * Printing reverse string from the given string.
     *
     * @param scanner scanner
     */
    @Override
    public void serveWordReverseRequest(Scanner scanner) {
        var wordToReverse = scanner.nextLine();

        while (!wordToReverse.equals("Stop!")) {
            printReversedString(wordToReverse);
            wordToReverse = scanner.nextLine();
        }
    }

    String getReversedWord(String wordToReverse) {
        var reversedWord = reverseString(wordToReverse);
        return Optional.of(reversedWord)
            .filter(rw -> rw.equalsIgnoreCase(wordToReverse))
            .map(rw -> reversedWord + "\n¡Bonita palabra!")
            .orElse(reversedWord);
    }

    private String reverseString(String inputString) {

        byte[] strAsByteArray = inputString.getBytes();

        byte[] result = new byte[strAsByteArray.length];

        for (int i = 0; i < strAsByteArray.length; i++) {
            result[i] = strAsByteArray[strAsByteArray.length - i - 1];
        }

        return new String(result);
    }

}
