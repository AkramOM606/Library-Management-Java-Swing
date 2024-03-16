import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


public class mediatheque {
    AdherentsXML AXML;
    OeuvresXML OXML;
    PretsXML PXML;
    
    public mediatheque(String AFP,String OFP, String PFP) {
        AXML = new AdherentsXML(AFP);
        OXML = new OeuvresXML(OFP);
        PXML = new PretsXML(PFP);
    }

    public void addOeuvre(oeuvre oeuv) {
        OXML.add(oeuv); 
    }

    public void deleteOeuvre(oeuvre oeuv) {
        OXML.delete(oeuv);
    }

    public oeuvre search(oeuvre oeuv) {
        oeuvre result = OXML.get(oeuv.getId());
        if (result == null) {
            result = OXML.get(oeuv.getTitre());
        }
        return result;
    }

    public ArrayList<ArrayList<String>> OprintAll() {
        return OXML.print("*");
    }

    public ArrayList<ArrayList<String>> OprintCat(String x) {
        return OXML.print(x);
    }

    public void addAdherent(adherent ad) {
        AXML.add(ad);
    }

    public void deleteAdherent(adherent ad){
        AXML.delete(ad);
    }

    public Boolean emprunter(pret pr) {
        if ((!PXML.isLimited(AXML.get(pr.getIdadherant()))) && (OXML.getStatut(OXML.get(pr.getIdoeuvre())).equals("disponible"))) {
            System.out.println("????");
            PXML.add(pr);
            OXML.setStatut(OXML.get(pr.getIdoeuvre()), "non disponible");
            return true;
        } else {
            System.out.println("Impossible d'emprunter !");
            return false;
        }
    }

    public void eventB1() {
        int choice = JOptionPane.showOptionDialog(null, "Choisissez une option :", "Ajouter ou Supprimer ?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Ajouter une oeuvre", "Supprimer une oeuvre"}, null);
        if (choice == 0) {
            JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
            JTextField idField = new JTextField();
            JTextField titleField = new JTextField();
            JTextField categoryField = new JTextField();
            JTextField authorField = new JTextField();
            JTextField publisherField = new JTextField();
            JTextField launchDateField = new JTextField();
            JTextField statusField = new JTextField();
            dialogPanel.add(new JLabel("ID :"));
            dialogPanel.add(idField);
            dialogPanel.add(new JLabel("Titre :"));
            dialogPanel.add(titleField);
            dialogPanel.add(new JLabel("Categorie:"));
            dialogPanel.add(categoryField);
            dialogPanel.add(new JLabel("Auteur :"));
            dialogPanel.add(authorField);
            dialogPanel.add(new JLabel("Editeur :"));
            dialogPanel.add(publisherField);
            dialogPanel.add(new JLabel("Annee de sortie :"));
            dialogPanel.add(launchDateField);
            dialogPanel.add(new JLabel("Statut :"));	
            dialogPanel.add(statusField);
        
            int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Ajouter une oeuvre", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                addOeuvre(new oeuvre(Integer.parseInt(idField.getText()), titleField.getText(), categoryField.getText(), authorField.getText(), publisherField.getText(), Integer.parseInt(launchDateField.getText()), statusField.getText()));
                System.out.println("Added !");
            }   
        } else if (choice == 1) {
            int choice1 = JOptionPane.showOptionDialog(null, "Choisissez une option :", "Titre ou ID ?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Selection par Titre", "Selection par ID"}, null);
            if (choice1 == 0 ) {
                JPanel panel = new JPanel();
                panel.add(new JLabel("Entrer le titre de l'oeuvre a supprimer :"));
                JTextField textField = new JTextField(20);
                panel.add(textField);
                int result = JOptionPane.showConfirmDialog(null, panel, "Titre de l'oeuvre", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    deleteOeuvre(new oeuvre(0, textField.getText(), null, null, null,0, null));
                    System.out.println("Deleted ?!");
                }
            } else if (choice1 == 1) {
                JPanel panel = new JPanel();
                panel.add(new JLabel("Entrer l'ID de l'oeuvre a supprimer :"));
                JTextField textField = new JTextField(20);
                panel.add(textField);
                int result = JOptionPane.showConfirmDialog(null, panel, "ID de l'oeuvre", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    deleteOeuvre(new oeuvre(Integer.parseInt(textField.getText()), null, null, null, null, 0, null));
                }
            }
        }
    }

    public void eventB2() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Entrer l'ID ou bien le titre de l'oeuvre a chercher :"));
        JTextField textField = new JTextField(20);
        panel.add(textField);
        int result = JOptionPane.showConfirmDialog(null, panel, "ID/Titre de l'oeuvre", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            oeuvre oeu = new oeuvre();
            try {
                oeu = search(new oeuvre(Integer.parseInt(textField.getText()), null, null, null, null, 0, null));
            } catch (NumberFormatException e) {
                oeu = search(new oeuvre(0, textField.getText(), null, null, null, 0, null));
            }

            if (oeu != null) {
                String message = "ID :" + oeu.getId() +  "\nCategorie: " + oeu.getCategorie() + "\nTitre: " + oeu.getTitre() + "\nEditeur: " + oeu.getEditeur() + "\nDate de sortie: " + oeu.getLaunchdate() + "\nStatut: " + oeu.getStatut();
                JOptionPane.showMessageDialog(null, message);               
            } else {
                JOptionPane.showMessageDialog(null, "Introuvable", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eventB34(String x) {
        if (!x.equals("*")) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Entrer la categorie :"));
            JTextField textField = new JTextField(20);
            panel.add(textField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Categorie ?", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                x = textField.getText();
            }
        }

        String[] columnNames = {"ID", "Titre", "Categorie", "Auteur", "Editeur", "Date de sortie", "Statut"};

        ArrayList<ArrayList<String>> data = OXML.print(x);
        
        if (data.size()!=0) {
            Object[][] dataArray = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                ArrayList<String> row = data.get(i);
                dataArray[i] = row.toArray(new String[row.size()]);
            }

            JTable table = new JTable(dataArray, columnNames);
            table.setFont(new Font("Arial", Font.BOLD, 14));

            JScrollPane scrollPane = new JScrollPane(table);

            JFrame frame = new JFrame();
            frame.add(scrollPane);
            frame.setPreferredSize(new Dimension(800, 600));
            frame.pack();
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Introuvable", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eventB5() {
        JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
        JTextField numeroAd = new JTextField();
        JTextField nom = new JTextField();
        JTextField prenom = new JTextField();
        JTextField adresse = new JTextField();
        JTextField email = new JTextField();
        dialogPanel.add(new JLabel("Numero d'adherent :"));
        dialogPanel.add(numeroAd);
        dialogPanel.add(new JLabel("Nom :"));
        dialogPanel.add(nom);
        dialogPanel.add(new JLabel("Prenom :"));
        dialogPanel.add(prenom);
        dialogPanel.add(new JLabel("Adresse :"));
        dialogPanel.add(adresse);
        dialogPanel.add(new JLabel("E-mail :"));
        dialogPanel.add(email);

        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Ajouter un adherent", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            addAdherent(new adherent(Integer.parseInt(numeroAd.getText()), nom.getText(), prenom.getText(), adresse.getText(), email.getText()));
            System.out.println("Added !");
        }
    }

    public void eventB6() {
        int choice = JOptionPane.showOptionDialog(null, "Choisissez une option :", "ID ou E-mail ?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Selection par ID", "Selection par E-mail"}, null);
        if (choice == 0 ) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Entrer l'ID de l'adherent a supprimer :"));
            JTextField textField = new JTextField(20);
            panel.add(textField);
            int result = JOptionPane.showConfirmDialog(null, panel, "ID de l'adherent", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                deleteAdherent(new adherent(Integer.parseInt(textField.getText()), null, null, null, null));
            }
        } else if (choice == 1) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Entrer l'e-mail de l'adherent a supprimer :"));
            JTextField textField = new JTextField(20);
            panel.add(textField);
            int result = JOptionPane.showConfirmDialog(null, panel, "ID de l'oeuvre", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                deleteAdherent(new adherent(0, null, null, null, textField.getText()));
            }
        }
    }

    public void eventB7() {
        String[] columnNames = {"ID", "Nom", "Prenom", "Adresse", "E-mail"};

        ArrayList<ArrayList<String>> data = AXML.print();
        
        if (data.size()!=0) {
            Object[][] dataArray = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                ArrayList<String> row = data.get(i);
                dataArray[i] = row.toArray(new String[row.size()]);
            }

            JTable table = new JTable(dataArray, columnNames);
            table.setFont(new Font("Arial", Font.BOLD, 14));

            JScrollPane scrollPane = new JScrollPane(table);

            JFrame frame = new JFrame();
            frame.add(scrollPane);
            frame.setPreferredSize(new Dimension(800, 600));
            frame.pack();
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Introuvable", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eventB8() {
        JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
        JTextField pretdate = new JTextField();
        JTextField idadherent = new JTextField();
        JTextField idoeuvre = new JTextField();
        dialogPanel.add(new JLabel("Date de prete :"));
        dialogPanel.add(pretdate);
        dialogPanel.add(new JLabel("ID d'adherent :"));
        dialogPanel.add(idadherent);
        dialogPanel.add(new JLabel("ID d'oeuvre :"));
        dialogPanel.add(idoeuvre);
        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Preter LOG", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (!emprunter(new pret(pretdate.getText(), Integer.parseInt(idadherent.getText()), Integer.parseInt(idoeuvre.getText())))) {
                JOptionPane.showMessageDialog(null, "Impossible d'emprunter", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eventB9() {
        String[] columnNames = {"Date de prete", "ID adherent", "ID oeuvre"};

        ArrayList<ArrayList<String>> data = PXML.print();
        
        if (data.size()!=0) {
            Object[][] dataArray = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                ArrayList<String> row = data.get(i);
                dataArray[i] = row.toArray(new String[row.size()]);
            }

            JTable table = new JTable(dataArray, columnNames);
            table.setFont(new Font("Arial", Font.BOLD, 14));

            JScrollPane scrollPane = new JScrollPane(table);

            JFrame frame = new JFrame();
            frame.add(scrollPane);
            frame.setPreferredSize(new Dimension(800, 600));
            frame.pack();
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Introuvable", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}