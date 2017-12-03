package dataset;

import domain.AcceptedTypeId;
import domain.Post;
import domain.PostType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostParser {

    private List<Post> posts = new ArrayList<>(800_000);

    public void readPosts(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                PostType postTypeId = Integer.parseInt(parts[1]) == 1 ? PostType.QUESTION : PostType.ANSWER;
                int parrentId = "".equals(parts[2]) ? Integer.parseInt(parts[2]) : -1;
                int acceptedId = "".equals(parts[3]) ? -1 : Integer.parseInt(parts[3]);
                String creationDate = parts[4];
                int score = Integer.parseInt(parts[5]);
                int viewCount = Integer.parseInt(parts[6]);
                String body = parts[7];
                int ownerUserId = Integer.parseInt(parts[8]);
                String title = parts[14];
                int answerCount = Integer.parseInt(parts[16]);
                int commentCount = Integer.parseInt(parts[17]);
                int favoriteCount = Integer.parseInt(parts[18]);
                int[] sphere = new int[0];
                posts.add(new Post(id,
                                    postTypeId,
                                    parrentId,
                                    acceptedId,
                                    creationDate,
                                    score,
                                    viewCount,
                                    body,
                                    ownerUserId,
                                    title,
                                    answerCount,
                                    commentCount,
                                    favoriteCount,
                                    sphere));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


