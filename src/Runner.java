import java.util.ArrayList;

public class Runner {
    private String name;
    private ArrayList<Run> runs;     // list of runs
    private Goal goal;          // the goal of the runner


    //Constructor
    public Runner(String name) {
        setName(name);
        this.runs = new ArrayList<>();
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRuns(ArrayList<Run> runs) {
        this.runs = runs;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    //Getters
    public String getName() {
        return name;
    }

    public ArrayList<Run> getRuns() {
        return runs;
    }

    public Goal getGoal() {
        return goal;
    }

    public void addRun(Run run) {
        runs.add(run);
    }

    public double calculateTotalDistance() {
        double total = 0;
        for (Run run : runs) {
            total += run.getDistance();
        }
        return total;
    }

    public double calculateAveragePace() {
        if (runs.isEmpty())
            return 0;
        double totalTime = 0;
        double totalDistance = 0;
        for (Run run : runs) {
            totalTime += run.getTime();
            totalDistance += run.getDistance();
        }
        return totalTime / totalDistance;
    }

}
