import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class AdherentsXML {
    private String filePath;
    private Document doc;
    private Element root;
    
    public AdherentsXML(String filePath) {
        this.filePath=filePath;
        load();
    }

    private void load() {
        try {
            SAXBuilder sax = new SAXBuilder();
            doc = sax.build(new File(filePath));
            root = doc.getRootElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(adherent adh) {
        Element e = new Element("adherent");
        Attribute a = new Attribute("numero", "" + adh.getNumero());
        e.setAttribute(a);
        
        Element e1 = new Element("nom");
        e1.addContent(adh.getNom());
        e.addContent(e1);

        Element e2 = new Element("prenom");
        e2.addContent(adh.getPrenom());
        e.addContent(e2);
        
        Element e3 = new Element("adresse");
        e3.addContent(adh.getAdresse());
        e.addContent(e3);
        
        Element e4 = new Element("email");
        e4.addContent(adh.getEmail());
        e.addContent(e4);

        root.addContent(e);
        save();
    }
    
    public void delete(adherent ad) {
        List<Element> l = root.getChildren();
        if (l.size() > 0) {
            for (Element child : l) {
                int num = Integer.parseInt(child.getAttributeValue("numero"));
                String mail = child.getChildText("email");
                if (ad.getNumero() == num) {
                    child.getParent().removeContent(child);
                    save();
                } else if (mail.equals(ad.getEmail())) {
                    child.getParent().removeContent(child);
                    save();
                }
            }
        }
    }

    private void save() {
        try {
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            out.output(doc, new FileOutputStream(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public adherent get(int numero) {
        List<Element> l = root.getChildren();
        if (l.size() > 0) {
            for (Element child : l) {
                int num = Integer.parseInt(child.getAttributeValue("numero"));
                if (numero == num) {
                    String nom = child.getChildText("nom");
                    String prenom = child.getChildText("prenom");
                    String adresse = child.getChildText("adresee");
                    String email = child.getChildText("email");
                    return new adherent(numero, nom, prenom, adresse, email);
                }
            }
        }
        return null;
    }

    public ArrayList<ArrayList<String>> print() {
        ArrayList<ArrayList<String>> S = new ArrayList<ArrayList<String>>();
        List<Element> l = root.getChildren();
        if (l.size() > 0) {
            for (Element child : l) {
                ArrayList<String> row = new ArrayList<String>();
                String id = child.getAttributeValue("numero");
                String nom = child.getChildText("nom");
                String prenom = child.getChildText("prenom");
                String adresse = child.getChildText("adresse");
                String email = child.getChildText("email");
                row.add(id);
                row.add(nom);
                row.add(prenom);
                row.add(adresse);
                row.add(email);
                S.add(row);
                System.out.println(new adherent(Integer.parseInt(id), nom, prenom, adresse, email));    
            }
        }
        return S;
    }
}