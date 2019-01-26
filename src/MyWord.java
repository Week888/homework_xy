import java.util.ArrayList;

public class MyWord {
    public String content;
    public int mlength;
    public ArrayList<Integer> line = new ArrayList<>() ;

    public MyWord(String content) {
        this.content = content;
        mlength = content.length();

    }

    public String lineToString(){
        String temp =line.toString();
        return temp.substring(1,temp.length()-1);
    }


}
