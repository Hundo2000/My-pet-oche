package com.example.ohce.greetingservice;

import java.time.LocalTime;
import java.util.Scanner;

public interface GreetingService {
    String generateGreetingText(LocalTime currentTime);
    String getValidGreetingRequest(Scanner scanner, String name);
}
