package org.example;

import java.io.IOException;

public class Task3Test {
    public static void main(String[] args) {
        try {
            Task3 apiClient = new Task3();
            apiClient.displayOpenTodosForUser(5);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
