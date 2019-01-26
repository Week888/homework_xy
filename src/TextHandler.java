import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHandler {
    ArrayList<MyWord> list = new ArrayList<>();
    String text;
    int width; //整段的宽度


    public TextHandler(String text, int width) {
        this.text = text;
        this.width = width;

    }

    public String handle() {
        if (!isInvalidChar(text)) {
            return TextConstant.invalidText;
        } else {
            this.list = splitWord(list, text);
            this.list = countLine(list);
            return outResult();
        }

    }

    public ArrayList<MyWord> splitWord(ArrayList<MyWord> list, String text) {
        StringTokenizer st = new StringTokenizer(text, " ");
        while (st.hasMoreTokens()) {
            list.add(new MyWord(st.nextToken()));
            list.add(new MyWord(" "));
        }
        list.remove(list.size() - 1);
        return list;

    }

    public ArrayList<MyWord> countLine(ArrayList<MyWord> list) {
        int i = 1;
        int count = 0;
        for (int j = 0; j < list.size(); j++) {
            MyWord word = list.get(j);
            if (count + word.mlength < width) {
                count = count + word.mlength;
                word.line.add(i);
            } else if (count + word.mlength == width) {
                word.line.add(i);
                i++;
                count = 0;
            } else {
                int temp = (count + word.mlength) / width;//判断该节占多少行
                count = (count + word.mlength) % width;
                if (count != 0) temp++; //上取整
                while (temp > 0) {
                    word.line.add(i);
                    i++;
                    temp--;
                }
                if (count != 0) i--;
            }

        }
        return list;
    }

    public String outResult() {
        StringBuffer sBuffer = new StringBuffer("");
        for (int j = 0; j < list.size(); j++) {
            MyWord word = list.get(j);
            sBuffer.append(word.content).append("(").append(word.lineToString()).append(")").append(";");
        }
        return sBuffer.toString();
    }


    private boolean isInvalidChar(String text) {
        Pattern pattern = Pattern.compile(TextConstant.regex);
        Matcher m = pattern.matcher(text);
        return m.matches();
    }

}
