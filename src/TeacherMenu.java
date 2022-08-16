import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMenu extends JFrame implements ActionListener,DocumentListener {
    AccountHandler MainHandler = new AccountHandler();
    int EnterClick = 0;
    private JFrame frame;
    private JButton LoginButton;
    private JButton CreateAccountButton;
    private JButton Exit;
    private JButton EnterButton;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private String WhichMenu;
    private String InputFromBox;

    JLabel Title = new JLabel("Teacher Master");


    public void TeacherMenu() {
        frame = new JFrame("Teacher Master");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 365, 250);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}