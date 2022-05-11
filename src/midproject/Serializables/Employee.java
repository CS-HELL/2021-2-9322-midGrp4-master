package midproject.Serializables;

import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee implements Serializable {

    //Login Requirements
    private int ID_Number;
    private String Password;
    //Login Requirements =END=

    //Additional Information of Employee
    private String LastName;
    private String FirstName;
    private String MiddleName;
    private String Birthdate;
    private String Position;
    private String EmailAddress;
    private int Age;
    private String Gender;
    private Employee Employee;
    //Additional Information of Employee =END=
    public Employee(){}

    public Employee(int ID_Number, String Password,
                    String LastName, String FirstName,
                    String MiddleName, String Birthdate,
                    String Position, String EmailAddress,
                    int Age, String Gender){
        this.ID_Number = ID_Number;
        this.Password = Password;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.Birthdate = Birthdate;
        this.Position = Position;
        this.EmailAddress = EmailAddress;
        this.Age = Age;
        this.Gender = Gender;
    }

    public Employee(int ID_Number, String Password){
        this.ID_Number = ID_Number;
        this.Password = Password;
    }

    //Getters Area
    public Employee getEmployee(Employee Employee){return this.Employee;}
    public int getID_Number(){return this.ID_Number;}
    public String getPassword(){return this.Password;}

    public String getLastName(){return this.LastName;}
    public String getFirstName(){return this.FirstName;}
    public String getMiddleName(){return this.MiddleName;}
    public String getBirthdate(){return this.Birthdate;}
    public String getPosition(){return this.Position;}
    public String getEmailAddress(){return this.EmailAddress;}
    public int getAge(){return this.Age;}
    public String getGender(){return this.Gender;}
    //Getters Area =END=

    //Setters Area
    public void setEmployee(Employee Employee){this.Employee = Employee;}
    public void setID_Number(int ID_Number){
        this.ID_Number = ID_Number;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }

    public void setLastName(String LastName){
        this.LastName = LastName;
    }
    public void setFirstName(String FirstName){
        this.FirstName = FirstName;
    }
    public void setMiddleName(String MiddleName){
        this.MiddleName = MiddleName;
    }
    public void setBirthdate(String Birthdate){
        this.Birthdate = Birthdate;
    }
    public void setPosition(String Position){
        this.Position = Position;
    }
    public void setEmailAddress(String EmailAddress){
        this.EmailAddress = EmailAddress;
    }
    public void setAge(int Age){
        this.Age = Age;
    }
    public void setGender(String Gender){
        this.Gender = Gender;
    }
    //Setters Area =END=

    //To String
    public String LoginInformation_toString(){
        return "ID Number: " + ID_Number +
                "\nPassword: " + Password;
    }

    public String EmployeeInformation_toString(){
        return  "ID Number: " + ID_Number +
                "\nPassword: " + Password +
                "\nLast Name: " + LastName +
                "\nFirst Name: " + FirstName +
                "\nMiddle Name: " + MiddleName +
                "\nBirthdate: " + Birthdate +
                "\nPosition: " + Position +
                "\nAge: " + Age +
                "\nGender: " + Gender +
                "\nEmail Address: " + EmailAddress;
    }

    public String LastNameFirstName_toString(){
        return LastName + ", " + FirstName;
    }

    public String FullName_toString(){
        return LastName + ", " + FirstName + " " + MiddleName;
    }

    public String Output_toString(){
        return  ID_Number + "," + Password + "," +
                LastName + ","+ FirstName + "," + MiddleName + "," +
                Birthdate + "," + Position + "," +
                EmailAddress + "," + Gender;
    }
}
