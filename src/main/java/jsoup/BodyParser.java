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



        Element questionDiv = doc.getElementsByClass("question").first();

        //body
        Elements posts = questionDiv.getElementsByClass("post-text");
        Element questionPost = posts.first();

        //tags
        Elements tags = questionDiv.getElementsByClass("post-taglist");



        String questionBody = questionPost.html();
        for (String badWord : BAD_WORDS) {
            questionBody = questionBody.replaceAll(badWord, " ");
        }

        StringBuilder questionBodyBuilder = new StringBuilder(questionBody);
        for(Element tag : tags){
            questionBodyBuilder.append((tag.getElementsByTag("a").html().trim()).replaceAll("\\s+", " "));
        }
        questionBody = questionBodyBuilder.toString();
        return questionBody.trim();
    }




    public static void main(String[] args) throws IOException {
        BodyParser bp = new BodyParser();
        System.out.println(bp.getQuestionBody("https://stackoverflow.com/questions/47541598/profiling-inlined-c-functions-with-visual-studio-compiler"));
    }


}
