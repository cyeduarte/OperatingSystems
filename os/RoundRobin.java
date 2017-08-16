
package os;

import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * Christopher Eduarte
 * Project Operating System Simulation
 * Description: Round Robin calculation for a series of process
 */

import java.util.*;


//may need to change all queues into array lists
public class RoundRobin 
{
    private int quantum; //quantum 
    private ArrayList<Integer> burstTime = new ArrayList<Integer>();
    private ArrayList<String> nameList = new ArrayList<>();
    private final ArrayList<Job> process = new ArrayList<>();
    protected ArrayList<Integer> TTList = new ArrayList<>();
    protected ArrayList<Integer> WTList = new ArrayList<>();
    protected ArrayList<Job> Queue = new ArrayList<>();
    protected DecimalFormat edit = new DecimalFormat("0.00");

    public RoundRobin(){
        //empty constructor
    }
    
    /**
     * Adds the job into the process, burstTime, and nameList array lists
     * @param job: a class that contains name and burst time of each process
     */
    public void addJob(Job job)
    {
        process.add(job);
        burstTime.add(job.getTime());
        nameList.add(job.getName());
    }
    
    //Set the quantum of Round Robin
    public void setQuantum(int quant)
    {
        quantum = quant;
    }
    
    /**
     * Calculates the round robin of the processes
     */
    public void calcRoundRobin()
    {
        int size = process.size();
        int checkStatus = 0;
        int start = 0;
        Job job;
       
        while((!process.isEmpty()))//Stops when the list is empty
        {
            for(int j = 0; j < size; j++)
            {
                checkStatus = process.get(j).getTime() - quantum;
                //System.out.println("check status is for index " + j + " is " + checkStatus); //Used to check status
               
                if(checkStatus > 0) // if burst time > 0
                {
                    process.get(j).setTime(process.get(j).getTime() - quantum);
                    job = new Job(process.get(j).getName(), start, start + quantum, 0);
                    Queue.add(job);
                    start = start + quantum;
                }
                else if(checkStatus < 0) //if burst time < 0
                {
                    job = new Job(process.get(j).getName(), start, 
                                 start +  process.get(j).getTime(), 
                                 start +  process.get(j).getTime());
                    Queue.add(job);
                    start = start + process.get(j).getTime();
                    TTList.add(start);
                    Queue.get(j).setOutput(start);
                    process.remove(j);                    
                }
                else if(checkStatus == 0) // if burst time is equal to 0
                {
                    job = new Job(process.get(j).getName(), start, start + 
                                  quantum, start + quantum);
                    Queue.add(job);
                    start = start + quantum;
                    TTList.add(start);
                    Queue.get(j).setOutput(start);
                    process.remove(j);
                }       
                
               // System.out.println("index is " + j + " is equal to " + process.get(j).getTime());
                size = process.size();
            }
                        
            size = process.size();
        }
    }
    
    //Calculates Average Processing Time and Prints results
     public void APT() 
    {
        double sum = 0;
        double average;
        
        //Calculate the sum 
        for(int i = 0; i < burstTime.size(); i++)
        {
            sum += burstTime.get(i);
        }
        
        average = sum/burstTime.size(); //calculate the average
        
        System.out.println("Average Processing Time(APT) = " + edit.format(average));
    }
   
    //Cacluates the average TAT time and Prints results
    public void TATAverage()
    {
        double sum = 0;
        double average;
              
        for(int i = 0; i < TTList.size(); i++)
        {
            sum += TTList.get(i);
        }
        
        average = sum/((double)burstTime.size());
        
        
        System.out.println("Average Turnaround Time (ATT) = " + edit.format(average));
        
    }
    
    //Calculates waittime by subtracting burstime with tat time or vice versa
    public void setWaitTime()
    {
        int time = 0;
       
        for(int i = 0; i < TTList.size(); i++) //set a list of wait time
        {
            if(burstTime.get(i) > TTList.get(i))
            {
                time = burstTime.get(i) - TTList.get(i);
                WTList.add(time);
            }
            else
            {
                time = TTList.get(i) - burstTime.get(i);
                WTList.add(time);
            }
        }
    }
    
    //Calculates Wait ime Average
    public void waitTimeAverage()
    {
        double sum = 0;
        double average;
        
        for(int i = 0; i < WTList.size(); i++)
        {
            sum += WTList.get(i);
        }
        
        average = sum/burstTime.size();
        
        System.out.println("Average Waiting Time (AWT) = " + edit.format(average));      
        
    }
    
    //prints the process
    public void printProcess()
    {
        System.out.println("");
        System.out.print("Job:             ");
        
        for(int i = 0; i < nameList.size(); i++)
        {
            System.out.printf("%-9s", nameList.get(i));
        }
        
        System.out.println("");
        
        System.out.print("Processing Time: ");
        
        for(int i = 0; i < burstTime.size(); i++)
        {
            System.out.printf("%-9s", burstTime.get(i));
        }
        
        System.out.println("\n\n");
    }
    
    public void printData()
    {
        System.out.println("Job       Start    End     Output  ");
        System.out.println("----------------------------------");
        
        for(int i = 0; i < Queue.size(); i++)
        {
            System.out.print(Queue.get(i));
        }
        
        System.out.println("----------------------------------");
    }
}
