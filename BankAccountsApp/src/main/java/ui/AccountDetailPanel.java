package ui;

import model.Account;

import javax.swing.*;
import java.awt.*;

public class AccountDetailPanel extends JPanel {

    private final JPanel container;
    private final Account account;

    public AccountDetailPanel(JPanel container, Account account) {
        this.container = container;
        this.account = account;
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Account Details");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        add(title, gbc);
        gbc.gridwidth=1;

        addRow(gbc, 1, "Routing Number", account.getRoutingNumber());
        addRow(gbc, 2, "Account Number", account.getAccountNumber());
        addRow(gbc, 3, "Bank Name", account.getBankName());
        addRow(gbc, 4, "Balance", String.valueOf(account.getBalance()));

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.previous(container);
        });
        gbc.gridx=0; gbc.gridy=5; gbc.gridwidth=2;
        add(btnBack, gbc);
    }

    private void addRow(GridBagConstraints gbc, int y, String label, String value) {
        gbc.gridx=0; gbc.gridy=y;
        add(new JLabel(label), gbc);
        gbc.gridx=1;
        JTextField tf = new JTextField(value, 20);
        tf.setEditable(false);
        add(tf, gbc);
    }
}
