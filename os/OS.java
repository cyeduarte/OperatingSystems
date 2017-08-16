/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.File;

/**
 *Christopher Eduarte
 *CS431
 *Project 1 Operating System Simulation
 */
public class OS
{

    /**
     * Compare the process average, TAT average, and Wait Time average of the
     * list of processes
     */
    public static void main(String[] args) 
    {
        // Variables
        FCFS fcfs = new FCFS(); //class for FCFS
        ShortestJob shortJob = new ShortestJob(); //class for shortest job
        RoundRobin robin = new RoundRobin(); // class for round robin
        String name = ""; // name of file
        int option; // option for menu
        Scanner nameInput = new Scanner(System.in); //chosen file name
        Scanner keyboard = new Scanner(System.in); //basic input
        // end of variables
            
        try
        {

            System.out.println("Please enter the name of the file: ");
            name = nameInput.nextLine();
            
            File file = new File(name); //create file
            Scanner input = new Scanner(file); //get input
                    
            //getting contents from the file
            while(input.hasNext())
            {
                String jobName = input.next();
                int burstTime = input.nextInt();
                Job process = new Job(jobName, burstTime); //create job class for every index
                fcfs.addProcess(process); //add proecess to the fcsfs, short job and robin
                shortJob.addJob(process);
                robin.addJob(process);
            }
            
            //test Shortest Job First
            //robin.APT();
            //robin.print();
            /*robin.setQuantum(5);
            robin.calcRoundRobin();
            robin.printProcess();
            robin.APT();
            robin.TATAverage();
            robin.setWaitTime();
            robin.waitTimeAverage();
            System.out.println("");
            robin.printData();*/
            
           
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        
        do //Program Menu
        {
            System.out.println("Please choose one of the following:\n"
             + "1)First Come First Serve\n2)Shortest Job\n"
             + "3)Round Robin with a time splice of 2\n" +
               "4)Round Robin with a time splice of 5\n"
            +  "0)To exit the program");
       
            option = keyboard.nextInt();
                        
            if(option == 1)
            {
                System.out.println("First Come First Serve:");
                fcfs.printProcess();
                fcfs.APT(); 
                fcfs.setTAT();
                fcfs.TATAverage();
                fcfs.setWaitTime();
                fcfs.waitTimeAverage();
                fcfs.printData();
            }
            else if(option == 2)
            {
                System.out.println("Shortest Job\n\n");
                shortJob.printProcess();
                shortJob.sort();
                shortJob.APT();
                shortJob.setTAT();
                shortJob.TATAverage();
                shortJob.setWaitTime();
                shortJob.waitTimeAverage();
                shortJob.printData();
            }
            else if(option == 3)
            {
                System.out.println("Round Robin With time splice 2\n\n");
                robin.setQuantum(2);
                robin.calcRoundRobin();
                robin.printProcess();
                robin.APT();
                robin.TATAverage();
                robin.setWaitTime();
                robin.waitTimeAverage();
                System.out.println("");
                robin.printData();
            }
            else if(option == 4)
            {
                System.out.println("Round Robin With time splice 5\n\n");
                robin.setQuantum(5);
                robin.calcRoundRobin();
                robin.printProcess();
                robin.APT();
                robin.TATAverage();
                robin.setWaitTime();
                robin.waitTimeAverage();
                System.out.println("");
                robin.printData();
            }
            else if(option == 0)
            {
                System.out.println("You have quit the program.");
            }
            else
            {
                System.out.println("You have chosen the wrong option");
            }
        }while(option != 0);
        
    }
    
}
