package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class Task3 extends Task1 {
    public void displayOpenTodosForUser(int userId) throws IOException {
        String todos = getUserTodos(userId);
        if (todos != null) {
            System.out.println("Open todos for user " + userId + ":");
            JsonArray todosArray = JsonParser.parseString(todos).getAsJsonArray();
            for (JsonElement todoElement : todosArray) {
                JsonObject todo = todoElement.getAsJsonObject();
                boolean completed = todo.get("completed").getAsBoolean();
                if (!completed) {
                    String title = todo.get("title").getAsString();
                    System.out.println("- " + title);
                }
            }
        }
    }

    private String getUserTodos(int userId) throws IOException {
        return sendRequest("/users/" + userId + "/todos", "GET", null);
    }
}
