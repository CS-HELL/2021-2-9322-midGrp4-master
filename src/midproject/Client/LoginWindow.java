package midproject.Client;

import midproject.Interfaces.EntryInterface;
import midproject.Serializables.Employee;
import midproject.Servants.EntryServant;
import midproject.Server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

class LogInWindow extends javax.swing.JFrame {
    // Variables declaration
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField title;
    private javax.swing.JLabel idnumberLabel;
    private javax.swing.JTextField idnumberField;
    private final Image icon = Toolkit.getDefaultToolkit().getImage("res\\img\\croppedLogo.png");

    String EntryInterface = "EntryInterface";

    Registry registry;
    EntryInterface entryInterface;
    // End of variables declaration

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogInWindow().setVisible(true);
            }
        });
    }

    /**
     * Initial Checking of the LoginWindow if the Server is online or offline.
     */
    public LogInWindow() {
        try {
            String IpAddress_Port = JOptionPanelMultiInput();

            String IpAddress = getIpAddress(IpAddress_Port);
            int Port = getPort(IpAddress_Port);

            registry = LocateRegistry.getRegistry(IpAddress, Port);
            entryInterface = (EntryInterface) registry.lookup(EntryInterface);

            initComponents();
        }catch(RemoteException | NotBoundException exception){
            JOptionPane.showMessageDialog(null, "Invalid Server or Server May Be Offline!\nPlease Try Again Later.", "Server Not Found!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Components of the Window Form
     */
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        title = new javax.swing.JTextField();
        logoLabel = new javax.swing.JLabel();
        idnumberLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        idnumberField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        //Title Text Field
        setTitle(title);
        //Logo Label
        setLogoLabel(logoLabel);
        //ID Number and Password Label
        setidnumberLabel_setpasswordLabel(idnumberLabel, passwordLabel);
        //Status Label
        setStatusLabel(statusLabel);
        //ID Number and Password Text Field
        setidnumberField_setpasswordField(idnumberField, passwordField);

        //Login Button
        setLoginButton(loginButton);
        //Register Button
        setRegisterButton(registerButton);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(passwordField))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(idnumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(idnumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 29, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addComponent(logoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(idnumberField, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                        .addComponent(idnumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                        .addComponent(passwordField))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                                .addGap(28, 28, 28)
                                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        getContentPane().setBackground(new java.awt.Color(0, 204, 204));
        setResizable(false);
        setIconImage(icon);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Checks the login details of the Employee if its true to gain access to the application.
     */
    public void Login(){
        try{
            char[] passwordChar = passwordField.getPassword();
            String Password = new String(passwordChar);

            int ID_Number = Integer.parseInt(idnumberField.getText());


            int Outcome = entryInterface.checkLoginInfo(ID_Number, Password);

            if(Outcome == 0) {
                statusLabel.setText("No Employee Associated With The ID Number.");
            }

            else if(Outcome == 1) {
                java.awt.EventQueue.invokeLater(() -> {
                    try {
                        Employee employeeData = entryInterface.getEmployeeData(ID_Number);
                        employeeData.setEmployee(employeeData);
                        boolean isOnline = entryInterface.currentlyOnline(employeeData);

                        if(isOnline){
                            JOptionPane.showMessageDialog(null, "Employee is already online!", "Duplicate User!", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            entryInterface.onlineClient(employeeData);
                            CommandWindow commandWindow = new CommandWindow(employeeData, entryInterface);
                            this.dispose();
                            commandWindow.setVisible(true);
                        }

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }

            else {
                statusLabel.setText("Invalid Password Associated With The Account.");
            }

        }catch(RemoteException exception){
            JOptionPane.showMessageDialog(null, "Server Has Been Forcibly Closed.", "Error Connecting to the Server!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);

        } catch (NumberFormatException n){
            statusLabel.setText("Invalid ID Number!");
        }
    }

    /**
     * Opens a new window to register an Employee.
     */
    public void Register(){
        System.exit(0);
    }

    /**
     * Retrieves the Ip Address from the String.
     * @param string unsplitted String.
     * @return string Ip Address.
     */
    private String getIpAddress(String string){
        String[] split = string.split(" ");
        return split[0];
    }

    /**
     * Retrieves the Port from the String.
     * @param string unsplitted String.
     * @return integer Port.
     */
    private int getPort(String string){
        String[] split = string.split(" ");
        int number = 0;

        if(string.equals("")){
            System.exit(0);
        }

        try{
            number = Integer.parseInt(split[1]);
        }catch(Exception exception){
            JOptionPane.showMessageDialog(null, "Port input should be numerical!", "Invalid Input!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return number;
    }

    /**
     * Set the title Label.
     * @param title is the Label.
     */
    private void setTitle(JTextField title){
        title.setEditable(false);
        title.setBackground(new java.awt.Color(0, 0, 0));
        title.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        title.setForeground(new java.awt.Color(0, 204, 204));
        title.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        title.setText("HELLO, EMPLOYEE!");
        title.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    }

    /**
     * Set the Log in Button.
     * @param loginButton is the Button.
     */
    private void setLoginButton(JButton loginButton){
        loginButton.setBackground(new java.awt.Color(204, 204, 204));
        loginButton.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        loginButton.setText("Login");
        loginButton.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        loginButton.setFocusPainted(false);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                loginButton.setBackground(new java.awt.Color(0, 0, 0));
                loginButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                loginButton.setBackground(new java.awt.Color(204, 204, 204));
                loginButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Login();
            }
        });
    }

    /**
     * Set the Register Button.
     * @param registerButton is the Button.
     */
    private void setRegisterButton(JButton registerButton){
        registerButton.setBackground(new java.awt.Color(204, 204, 204));
        registerButton.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        registerButton.setText("Exit");
        registerButton.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        registerButton.setFocusPainted(false);
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                registerButton.setBackground(new java.awt.Color(0, 0, 0));
                registerButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                registerButton.setBackground(new java.awt.Color(204, 204, 204));
                registerButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Register();
            }
        });
    }

    /**
     * Set the Logo Label.
     * @param logoLabel is the Label.
     */
    private void setLogoLabel(JLabel logoLabel) {
        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new javax.swing.ImageIcon("res\\img\\logo.png")); // NOI18N
    }

    /**
     * Sets the ID Number and Password Labels.
     * @param idnumberLabel is the ID Number Label.
     * @param passwordLabel is the Password Label.
     */
    private void setidnumberLabel_setpasswordLabel(JLabel idnumberLabel, JLabel passwordLabel){
        idnumberLabel.setText("ID Number: ");
        passwordLabel.setText("Password: ");
    }

    /**
     * Set the Status Label.
     * @param statusLabel is the Label.
     */
    public void setStatusLabel(JLabel statusLabel) {
        statusLabel.setForeground(new java.awt.Color(255, 0, 0));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Sets the ID Number and Password Field.
     * @param idnumberField is the ID Number Field.
     * @param passwordField is the Password Field.
     */
    private void setidnumberField_setpasswordField(JTextField idnumberField, JPasswordField passwordField) {
        idnumberField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        passwordField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    }

    /**
     * JOption Panel with 2 Inputs for the IpAddress and Port.
     * @return the String with IpAddress and Port.
     */
    private String JOptionPanelMultiInput(){
        JTextField IpAddressField = new JTextField(8);
        IpAddressField.setText("localhost");
        JTextField PortField = new JTextField(8);
        PortField.setText("8888");
        String IpAddress_Port = "";

        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Ip Address: "));
        jPanel.add(IpAddressField);

        jPanel.add(Box.createHorizontalStrut(10));

        jPanel.add(new JLabel("Port: "));
        jPanel.add(PortField);

        int result = JOptionPane.showConfirmDialog(null, jPanel, "Please Enter Ip Address and Port of the Server.", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION){
            IpAddress_Port = IpAddressField.getText() + " " + PortField.getText();
        }
        return IpAddress_Port;
    }
}
