package midproject.Servants;

import midproject.Functions;
import midproject.Interfaces.EntryInterface;
import midproject.Serializables.Employee;
import midproject.Serializables.Timer;
import midproject.TextAreaOutputStream;

import javax.swing.*;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EntryServant extends UnicastRemoteObject implements EntryInterface {
    // Variables declaration
    String FilePath; String newFilePath;

    HashMap<Integer, Employee> EmployeeMap = new HashMap<>();
    HashMap<Integer, String> EmployeeLoginMap = new HashMap<>();
    HashMap<Integer, Timer> TimerMap = new HashMap<>();
    HashMap<Integer, ArrayList<String>> EmployeeTimeDetails = new HashMap<>();

    ArrayList<Integer> onlineClients;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
    DateFormat dateFormat = new SimpleDateFormat("MMMM/dd/yyyy");
    Date date = new Date();

    String timeIn, timeOut;
    // End of Variables declaration

    public EntryServant() throws RemoteException {
        super();
        populateEmployeeMap();
        populateEmployeeTimeDetails();
        onlineClients = new ArrayList<>();
        TextAreaOutputStream.printToLogs("Fetching Employee Data . . .");
    }

    /**
     * Verifies the log in of a user.
     * @param ID_Number is the id number.
     * @param Password is the password.
     * @return
     * @throws RemoteException
     */
    @Override
    public int checkLoginInfo(int ID_Number, String Password) throws RemoteException {
        int Outcome = searchEmployeeMap(ID_Number, Password);
        if(Outcome == 2 && !isOnline(ID_Number)) TextAreaOutputStream.printToLogs("\nEmployee with ID Number: " + ID_Number + " has entered a wrong password!");

        return searchEmployeeMap(ID_Number, Password);
    }

    /**
     * Method to insert a new employee to the hashmap.
     * @param employee is the new employee.
     * @throws RemoteException
     */
    @Override
    synchronized public void insertToEmployeeHashmap(Employee employee) throws RemoteException {
        EmployeeMap.put(employee.getID_Number(), employee);
        EmployeeLoginMap.put(employee.getID_Number(), employee.getPassword());
        TextAreaOutputStream.printToLogs("Employee: " + employee.getID_Number() + " has been added successfully.");
        printHashmapToFile();
    }

    /**
     * Checks if the employee is in the hashmap to avoid multiple log ins.
     * @param ID_Number is the id number.
     * @return boolean isWorking.
     * @throws RemoteException
     */
    @Override
    public boolean isInEmployeeHashmap(int ID_Number) throws RemoteException {
        boolean isWorking = false;
        if(EmployeeMap.containsKey(ID_Number)){
            isWorking = true;
        }
        return isWorking;
    }

    /**
     * Returns if an employee with the same ID Number is online so prevent multiple access.
     * @param Employee is the Last Name and First Name of the client.
     * @return true or false.
     * @throws RemoteException
     */
    @Override
    public boolean currentlyOnline(Employee Employee) throws RemoteException {
        boolean isOnline = false;
        for(Integer onlineClients : onlineClients){
            if (onlineClients == Employee.getID_Number()) {
                isOnline = true;
                break;
            }
        }
        return isOnline;
    }

    /**
     * Method to add client that logged in into the active list.
     * @param Employee is the Last Name and First Name of the client
     * @throws RemoteException
     */
    @Override
    public synchronized void onlineClient(Employee Employee) throws RemoteException {
        onlineClients.add(Employee.getID_Number());
        TextAreaOutputStream.printToLogs("Employee with ID Number: " + Employee.getID_Number() + " has logged in!");
    }

    /**
     * Method to remove client that logged out from the active list.
     * @param Employee is the Last Name and First Name of the client.
     * @throws RemoteException
     */
    @Override
    public synchronized void offlineClient(Employee Employee) throws RemoteException {
        onlineClients.remove(Integer.valueOf(Employee.getID_Number()));
        TextAreaOutputStream.printToLogs("Employee with ID Number: " + Employee.getID_Number() + " has logged out!");
    }

    /**
     * Method for the time in button.
     * @param employee is the employee.
     * @throws RemoteException
     */
    @Override
    public synchronized void timeIn(Employee employee) throws RemoteException {
        if(TimerMap.containsKey(employee.getID_Number())){
            TextAreaOutputStream.printToLogs("Employee with ID Number: " + employee.getID_Number() + " is trying to time in again.");
        } else {
            Timer timer = new Timer(employee);
            timer.startTimer();
            TimerMap.put(employee.getID_Number(), timer);
            String currentTime = dateFormatter.format(new Date());

            TextAreaOutputStream.printToLogs("Employee with ID Number: " + employee.getID_Number() + " timed in at " + currentTime);

            timeIn = currentTime;
        }
    }

    /**
     * Method for the time out button.
     * @param employee is the employee.
     * @throws RemoteException
     */
    @Override
    public synchronized void timeOut(Employee employee) throws RemoteException {
        if(!TimerMap.containsKey(employee.getID_Number())){
            TextAreaOutputStream.printToLogs("Employee with ID Number: " + employee.getID_Number() + " is trying to time out again.");
        } else {

            TimerMap.get(employee.getID_Number()).stopTimer();

            long elapsedTime = TimerMap.get(employee.getID_Number()).getTime();
            int elapsedSeconds = (int) (elapsedTime / 1000) % 60;
            int elapsedMinutes = (int) (elapsedTime / (1000*60)) % 60;
            int elapsedHours = (int) (elapsedTime / (1000*60*60)) % 24;

            TimerMap.remove(employee.getID_Number());
            String currentTime = dateFormatter.format(new Date());

            TextAreaOutputStream.printToLogs("Employee with ID Number: " + employee.getID_Number() + " timed out at " + currentTime);
            String timeElapsed = "";

            timeElapsed = PrintStoreElapsed(elapsedHours, elapsedMinutes, elapsedSeconds, employee.getID_Number(), timeElapsed);

            String currentDate = dateFormat.format(date);
            timeOut = currentTime;

            ArrayList<String> timeInTimeOutDetails = new ArrayList<>();

            timeInTimeOutDetails.add(employee.getID_Number() + "," + currentDate + "," + timeIn + "," + timeOut + "," + timeElapsed);

            addToEmployeeTimeDetails(employee.getID_Number(), currentDate, timeElapsed, timeIn, timeOut);

            printTimeLogsToFile();
        }
    }

    /**
     * Method to return the Employee Data to the current client User.
     * @param ID_Number is the ID Number contained in the EmployeeMap.
     * @return the employee details.
     * @throws RemoteException
     */
    @Override
    public Employee getEmployeeData(int ID_Number) throws RemoteException {
        Employee employee = new Employee();
        for(Map.Entry pairEntry : EmployeeMap.entrySet()){
            if(pairEntry.getKey().equals(ID_Number)){
                employee = (Employee) pairEntry.getValue();
            }
        }
        return employee;
    }

    /**
     * Method to fetch the Employee Hashmap.
     * @return the updated Hashmap Data.
     * @throws RemoteException
     */
    @Override
    public HashMap<Integer, Employee> getEmployeeHashMap() throws RemoteException {
        return EmployeeMap;
    }

    /**
     * Method to return the time logs arraylist of a certain employee.
     * @param ID_Number is the ID Number of the employee.
     * @return Arraylist of time logs.
     */
    @Override
    public ArrayList<String> getEmployeeTimeDetails(int ID_Number){
        return EmployeeTimeDetails.get(ID_Number);
    }

    /**
     * Retrieves the employee time details hashmap.
     * @return is the time details.
     * @throws RemoteException
     */
    @Override
    public HashMap<Integer, ArrayList<String>> getEmployeeTimeDetailsHashMap() throws RemoteException {
        return EmployeeTimeDetails;
    }

    /**
     * Checks the timer map if the employee is currently logged in.
     * @param ID_Number is the id number of the employee.
     * @return boolean isWorking.
     * @throws RemoteException
     */
    @Override
    public boolean isInTimerMap(int ID_Number) throws RemoteException {
        boolean isWorking = false;
        if(TimerMap.containsKey(ID_Number)){
            isWorking = true;
        }
        return isWorking;
    }

    /**
     * Update the employee in the hashmap.
     * @param employee is the employee.
     * @throws RemoteException
     */
    @Override
    public void updateEmployee(Employee employee) throws RemoteException {
        EmployeeMap.replace(employee.getID_Number(), employee);
        EmployeeLoginMap.replace(employee.getID_Number(), employee.getPassword());
        printHashmapToFile();
    }

    /**
     * Prints the message for the logs of a successful password change.
     * @param userEmployee is the user employee.
     * @throws RemoteException
     */
    @Override
    public void PasswordChangedToLogs(Employee userEmployee) throws RemoteException {
        TextAreaOutputStream.printToLogs("Employee: " + userEmployee.getID_Number() + " has changed their Password.");
    }

    /**
     * Prints the message for the logs of which employee data got updated and who updated it.
     * @param userEmployee is the current user employee.
     * @param updatedEmployee is the updated employee.
     * @throws RemoteException
     */
    @Override
    public void UpdateToLogs(Employee userEmployee, Employee updatedEmployee) throws RemoteException {
        TextAreaOutputStream.printToLogs("Employee: " + userEmployee.getID_Number() + " has updated the data of Employee: " + updatedEmployee.getID_Number() + ".");
    }


    /**
     * Populates the Employee Hashmap with data from res\\dat\\EmployeeData.csv
     */
    private void populateEmployeeMap(){
        FilePath = "res\\dat\\EmployeeData.csv";
        String line;

        try(
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FilePath));
        ){
            int ID_Number, Age;
            String Password, LastName, FirstName, MiddleName, Position, EmailAddress, Gender;
            while((line = bufferedReader.readLine()) != null){
                String[] split = line.split(",");

                ID_Number = Integer.parseInt(split[0]);
                Password = split[1];
                LastName = split[2];
                FirstName = split[3];
                MiddleName = split[4];
                String Birthdate = split[5];
                Position = split[6];
                EmailAddress = split[7];
                Age = Functions.calculateAge(Birthdate);
                Gender = split[8];

                Employee employee = new Employee(ID_Number, Password, LastName, FirstName, MiddleName, Birthdate, Position, EmailAddress, Age, Gender);

                EmployeeMap.put(ID_Number, employee);
                EmployeeLoginMap.put(ID_Number, Password);
            }

        }catch(Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Account File Not Found!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Populate the Employee Time Details with data from res\\dat\\TimeLogs.csv
     */
    private void populateEmployeeTimeDetails(){
        FilePath = "res\\dat\\TimeLogs.csv";

        String line;

        try(
                BufferedReader bufferedReader = new BufferedReader(new FileReader(FilePath));
        ){
            int ID_Number;
            String Date, TimeIn, TimeOut, Duration;

            while((line = bufferedReader.readLine()) != null){
                String[] split = line.split(",");

                ID_Number = Integer.parseInt(split[0]);
                Date = split[1];
                TimeIn = split[2];
                TimeOut = split[3];
                Duration = split[4];

                addToEmployeeTimeDetails(ID_Number, Date, Duration, TimeIn, TimeOut);
            }

        }catch(Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "TimeLogs File Not Found!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Scans the Employee Hashmap for the Employee.
     * 0 = No Such Employee
     * 1 = Both ID Number and Password are correct
     * 2 = ID Number is correct, Password is incorrect
     * @param ID_Number is the ID Number of the Employee
     * @param Password is the Password of the Employee
     * @return the number of choice
     */
    private int searchEmployeeMap(int ID_Number, String Password){
        int Outcome = 0;

        for(Map.Entry pairEntry : EmployeeLoginMap.entrySet()){
            if(pairEntry.getKey().equals(ID_Number)){
                Outcome = 2;
                if(pairEntry.getValue().equals(Password)){
                    Outcome = 1;
                }
            }
        }

        return Outcome;
    }

    /**
     * Returns if an employee with the same ID Number is online.
     * @param ID_Number is the ID Number
     * @return true or false.
     * @throws RemoteException
     */
    private boolean isOnline(int ID_Number){
        boolean isOnline = false;
        for(Integer onlineClients : onlineClients){
            if (onlineClients == ID_Number) {
                isOnline = true;
                break;
            }
        }
        return isOnline;
    }

    /**
     * Returns the appropriate string depending on the total hours, minutes, and seconds of the latest time out.
     * @param elapsedHours is the elapsed hours.
     * @param elapsedMinutes is the elapsed minutes.
     * @param elapsedSeconds is the elapsed seconds.
     * @param ID_Number is the elapsed id number.
     * @param timeElapsed is the total time.
     * @return the total time.
     */
    private String PrintStoreElapsed(int elapsedHours, int elapsedMinutes, int elapsedSeconds, int ID_Number, String timeElapsed){
        if(elapsedHours != 0){
            TextAreaOutputStream.printToLogs("Employee with ID Number: " + ID_Number + " Time Out Details: " + elapsedHours + " Hours, " +  elapsedMinutes + " Minutes, " + elapsedSeconds + " Seconds.");
            timeElapsed = elapsedHours + "h/" +  elapsedMinutes + "m/" + elapsedSeconds + "s";
        }
        else if(elapsedHours == 0 && elapsedMinutes != 0){
            TextAreaOutputStream.printToLogs("Employee with ID Number: " + ID_Number + " Time Out Details: " + elapsedMinutes + " Minutes, " + elapsedSeconds + " Seconds.");
            timeElapsed = elapsedMinutes + "m/" + elapsedSeconds + "s";
        }
        else{
            TextAreaOutputStream.printToLogs("Employee with ID Number: " + ID_Number + " Time Out Details: " + elapsedSeconds + " Seconds.");
            timeElapsed = elapsedSeconds + "s";
        }
        return timeElapsed;
    }

    /**
     * Add employee time details to hashmap.
     * @param ID_Number is the id number.
     * @param currentDate is the current date.
     * @param timeElapsed is the time elapsed.
     * @param timeIn is the time in.
     * @param timeOut is the time out.
     */
    private void addToEmployeeTimeDetails(int ID_Number, String currentDate, String timeElapsed, String timeIn, String timeOut){
        ArrayList<String> timeInTimeOutDetails = new ArrayList<>();

        timeInTimeOutDetails.add(ID_Number + "," + currentDate + "," + timeIn + "," + timeOut + "," + timeElapsed);

        if(!EmployeeTimeDetails.containsKey(ID_Number)){
            EmployeeTimeDetails.put(ID_Number, timeInTimeOutDetails);
        }
        else{
            EmployeeTimeDetails.get(ID_Number).add(ID_Number + "," + currentDate + "," + timeIn + "," + timeOut + "," + timeElapsed);
        }
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

            File newFile = new File(FilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));

            for(Employee entry : EmployeeMap.values()){
                bufferedWriter.write(entry.Output_toString());
                bufferedWriter.newLine();
            }

            TextAreaOutputStream.printToLogs("Data Saved!");

            fileOutputStream.close();
            bufferedWriter.flush();
            bufferedWriter.close();

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * Save Employee Time Details
     */
    private void printTimeLogsToFile(){
        try{
            FilePath = "res\\dat\\TimeLogs.csv";
            newFilePath = "res\\dat\\TimeLogs.csv";

            File oldFile = new File(FilePath);
            if(oldFile.delete()){}

            File newFile = new File(FilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newFile));

            for(ArrayList<String> entry : EmployeeTimeDetails.values()){
                for(String string : entry){
                    bufferedWriter.write(string);
                    bufferedWriter.newLine();
                }
            }

            TextAreaOutputStream.printToLogs("Data Saved!");


            fileOutputStream.close();
            bufferedWriter.flush();
            bufferedWriter.close();

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

}
