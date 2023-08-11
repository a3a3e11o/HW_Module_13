package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Task1 {
    private static final String URL = "https://jsonplaceholder.typicode.com";
    String sendRequest(String endpoint, String method, String requestBody) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = null;

        switch (method) {
            case "GET" -> {
                HttpGet httpGet = new HttpGet(URL + endpoint);
                response = httpClient.execute(httpGet);
            }
            case "POST" -> {
                HttpPost httpPost = new HttpPost(URL + endpoint);
                if (requestBody != null) {
                    StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
                    httpPost.setEntity(entity);
                }
                response = httpClient.execute(httpPost);
            }
            case "PUT" -> {
                HttpPut httpPut = new HttpPut(URL + endpoint);
                if (requestBody != null) {
                    StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
                    httpPut.setEntity(entity);
                }
                response = httpClient.execute(httpPut);
            }
            case "DELETE" -> {
                HttpDelete httpDelete = new HttpDelete(URL + endpoint);
                response = httpClient.execute(httpDelete);
            }
        }

        if (response != null) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        }

        return null;
    }

    public String createUser(String userJson) throws IOException {
        return sendRequest("/users", "POST", userJson);
    }

    public String updateUser(int userId, String updatedUserJson) throws IOException {
        return sendRequest("/users/" + userId, "PUT", updatedUserJson);
    }

    public int deleteUser(int userId) throws IOException {
        sendRequest("/users/" + userId, "DELETE", null);
        return 204;
    }

    public String getAllUsers() throws IOException {
        return sendRequest("/users", "GET", null);
    }

    public String getUserById(int userId) throws IOException {
        return sendRequest("/users/" + userId, "GET", null);
    }

    public String getUserByUsername(String username) throws IOException {
        return sendRequest("/users?username=" + username, "GET", null);
    }
}
