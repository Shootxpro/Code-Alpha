import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingAppUI {
    static double balance = 0; // Account balance

    // Declare Swing components
    private static JFrame frame;
    private static JTextField amountField;
    private static JTextArea balanceDisplay;
    private static JLabel messageLabel;

    public static void main(String[] args) {
        // Create and set up the frame
        frame = new JFrame("Simple Banking Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        // Add balance display
        balanceDisplay = new JTextArea(1, 20);
        balanceDisplay.setEditable(false);
        balanceDisplay.setText("Current Balance: $" + balance);
        frame.add(balanceDisplay);

        // Add amount input field
        amountField = new JTextField(15);
        frame.add(amountField);

        // Add message label
        messageLabel = new JLabel("");
        frame.add(messageLabel);

        // Add deposit button
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });
        frame.add(depositButton);

        // Add withdraw button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });
        frame.add(withdrawButton);

        // Add check balance button
        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        frame.add(checkBalanceButton);

        // Add exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });
        frame.add(exitButton);

        // Show the frame
        frame.setVisible(true);
    }

    private static void handleDeposit() {
        try {
            double depositAmount = Double.parseDouble(amountField.getText());
            if (depositAmount <= 0) {
                messageLabel.setText("Deposit amount must be positive!");
            } else {
                balance += depositAmount;
                updateBalance();
                messageLabel.setText("Deposit successful!");
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid number for deposit!");
        }
    }

    private static void handleWithdraw() {
        try {
            double withdrawAmount = Double.parseDouble(amountField.getText());
            if (withdrawAmount <= 0) {
                messageLabel.setText("Withdrawal amount must be positive!");
            } else if (withdrawAmount > balance) {
                messageLabel.setText("Insufficient funds!");
            } else {
                balance -= withdrawAmount;
                updateBalance();
                messageLabel.setText("Withdrawal successful!");
            }
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid number for withdrawal!");
        }
    }

    private static void checkBalance() {
        messageLabel.setText("Current balance: $" + balance);
    }

    private static void updateBalance() {
        balanceDisplay.setText("Current Balance: $" + balance);
    }

    private static void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            frame.dispose(); // Close the application window
        }
    }
}
