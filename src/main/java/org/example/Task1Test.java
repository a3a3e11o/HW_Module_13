package org.example;

import java.io.IOException;

public class Task1Test {

    public static void main(String[] args) {
        try {
            Task1 apiClient = new Task1();

            String newUserJson = "{\"name\":\"Dima Dmitrovich\",\"username\":\"dimadmitrovich\",\"email\":\"dimadmitrovich@google.com\"}";
            String createdUser = apiClient.createUser(newUserJson);
            System.out.println("Created User: " + createdUser);
            System.out.println("###################################################################################");

            String updatedUserJson = "{\"name\":\"Pasha Pashovich\",\"username\":\"pashapashovich\",\"email\":\"pashapashovich@google.com\"}";
            String updatedUser = apiClient.updateUser(2, updatedUserJson);
            System.out.println("Updated User: " + updatedUser);
            System.out.println("###################################################################################");

            int deleteStatusCode = apiClient.deleteUser(3);
            System.out.println("Delete Status Code: " + deleteStatusCode);
            System.out.println("###################################################################################");

            String allUsers = apiClient.getAllUsers();
            System.out.println("All Users: " + allUsers);
            System.out.println("###################################################################################");

            String userById = apiClient.getUserById(10);
            System.out.println("User by ID: " + userById);
            System.out.println("###################################################################################");

            String userByUsername = apiClient.getUserByUsername("Antonette");
            System.out.println("User by Username: " + userByUsername);
            System.out.println("###################################################################################");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}