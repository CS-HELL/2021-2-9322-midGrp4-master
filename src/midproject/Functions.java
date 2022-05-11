package midproject;

import midproject.Interfaces.EntryInterface;
import midproject.Serializables.Employee;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class Functions {

    /**
     * Calculates the age of the employee based on their birthdate.
     * @param Birthdate is the birthdate;
     * @return Age
     */
    public static int calculateAge(String Birthdate){
        int Age = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(Birthdate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);

            LocalDate localDate = LocalDate.of(year, month, day);
            LocalDate nowDate = LocalDate.now();
            Period difference = Period.between(localDate, nowDate);

            Age = difference.getYears();
        }catch(Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error In Reading Birthdate!", JOptionPane.ERROR_MESSAGE);
        }
        return Age;
    }

    /**
     * Set the WindowListener for this frame.
     * @param MainFrame is the Frame.
     */
    public void setMainFrameListener(JFrame MainFrame, EntryInterface entryInterface, Employee employee){
        MainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    entryInterface.offlineClient(employee);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
                super.windowClosing(e);
            }
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    entryInterface.offlineClient(employee);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
                super.windowClosed(e);
            }
        });
    }
}
