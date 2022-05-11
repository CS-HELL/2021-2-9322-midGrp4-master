package midproject.Interfaces;

import midproject.Serializables.Employee;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface EntryInterface extends Remote {


    int checkLoginInfo(int ID_Number, String Password) throws RemoteException;
    void insertToEmployeeHashmap(Employee employee) throws RemoteException;
    boolean isInEmployeeHashmap(int ID_Number) throws RemoteException;
    boolean currentlyOnline(Employee Employee) throws RemoteException;
    void onlineClient(Employee Employee) throws RemoteException;
    void offlineClient(Employee Employee) throws RemoteException;
    void timeIn(Employee employee) throws RemoteException;
    void timeOut(Employee employee) throws RemoteException;

    Employee getEmployeeData(int ID_Number) throws RemoteException;
    HashMap<Integer, Employee> getEmployeeHashMap() throws RemoteException;

    ArrayList<String> getEmployeeTimeDetails(int ID_Number) throws RemoteException;
    HashMap<Integer, ArrayList<String>> getEmployeeTimeDetailsHashMap() throws RemoteException;

    boolean isInTimerMap(int ID_Number) throws RemoteException;
    void updateEmployee(Employee employee) throws RemoteException;

    void PasswordChangedToLogs(Employee userEmployee) throws RemoteException;
    void UpdateToLogs(Employee userEmployee, Employee updatedEmployee) throws RemoteException;
}
