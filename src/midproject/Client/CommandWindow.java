package midproject.Client;

import midproject.Functions;
import midproject.Interfaces.EntryInterface;
import midproject.Serializables.Employee;
import midproject.CurrentTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Calendar;

public class CommandWindow extends javax.swing.JFrame {
    // Variables declaration
    private javax.swing.JLabel TimeDateLabel;
    private javax.swing.JTextField greetingsTextField;
    private javax.swing.JPanel panel1;
    private javax.swing.JTextField statusTextField;
    private javax.swing.JButton summaryButton;
    private javax.swing.JButton timeInContinueButton;
    private javax.swing.JButton timeOutButton;
    private final Image icon = Toolkit.getDefaultToolkit().getImage("res\\img\\croppedLogo.png");

    Employee employee;
    EntryInterface entryInterface;

    // End of variables declaration

    public CommandWindow(Employee employee, EntryInterface entryInterface) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            this.employee = employee;
            this.entryInterface = entryInterface;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CommandWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        initComponents();
    }

    private void initComponents() {
        panel1 = new javax.swing.JPanel();
        greetingsTextField = new javax.swing.JTextField();
        statusTextField = new javax.swing.JTextField();
        timeInContinueButton = new javax.swing.JButton();
        timeOutButton = new javax.swing.JButton();
        summaryButton = new javax.swing.JButton();
        TimeDateLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setForeground(new java.awt.Color(255, 255, 255));

        //Greetings TextField
        setGreetingsTextField(greetingsTextField);
        //Status TextField
        setStatusTextField(statusTextField);
        //Time in Continue Button
        setTimeInContinueButton(timeInContinueButton);
        //Time out Button
        setTimeOutButton(timeOutButton);
        //Summary Button
        setSummaryButton(summaryButton);
        //Time Date Label
        setTimeDateLabel(TimeDateLabel);
        //Main Panel WindowListener
        Functions functions = new Functions();
        functions.setMainFrameListener(this, entryInterface, employee);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(greetingsTextField)
                        .addComponent(statusTextField)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TimeDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timeInContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(timeOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(summaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(greetingsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TimeDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(timeInContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(summaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().setBackground(new java.awt.Color(0, 204, 204));
        setIconImage(icon);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Set the Greetings Text Field.
     * @param greetingsTextField is the Text Field.
     */
    private void setGreetingsTextField(JTextField greetingsTextField){
        greetingsTextField.setEditable(false);
        greetingsTextField.setBackground(new java.awt.Color(0, 0, 0));
        greetingsTextField.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        greetingsTextField.setForeground(new java.awt.Color(0, 204, 204));
        greetingsTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Calendar c = Calendar.getInstance();

        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay < 12){
            greetingsTextField.setText("Good Morning Employee: " + employee.getLastName() + ", " + employee.getFirstName());
        }else if(timeOfDay >= 12 && timeOfDay < 18){
            greetingsTextField.setText("Good Afternoon Employee: " + employee.getLastName() + ", " + employee.getFirstName());
        }else {
            greetingsTextField.setText("Good Evening Employee: " + employee.getLastName() + ", " + employee.getFirstName());
        }
    }

    /**
     * Set the Status Text Field.
     * @param statusTextField is the Text Field.
     */
    private void setStatusTextField(JTextField statusTextField){
        statusTextField.setEditable(false);
        statusTextField.setBackground(new java.awt.Color(0, 0, 0));
        statusTextField.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        statusTextField.setForeground(new java.awt.Color(0, 204, 204));
        statusTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        try {
            boolean isWorking = entryInterface.isInTimerMap(employee.getID_Number());
            if(isWorking){
                statusTextField.setText("Status: Working.");
                timeInContinueButton.setEnabled(false);
                timeOutButton.setEnabled(true);
            }
            else{
                statusTextField.setText("Status: Break.");
                timeInContinueButton.setEnabled(true);
                timeOutButton.setEnabled(false);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the Time in and Continue Button.
     * @param timeInContinueButton is the Button.
     */
    private void setTimeInContinueButton(JButton timeInContinueButton){
        timeInContinueButton.setText("Time In");
        timeInContinueButton.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        timeInContinueButton.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        timeInContinueButton.setFocusPainted(false);
        timeInContinueButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                timeInContinueButton.setBackground(new java.awt.Color(0, 0, 0));
                timeInContinueButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                timeInContinueButton.setBackground(new java.awt.Color(204, 204, 204));
                timeInContinueButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    boolean isWorking = entryInterface.isInTimerMap(employee.getID_Number());
                    TimeInWorking(isWorking);

                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });
    }

    /**
     * Set the Time out Button.
     * @param timeOutButton is the Button.
     */
    private void setTimeOutButton(JButton timeOutButton){
        timeOutButton.setText("Time Out");
        timeOutButton.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        timeOutButton.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        timeOutButton.setFocusPainted(false);
        timeOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                timeOutButton.setBackground(new java.awt.Color(0, 0, 0));
                timeOutButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                timeOutButton.setBackground(new java.awt.Color(204, 204, 204));
                timeOutButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    boolean isWorking = entryInterface.isInTimerMap(employee.getID_Number());
                    TimeOutWorking(isWorking);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });
    }

    /**
     * Set the Summary Button.
     * @param summaryButton is the Button.
     */
    private void setSummaryButton(JButton summaryButton){
        summaryButton.setText("Summary");
        summaryButton.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        summaryButton.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        summaryButton.setFocusPainted(false);
        summaryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                summaryButton.setBackground(new java.awt.Color(0, 0, 0));
                summaryButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                summaryButton.setBackground(new java.awt.Color(204, 204, 204));
                summaryButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Summary();
            }
        });
    }

    /**
     * Set the Time Date Label.
     * @param TimeDateLabel is the Label.
     */
    public void setTimeDateLabel(JLabel TimeDateLabel){
        TimeDateLabel.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        TimeDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        new CurrentTime(TimeDateLabel);
    }

    private void Summary(){
        SummaryWindow summaryWindow = new SummaryWindow(employee, entryInterface, this);
        summaryWindow.setVisible(true);
        this.setVisible(false);
    }

    private void TimeInWorking(boolean isWorking){
        try {
            if (!isWorking) {
                entryInterface.timeIn(employee);
                statusTextField.setText("Status: Working.");
                timeInContinueButton.setEnabled(false);
                timeOutButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "You are already timed in.");
            }
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void TimeOutWorking(boolean isWorking){
        try {
            if (isWorking) {
                entryInterface.timeOut(employee);
                statusTextField.setText("Status: Break.");
                timeInContinueButton.setEnabled(true);
                timeOutButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "You are not timed in yet.");
            }
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
