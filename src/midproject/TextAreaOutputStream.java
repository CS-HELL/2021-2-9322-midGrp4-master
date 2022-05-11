package midproject;

import javax.swing.*;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextAreaOutputStream extends OutputStream {

    private final JTextArea chatTxtArea;
    private final StringBuilder stringBuilder = new StringBuilder();
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("[hh:mm a]");

    public TextAreaOutputStream(JTextArea txtArea){this.chatTxtArea = txtArea;}

    @Override
    public void write(int b) {
        if (b == '\r') {
            return;
        }
        if (b == '\n') {
            final String text = stringBuilder.toString() + "\n";

            chatTxtArea.append(text);
            stringBuilder.setLength(0);
        } else {
            stringBuilder.append((char) b);
        }
    }

    public static void printToLogs(String logMessage){
        System.out.printf("%s %s\n", dateFormatter.format(new Date()), logMessage);
    }
}
