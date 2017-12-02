package connector;

import domain.Post;

public class PostsConnector {

    public static void changeSphere(Post editing, int[] newSphere){
        int[] oldSphere = editing.getSphere();
        int[] resultSphere = new int[newSphere.length];
        System.arraycopy(newSphere, 0, resultSphere, 0, newSphere.length);
        for(int i = 0; i < oldSphere.length; i++){
            resultSphere[i] += oldSphere[i];
        }
        editing.setSphere(resultSphere);
    }

}
