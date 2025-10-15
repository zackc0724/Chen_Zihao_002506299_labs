package ui;

import model.AccountDirectory;
import model.Account;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);
    private final AccountDirectory directory = new AccountDirectory();

    // Keep references to panels we need to refresh
    private ListAccountsPanel listPanel;
    private CreateAccountPanel createPanel;
    private SearchAccountPanel searchPanel;

    public MainFrame() {
        setTitle("Bank Accounts App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // seed some sample data
        directory.addAccount(new Account("011000015", "1001", "Bank of Spring", 1200));
        directory.addAccount(new Account("021000021", "1002", "Winter Credit Union", 2500));
        directory.addAccount(new Account("031000053", "1003", "Autumn Savings", 400));

        JPanel home = buildHome();
        cards.add(home, "HOME");

        createPanel = new CreateAccountPanel(cards, directory);
        cards.add(createPanel, "CREATE");

        listPanel = new ListAccountsPanel(cards, directory);
        cards.add(listPanel, "LIST");

        searchPanel = new SearchAccountPanel(cards, directory);
        cards.add(searchPanel, "SEARCH");

        add(cards, BorderLayout.CENTER);
        showCard("HOME");
    }

    private JPanel buildHome() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Bank Accounts App");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        gbc.gridx=0; gbc.gridy=0;
        p.add(title, gbc);

        JButton btnCreate = new JButton("Create Account");
        btnCreate.addActionListener(e -> showCard("CREATE"));
        gbc.gridy=1;
        p.add(btnCreate, gbc);

        JButton btnList = new JButton("View All Accounts");
        btnList.addActionListener(e -> {
            listPanel.refreshTable();
            showCard("LIST");
        });
        gbc.gridy=2;
        p.add(btnList, gbc);

        JButton btnSearch = new JButton("Search by Account Number");
        btnSearch.addActionListener(e -> showCard("SEARCH"));
        gbc.gridy=3;
        p.add(btnSearch, gbc);

        return p;
    }

    public void showCard(String name) {
        cardLayout.show(cards, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
