
import javax.swing.*;

public class BaseFrame extends JFrame {

    protected String username;

    public BaseFrame(String title, String username) {
        super(title);
        this.username = username;

        // Ortak ayarlar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    public void showFrameInfo() {
        System.out.println("Bu bir temel frame");
    }

    public void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Bilgi", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Hata", JOptionPane.ERROR_MESSAGE);
    }

}
