package midproject.Client;

import midproject.Interfaces.EntryInterface;
import midproject.Serializables.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SummaryWindow extends javax.swing.JFrame {

    // Variables declaration - do not modify
    private javax.swing.JLabel ageLabel;
    private javax.swing.JLabel birthdayLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel greetingsLabel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel idNumLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel timeLogLabel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton manageAccountButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JTable timeLogTable;
    private final Image icon = Toolkit.getDefaultToolkit().getImage("res\\img\\croppedLogo.png");

    Employee employee;
    EntryInterface entryInterface;
    CommandWindow commandWindow;
    // End of variables declaration


    /**
     * Creates new form SummaryWindow
     */
    public SummaryWindow(Employee employee, EntryInterface entryInterface, CommandWindow commandWindow) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            this.employee = employee;
            this.entryInterface = entryInterface;
            this.commandWindow = commandWindow;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SummaryWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D graphics2D = (Graphics2D) graphics;
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int width = getWidth();
                int height = getHeight();
                Color color1 = new java.awt.Color(	8	,80	,120);
                Color color2 = new java.awt.Color(	133	,216	,206);
                GradientPaint gradientPaint = new GradientPaint(0, 0, color1, 0, height, color2);
                graphics2D.setPaint(gradientPaint);
                graphics2D.fillRect(0, 0, width, height);
            }
        };

        greetingsLabel = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        positionLabel = new javax.swing.JLabel();
        idNumLabel = new javax.swing.JLabel();
        timeLogLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        birthdayLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        ageLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        manageAccountButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        timeLogTable = new javax.swing.JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 51));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        setGreetings_TimeLog_Label(greetingsLabel, timeLogLabel);
        setProfileInfoLabel(positionLabel, idNumLabel, emailLabel, nameLabel, birthdayLabel, genderLabel, ageLabel);
        greetingsLabel.setBackground(new java.awt.Color(0, 0, 0));
        greetingsLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        greetingsLabel.setForeground(new java.awt.Color(0, 204, 204));
        greetingsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        greetingsLabel.setText("SUMMARY");
        greetingsLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        greetingsLabel.setOpaque(true);


        setIconLabel(iconLabel);
        setManageAccountButton();
        setLogoutButton();
        setBackButton(backButton);
        setMainFrameListener(this);
        // end of setting


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(greetingsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(positionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(idNumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(birthdayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(ageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(60, 60, 60)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(manageAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(backButton)
                                                .addContainerGap())))
                        .addComponent(timeLogLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(greetingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(23, 23, 23)
                                                .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(positionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(idNumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, Short.MAX_VALUE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(manageAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(birthdayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(timeLogLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setTimeLogTable(timeLogTable);

        jScrollPane1.setViewportView(timeLogTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setBackground(new java.awt.Color(	133	,216	,206));
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        timeLogTable.getTableHeader().setReorderingAllowed(false);
        getContentPane().setBackground(new Color(0,153,153));
        setIconImage(icon);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Set the appropriate values for the labels.
     * @param positionLabel is the Position of the employee.
     * @param idNumLabel is the ID Number of the employee.
     * @param emailLabel is the Email of the employee.
     * @param nameLabel is the Full name of the employee.
     * @param birthdayLabel is the Birthday of the employee.
     * @param genderLabel is the Gender of the employee.
     * @param ageLabel is the Age of the employee.
     */
    private void setProfileInfoLabel(JLabel positionLabel, JLabel idNumLabel, JLabel emailLabel, JLabel nameLabel, JLabel birthdayLabel, JLabel genderLabel, JLabel ageLabel){ //not working because jlabel getName returns null hecc
        ArrayList<JLabel> jLabelArrayList = new ArrayList<>();

        jLabelArrayList.add(positionLabel); jLabelArrayList.add(idNumLabel); jLabelArrayList.add(emailLabel);
        jLabelArrayList.add(nameLabel); jLabelArrayList.add(birthdayLabel);
        jLabelArrayList.add(genderLabel); jLabelArrayList.add(ageLabel);

        for(JLabel jLabel : jLabelArrayList){
            jLabel.setBackground(new java.awt.Color(174, 201, 212));
            jLabel.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
            jLabel.setForeground(new java.awt.Color(0, 0, 0));
            jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel.setToolTipText("");
            jLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
            jLabel.setOpaque(true);
        }

        positionLabel.setText(employee.getPosition());
        idNumLabel.setText(String.valueOf(employee.getID_Number()));
        emailLabel.setText(employee.getEmailAddress());
        nameLabel.setText(employee.FullName_toString());
        birthdayLabel.setText(employee.getBirthdate());
        genderLabel.setText(employee.getGender());
        ageLabel.setText(String.valueOf(employee.getAge()));
    }

    private void setManageAccountButton(){
        manageAccountButton.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        manageAccountButton.setText("Manage Accounts");
        manageAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                manageAccountButton.setBackground(new java.awt.Color(0, 0, 0));
                manageAccountButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                manageAccountButton.setBackground(new java.awt.Color(204, 204, 204));
                manageAccountButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(employee.getPosition().equalsIgnoreCase("HR Manager")){
                    super.mouseClicked(e);
                    try {
                        ManageAccount();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else{
                    try{
                        EmployeeManageAccount();
                    } catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }

    private void setLogoutButton(){
        logoutButton.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                logoutButton.setBackground(new java.awt.Color(0, 0, 0));
                logoutButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                logoutButton.setBackground(new java.awt.Color(204, 204, 204));
                logoutButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int choice = JOptionPane.showConfirmDialog(null, "You are about to log out, continue?", "Verify.", JOptionPane.YES_NO_OPTION);
                if (choice == 0){
                    try {
                        entryInterface.offlineClient(employee);
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                    System.exit(0);
                }
            }
        });
    }

    private void setIconLabel(JLabel iconLabel) {
        Image properlyScaledIconF = new javax.swing.ImageIcon("res\\img\\ayaka.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        Image properlyScaledIconM = new javax.swing.ImageIcon("res\\img\\zhongli.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH);

        if(employee.getGender().equals("Female")){
            iconLabel.setIcon(new ImageIcon(properlyScaledIconF));
        }
        else{
            iconLabel.setIcon(new ImageIcon(properlyScaledIconM));
        }
        iconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 204)));
    }

    private void setGreetings_TimeLog_Label(JLabel greetingsLabel, JLabel timeLogLabel){
        greetingsLabel.setBackground(new java.awt.Color(0, 0, 0));
        greetingsLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        greetingsLabel.setForeground(new java.awt.Color(0, 204, 204));
        greetingsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        greetingsLabel.setText("Hello");
        greetingsLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        greetingsLabel.setOpaque(true);

        timeLogLabel.setBackground(new java.awt.Color(0, 0, 0));
        timeLogLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        timeLogLabel.setForeground(new java.awt.Color(0, 204, 204));
        timeLogLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLogLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        timeLogLabel.setOpaque(true);
        timeLogLabel.setText("Time Log");
    }

    private void setBackButton(JButton backButton){
        Image properlyScaledIcon = new javax.swing.ImageIcon("res\\img\\back-arrow.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        backButton.setIcon(new ImageIcon(properlyScaledIcon));
        backButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                backButton.setBackground(new java.awt.Color(0, 204, 204));
                backButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                backButton.setBackground(new java.awt.Color(204, 204, 204));
                backButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                setVisible(false);
                dispose();
                commandWindow.setVisible(true);
            }
        });
    }

    private void setTimeLogTable(JTable timeLogTable){

        timeLogTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204)));
        timeLogTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Date", "Time In", "Time Out", "Working Time"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        timeLogTable.getTableHeader().setReorderingAllowed(false);

        DefaultTableModel model = (DefaultTableModel) timeLogTable.getModel();

        ArrayList<String> timeDetails = null;
        try {
            timeDetails = entryInterface.getEmployeeTimeDetails(employee.getID_Number());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(timeDetails != null){
            for(String string : timeDetails){
                String[] split = string.split(",");
                String Date = split[1];
                String TimeIn = split[2];
                String TimeOut = split[3];
                String Duration = split[4];

                model.addRow(new Object[]{Date, TimeIn, TimeOut, Duration});
            }
        }
    }

    public void setMainFrameListener(JFrame MainFrame){
        MainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                commandWindow.setVisible(true);
                super.windowClosing(e);
            }
            @Override
            public void windowClosed(WindowEvent e) {
                commandWindow.setVisible(true);
                super.windowClosed(e);
            }
        });
    }

    public void ManageAccount() throws IOException {
        ManageAccountWindow manageAccountWindow = new ManageAccountWindow(employee,entryInterface, this);
        manageAccountWindow.setVisible(true);
        manageAccountWindow.setLocationRelativeTo(null);
        setVisible(false);
    }

    public void EmployeeManageAccount() throws IOException {
        EmployeeManageAccountWindow employeeManageAccountWindow = new EmployeeManageAccountWindow(employee,entryInterface, this);
        employeeManageAccountWindow.setVisible(true);
        employeeManageAccountWindow.setLocationRelativeTo(null);
        setVisible(false);
    }
}
