package com.example.ohce.wordreverseservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WordReverseServiceImplTest {

    private WordReverseServiceImpl wordReverseService = new WordReverseServiceImpl();

    static Stream<Arguments> greetingRequests() {
        return Stream.of(
            Arguments.of("notPalindrome", "emordnilaPton"),
            Arguments.of("level", "level\n¡Bonita palabra!"));
    }

    @ParameterizedTest
    @MethodSource("greetingRequests")
    void shouldReversedWordAndDetectPalindrome(String wordToReverse, String reversedWord) {

        assertEquals(wordReverseService.getReversedWord(wordToReverse), reversedWord);
    }
}