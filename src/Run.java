public class Run {
    private double distance;    // k"m
    private double time;        // minutes
    private String date;        // date of running


    //Constructor
    public Run(double distance, double time, String date) {
        if (distance <= 0 || time <= 0) {
            throw new IllegalArgumentException("Distance and time must be greater than 0");
        }
        setDate(date);
        setDistance(distance);
        setTime(time);
    }


    // Setters
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //Getters
    public double getDistance() {
        return distance;
    }

    public double getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public double calculatePace() {
        return time / distance;     // minutes per kilometer
    }

    @Override
    public String toString() {
        return "Running date " + date + ": " + distance + "Km, " + time + " minutes, Pace: "
                + String.format("%.2f", calculatePace()) + " Minutes per Kilometer";
    }


}
