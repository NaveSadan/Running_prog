# Running Tracker 🏃‍♂️

A JavaFX-based application to track running activities, visualize progress, and provide recommendations and alerts for runners.

## Overview

Running Tracker is a desktop application designed to help runners monitor their running activities. It allows users to:
- View a table of their runs with details like date, distance, time, and pace.
- Visualize their cumulative distance progress over time with a line chart.
- Receive personalized recommendations and alerts based on their running data and goals.

The project is built using **Java 17** and **JavaFX 17**, and it reads running data from a CSV file (`runs.csv`).

## Features

- **Run Table**: Displays all runs with columns for date, distance (km), time (min), and pace (min/km).
- **Progress Chart**: A line chart showing the cumulative distance over time (days).
- **Recommendations & Alerts**:
  - Recommendations based on average pace and goal progress.
  - Alerts for inactivity or significant drops in pace.
- **Goal Tracking**: Set a distance goal with a deadline and track your progress.

## Screenshots

### Main Interface
![Running Tracker Interface](screenshot.png)
*Caption: The main interface showing the run table, progress chart, and recommendations.*

## Prerequisites

To run this project, you need:
- **Java 17** (JDK 17) installed on your machine.
- **JavaFX 17** SDK (download from [Gluon](https://gluonhq.com/products/javafx/)).
- An IDE like IntelliJ IDEA (recommended) or any other Java IDE.

## Setup and Running the Project

### 1. Clone the Repository
```bash
git clone https://github.com/USERNAME/REPOSITORY_NAME.git
cd REPOSITORY_NAME
2. Configure JavaFX

    Download JavaFX 17 from Gluon.
    Extract the JavaFX SDK to a directory (e.g., /path/to/javafx-sdk-17.0.14).
    Update the path in the RunningTrackerUI.java file if needed (for the runs.csv file).

3. Run the Application in IntelliJ

    Open the project in IntelliJ IDEA.
    Go to File > Project Structure > Project and set the Project SDK to JDK 17.
    Go to Run > Edit Configurations, select RunningTrackerUI, and add the following to VM options:
    text

    --module-path /path/to/javafx-sdk-17.0.14/lib --add-modules javafx.controls,javafx.fxml
    Replace /path/to/javafx-sdk-17.0.14 with the actual path to your JavaFX SDK.
    Run the RunningTrackerUI class (click the green "Run" button).

4. Run the Application via Terminal

    Navigate to the project directory:
    bash

    cd /path/to/REPOSITORY_NAME
    Compile and run the project:
    bash

    java --module-path /path/to/javafx-sdk-17.0.14/lib --add-modules javafx.controls,javafx.fxml -cp out/production/Running_prog RunningTrackerUI
    Replace /path/to/javafx-sdk-17.0.14 with the actual path to your JavaFX SDK.

Project Structure :
text
REPOSITORY_NAME/
├── src/
│   ├── AlertSystem.java        # Handles alerts based on running data
│   ├── DataImporter.java       # Imports running data from CSV
│   ├── Goal.java              # Represents a runner's goal
│   ├── RecommendationEngine.java # Generates recommendations
│   ├── Run.java               # Represents a single run
│   ├── Runner.java            # Manages a runner's data
│   ├── RunningTrackerUI.java   # Main UI class (JavaFX)
├   |── runs.csv                   # Sample running data
├── .gitignore                 # Git ignore file
├── README.md                  # This file
Sample Data

The runs.csv file contains sample running data in the following format:
text
Activity Type,Date,Distance,Time,Calories,Average HR,...
Running,2025-04-01 08:00:00,5.02,00:25:12,300,145,...
Running,2025-04-05 07:30:00,8.01,00:40:15,480,150,...
Future Improvements

    Add a form to input new runs directly in the UI.
    Enhance the UI with better styling (colors, fonts, etc.).
    Add more charts (e.g., pace over time).
    Save data automatically when closing the application.

License

This project is licensed under the MIT License - see the  file for details.
Acknowledgments

    Built with ❤️ by Nave Sadan.
    Thanks to JavaFX for the awesome UI framework!