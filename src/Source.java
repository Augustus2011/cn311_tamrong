import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Source{
    public void changeColor(JPanel panel1){
        panel1.setBackground(new Color(240,240,240));
    }
    public void normalColor(JPanel panel1){
        panel1.setBackground(new Color(204,204,204));
    }
    public void serverChange(JLabel label1){
        label1.setForeground(Color.BLACK);
    }
    public void serverNormal(JLabel label1){
        label1.setForeground(Color.BLACK);
    }
    public void NormalWhite(JPanel panel1){
        panel1.setBackground(new Color(255,255,255));
    }
}

