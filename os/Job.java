
package os;

import java.util.LinkedList;
import java.util.Collections;

/**
 *Christopher Eduarte
 *CS431 OS project
 *Description: a process that contains the name of the job and the time sent 
 *             by the user.
 */

public class Job implements Comparable<Job>
{
    private String name;
    private int time;
    private int startTime;
    private int endTime;
    private int output;
    LinkedList linkNames = new LinkedList();
    LinkedList linkTimes = new LinkedList();
    
    //regular construcor for FCSFS and Shortest Job
    public Job(String name, int time)
    {        
        setName(name);
        setTime(time);
    }
    
    //Constructor specifically for Round Robin
    public Job(String name, int start, int end, int output)
    {
        setName(name);
        setStartTime(start);
        setEndTime(end);
        setOutput(output);
    }
    
    //Set the name of the job
    public void setName(String name)
    {
        this.name = name;
        linkNames.add(name);
    }
    
    //Set the burst time
    public void setTime(int time)
    {
        this.time = time;
        linkTimes.add(time);
    }
    
    //Set the start time
    public void setStartTime(int start)
    {
        this.startTime = start;
    }
    
    //Set the end time
    public void setEndTime(int end)
    {
        endTime = end;
    }
    
    //Set the output
    public void setOutput(int output)
    {
        this.output = output;
    }
    
    //Return the name
    public String getName()
    {
        return name;
    }
    
    //Return the time
    public int getTime()
    {
        return time;
    }
    
    //Return start time
    public int getStartTime()
    {
        return startTime;
    }
    
    //Return start time
    public int getEndTime()
    {
        return endTime;
    }
    
    //Comparison between class variables
    public int compareTo(Job process)
    {
        int compare = ((Job)process).getTime();
        //System.out.println(compare);
        return this.time - compare;
    }

    //Show the set of data
    public String toString()
    {
        String msg = "";
        
        if(output == 0)
        {
            msg = (name + "    " + String.format("%-9s%-9s",startTime, endTime)
                     +  "\n");
        }
        else
        {
            msg =  (name + "    " + String.format("%-9s%-9s%-8s",
                    startTime, endTime, output)  + "\n");
        }
        
        return msg;
    }
    
}
