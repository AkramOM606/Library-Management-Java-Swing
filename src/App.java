import java.awt.*;
import javax.swing.*;

public class App extends JFrame {
    private mediatheque M = new mediatheque(Constants.ADHERENTS_XML_FILEPATH, Constants.OEUVRES_XML_FILEPATH, Constants.PRETS_XML_FILEPATH);
    public App() {
        GUI();
    }

    void GUI() {
        setTitle("Library Management System");
        setSize(800, 600);

        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mediathequeLabel = new JLabel("Library Management System");
        mediathequeLabel.setForeground(Color.RED);
        mediathequeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        Insets margin = new Insets(15, 0, 40, 0);
        topPanel.setBorder(BorderFactory.createEmptyBorder(margin.top, margin.left, margin.bottom, margin.right));
        topPanel.add(mediathequeLabel);
        
        JPanel middlePanel = new JPanel(new GridLayout(1, 3));
        
        JPanel oeuvresPanel = new JPanel(new BorderLayout());
        JLabel oeuvresLabel = new JLabel("Book Management", SwingConstants.CENTER);
        oeuvresLabel.setFont(new Font("Arial", Font.BOLD, 20));
        oeuvresPanel.add(oeuvresLabel, BorderLayout.NORTH);
        JPanel oeuvresButtonsPanel = new JPanel(new GridLayout(4, 1));

        MultilineButton Button1 = new MultilineButton("Add/Remove a Book\nfrom the Collection");
        MultilineButton Button2 = new MultilineButton("Search for a Book");
        MultilineButton Button3 = new MultilineButton("Display All Books");
        MultilineButton Button4 = new MultilineButton("Display All Books\nin a Category");

        
        Button1.addActionListener(e -> M.eventB1());
        Button2.addActionListener(e -> M.eventB2());
        Button3.addActionListener(e -> M.eventB34("*"));
        Button4.addActionListener(e -> M.eventB34("A"));

        oeuvresButtonsPanel.add(Button1);
        oeuvresButtonsPanel.add(Button2);
        oeuvresButtonsPanel.add(Button3);
        oeuvresButtonsPanel.add(Button4);
        oeuvresPanel.add(oeuvresButtonsPanel, BorderLayout.CENTER);
        middlePanel.add(oeuvresPanel);
        
        JPanel adherentsPanel = new JPanel(new BorderLayout());
        JLabel adherentsLabel = new JLabel("Members Management", SwingConstants.CENTER);
        adherentsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        adherentsPanel.add(adherentsLabel, BorderLayout.NORTH);
        JPanel adherentsButtonsPanel = new JPanel(new GridLayout(3, 1));

        MultilineButton Button5 = new MultilineButton("Add a member");
        MultilineButton Button6 = new MultilineButton("Remove a member");
        MultilineButton Button7 = new MultilineButton("Display members");

        Button5.addActionListener(e -> M.eventB5());
        Button6.addActionListener(e -> M.eventB6());
        Button7.addActionListener(e -> M.eventB7());

        adherentsButtonsPanel.add(Button5);
        adherentsButtonsPanel.add(Button6);
        adherentsButtonsPanel.add(Button7);
        adherentsPanel.add(adherentsButtonsPanel, BorderLayout.CENTER);
        middlePanel.add(adherentsPanel);
        
        JPanel pretsPanel = new JPanel(new BorderLayout());
        JLabel pretsLabel = new JLabel("Lending Management", SwingConstants.CENTER);
        pretsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pretsPanel.add(pretsLabel, BorderLayout.NORTH);
        JPanel pretsButtonsPanel = new JPanel(new GridLayout(2, 1));

        MultilineButton Button8 = new MultilineButton("Borrow a Book");
        MultilineButton Button9 = new MultilineButton("Display Borrowed\nBooks");

        Button8.addActionListener(e -> M.eventB8());
        Button9.addActionListener(e -> M.eventB9());

        pretsButtonsPanel.add(Button8);
        pretsButtonsPanel.add(Button9);
        pretsPanel.add(pretsButtonsPanel, BorderLayout.CENTER);
        middlePanel.add(pretsPanel);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        
        setContentPane(mainPanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Library Management System Is Up And Running !");
        new App();
    }
}