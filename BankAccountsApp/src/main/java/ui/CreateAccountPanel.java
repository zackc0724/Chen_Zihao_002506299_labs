package ui;

import model.AccountDirectory;
import model.Account;

import javax.swing.*;
import java.awt.*;

public class CreateAccountPanel extends JPanel {

    private final JPanel container;
    private final AccountDirectory directory;

    private JTextField txtRouting;
    private JTextField txtAccount;
    private JTextField txtBank;
    private JTextField txtBalance;

    public CreateAccountPanel(JPanel container, AccountDirectory directory) {
        this.container = container;
        this.directory = directory;
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Create Account");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        add(title, gbc);
        gbc.gridwidth=1;

        gbc.gridx=0; gbc.gridy=1;
        add(new JLabel("Routing Number"), gbc);
        gbc.gridx=1;
        txtRouting = new JTextField(20);
        add(txtRouting, gbc);

        gbc.gridx=0; gbc.gridy=2;
        add(new JLabel("Account Number"), gbc);
        gbc.gridx=1;
        txtAccount = new JTextField(20);
        add(txtAccount, gbc);

        gbc.gridx=0; gbc.gridy=3;
        add(new JLabel("Bank Name"), gbc);
        gbc.gridx=1;
        txtBank = new JTextField(20);
        add(txtBank, gbc);

        gbc.gridx=0; gbc.gridy=4;
        add(new JLabel("Balance"), gbc);
        gbc.gridx=1;
        txtBalance = new JTextField(20);
        add(txtBalance, gbc);

        JPanel buttons = new JPanel();
        JButton btnCreate = new JButton("Create");
        JButton btnBack = new JButton("Back");
        buttons.add(btnCreate);
        buttons.add(btnBack);

        gbc.gridx=0; gbc.gridy=5; gbc.gridwidth=2;
        add(buttons, gbc);

        btnCreate.addActionListener(e -> onCreate());
        btnBack.addActionListener(e -> back());
    }

    private void onCreate() {
        String routing = txtRouting.getText().trim();
        String account = txtAccount.getText().trim();
        String bank = txtBank.getText().trim();
        String balText = txtBalance.getText().trim();

        if (routing.isEmpty() || account.isEmpty() || bank.isEmpty() || balText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int balance;
        try {
            balance = Integer.parseInt(balText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Balance must be an integer", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Account a = directory.addAccount();
        a.setRoutingNumber(routing);
        a.setAccountNumber(account);
        a.setBankName(bank);
        a.setBalance(balance);

        JOptionPane.showMessageDialog(this, "Account created.");
        clear();
    }

    private void clear() {
        txtRouting.setText("");
        txtAccount.setText("");
        txtBank.setText("");
        txtBalance.setText("");
    }

    private void back() {
        CardLayout cl = (CardLayout) container.getLayout();
        cl.previous(container);
    }
}
