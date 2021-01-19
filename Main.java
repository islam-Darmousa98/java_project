import java.util.ArrayList;
import java.util.Random;

public class Main {
	static int low = 1;
	static int high = 100;

	public static void main(String[] args) {
		FCFS();
		RR(10);
		SJF();
		
	}

    
	// First Come First Serve Scheduling Algorithm (FCFS)
	public static void FCFS() {
        // System.out.println();
		int n = 8;
       // Loop to execute iteration 10,100,1000,10000,100000.
		for (long turn = 10; turn <= 100000; turn *= 10) {
			long TotatAverageWaitingTime = 0;
			long TotalAverageTurnAroundTime = 0;
            // Entering loop
			for (int j = 0; j < turn; j++) {
                // Define Processes in array list
				ArrayList<Process> Processes = new ArrayList<Process>(n);
				Random rand1 = new Random();// To assign burst randomly from 1 to 99
				Random rand2 = new Random();// To assign arrival time randomly from 0 to 20
				for (int i = 0; i < n; i++) {
					Processes.add(new Process(i, rand1.nextInt(high - low) + low, rand2.nextInt(20)));
               // System.out.println(Processes.get(i).toString());
				}
				
               // Finding waiting time for each process waiting time for process
				int waittime[] = new int[n];
				int arrival[] = new int[n];// Arrival time
               // wait time for the first process is Zero
				arrival[0] = 0;
				waittime[0] = 0;
				for (int i = 1; i < n; i++) {
                // cpu burst of previous process
					arrival[i] = arrival[i - 1] + Processes.get(i - 1).getBurst();
                    // wait time for process
					waittime[i] = arrival[i] - Processes.get(i).getArrival();
                   // When wait time is zero means process is in the read queue
					if (waittime[i] < 0)
						waittime[i] = 0;
				}
                // Finding Turn around time for each process
				int turnaroundtime[] = new int[n];
				for (int i = 0; i < n; i++) {
                // time for burst time & wait time for each process
					turnaroundtime[i] = Processes.get(i).getBurst() + waittime[i];
				}
// adding Waiting time & turn around time to total from arrays
				for (int i = 0; i < n; i++) {
					TotatAverageWaitingTime += waittime[i];
					TotalAverageTurnAroundTime += turnaroundtime[i];
				}
			}
			
// Average Waiting Time for 100,1000,10000...etc times.
			TotatAverageWaitingTime /= turn * n;
// Average Turn Around Time for 100,1000,10000...etc times.
			TotalAverageTurnAroundTime /= turn * n;
			System.out.println("FCFS " + turn + "  AWT  " + TotatAverageWaitingTime + "\nFCFS " + turn + "  ATT  "
					+ TotalAverageTurnAroundTime);
		}
	}

// Round-Robin Scheduling Algorithm (RR)
	public static void RR(int quantum) {
		System.out.println();
		int n = 8;
// Loop to execute iteration 10,100,1000,10000,100000.
		for (long turn = 10; turn <= 100000; turn *= 10) {
			long TotatAverageWaitingTime = 0;
			long TotalAverageTurnAroundTime = 0;
// Entering loop
			for (int j = 0; j < turn; j++) {
// Define Processes in array list
				ArrayList<Process> Processes = new ArrayList<Process>(n);
				Random rand1 = new Random();// To assign burst randomly from 1 to 99
				Random rand2 = new Random();// To assign arrival time randomly from 0 to 20
				for (int i = 0; i < n; i++) {
					Processes.add(new Process(i, rand1.nextInt(high - low) + low, rand2.nextInt(20)));
				}
// Finding waiting time for each process waiting time for process
				int waittime[] = new int[n];
// to calculate from it the remaining from cpu burst after each quantum
				int Remaining[] = new int[n];
				for (int i = 0; i < n; i++) {
// copying cpu burst for each process to remaining array
					Remaining[i] = Processes.get(i).getBurst();
// System.out.println(Remaining[i]);
				}
				int Time = 0;
				while (true) {
					boolean finish = true;
					for (int i = 0; i < n; i++) {
						if (Remaining[i] > 0) {
							// process is assigned to false to know that is not finished
							finish = false;
							// cpu burst for the process is less or equal the quantum time
							if (Remaining[i] <= quantum) {
								// add remaining cpu burst time to time
								Time += Remaining[i];
								waittime[i] = Time - Processes.get(i).getBurst();
								// process is finished
								Remaining[i] = 0;
							} // cpu burst for the process is bigger than the quantum time
							else {
								// add quantum time to time
								Time += quantum;
								// subtract quantum time from cpu burst (remaining array) for each process
								Remaining[i] -= quantum;
							}
						}
					}
					if (finish == true)
						break;
				}
// Finding Turn around time for each process
				int turnaroundtime[] = new int[n];
				for (int i = 0; i < n; i++) {
// time for burst time & wait time for each process
					turnaroundtime[i] = Processes.get(i).getBurst() + waittime[i];
				}
// adding Waiting time & turn around time to total from arrays
				for (int i = 0; i < n; i++) {
					TotatAverageWaitingTime += waittime[i];
					TotalAverageTurnAroundTime += turnaroundtime[i];
				}
			}
// Average Waiting Time for 100,1000,10000...etc times.
			TotatAverageWaitingTime /= turn * n;
// Average Turn Around Time for 100,1000,10000...etc times.
			TotalAverageTurnAroundTime /= turn * n;
			System.out.println("RR " + turn + "  AWT  " + TotatAverageWaitingTime + "\nRR " + turn + "  ATT  "
					+ TotalAverageTurnAroundTime);
		}
	}

// Shortest Job First Scheduling Algorithm (SJF)
	public static void SJF() {
		System.out.println();
		int n = 8;
// Loop to execute iteration 10,100,1000,10000,100000.
		for (long turn = 10; turn <= 100000; turn *= 10) {
			long TotatAverageWaitingTime = 0;
			long TotalAverageTurnAroundTime = 0;
// Entering loop
			for (int j = 0; j < turn; j++) {
				int turnaroundtime[] = new int[n];
				int waittime[] = new int[n];
// # of executed process
				int total = 0;
// system time
				int st = 0;

				boolean[] finish = new boolean[n];
// Define Processes in array list
				ArrayList<Process> Processes = new ArrayList<Process>(n);
// To assign burst randomly from 10 to 999
				Random rand1 = new Random();
// To assign arrival time randomly from 0 to 50
				Random rand2 = new Random();
				for (int i = 0; i < n; i++) {
					Processes.add(new Process(i, rand1.nextInt(high - low) + low, rand2.nextInt(20)));
				}
// Finding waiting time & Turn Around Time for each process waiting time for
// process
				while (true) {
// range of burst less than 1000
					int limit = 1000;
					int c = n;
					if (total == n)
						break;// All process are executed
					for (int i = 0; i < n; i++) {
						if ((Processes.get(i).getArrival() <= st) && (finish[i] == false)
								&& (Processes.get(i).getBurst() < limit)) {
							limit = Processes.get(i).getBurst();
							c = i;
						}
					}
					if (c == n)
						st++;
					else {
						int done = st + Processes.get(c).getBurst();
						st += Processes.get(c).getBurst();
						turnaroundtime[c] = done - Processes.get(c).getArrival();
						waittime[c] = turnaroundtime[c] - Processes.get(c).getBurst();
						finish[c] = true;
						total++;
					}
				}
// adding Waiting time & turn around time to total from arrays
				for (int i = 0; i < n; i++) {
					TotatAverageWaitingTime += waittime[i];
					TotalAverageTurnAroundTime += turnaroundtime[i];
				}
			}
// Average Waiting Time for 10,100,1000,10000...etc times.
			TotatAverageWaitingTime /= turn * n;
// Average Turn Around Time for 100,1000,10000...etc times.
			TotalAverageTurnAroundTime /= turn * n;
			System.out.println("SJF " + turn + "  AWT  " + TotatAverageWaitingTime + "\nSJF " + turn + "  ATT  "
					+ TotalAverageTurnAroundTime);
		}
	}
	 
	
	
}

class Process {
	int id;
	int burst;
	int arrival;

	public Process(int id, int burst, int arrival) {
		this.id = id;
		this.burst = burst;
		this.arrival = arrival;
	}

	public Process() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBurst() {
		return burst;
	}

	public void setBurst(int burst) {
		this.burst = burst;
	}

	public int getArrival() {
		return arrival;
	}

	public void setArrival(int arrival) {
		this.arrival = arrival;
	}

	@Override
	public String toString() {
		return "Process [id=" + id + ", burst=" + burst + ", arrival=" + arrival + "]";
	}

}


