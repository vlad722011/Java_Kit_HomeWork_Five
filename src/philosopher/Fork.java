package philosopher;

public class Fork implements Runnable {
    private volatile boolean forkIsBlocked = false;
    private String forkName;

    public Fork( String forkName) {
        this.forkName = forkName;
    }

    public String getForkName() {
        return forkName;
    }

    public void setForkName(String forkName) {
        this.forkName = forkName;
    }

    public boolean isForkIsBlocked() {
        return forkIsBlocked;
    }

    public void setForkIsBlocked(boolean forkIsBlocked) {
        this.forkIsBlocked = forkIsBlocked;
    }

    @Override
    public String toString() {
        return String.format("fork %s", forkName);
    }


    @Override
    public synchronized void run() {
        if(!forkIsBlocked){
            forkIsBlocked = true;
        }
    }

    public void start() {
        run();
    }
}



