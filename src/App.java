import java.awt.*;
import javax.swing.*;

public class App extends JFrame {
    private mediatheque M = new mediatheque(Constants.ADHERENTS_XML_FILEPATH, Constants.OEUVRES_XML_FILEPATH, Constants.PRETS_XML_FILEPATH);
    public App() {
        //test01();
        //test02();
        //test03();
        GUI();
    }

    void test01() {
        mediatheque M = new mediatheque(Constants.ADHERENTS_XML_FILEPATH, Constants.OEUVRES_XML_FILEPATH, Constants.PRETS_XML_FILEPATH);
        M.addAdherent(new adherent(001, "ADRANE", "Akram", "Med V Goulmima", "akram.quark@gmail.com"));
        M.addAdherent(new adherent(002, "HARCHA", "Badr", "Med V Oujda", "badr.harcha@gmail.com"));
        adherent testAD1 = new adherent(003, "MOUGHIL", "Naoufal", "MED V Kenitra", "naoufal.moughil@gmail.com");
        M.addAdherent(testAD1);
        //M.deleteAdherent(testAD1);
        M.addOeuvre(new oeuvre(0000,"Jolie", "Nothing", "Ricardo", "William", 2020, "disponible"));
        oeuvre testOEUV1 = new oeuvre(0001,"Ikhan", "WOW", "Igoulmimen", "Aha", 2023, "disponible");
        M.addOeuvre(testOEUV1);
        //M.deleteOeuvre(testOEUV1);
    }

    void test02() {
        M.OprintAll();
        M.OprintCat("Nothing");
    }

    void test03() {
        M.emprunter(new pret("01/01/2023", 1, 0));
        M.emprunter(new pret("01/01/2023", 1, 0));
    }

    void GUI() {
        setTitle("TP 4 in Java");
        setSize(800, 600);

        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mediathequeLabel = new JLabel("Mediatheque");
        mediathequeLabel.setForeground(Color.RED);
        mediathequeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        topPanel.add(mediathequeLabel);
        
        JPanel middlePanel = new JPanel(new GridLayout(1, 3));
        
        JPanel oeuvresPanel = new JPanel(new BorderLayout());
        JLabel oeuvresLabel = new JLabel("Gestion des oeuvres", SwingConstants.CENTER);
        oeuvresLabel.setFont(new Font("Arial", Font.BOLD, 20));
        oeuvresPanel.add(oeuvresLabel, BorderLayout.NORTH);
        JPanel oeuvresButtonsPanel = new JPanel(new GridLayout(4, 1));

        MultilineButton Button1 = new MultilineButton("Ajouter / Supprimer\nune oeuvre de la collection");
        MultilineButton Button2 = new MultilineButton("Rechercher une oeuvre");
        MultilineButton Button3 = new MultilineButton("Afficher tous les objets\nde la collection");
        MultilineButton Button4 = new MultilineButton("Afficher tous les objets\nd'une meme categorie");

        
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
        JLabel adherentsLabel = new JLabel("Gestion des adherents", SwingConstants.CENTER);
        adherentsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        adherentsPanel.add(adherentsLabel, BorderLayout.NORTH);
        JPanel adherentsButtonsPanel = new JPanel(new GridLayout(3, 1));

        MultilineButton Button5 = new MultilineButton("Ajouter un adherant");
        MultilineButton Button6 = new MultilineButton("Supprimer un adherant");
        MultilineButton Button7 = new MultilineButton("Afficher les adherants");

        Button5.addActionListener(e -> M.eventB5());
        Button6.addActionListener(e -> M.eventB6());
        Button7.addActionListener(e -> M.eventB7());

        adherentsButtonsPanel.add(Button5);
        adherentsButtonsPanel.add(Button6);
        adherentsButtonsPanel.add(Button7);
        adherentsPanel.add(adherentsButtonsPanel, BorderLayout.CENTER);
        middlePanel.add(adherentsPanel);
        
        JPanel pretsPanel = new JPanel(new BorderLayout());
        JLabel pretsLabel = new JLabel("Gestion des prets", SwingConstants.CENTER);
        pretsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pretsPanel.add(pretsLabel, BorderLayout.NORTH);
        JPanel pretsButtonsPanel = new JPanel(new GridLayout(2, 1));

        MultilineButton Button8 = new MultilineButton("Emprunter une oeuvre");
        MultilineButton Button9 = new MultilineButton("Afficher les emprunts");

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
        System.out.println("TP 4 in Java !");
        System.out.println("Hello World !");
        new App();
    }
}