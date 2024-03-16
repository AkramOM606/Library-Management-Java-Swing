import java.awt.Font;
import javax.swing.JButton;

public class MultilineButton extends JButton {
    public MultilineButton(String text) {
        super("<html><div style='text-align:center'>" + text.replace("\n", "<br>") + "</div></html>");
        setFont(new Font("Arial", Font.BOLD, 14));
        setHorizontalAlignment(JButton.CENTER);
        setVerticalAlignment(JButton.CENTER);
    }
}