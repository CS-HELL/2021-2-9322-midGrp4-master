package midproject.Serializables;

import java.io.Serializable;

public class Timer implements Serializable {
    private Employee employee;
    private String timerInString;
    private long startTime, endTime;

    public Timer(Employee employee){
        this.employee = employee;
    }

    public void startTimer(){
        startTime = System.currentTimeMillis();
    }

    public void stopTimer(){
        endTime = System.currentTimeMillis();
    }

    public Long getTime(){
        return endTime - startTime;
    } //prints in milliseconds
}
