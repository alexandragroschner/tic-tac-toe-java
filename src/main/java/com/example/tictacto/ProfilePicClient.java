package com.example.tictacto;

import com.example.tictacto.model.ProfilePic;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.net.HttpURLConnection;
import java.net.URL;

public class ProfilePicClient extends Service<ProfilePic> {
    public ProfilePicClient(ImageView profilePic) {
        setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                profilePic.setImage(new Image(((ProfilePic)workerStateEvent.getSource().getValue()).getUrl()));
            }
        });
    }

    @Override
    protected Task<ProfilePic> createTask() {
        return new Task<ProfilePic>() {
            @Override
            protected ProfilePic call() throws Exception {
                return getProfilePic();
            }
        };
    }

    public ProfilePic getProfilePic() throws Exception {

        String apiUrl = "https://cataas.com";

        URL url = new URL(apiUrl + "/cat?json=true");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        byte[] bytearray = con.getInputStream().readAllBytes();

        if (con.getResponseCode() != 200) {
            throw new Exception("Could not retrieve profile pic");
        }
        String response = new String(bytearray);

        // to map json response to java object
        ObjectMapper mapper = new ObjectMapper();
        // json key "url" contains pic-specific portion of path but needs to be stripped of '"'
        String fullPicUrl = apiUrl + StringUtils.strip(mapper.readTree(response).get("url").toString(), "\"");

        con.disconnect();

        return new ProfilePic(fullPicUrl);
    }
}
