package jsoup;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BodyParser {

    private static final String[] BAD_WORDS = new String[]{"\r", "\n", "&amp;", "&lt;", "&gt;", "/pgt;", "\t", "<code>.*?</code>", "<.+?>", "\\s+"};

    //https://stackoverflow.com/questions/47605439/how-to-get-values-out-of-promises

    private Document getDocument(String path) throws IOException {
        return Jsoup.connect(path).get();
    }


    public String getQuestionBody(String url) throws IOException {
        Document doc = null;
        try {
            doc = getDocument(url);
        } catch (IOException e) {
            throw new IOException("Wrong URL");
        }
        Elements posts = doc.getElementsByClass("post-text");
        Element questionPost = posts.first();
        String questionBody = questionPost.html();
        for (String badWord : BAD_WORDS) {
            questionBody = questionBody.replaceAll(badWord, " ");
        }
        return questionBody.trim();
    }


    public static void main(String[] args) throws IOException {
        BodyParser bp = new BodyParser();
        System.out.println(bp.getQuestionBody(""));
    }


}
