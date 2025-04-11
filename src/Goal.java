public class Goal {
    private String type;        // Type of target: Distance, Time, Pace
    private double target;      // Kind of the target (for example: 10km, 60 minutes)
    private String deadline;    // Date of target

    public Goal(String type, double target, String deadline) {
        if (target <= 0) {
            throw new IllegalArgumentException("Value have to be greater than 0");
        }
        setType(type);
        setTarget(target);
        setDeadline(deadline);
    }

    //Setters
    public void setType(String type) {
        this.type = type;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    //Getters
    public String getType() {
        return type;
    }

    public double getTarget() {
        return target;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "Target: " + target + " of " + type + " on " + deadline;
    }


}
