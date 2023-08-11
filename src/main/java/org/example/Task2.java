package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;

public class Task2 extends Task1 {

    public void saveLatestPostCommentsForUser(int userId) throws IOException {
        int latestPostId = getLatestPostIdForUser(userId);
        if (latestPostId != -1) {
            String comments = getPostComments(latestPostId);
            if (comments != null) {
                String filename = "user-" + userId + "-post-" + latestPostId + "-comments.json";
                try (FileWriter writer = new FileWriter(filename)) {
                    writer.write(comments);
                }

                System.out.println("Comments for latest post of user " + userId + ":");
                JsonArray commentsArray = JsonParser.parseString(comments).getAsJsonArray();
                for (JsonElement commentElement : commentsArray) {
                    JsonObject comment = commentElement.getAsJsonObject();
                    String name = comment.get("name").getAsString();
                    String body = comment.get("body").getAsString();
                    System.out.println("Name: " + name);
                    System.out.println("Body: " + body);
                    System.out.println("-----------------------");
                }
            }
        }
    }

    public int getLatestPostIdForUser(int userId) throws IOException {
        String userPosts = getUserPosts(userId);
        if (userPosts != null) {
            JsonArray postsArray = JsonParser.parseString(userPosts).getAsJsonArray();
            int latestPostId = -1;

            for (JsonElement postElement : postsArray) {
                JsonObject post = postElement.getAsJsonObject();
                int postId = post.get("id").getAsInt();
                if (postId > latestPostId) {
                    latestPostId = postId;
                }
            }

            return latestPostId;
        }

        return -1;
    }

    private String getUserPosts(int userId) throws IOException {
        return sendRequest("/users/" + userId + "/posts", "GET", null);
    }

    private String getPostComments(int postId) throws IOException {
        return sendRequest("/posts/" + postId + "/comments", "GET", null);
    }
}
