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
            System.out.println("Impossible to lend !");
            return false;
        }
    }

    public void eventB1() {
        int choice = JOptionPane.showOptionDialog(null, "Choose an option :", "Add or Remove?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Add a book", "Delete a book"}, null);
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
            dialogPanel.add(new JLabel("Title :"));
            dialogPanel.add(titleField);
            dialogPanel.add(new JLabel("Category :"));
            dialogPanel.add(categoryField);
            dialogPanel.add(new JLabel("Author :"));
            dialogPanel.add(authorField);
            dialogPanel.add(new JLabel("Publisher :"));
            dialogPanel.add(publisherField);
            dialogPanel.add(new JLabel("Release date :"));
            dialogPanel.add(launchDateField);
            dialogPanel.add(new JLabel("Status :"));	
            dialogPanel.add(statusField);
        
            int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Add a book", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                addOeuvre(new oeuvre(Integer.parseInt(idField.getText()), titleField.getText(), categoryField.getText(), authorField.getText(), publisherField.getText(), Integer.parseInt(launchDateField.getText()), statusField.getText()));
                System.out.println("Added !");
            }   
        } else if (choice == 1) {
            int choice1 = JOptionPane.showOptionDialog(null, "Choose an option :", "Title or ID ?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Selection by Title", "Selection by ID"}, null);
            if (choice1 == 0 ) {
                JPanel panel = new JPanel();
                panel.add(new JLabel("Enter the title of the book to be deleted :"));
                JTextField textField = new JTextField(20);
                panel.add(textField);
                int result = JOptionPane.showConfirmDialog(null, panel, "Title of the book", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    deleteOeuvre(new oeuvre(0, textField.getText(), null, null, null,0, null));
                    System.out.println("Deleted ?!");
                }
            } else if (choice1 == 1) {
                JPanel panel = new JPanel();
                panel.add(new JLabel("Enter the ID of the book to be deleted :"));
                JTextField textField = new JTextField(20);
                panel.add(textField);
                int result = JOptionPane.showConfirmDialog(null, panel, "ID of the book", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    deleteOeuvre(new oeuvre(Integer.parseInt(textField.getText()), null, null, null, null, 0, null));
                }
            }
        }
    }

    public void eventB2() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter the ID or title of the book to search for :"));
        JTextField textField = new JTextField(20);
        panel.add(textField);
        int result = JOptionPane.showConfirmDialog(null, panel, "ID/Title of book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            oeuvre oeu = new oeuvre();
            try {
                oeu = search(new oeuvre(Integer.parseInt(textField.getText()), null, null, null, null, 0, null));
            } catch (NumberFormatException e) {
                oeu = search(new oeuvre(0, textField.getText(), null, null, null, 0, null));
            }

            if (oeu != null) {
                String message = "ID :" + oeu.getId() +  "\nCategory: " + oeu.getCategorie() + "\nTitle: " + oeu.getTitre() + "\nPublisher: " + oeu.getEditeur() + "\nRelease date: " + oeu.getLaunchdate() + "\nStatus: " + oeu.getStatut();
                JOptionPane.showMessageDialog(null, message);               
            } else {
                JOptionPane.showMessageDialog(null, "Not Found !", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eventB34(String x) {
        if (!x.equals("*")) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Enter category :"));
            JTextField textField = new JTextField(20);
            panel.add(textField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Category ?", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                x = textField.getText();
            }
        }

        String[] columnNames = {"ID", "Title", "Category", "Author", "Publisher", "Release date", "Status"};

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
            JOptionPane.showMessageDialog(null, "Not Found !", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eventB5() {
        JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
        JTextField numeroAd = new JTextField();
        JTextField nom = new JTextField();
        JTextField prenom = new JTextField();
        JTextField adresse = new JTextField();
        JTextField email = new JTextField();
        dialogPanel.add(new JLabel("Member ID :"));
        dialogPanel.add(numeroAd);
        dialogPanel.add(new JLabel("Last name :"));
        dialogPanel.add(nom);
        dialogPanel.add(new JLabel("First name :"));
        dialogPanel.add(prenom);
        dialogPanel.add(new JLabel("Address :"));
        dialogPanel.add(adresse);
        dialogPanel.add(new JLabel("E-mail :"));
        dialogPanel.add(email);

        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Add a member", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            addAdherent(new adherent(Integer.parseInt(numeroAd.getText()), nom.getText(), prenom.getText(), adresse.getText(), email.getText()));
            System.out.println("Added !");
        }
    }

    public void eventB6() {
        int choice = JOptionPane.showOptionDialog(null, "Choose an option :", "ID or E-mail ?", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Selection by ID", "Selection by E-mail"}, null);
        if (choice == 0 ) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Enter the ID of the member to be deleted :"));
            JTextField textField = new JTextField(20);
            panel.add(textField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Member ID", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                deleteAdherent(new adherent(Integer.parseInt(textField.getText()), null, null, null, null));
            }
        } else if (choice == 1) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Enter the e-mail address of the member to be deleted :"));
            JTextField textField = new JTextField(20);
            panel.add(textField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Book ID", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                deleteAdherent(new adherent(0, null, null, null, textField.getText()));
            }
        }
    }

    public void eventB7() {
        String[] columnNames = {"ID", "Last name", "First name", "Address", "E-mail"};

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
            JOptionPane.showMessageDialog(null, "Not Found !", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eventB8() {
        JPanel dialogPanel = new JPanel(new GridLayout(0, 2));
        JTextField pretdate = new JTextField();
        JTextField idadherent = new JTextField();
        JTextField idoeuvre = new JTextField();
        dialogPanel.add(new JLabel("Lending date :"));
        dialogPanel.add(pretdate);
        dialogPanel.add(new JLabel("Member ID :"));
        dialogPanel.add(idadherent);
        dialogPanel.add(new JLabel("Book ID :"));
        dialogPanel.add(idoeuvre);
        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Lending Information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (!emprunter(new pret(pretdate.getText(), Integer.parseInt(idadherent.getText()), Integer.parseInt(idoeuvre.getText())))) {
                JOptionPane.showMessageDialog(null, "Impossible to lend", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eventB9() {
        String[] columnNames = {"Lending date", "Member ID", "Book ID"};

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
            JOptionPane.showMessageDialog(null, "Not Found !", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}