import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TravelItineraryPlanner {

    static List<String> destinations = new ArrayList<>();
    static double totalBudget = 0;

    private static JFrame frame;
    private static JTextField destinationField;
    private static JTextField dateField;
    private static JTextField transportCostField;
    private static JTextField accommodationCostField;
    private static JTextArea itineraryDisplay;
    private static JLabel totalBudgetLabel;
    private static JLabel messageLabel;

    public static void main(String[] args) {
        // Set up frame
        frame = new JFrame("Travel Itinerary Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new FlowLayout());

        // Add labels and text fields for user inputs
        frame.add(new JLabel("Destination:"));
        destinationField = new JTextField(20);
        frame.add(destinationField);

        frame.add(new JLabel("Date (e.g., 2025-12-01):"));
        dateField = new JTextField(20);
        frame.add(dateField);

        frame.add(new JLabel("Estimated Transport Cost:"));
        transportCostField = new JTextField(10);
        frame.add(transportCostField);

        frame.add(new JLabel("Estimated Accommodation Cost:"));
        accommodationCostField = new JTextField(10);
        frame.add(accommodationCostField);

        // Button to add destination and create itinerary
        JButton addDestinationButton = new JButton("Add Destination");
        addDestinationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDestination();
            }
        });
        frame.add(addDestinationButton);

        // Display area for the itinerary
        itineraryDisplay = new JTextArea(10, 30);
        itineraryDisplay.setEditable(false);
        frame.add(new JScrollPane(itineraryDisplay));

        // Label for displaying total budget
        totalBudgetLabel = new JLabel("Total Budget: $0.00");
        frame.add(totalBudgetLabel);

        // Label for general messages (like weather info, errors, etc.)
        messageLabel = new JLabel("");
        frame.add(messageLabel);

        // Show the frame
        frame.setVisible(true);
    }

    private static void addDestination() {
        try {
            // Get user input
            String destination = destinationField.getText();
            String date = dateField.getText();
            double transportCost = Double.parseDouble(transportCostField.getText());
            double accommodationCost = Double.parseDouble(accommodationCostField.getText());

            // Add the destination to the list and update budget
            destinations.add(destination);
            totalBudget += transportCost + accommodationCost;
            updateItinerary(destination, date, transportCost, accommodationCost);
            updateTotalBudget();

            // Clear input fields
            destinationField.setText("");
            dateField.setText("");
            transportCostField.setText("");
            accommodationCostField.setText("");

            messageLabel.setText("Destination added!");
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter valid costs.");
        }
    }

    private static void updateItinerary(String destination, String date, double transportCost, double accommodationCost) {
        itineraryDisplay.append("Destination: " + destination + "\n");
        itineraryDisplay.append("Date: " + date + "\n");
        itineraryDisplay.append("Transport Cost: $" + transportCost + "\n");
        itineraryDisplay.append("Accommodation Cost: $" + accommodationCost + "\n");
        itineraryDisplay.append("-----------------------------------\n");
    }

    private static void updateTotalBudget() {
        totalBudgetLabel.setText("Total Budget: $" + totalBudget);
    }

    // Optional: Function to fetch weather data (you could integrate OpenWeatherMap API here)
    private static void getWeatherInfo(String destination) {
        // For simplicity, you can fetch weather using an API and display it here.
        // This is just a placeholder message.
        messageLabel.setText("Weather info for " + destination + " will be fetched.");
    }
}
