/*
Jonathan M. Rodriguez

Single Server Queue
*/

import java.util.*;
import java.lang.*;
import java.text.*;

public class Queue
{
	//init random
	static Random random = new Random();
	public static void main(String[] args)
	{
		//keyboard input
		Scanner in = new Scanner(System.in);

		//get # of jobs
		System.out.println("Enter number of jobs: ");
		int size = in.nextInt();

		//get values for A,B,C,D
		System.out.println("Enter value for A: ");
		int A = in.nextInt();

		System.out.println("Enter value for B: ");
		int B = in.nextInt();

		System.out.println("Enter value for C: ");
		int C = in.nextInt();

		System.out.println("Enter value for D: ");
		int D = in.nextInt();

		//array of jobs
		ArrayList<Job> jobs = new ArrayList<Job>(size);


		//add job object to array of jobs
		for(int i =0; i < size; i++)
		{
			Job job = new Job();
			jobs.add(job);

		}

		//do job zero first
		//there's no jobs before it
		jobs.get(0).arrival = 0;
		jobs.get(0).waiting = 0;
		jobs.get(0).start = 0 ;
		jobs.get(0).service = (int) (random.nextInt(B) + A);
		jobs.get(0).finished = jobs.get(0).service;
		jobs.get(0).cpu = jobs.get(0).service;

		//input arrival times randomly
		for(int i = 1; i < size; i++)
		{
			if(i % 2 == 1)
			{
				jobs.get(i).arrival = A + jobs.get(i - 1).arrival;
			}
			if(i % 2 == 0)
			{
				jobs.get(i).arrival = B + jobs.get(i - 1).arrival;
			}
		}

		//add service times
		for(int j = 1; j < size; j++)
		{
			jobs.get(j).service = (int) (random.nextInt(D) + C);
		}

		//waiting/arrival/service operations
		for(int n = 1; n < size; n++)
		{

			//job didnt wait in Q.
			if(jobs.get(n).arrival > jobs.get(n - 1).finished)
			{
				//there's no waiting time
				jobs.get(n).waiting = 0;
				jobs.get(n).start = jobs.get(n).arrival;
			}

			//job waited in the Q
			if(jobs.get(n).arrival < jobs.get(n - 1).finished)
			{
				//arrival time = previous finished time
				jobs.get(n).waiting = jobs.get(n - 1).finished - jobs.get(n).arrival;
				jobs.get(n).start = jobs.get(n - 1).finished;
			}


			//if they don't have to wait
			if(jobs.get(n - 1).finished == jobs.get(n).arrival)
			{
				jobs.get(n).start = jobs.get(n - 1).finished + 1;
			}

			//if they overlap
			else
			{
				jobs.get(n).waiting = jobs.get(n).start - jobs.get(n).arrival;
			}


			//finished time = start time + service time
			jobs.get(n).finished = jobs.get(n).start + jobs.get(n).service;

			jobs.get(n).cpu = jobs.get(n).finished - jobs.get(n).arrival;

		}

		//summary operations

		//init queue/cpu counter
		//compute turnaround/cpu time
		int totalCPU = 0;
		int counter = 0;
		int totalWait = 0;
		int totalQueue = 0;
		int maxQueue = 0;
		double totalService = 0;

		//check # in queue
		for(int k = 0; k < size; k++)
		{
			//add all service times
			totalService += jobs.get(k).service;

			//calculate cpu util.
			totalCPU += jobs.get(k).cpu;

			//calculate average time waiting
			totalWait += jobs.get(k).waiting;

			for(int h = k; h < size; h++)
			{
				//if the job's finished after the arrival
				if((jobs.get(k).finished > jobs.get(h).arrival) && jobs.get(h).waiting != 0)
				{
					counter++;
				}
			}
			//add to queue variable
			jobs.get(k).inQueue = counter;

			//reset counter
			counter=0;

			//calculate average queue length
			int queue = jobs.get(k).inQueue;
			totalQueue += queue;

			//and max queue length
			maxQueue = Math.max(queue, maxQueue);
		}

		int averageTurnAround = totalCPU / size;
		int avgWait = totalWait / size;
		int avgQueue = totalQueue / size;
		
		//computer avg cpu time
		double avgCPU = totalService / jobs.get(size - 1).finished;
		avgCPU = avgCPU * 100;

		//convert to ##.## format
		DecimalFormat form = new DecimalFormat("##.##");
		avgCPU = Double.valueOf(form.format(avgCPU));

		//now print the table
		System.out.println("#\t" + "Arrival\t" + "Service\t" + "Begin\t" + "End\t" + "Wait\t" + "CPU\t" + "# in Queue\t");

		for (int i = 0; i < size; i++)
		{
			System.out.println(i + "\t" + jobs.get(i).arrival + "\t" + jobs.get(i).service + "\t" + jobs.get(i).start
			+ "\t" + jobs.get(i).finished + "\t" + jobs.get(i).waiting + "\t" + jobs.get(i).cpu
			+ "\t" + jobs.get(i).inQueue + "\t");
		}
		//print summary
		System.out.println("CPU Utilization: " + avgCPU + "%");
		System.out.println("Average time waiting in queue: " + avgWait);
		System.out.println("Average length of queue: " + avgQueue);
		System.out.println("Maximum size of queue: " + maxQueue);
		System.out.println("Average turnaround time: " + averageTurnAround);
	}
}

//create object for each job
class Job
{
	int arrival;
	int start;
	int service;
	int waiting;
	int finished;
	int cpu;
	int inQueue;

	Job()
	{
		this.arrival = arrival;
		this.start = start;
		this.service = service;
		this.waiting = waiting;
		this.finished = finished;
		this.cpu = cpu;
		this.inQueue = inQueue;
	}
}
