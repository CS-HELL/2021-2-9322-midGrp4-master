package midproject.Server;

import midproject.Interfaces.EntryInterface;
import midproject.Serializables.Employee;
import midproject.Servants.EntryServant;
import midproject.TextAreaOutputStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServerWindow extends javax.swing.JFrame {
    // Variables declaration - do not modify
    private javax.swing.JButton Exit_Button1;
    public javax.swing.JTextArea LogArea;
    private javax.swing.JPanel MainJPanel;
    private javax.swing.JButton StartServer_Button;
    private javax.swing.JButton StopServer_Button1;
    private javax.swing.JScrollPane jScrollPane1;
    private final Image icon = Toolkit.getDefaultToolkit().getImage("res\\img\\croppedLogo.png");

    String FilePath;
    String newFilePath;
    String EntryInterfaceString = "EntryInterface";
    Registry registry;
    EntryInterface entryInterface;
    // End of variables declaration

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            ServerWindow serverWindow = new ServerWindow();
            System.setOut(new PrintStream(new TextAreaOutputStream(serverWindow.LogArea)));
            serverWindow.setVisible(true);
        });
    }

    public ServerWindow() {
        initComponents();
    }

    private void initComponents() {

        MainJPanel = new javax.swing.JPanel();
        StartServer_Button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        LogArea = new javax.swing.JTextArea();
        StopServer_Button1 = new javax.swing.JButton();
        Exit_Button1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setStartServerButton(StartServer_Button);

        LogArea.setEditable(false);
        LogArea.setColumns(20);
        LogArea.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        LogArea.setRows(5);
        jScrollPane1.setViewportView(LogArea);

        setStopServerButton(StopServer_Button1);
        setExitButton(Exit_Button1);

        javax.swing.GroupLayout MainJPanelLayout = new javax.swing.GroupLayout(MainJPanel);
        MainJPanel.setLayout(MainJPanelLayout);
        MainJPanelLayout.setHorizontalGroup(
                MainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MainJPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(MainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(MainJPanelLayout.createSequentialGroup()
                                                .addComponent(StartServer_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(MainJPanelLayout.createSequentialGroup()
                                                .addComponent(StopServer_Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                                                .addComponent(Exit_Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        MainJPanelLayout.setVerticalGroup(
                MainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainJPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                                .addGap(16, 16, 16)
                                .addComponent(StartServer_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(MainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(StopServer_Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Exit_Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(MainJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(MainJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        getContentPane().setBackground(new java.awt.Color(0, 204, 204));
        setIconImage(icon);
        pack();
        setLocationRelativeTo(null);

        LogArea.setLineWrap(true);
        LogArea.setWrapStyleWord(true);
    }

    /**
     * Initiate StartServer Method
     */
    private void setStartServerButton(JButton StartServer_Button) {
        StartServer_Button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        StartServer_Button.setText("Start Server");
        StartServer_Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                StartServer_Button.setBackground(new java.awt.Color(0, 0, 0));
                StartServer_Button.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                StartServer_Button.setBackground(new java.awt.Color(204, 204, 204));
                StartServer_Button.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StartServer();
            }
        });
    }

    /**
     * Initiate StopServer() method.
     */
    private void setStopServerButton(JButton StopServer_Button1){
        StopServer_Button1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        StopServer_Button1.setText("Stop Server");
        StopServer_Button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                StopServer_Button1.setBackground(new java.awt.Color(0, 0, 0));
                StopServer_Button1.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                StopServer_Button1.setBackground(new java.awt.Color(204, 204, 204));
                StopServer_Button1.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StopServer();
            }
        });
    }

    public void setExitButton(JButton Exit_Button1) {
        Exit_Button1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Exit_Button1.setText("Exit");
        Exit_Button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Exit_Button1.setBackground(new java.awt.Color(0, 0, 0));
                Exit_Button1.setForeground(new java.awt.Color(0, 204, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Exit_Button1.setBackground(new java.awt.Color(204, 204, 204));
                Exit_Button1.setForeground(new java.awt.Color(0, 0, 0));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
    }

    /**
     * Starts the server.
     */
    private void StartServer(){
        try{
            String ipaddress = JOptionPane.showInputDialog("Input IP Address: ", "192.168.");
            String port = JOptionPane.showInputDialog("Input Port: ", 8888);
            registry = LocateRegistry.createRegistry(Integer.parseInt(port));
            InetAddress inetAddress = InetAddress.getLocalHost();

            System.setProperty("java.rmi.server.hostname", "localhost");
            registry.rebind(EntryInterfaceString, new EntryServant());

            TextAreaOutputStream.printToLogs(EntryInterfaceString + " RMI Initiating . . .");
            TextAreaOutputStream.printToLogs(EntryInterfaceString + " IP Address: " + inetAddress.getHostAddress());
            TextAreaOutputStream.printToLogs(EntryInterfaceString + " Linked to Port: " + port);

            registry = LocateRegistry.getRegistry(ipaddress, Integer.parseInt(port));
            entryInterface = (EntryInterface) registry.lookup(EntryInterfaceString);

        }catch(RemoteException | UnknownHostException | NotBoundException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Server Already Running!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Stops the server and exits the window.
     */
    private void StopServer(){
        printHashmapToFile();
        printTimeLogsToFile();
        JOptionPane.showMessageDialog(null, "Data Successfully Saved!", "Saving data...", JOptionPane.INFORMATION_MESSAGE);
        //System.exit(0);
    }

    /**
     * Save EmployeeFile.
     */
    private void printHashmapToFile(){
        try{
            FilePath = "res\\dat\\EmployeeData.csv";
            newFilePath = "res\\dat\\EmployeeData.csv";

            File oldFile = new File(FilePath);
            if(oldFile.delete()){}

            HashMap<Integer, Employee> EmployeeMap = entryInterface.getEmployeeHashMap();
            File newFile = new File(FilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));

            for(Employee entry : EmployeeMap.values()){
                bufferedWriter.write(entry.Output_toString());
                bufferedWriter.newLine();
                System.out.println(entry.Output_toString());
            }

            System.out.println("Data Saved!");

            fileOutputStream.close();
            bufferedWriter.flush();
            bufferedWriter.close();

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private void printTimeLogsToFile(){
        try{
            FilePath = "res\\dat\\TimeLogs.csv";
            newFilePath = "res\\dat\\TimeLogs.csv";

            File oldFile = new File(FilePath);
            if(oldFile.delete()){}

            HashMap<Integer, ArrayList<String>> EmployeeTimeDetails = entryInterface.getEmployeeTimeDetailsHashMap();

            File newFile = new File(FilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));

            for(ArrayList<String> entry : EmployeeTimeDetails.values()){
                for(String string : entry){
                    bufferedWriter.write(string);
                    bufferedWriter.newLine();
                }

            }

            System.out.println("Data Saved!");

            fileOutputStream.close();
            bufferedWriter.flush();
            bufferedWriter.close();

        }catch(Exception exception){
        exception.printStackTrace();
        }
    }


}

