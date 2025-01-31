import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GradeCalculatorGUI extends JFrame {

    private ArrayList<Integer> grades = new ArrayList<>();
    private JTextField gradeInputField;
    private JTextArea resultArea;
    private JButton addButton, calculateButton, resetButton;

    public GradeCalculatorGUI() {
        // Set up the frame with modern design
        setTitle("Grade Calculator");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setLocationRelativeTo(null); // Center the window on the screen
        getContentPane().setBackground(new Color(245, 245, 245)); // Light grey background for the window

        // Set custom fonts (make sure the fonts are available)
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font textAreaFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Create components with modern styles
        JLabel gradeLabel = new JLabel("Enter grade (0-100):");
        gradeLabel.setFont(labelFont);

        gradeInputField = new JTextField(10);
        gradeInputField.setFont(textAreaFont);
        gradeInputField.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 255), 2));  // Blue border
        gradeInputField.setBackground(new Color(255, 255, 255));  // White background

        addButton = new JButton("Add Grade");
        addButton.setFont(buttonFont);
        addButton.setBackground(new Color(0, 204, 255));  // Blue button
        // addButton.setBackground(new Color(108,115 , 141));  // Blue button
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 255), 2));
        addButton.setPreferredSize(new Dimension(120, 40)); // Larger button

        calculateButton = new JButton("Calculate");
        calculateButton.setFont(buttonFont);
        calculateButton.setBackground(new Color(102, 255, 102));  // Cyan gradient
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setEnabled(false);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorder(BorderFactory.createLineBorder(new Color(0, 230, 0), 2));
        calculateButton.setPreferredSize(new Dimension(120, 40));

        resetButton = new JButton("Reset");
        resetButton.setFont(buttonFont);
        resetButton.setBackground(Color.YELLOW);  // Set background color to red
        resetButton.setForeground(Color.WHITE);  // Set text color to black
        resetButton.setEnabled(false);  // Initially disabled
        resetButton.setFocusPainted(false);  // Remove focus border
        resetButton.setBorder(BorderFactory.createLineBorder(new Color(255, 234, 0), 2));  // Optional custom border color
        resetButton.setPreferredSize(new Dimension(120, 40));  // Size adjustment

        resultArea = new JTextArea(8, 30);
        resultArea.setFont(textAreaFont);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 255), 2));  // Blue border for results area
        resultArea.setBackground(new Color(240, 240, 240));  // Light gray background
        resultArea.setLineWrap(true);

        // Add components to the frame
        add(gradeLabel);
        add(gradeInputField);
        add(addButton);
        add(calculateButton);
        add(resetButton);
        add(new JScrollPane(resultArea));

        // Button action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int grade = Integer.parseInt(gradeInputField.getText());
                    if (grade < 0 || grade > 100) {
                        JOptionPane.showMessageDialog(null, "Grade must be between 0 and 100!");
                    } else {
                        grades.add(grade);
                        gradeInputField.setText("");  // Clear the input field
                        calculateButton.setEnabled(true);  // Enable calculate button
                        resetButton.setEnabled(true);  // Enable reset button
                        JOptionPane.showMessageDialog(null, "Grade added successfully!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number between 0 and 100.");
                }
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (grades.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No grades to calculate!");
                    return;
                }

                int sum = 0;
                int highest = grades.get(0);
                int lowest = grades.get(0);

                for (int grade : grades) {
                    sum += grade;
                    if (grade > highest) highest = grade;
                    if (grade < lowest) lowest = grade;
                }

                double average = (double) sum / grades.size();

                // Display results in the text area with modern formatting
                resultArea.setText("Results:\n");
                resultArea.append("Total number of grades entered: " + grades.size() + "\n");
                resultArea.append("Highest grade: " + highest + "\n");
                resultArea.append("Lowest grade: " + lowest + "\n");
                resultArea.append("Average grade: " + String.format("%.2f", average));
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grades.clear();
                resultArea.setText("");  // Clear the result area
                gradeInputField.setText("");  // Clear the input field
                calculateButton.setEnabled(false);  // Disable calculate button
                resetButton.setEnabled(false);  // Disable reset button
                JOptionPane.showMessageDialog(null, "Data reset!");
            }
        });
    }

    public static void main(String[] args) {
        // Launch the GUI with the modern look
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculatorGUI().setVisible(true);
            }
        });
    }
}
