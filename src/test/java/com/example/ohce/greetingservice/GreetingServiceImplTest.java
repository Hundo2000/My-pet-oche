package com.example.ohce.greetingservice;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.stream.Stream;

import com.example.ohce.wordreverseservice.WordReverseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GreetingServiceImplTest {

    private GreetingServiceImpl greetingService;

    @Mock
    private WordReverseService wordReverseService;

    static Stream<Arguments> localTime() {
        return Stream.of(
            Arguments.of(LocalTime.of(22, 16), "¡Buenas noches"),
            Arguments.of(LocalTime.of(8, 25), "¡Buenos días"),
            Arguments.of(LocalTime.of(12, 27), "¡Buenas tardes"));
    }

    @BeforeEach
    void setUp() {
        greetingService = new GreetingServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("localTime")
    void shouldGenerateGreetingTextProperly(LocalTime localTime, String expectedText) {

        //when
        var actualText = greetingService.generateGreetingText(localTime);

        //then
        assertEquals(expectedText, actualText);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "Pedro", "oche", "oche  ", "  "})
        //should return empty Optional because of invalid request
    void shouldReturnEmptyOptional(String name) {

        assertThat(greetingService.validateRequest(name)).isEmpty();

    }

    @ParameterizedTest
    @ValueSource(strings = {"oche Pedro", "oche Aram"})
    void shouldDetectValidRequest(String name) {

        assertThat(greetingService.validateRequest(name)).isNotEmpty();
    }

}