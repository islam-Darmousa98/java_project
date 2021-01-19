
public class Queue {
	private int priority ;
    private String algo;
    private int numOfProcess;

    public Queue(int priority, String algo, int numOfProcess) {
        this.priority = priority;
        this.algo = algo;
        this.numOfProcess = numOfProcess;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getAlgo() {
        return algo;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    public int getNumOfProcess() {
        return numOfProcess;
    }

    public void setNumOfProcess(int numOfProcess) {
        this.numOfProcess = numOfProcess;
    }

}
