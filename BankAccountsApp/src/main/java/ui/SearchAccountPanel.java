package ui;

import model.Account;
import model.AccountDirectory;

import javax.swing.*;
import java.awt.*;

public class SearchAccountPanel extends JPanel {

    private final JPanel container;
    private final AccountDirectory directory;
    private JTextField txtAccountNumber;

    public SearchAccountPanel(JPanel container, AccountDirectory directory) {
        this.container = container;
        this.directory = directory;
        init();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Search Account by Account Number");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        add(title, gbc);
        gbc.gridwidth=1;

        gbc.gridx=0; gbc.gridy=1;
        add(new JLabel("Account Number"), gbc);
        gbc.gridx=1;
        txtAccountNumber = new JTextField(20);
        add(txtAccountNumber, gbc);

        JPanel buttons = new JPanel();
        JButton btnSearch = new JButton("Search");
        JButton btnBack = new JButton("Back");
        buttons.add(btnSearch);
        buttons.add(btnBack);
        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=2;
        add(buttons, gbc);

        btnSearch.addActionListener(e -> onSearch());
        btnBack.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.previous(container);
        });
    }

    private void onSearch() {
        String acct = txtAccountNumber.getText().trim();
        if (acct.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter an account number.");
            return;
        }
        Account a = directory.searchByAccountNumber(acct);
        if (a == null) {
            JOptionPane.showMessageDialog(this, "No account found for: " + acct);
            return;
        }
        AccountDetailPanel detail = new AccountDetailPanel(container, a);
        container.add(detail, "DETAIL");
        CardLayout cl = (CardLayout) container.getLayout();
        cl.show(container, "DETAIL");
    }
}
