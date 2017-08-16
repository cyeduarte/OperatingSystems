/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.util.*;
import java.text.DecimalFormat;

/**
 * Christopher Eduarte
 * Project 1 OS Simulation
 * CS431
 * Definition: Calculate the shortest job for every process sent.
 */
public final class ShortestJob 
{
    private Scanner reader = new Scanner(System.in);
    private ArrayList<Job> processList = new ArrayList<>();
    protected ArrayList<Integer> TTList = new ArrayList<>(); //TAT list
    protected ArrayList<Integer> WTList = new ArrayList<>(); // Wait time list
    protected ArrayList<Job> sortedList = new ArrayList<>(); //list of sorted data
    private int startAT;
    protected DecimalFormat edit = new DecimalFormat("0.0");
    
    public ShortestJob()
    {
        startAT = 0;
        //do nothing
    }
    
    //Adds job into the process list
    public void addJob(Job process)
    {
        processList.add(process);
    }
    
    //Sorts the array 
    public void sort()
    {
        Collections.sort(processList);
    }
    
    //Calculates Average Process Time and shows the result
    public void APT() //Average Processing Time
    {
        double sum = 0;
        double average;
      
        //Calculate the sum 
        for(int i = 0; i < processList.size(); i++)
        {
            sum += processList.get(i).getTime();
        }
        
        average = sum/processList.size(); //calculate the average
        
        System.out.println("Average Processing Time(APT) = " + edit.format(average));
    }
    
    //Set TAT time lists
     public void setTAT()
    {
        int sum = 0;
        int length = processList.size();
        
        for(int i = 0; i < processList.size(); i++ )
        {
            sum += processList.get(i).getTime();
            //System.out.println("sum " + sum);
            TTList.add(sum);
            processList.get(i).setEndTime(sum);
            processList.get(i).setOutput(sum);
        }
           
    }
    
     //Calculate averge and Display reults
    public void TATAverage()
    {
        double sum = 0;
        double average;
        
        for(int i = 0; i < TTList.size(); i++)
        {
            sum += TTList.get(i);
        }
        
        average = sum/((double)processList.size());
        
        System.out.println("Average Turnaround Time (ATT) = " + edit.format(average));
        
    }
    
    //Set the wait time 
    public void setWaitTime()
    {
        int time = 0;
        
        for(int i = 0; i < processList.size(); i++) //set a list of wait time
        {
            if(processList.get(i).getTime() > TTList.get(i))
            {
                time = processList.get(i).getTime() - TTList.get(i);
                WTList.add(time);
                processList.get(i).setStartTime(time);
            }
            else
            {
                time = TTList.get(i) - processList.get(i).getTime();
                WTList.add(time);
                processList.get(i).setStartTime(time);
            }
            
        }
    }
    
    //Calculate and Display the wait time average
    public void waitTimeAverage()
    {
        double sum = 0;
        double average;
        
        for(int i = 0; i < WTList.size(); i++)
        {
            sum += WTList.get(i);
        }
        
        average = sum/processList.size();
        
        System.out.println("Average Waiting Time (AWT) = " + edit.format(average));
        
        
    }
 
    //prints the process
    public void printProcess()
    {
        System.out.println("");
        System.out.print("Job:             ");
        
        for(int i = 0; i < processList.size(); i++)
        {
            System.out.printf("%-9s", processList.get(i).getName());
        }
        
        System.out.println("");
        
        System.out.print("Processing Time: ");
        
        for(int i = 0; i < processList.size(); i++)
        {
            System.out.printf("%-9s", processList.get(i).getTime());
        }
        
        System.out.println("\n");
    }
    
    public void printData()
    {
        System.out.println("Job       Start    End     Output  ");
        System.out.println("----------------------------------");
        
        for(int i = 0; i < processList.size(); i++)
        {
            System.out.print(processList.get(i));
        }
        
        System.out.println("----------------------------------");
    }
}
