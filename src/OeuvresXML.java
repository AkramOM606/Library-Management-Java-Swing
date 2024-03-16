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

public class OeuvresXML {
    private String filePath;
    private Document doc;
    private Element root;
    
    public OeuvresXML(String filePath) {
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

    
    private void save() {
        try {
            XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
            out.output(doc, new FileOutputStream(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(oeuvre oeuv) {
        Element e = new Element("oeuvre");

        Attribute ep = new Attribute("id", "" + oeuv.getId());
        e.setAttribute(ep);
        
        Element e1 = new Element("titre");
        e1.addContent(oeuv.getTitre());
        e.addContent(e1);

        Element e2 = new Element("categorie");
        e2.addContent(oeuv.getCategorie());
        e.addContent(e2);
        
        Element e3 = new Element("auteur");
        e3.addContent(oeuv.getAuteur());
        e.addContent(e3);
        
        Element e4 = new Element("editeur");
        e4.addContent(oeuv.getEditeur());
        e.addContent(e4);

        Element e5 = new Element("launchdate");
        e5.addContent("" + oeuv.getLaunchdate());
        e.addContent(e5);
        
        Element e6 = new Element("statut");
        e6.addContent(oeuv.getStatut());
        e.addContent(e6);

        root.addContent(e);
        save();
    }

    public void delete(oeuvre oeuv) {
        List<Element> l = root.getChildren();
        if (l.size() > 0) {
            for (Element child : l) {
                int id = Integer.parseInt(child.getAttributeValue("id"));
                if (id == oeuv.getId()) {
                    child.getParent().removeContent(child);
                    save();
                    return;
                }
            }
            for (Element child : l) {
                String title = child.getChildText("titre");
                if (title.equals(oeuv.getTitre())) {
                    child.getParent().removeContent(child);
                    save();
                    return;
                }
            }
        }
    }

    public oeuvre get(Object A) {
        List<Element> l = root.getChildren();
        if (A == null) {
            return null;
        }
        if (l.size() > 0) {
            for (Element child : l) {
                int id = Integer.parseInt(child.getAttributeValue("id"));
                String title = child.getChildText("titre");
                if (A.equals(id)) {
                    String titre = child.getChildText("titre");
                    String categorie = child.getChildText("categorie");
                    String auteur = child.getChildText("auteur");
                    String editeur = child.getChildText("editeur");
                    int launchdate = Integer.parseInt(child.getChildText("launchdate"));
                    String statut = child.getChildText("statut");
                    return new oeuvre(id, titre, categorie, auteur, editeur, launchdate, statut);
                } else if (A.equals(title)) {
                    int iden = Integer.parseInt(child.getAttributeValue("id"));
                    String categorie = child.getChildText("categorie");
                    String auteur = child.getChildText("auteur");
                    String editeur = child.getChildText("editeur");
                    int launchdate = Integer.parseInt(child.getChildText("launchdate"));
                    String statut = child.getChildText("statut");
                    return new oeuvre(iden, title, categorie, auteur, editeur, launchdate, statut);
                }
            }
        }
        return null;
    }

    public ArrayList<ArrayList<String>> print(String x) {
        ArrayList<ArrayList<String>> S = new ArrayList<ArrayList<String>>();
        List<Element> l = root.getChildren();
        if (l.size() > 0) {
            for (Element child : l) {
                String categorie = child.getChildText("categorie");
                if ((categorie.equals(x)) || (x == "*")) {
                    ArrayList<String> row = new ArrayList<String>();
                    int id = Integer.parseInt(child.getAttributeValue("id"));
                    String title = child.getChildText("titre");
                    String auteur = child.getChildText("auteur");
                    String editeur = child.getChildText("editeur");
                    int launchdate = Integer.parseInt(child.getChildText("launchdate"));
                    String statut = child.getChildText("statut");
                    row.add(String.valueOf(id));
                    row.add(title);
                    row.add(categorie);
                    row.add(auteur);
                    row.add(editeur);
                    row.add(String.valueOf(launchdate));
                    row.add(statut);
                    S.add(row);
                    System.out.println(new oeuvre(id, title, categorie, auteur, editeur, launchdate, statut));    
                }
            }
        }
        return S;
    }

    public boolean setStatut(oeuvre oeuv,String x) {
        List<Element> l = root.getChildren();
        if (l.size() > 0) {
            for (Element child : l) {
                int idO = Integer.parseInt(child.getAttributeValue("id"));
                if (idO == oeuv.getId()) {
                    Element e = new Element("statut");
                    e.addContent(x);
                    child.removeChild("statut");
                    child.addContent(e);
                    save();
                    return true;
                }
            }
        }
        return false;
    }

    public String getStatut(oeuvre oeuv) {
        List<Element> l = root.getChildren();
        if (l.size() > 0) {
            for (Element child : l) {
                int idO = Integer.parseInt(child.getAttributeValue("id"));
                if (idO == oeuv.getId()) {
                    return (child.getChildText("statut"));
                }
            }
        }
        return "NOT_FOUND";
    }
}