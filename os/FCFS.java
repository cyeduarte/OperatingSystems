/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

/**
 *
 * Christopher Eduarte
 * CS431
 * Project 1 
 * Definition: class that calculates the First Come First Serve for every 
 *             process that is sent.
 */

import java.util.*;
import java.text.DecimalFormat;

public final class FCFS 
{
    protected ArrayList<Job> processList = new ArrayList<>();
    protected ArrayList<Integer> TTList = new ArrayList<>();
    protected ArrayList<Integer> WTList = new ArrayList<>();
    protected ArrayList<Job> sortedList = new ArrayList<>();
    protected DecimalFormat edit = new DecimalFormat("0.00");
    
    public FCFS(){
        //Does nothing
    }
    
    //Add proccess 
    public void addProcess(Job process)
    {
        processList.add(process);
    }
    
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
    
    public void setTAT() //Set Tat 
    {
        int sum = 0;
        
        for(int i = 0; i < processList.size(); i++ )
        {
            sum += processList.get(i).getTime();
            TTList.add(sum);
            processList.get(i).setEndTime(sum);
            processList.get(i).setOutput(sum);
        }

    }
    
    //Calculate and print TAT Average
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
    
    //Set Wait Time
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
    
    //Calculate and Print out wait time average
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
               
    //Print the jobs
    //prints the process
    public void printProcess()
    {
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
