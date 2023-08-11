package org.example;

import java.io.IOException;

public class Task2Test {
    public static void main(String[] args) {
        try {
            Task2 apiClient = new Task2();
            apiClient.saveLatestPostCommentsForUser(10);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
