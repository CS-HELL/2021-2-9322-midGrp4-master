package midproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentTime {
    Timer updateTimer;
    int DELAY = 100;

    /**
     * Set the current time and date real time.
     * @param TimeDate is the JLabel.
     */
    public CurrentTime(JLabel TimeDate){
        updateTimer = new Timer(DELAY, e -> {
            Date currentTime = new Date();
            String formatTimeStr = "hh:mm:ss a";
            DateFormat formatTime = new SimpleDateFormat(formatTimeStr);
            String formattedTimeStr = formatTime.format(currentTime);

            Date currentDate = new Date();
            String formatDateStr = "MM/dd/yyyy";
            DateFormat formatDate = new SimpleDateFormat(formatDateStr);
            String formattedDateStr = formatDate.format(currentDate);

            TimeDate.setText(formattedTimeStr + " (" + formattedDateStr + ")");
        });
       updateTimer.start();
    }
}
