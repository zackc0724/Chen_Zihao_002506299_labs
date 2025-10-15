package ui;

import model.Account;
import model.AccountDirectory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListAccountsPanel extends JPanel {

    private final JPanel container;
    private final AccountDirectory directory;
    private JTable table;

    public ListAccountsPanel(JPanel container, AccountDirectory directory) {
        this.container = container;
        this.directory = directory;
        init();
    }

    private void init() {
        setLayout(new BorderLayout(10,10));

        JLabel title = new JLabel("All Accounts", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        add(title, BorderLayout.NORTH);

        table = new JTable(new DefaultTableModel(new Object[]{"Routing #", "Account #", "Bank", "Balance"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton btnView = new JButton("View Details");
        JButton btnBack = new JButton("Back");
        buttons.add(btnView);
        buttons.add(btnBack);
        add(buttons, BorderLayout.SOUTH);

        btnView.addActionListener(e -> onView());
        btnBack.addActionListener(e -> {
            CardLayout cl = (CardLayout) container.getLayout();
            cl.previous(container);
        });
    }

    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Account a : directory.getAccounts()) {
            model.addRow(new Object[]{a.getRoutingNumber(), a.getAccountNumber(), a.getBankName(), a.getBalance()});
        }
    }

    private void onView() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Select a row to view details.");
            return;
        }
        String accountNumber = table.getValueAt(row, 1).toString();
        Account a = directory.searchByAccountNumber(accountNumber);
        if (a == null) {
            JOptionPane.showMessageDialog(this, "Account not found.");
            return;
        }
        AccountDetailPanel detail = new AccountDetailPanel(container, a);
        container.add(detail, "DETAIL");
        CardLayout cl = (CardLayout) container.getLayout();
        cl.show(container, "DETAIL");
    }
}
