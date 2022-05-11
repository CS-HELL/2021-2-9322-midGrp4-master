package midproject.Client;

import midproject.Functions;
import midproject.Interfaces.EntryInterface;
import midproject.Serializables.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author void
 */
public class EmployeeManageAccountWindow extends javax.swing.JFrame {
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel changePasswordLabel;
    private javax.swing.JPasswordField confirmPassField;
    private javax.swing.JLabel confirmPassLabel;
    private javax.swing.JCheckBox confirmPassShowCheckBox;
    private javax.swing.JTextField idNumTextField;
    private javax.swing.JLabel idNumberLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField newPassField;
    private javax.swing.JLabel newPassLabel;
    private javax.swing.JCheckBox newPassShowCheckBox;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JPasswordField oldPassField;
    private javax.swing.JLabel oldPassLabel;
    private javax.swing.JCheckBox oldPassShowCheckBox;
    private javax.swing.JButton saveButton;
    private final Image icon = Toolkit.getDefaultToolkit().getImage("res\\img\\croppedLogo.png");
    Employee employee;
    Employee updatedEmployee;
    EntryInterface entryInterface;
    SummaryWindow summaryWindow;

    /**
     * Creates new form EmployeeManageAccount
     */
    public EmployeeManageAccountWindow(Employee employee, EntryInterface entryInterface, SummaryWindow summaryWindow){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            this.employee = employee;
            this.entryInterface = entryInterface;
            this.summaryWindow = summaryWindow;
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
        changePasswordLabel = new javax.swing.JLabel();
        idNumberLabel = new javax.swing.JLabel();
        oldPassLabel = new javax.swing.JLabel();
        newPassLabel = new javax.swing.JLabel();
        confirmPassLabel = new javax.swing.JLabel();
        oldPassField = new javax.swing.JPasswordField();
        newPassField = new javax.swing.JPasswordField();
        confirmPassField = new javax.swing.JPasswordField();
        oldPassShowCheckBox = new javax.swing.JCheckBox();
        newPassShowCheckBox = new javax.swing.JCheckBox();
        confirmPassShowCheckBox = new javax.swing.JCheckBox();
        noteLabel = new javax.swing.JLabel();
        idNumTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        setCheck(oldPassShowCheckBox, newPassShowCheckBox, confirmPassShowCheckBox);
        setLabels(idNumberLabel, oldPassLabel, newPassLabel, confirmPassLabel);
        setChangePasswordLabel();
        setMainFrameListener(this);

        //idNumTextField.setText(String.valueOf(employee.getID_Number()));
        idNumTextField.setEditable(false);

        noteLabel.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        noteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noteLabel.setText("Note: For editing of Official documents, send an email to the administrator.");

        setSaveButton(saveButton);
        setCancelButton(cancelButton);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(noteLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(changePasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(idNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(idNumTextField))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(oldPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(oldPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addComponent(newPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(confirmPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(confirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(oldPassShowCheckBox)
                                                        .addComponent(newPassShowCheckBox)
                                                        .addComponent(confirmPassShowCheckBox))))
                                .addContainerGap(40, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(changePasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(idNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idNumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(oldPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(oldPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(oldPassShowCheckBox))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(newPassShowCheckBox)
                                        .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(newPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(confirmPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(confirmPassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(confirmPassShowCheckBox))
                                .addGap(18, 18, 18)
                                .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        getContentPane().setBackground(new Color(0,153,153));
        setIconImage(icon);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setCheck(JCheckBox oldPassCheckBox, JCheckBox newPassCheckBox, JCheckBox confirmPassCheckBox ){
        oldPassCheckBox.setText("show password");
        newPassCheckBox.setText("show password");
        confirmPassCheckBox.setText("show password");

        oldPassCheckBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                oldPassField.setEchoChar((char) 0);
                oldPassField.setEchoChar((char) 0);
            }
            else{
                oldPassField.setEchoChar('*');
                oldPassField.setEchoChar('*');
            }
        });

        newPassCheckBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                newPassField.setEchoChar((char) 0);
                newPassField.setEchoChar((char) 0);
            }
            else{
                newPassField.setEchoChar('*');
                newPassField.setEchoChar('*');
            }
        });

        confirmPassCheckBox.addItemListener(e -> {
            if(e.getStateChange() == ItemEvent.SELECTED){
                confirmPassField.setEchoChar((char) 0);
                confirmPassField.setEchoChar((char) 0);
            }
            else{
                confirmPassField.setEchoChar('*');
                confirmPassField.setEchoChar('*');
            }
        });
    }

    private void setLabels(JLabel idNumberLabel, JLabel oldPassLabel, JLabel newPassLabel, JLabel confirmPassLabel){
        ArrayList<JLabel> jLabels = new ArrayList<>();
        jLabels.add(idNumberLabel);
        jLabels.add(oldPassLabel);
        jLabels.add(newPassLabel);
        jLabels.add(confirmPassLabel);

        for (JLabel jLabel: jLabels) {
            jLabel.setBackground(new java.awt.Color(174, 201, 212));
            jLabel.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
            jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel.setOpaque(true);
        }

        idNumberLabel.setText("ID Number :");
        newPassLabel.setText("New Password :");
        oldPassLabel.setText("Old Password :");
        confirmPassLabel.setText("Confirm Password :");
    }

    private void setSaveButton(JButton saveButton){
        saveButton.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        saveButton.setText("Save");
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                saveButton.setBackground(new java.awt.Color(0, 0, 0));
                saveButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                saveButton.setBackground(new java.awt.Color(204, 204, 204));
                saveButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    updatePassword();
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });
    }

    public void setCancelButton(JButton cancelButton) {
        cancelButton.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                cancelButton.setBackground(new java.awt.Color(0, 0, 0));
                cancelButton.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                cancelButton.setBackground(new java.awt.Color(204, 204, 204));
                cancelButton.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
    }

    private void setChangePasswordLabel(){
        changePasswordLabel.setBackground(new java.awt.Color(174, 201, 212));
        changePasswordLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        changePasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changePasswordLabel.setText("Change Password");
        changePasswordLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        changePasswordLabel.setOpaque(true);
    }

    public void setMainFrameListener(JFrame MainFrame){
        MainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                summaryWindow.setVisible(true);
                super.windowClosing(e);
            }
            @Override
            public void windowClosed(WindowEvent e) {
                summaryWindow.setVisible(true);
                super.windowClosed(e);
            }
        });
    }

    private void updatePassword() throws RemoteException {
        try {
            if (confirmPassField.getPassword() == null || oldPassField.getPassword() == null || newPassField.getPassword() == null) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty.", "Fill the required fields!", JOptionPane.WARNING_MESSAGE);
            } else if (!Arrays.equals(employee.getPassword().toCharArray(), oldPassField.getPassword())) {
                JOptionPane.showMessageDialog(null, "Your current password does not match old Password.", "Wrong Password!", JOptionPane.WARNING_MESSAGE);
            } else if (!Arrays.equals(newPassField.getPassword(), confirmPassField.getPassword())) {
                JOptionPane.showMessageDialog(null, "New Password and Confirm Password are not the same.", "Password Mismatch!", JOptionPane.WARNING_MESSAGE);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                updatedEmployee = this.employee;
                for (char c : newPassField.getPassword()) {
                    stringBuilder.append(c);
                }
                updatedEmployee.setPassword(stringBuilder.toString());
                entryInterface.updateEmployee(updatedEmployee);
                JOptionPane.showMessageDialog(summaryWindow.getContentPane(), "Successfully changed your password, please log in again.", "Success", JOptionPane.INFORMATION_MESSAGE);

                entryInterface.offlineClient(employee);
                entryInterface.PasswordChangedToLogs(employee);
                System.exit(0);
            }
        } catch (Exception x){
            x.printStackTrace();
        }
    }
}
