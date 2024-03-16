import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class PretsXML {
    private String filePath;
    private Document doc;
    private Element root;
    
    public PretsXML(String filePath) {
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

    public void add(pret pr) {
        Element e = new Element("pret");
        
        Element e1 = new Element("pretdate");
        e1.addContent(pr.getPretdate());
        e.addContent(e1);

        Element e2 = new Element("idadherent");
        e2.addContent("" + pr.getIdadherant());
        e.addContent(e2);
        
        Element e3 = new Element("idoeuvre");
        e3.addContent("" + pr.getIdoeuvre());
        e.addContent(e3);
        
        root.addContent(e);
        save();
    }

    public pret get(int idoeuvre) {
        List<Element> l = root.getChildren();
        if (l.size() > 0) {
            for (Element child : l) {
                int ido = Integer.parseInt(child.getChildText("idoeuvre"));
                if (idoeuvre == ido) {
                    String pretdate = child.getChildText("pretdate");
                    int idadherant = Integer.parseInt(child.getChildText("idadherent"));
                    return new pret(pretdate, idadherant, idoeuvre);
                }
            }
        }
        return null;
    }

    public boolean isLimited(adherent ad) {
        List<Element> l = root.getChildren();
        if (ad == null) {
            return true;
        }
        if (l.size() > 0) {
            int c=0;
            for (Element child : l) {
                int idA = Integer.parseInt(child.getChildText("idadherent"));            
                if (idA == ad.getNumero()) {
                    c++;
                }
            }
            if (c>3) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<ArrayList<String>> print() {
        ArrayList<ArrayList<String>> S = new ArrayList<ArrayList<String>>();
        List<Element> l = root.getChildren();
        if (l.size() > 0) {
            for (Element child : l) {
                ArrayList<String> row = new ArrayList<String>();
                String pretdate = child.getChildText("pretdate");
                String idadherent = child.getChildText("idadherent");
                String idoeuvre = child.getChildText("idoeuvre");
                row.add(pretdate);
                row.add(idadherent);
                row.add(idoeuvre);
                S.add(row);
                System.out.println(new pret(pretdate, Integer.parseInt(idadherent), Integer.parseInt(idoeuvre)));    
            }
        }
        return S;
    }
}