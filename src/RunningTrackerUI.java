import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class RunningTrackerUI extends Application {
    private Runner runner;

    @Override
    public void init() {        // initializes the first definition and creation runner named "Yossi"
        System.out.println("Initializing Runner...");
        runner = new Runner("Yossi");
        try {
            ArrayList<Run> importedRuns = (ArrayList<Run>) DataImporter.importFromCSV("src/runs.csv");
            System.out.println("Imported " + importedRuns.size() + " runs.");
            for (Run run : importedRuns) {
                runner.addRun(run);     // add runner's runs
            }
            // set GOAL!
            runner.setGoal(new Goal("Distance", 20.0, "2025-05-01"));
        } catch (Exception e) {
            System.out.println("Error during import: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Starting JavaFX application...");
        primaryStage.setTitle("Running Tracker");

        // Create the Table to display runs
        TableView<Run> table = new TableView<>();

        // Create the columns for the table
        TableColumn<Run, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Run, Double> distanceColumn = new TableColumn<>("Distance (km)");
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));

        TableColumn<Run, Double> timeColumn = new TableColumn<>("Time (min)");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Run, Double> paceColumn = new TableColumn<>("Pace (min/km)");
        paceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>
                (Math.round(cellData.getValue().calculatePace() * 100.0) / 100.0)); // Round 2 digits after "."

        table.getColumns().addAll(dateColumn, distanceColumn, timeColumn, paceColumn);
        table.getItems().addAll(runner.getRuns());
        System.out.println("Table populated with " + runner.getRuns().size() + " runs.");

        // Line chart to show cumulative distance progress
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Days");
        xAxis.setAutoRanging(false); // Disable auto-ranging for showing just relevant data
        xAxis.setLowerBound(1); // Start at day 1
        if (!runner.getRuns().isEmpty()) {
            LocalDate firstDate = LocalDate.parse(runner.getRuns().get(0).getDate());
            LocalDate lastDate = LocalDate.parse(runner.getRuns().get(runner.getRuns().size() - 1).getDate());
            long maxDays = ChronoUnit.DAYS.between(firstDate, lastDate);
            xAxis.setUpperBound(maxDays + 1); // Set upper bound to the last day + 1
            xAxis.setTickUnit(1); // Show ticks for each day
        }
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Cumulative Distance (km)");
        yAxis.setAutoRanging(false); // Disable auto-ranging to show relevant data
        yAxis.setLowerBound(0); // Start at 0 km
        double maxDistance = runner.calculateTotalDistance();
        yAxis.setUpperBound(maxDistance + 5); // Add some padding above the max distance
        yAxis.setTickUnit(Math.ceil(maxDistance / 5)); // Adjust ticks based on max distance
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Distance Progress");

        // Add data to the chart if there are runs
        if (!runner.getRuns().isEmpty()) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Cumulative Distance");
            double cumulativeDistance = 0;
            LocalDate firstDate = LocalDate.parse(runner.getRuns().get(0).getDate());
            for (Run run : runner.getRuns()) {
                cumulativeDistance += run.getDistance();
                LocalDate runDate = LocalDate.parse(run.getDate());
                long days = ChronoUnit.DAYS.between(firstDate, runDate);
                series.getData().add(new XYChart.Data<>(days, cumulativeDistance));
            }
            lineChart.getData().add(series);
            System.out.println("Chart populated with data.");
        } else {
            System.out.println("No runs to display in chart.");
        }

        // Text area for recommendations and alerts
        TextArea infoArea = new TextArea();
        infoArea.setEditable(false);
        infoArea.setText("Recommendations:\n" + RecommendationEngine.generateRecommendation(runner) +
                "\n\nAlerts:\n" + AlertSystem.checkAlerts(runner));

        // Layout
        VBox layout = new VBox(10, new Label("Runs"), table, new Label("Progress"), lineChart, new Label("Recommendations & Alerts"), infoArea);
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();        // Show the window created
        System.out.println("JavaFX window should now be visible.");
    }

    public static void main(String[] args) {
        System.out.println("Launching JavaFX application...");
        launch(args);       // start JavaFX
    }
}