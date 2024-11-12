package com.example.ohce;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import com.example.ohce.greetingservice.GreetingService;
import com.example.ohce.wordreverseservice.WordReverseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class OhceApplication implements CommandLineRunner, ExitCodeGenerator {
    private final GreetingService greetingService;
    private final WordReverseService reverseService;

    public static void main(String[] args) {
        SpringApplication.run(OhceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("enter you name with oche prefix");
        Scanner scanner = new Scanner(System.in);
        String greetingRequest = scanner.nextLine();

        var userName = greetingService.getValidGreetingRequest(scanner, greetingRequest)
            .replace("oche", "").trim();

        log.info(
            greetingService.generateGreetingText(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)) + " " + userName);

        log.info("please type a word you want to reverse");

        reverseService.serveWordReverseRequest(scanner);

        log.info("Adios " + userName);

        System.exit(1);
    }

    @Override
    public int getExitCode() {
        return 1;
    }
}
