public class RecommendationEngine {
    public static String generateRecommendation(Runner runner) {
        if (runner.getRuns().isEmpty()) {
            return "There's no data yet, Start running!";
        }

        double avgPace = Math.round(runner.calculateAveragePace() * 100.0) / 100.0; // Round to 2 decimal places
        double totalDistance = runner.calculateTotalDistance();
        Goal goal = runner.getGoal();

        StringBuilder recommendation = new StringBuilder();

        if (goal != null && goal.getType().equals("Distance")) {
            double progress = totalDistance / goal.getTarget() * 100;
            if (progress < 50) {
                recommendation.append("Your in- ").append(String.format("%.1f", progress))
                        .append("% from the goal. You should increase the weekly distance\n");
            } else {
                recommendation.append("Well done!! your in a- ").append(String.format("%.1f", progress))
                        .append("% from the goal. Keep it up!\n");
            }
        }

            if (avgPace > 6.0) {        // 6m/km its reasonable pace for beginners
            recommendation.append("Your pace is a bit high (").append(String.format("%.2f", avgPace))
                    .append(" min/km). Try to slow down a bit.");
        } else {
            recommendation.append("Excellent pace!! (").append(String.format("%.2f", avgPace))
                    .append(" min/km). Now, try to push yourself just a little bit more :)");
        }

        return recommendation.toString();
    }
}