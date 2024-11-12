package com.example.ohce.greetingservice;

import static java.util.function.Predicate.not;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Scanner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GreetingServiceImpl implements GreetingService {
    private static final LocalTime EIGHT_PM = LocalTime.of(20, 0, 0);
    private static final LocalTime SIX_AM = LocalTime.of(6, 0, 0);
    private static final LocalTime NOON = LocalTime.of(12, 0, 0);

    /**
     * Generates greeting text based on local time.
     *
     * @return generated text.
     */
    @Override
    public String generateGreetingText(LocalTime currentTime) {
        if (currentTime.isAfter(NOON) && currentTime.isBefore(EIGHT_PM)) {
            return "¡Buenas tardes";
        } else if (currentTime.isAfter(SIX_AM) && currentTime.isBefore(NOON)) {
            return "¡Buenos días";
        } else {
            return "¡Buenas noches";
        }
    }

    /**
     * Gets valid greeting request.
     *
     * @param scanner scanner
     * @param request request to validate
     *
     * @return valid request
     */
    public String getValidGreetingRequest(Scanner scanner, String request) {
        while (validateRequest(request).isEmpty()) {
            log.warn("Greeting text must start with prefix oche, and name must not be empty or null");
            request = scanner.nextLine();
        }
        return request;
    }

    Optional<String> validateRequest(String name) {
        return Optional.ofNullable(name)
            .filter(not(String::isBlank))
            .filter(userName -> userName.startsWith("oche"))
            .map(String::trim)
            .filter(n -> n.length() != 4);
    }
}
