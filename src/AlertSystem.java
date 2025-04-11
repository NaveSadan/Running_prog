import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AlertSystem {
    public static String checkAlerts(Runner runner) {
        if (runner.getRuns().isEmpty()) {
            return "No runs yet - start running!";
        }

        StringBuilder alerts = new StringBuilder();
        LocalDate today = LocalDate.now(); // Current date (2025-04-10 as per your setup)

        // Check last activity
        Run lastRun = runner.getRuns().get(runner.getRuns().size() - 1);
        String dateStr = lastRun.getDate();
        // Ensure the date is in YYYY-MM-DD format by removing time if present
        if (dateStr.contains(" ")) {
            dateStr = dateStr.split(" ")[0];
        }
        LocalDate lastRunDate = LocalDate.parse(dateStr);
        long daysSinceLastRun = ChronoUnit.DAYS.between(lastRunDate, today);
        if (daysSinceLastRun > 7) {
            alerts.append("You haven't run in ").append(daysSinceLastRun).append(" days - time to get back on track!\n");
        }

        // Check for a drop in pace
        if (runner.getRuns().size() >= 2) {
            Run secondLastRun = runner.getRuns().get(runner.getRuns().size() - 2);
            double lastPace = lastRun.calculatePace();
            double prevPace = secondLastRun.calculatePace();
            if (lastPace > prevPace * 1.2) { // More than 20% slower
                alerts.append("Notice: Your pace in the last run (")
                        .append(String.format("%.2f", lastPace))
                        .append(" min/km) is significantly slower than the previous one. Maybe take a rest?\n");
            }
        }

        return alerts.length() > 0 ? alerts.toString() : "No alerts at the moment - everything is fine!";
    }
}