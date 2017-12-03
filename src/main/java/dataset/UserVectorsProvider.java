package dataset;

import domain.Post;
import domain.PostType;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.*;

public class UserVectorsProvider {

    public static final List<String> SECTIONS = Arrays.asList(
            "function",
            "http",
            "html",
            "java",
            "go",
            "php",
            "android",
            "windows",
            "javascript",
            "url",
            "jquery",
            "xml",
            "https",
            "python",
            "website",
            "json",
            "mysql",
            "net",
            "ajax",
            "plugin",
            "node",
            "aspnet",
            "ios",
            "jsfiddle",
            "interface",
            "session",
            "mvc",
            "linux",
            "config",
            "firefox",
            "spring",
            "compiler",
            "angular",
            "facebook",
            "iphone",
            "csv",
            "algorithm",
            "xcode",
            "sdk",
            "ubuntu",
            "django",
            "regex",
            "wordpress",
            "dropdown",
            "tree",
            "codethe",
            "webpage",
            "microsoft",
            "textbox",
            "urls",
            "dictionary",
            "listview",
            "wpf",
            "checkbox",
            "popup",
            "exe",
            "backend",
            "github",
            "queue",
            "swift",
            "angularjs",
            "divs",
            "iis",
            "sqlite",
            "datetime",
            "hibernate",
            "laravel",
            "mongodb",
            "jsp",
            "canvas",
            "www",
            "wcf",
            "linq",
            "aspx",
            "htaccess",
            "dataframe",
            "vba",
            "namespace",
            "matlab",
            "plugins",
            "xaml",
            "html5",
            "png",
            "react",
            "iframe",
            "websites",
            "onclick",
            "ipad",
            "ssl",
            "servlet",
            "aws",
            "gridview",
            "scala",
            "logcat",
            "emails",
            "checkboxes",
            "youtube",
            "datatable",
            "tableview",
            "webservice"
    );

    private static final int TAG_COEFFICIENT = 2;

    private static Map<Integer, int[]> userVectors = initUserVectors();

    public static void main(String[] args) throws IOException {
        System.out.println(userVectors);
    }

    private static Map<Integer, int[]> initUserVectors() {
        Map<Integer, Post> idPostsToPosts = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Hackaton\\data\\Posteritos1.csv"))) {
            reader.readLine();
            for (String curr = reader.readLine(); curr != null; curr = reader.readLine()) {
                String[] parts = curr.split(",");
                int id = Integer.parseInt(parts[1]);
                Integer parentId = parts[2].isEmpty() ? null : (int)Double.parseDouble(parts[2]);
                PostType type = parentId == null ? PostType.QUESTION : PostType.ANSWER;
                Integer ownerUserId = parts[3].isEmpty() ? null : (int)Double.parseDouble(parts[3]);
                int score = (int)Double.parseDouble(parts[4]);
                int viewCount = 0;
                Post post = new Post(id, type, parentId, ownerUserId, score, viewCount);
                if (type == PostType.QUESTION) {
                    viewCount = (int)Double.parseDouble(parts[5]);
                    String tagsSingleString = parts[6];
                    if (!tagsSingleString.isEmpty()) {
                        tagsSingleString = tagsSingleString.substring(1, tagsSingleString.length() - 1);
                        String[] tags = tagsSingleString.split("><");
                        for (String tag : tags) {
                            int indexTag = SECTIONS.indexOf(tag);
                            if (indexTag > 0) {
                                post.incSphere(indexTag, TAG_COEFFICIENT);
                            }
                        }
                    }
                }
                idPostsToPosts.put(id, post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Hackaton\\out\\vectors.csv"))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] parts = line.split(",");
                int[] vector = new int[parts.length - 2];
                for (int i = 1; i < parts.length - 1; i++) {
                    vector[i - 1] = (int)Double.parseDouble(parts[i]);
                }
                Post post = idPostsToPosts.get(Integer.valueOf(parts[parts.length - 1]));
                if (post != null) {
                    post.merge(vector);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        idPostsToPosts.values()
                      .stream()
                      .filter(post -> post.getPostTypeId() == PostType.ANSWER)
                      .forEachOrdered(answer -> answer.setSphere(idPostsToPosts.get(answer.getParentId()).getSphere()));

        Map<Integer, List<Post>> collect = idPostsToPosts.values()
                                                         .stream()
                                                         .filter(post -> post.getPostTypeId() == PostType.ANSWER)
                                                         .filter(post -> post.getOwnerUserId() != null)
                                                         .collect(groupingBy(Post::getOwnerUserId, toList()));

        Map<Integer, int[]> userVectors = new TreeMap<>();
        for (Map.Entry<Integer, List<Post>> entry : collect.entrySet()) {
            int[] userVector = new int[SECTIONS.size()];
            for (Post post : entry.getValue()) {
                int[] postVector = post.getSphere();
                for (int i = 0; i < SECTIONS.size(); ++i) {
                    userVector[i] += post.getScore() >= 0 ? postVector[i] : -postVector[i];
                }
            }
            userVectors.put(entry.getKey(), userVector);
        }

        return userVectors;
    }

    public Map<Integer, int[]> getUserVectors() {
        return userVectors;
    }
}
