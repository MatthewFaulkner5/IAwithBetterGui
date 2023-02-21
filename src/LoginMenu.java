import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class LoginMenu extends JFrame implements ActionListener,DocumentListener{
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
    public String InputFromBox;
    private String InputPassword;
    JLabel Title = new JLabel("Question Master");


    public LoginMenu(){
        frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 365, 250);
        frame.setLayout(null);

        LoginButton = new JButton("Login");
        Exit = new JButton("Exit");
        CreateAccountButton = new JButton("Sign up");
        EnterButton = new JButton("Enter");
        LoginButton.setBounds(5, 100, 100, 40);
        LoginButton.addActionListener(this);
        Exit.setBounds(250, 100, 100, 40);
        Exit.setHorizontalAlignment(JButton.CENTER);
        Exit.addActionListener(this);
        CreateAccountButton.setBounds(125, 100, 100, 40);
        CreateAccountButton.addActionListener(this);
        Title.setBounds(0, 0, 365, 40);
        Title.setHorizontalAlignment(JLabel.CENTER);
        frame.add(Exit);
        frame.add(LoginButton);
        frame.add(CreateAccountButton);
        frame.add(Title);
        frame.add(EnterButton);
        EnterButton.setBounds(230, 70, 100, 40);
        EnterButton.setVisible(false);
        usernameInput = new JTextField("");
        usernameInput.setBounds(20, 70, 200, 40);
        usernameInput.getDocument().addDocumentListener(this);
        passwordInput = new JPasswordField();
        passwordInput.setBounds(20, 70, 200, 40);
        passwordInput.getDocument().addDocumentListener(this);
        frame.add(usernameInput);
        frame.add(passwordInput);
        usernameInput.setVisible(false);
        passwordInput.setVisible(false);
        EnterButton.addActionListener(this);
        frame.setVisible(true);
        GUIMainMenu();
    }
    public void GUIMainMenu(){
        Exit.setVisible(true);
        LoginButton.setVisible(true);
        CreateAccountButton.setVisible(true);
        usernameInput.setText("");
        passwordInput.setText("");
        Exit.setText("Exit");
        Exit.setBounds(250, 100, 100, 40);
        Title.setText("Question Master");
        Title.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        EnterButton.setVisible(false);
        usernameInput.setVisible(false);
        passwordInput.setVisible(false);
        WhichMenu = "Main";
        InputFromBox = null;
    }
    public void GUILogin(){
        LoginButton.setVisible(false);
        CreateAccountButton.setVisible(false);
        Exit.setText("Go back");
        Exit.setBounds(115, 150, 100, 40);
        Title.setText("Enter Username");
        usernameInput.setVisible(true);
        EnterButton.setVisible(true);
        WhichMenu = "Login";
    }
    public void GUINewAccount(){
        Title.setText("Enter Username");
        LoginButton.setVisible(false);
        CreateAccountButton.setVisible(false);
        passwordInput.setText("");
        usernameInput.setText("");
        Exit.setText("Go back");
        Exit.setBounds(230, 120, 100, 40);
        usernameInput.setVisible(true);
        passwordInput.setBounds(usernameInput.getBounds());
        passwordInput.setVisible(false);
        WhichMenu = "NewAccount";
        EnterButton.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            GUILogin();
        }
        if (e.getActionCommand().equals("Sign up")) {
            GUINewAccount();
        }
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
        if (e.getActionCommand().equals("Go back")) {
            GUIMainMenu();
        }
        //All the account creation
        if (Objects.equals(WhichMenu, "NewAccount")) {
            if (e.getActionCommand().equals("Enter") && (InputFromBox != null)) {
            System.out.println("Going into password");
            passwordInput.setVisible(false);
            InputPassword = String.valueOf(passwordInput.getPassword());
            usernameInput.setText("");
            Title.setText("Student or Teacher");
            EnterButton.setText("Create Account");
            EnterButton.setBounds(20, 70,100, 40);
            EnterButton.setText("Student");
            Exit.setBounds(130, 70, 100, 40);
            Exit.setText("Teacher");
        }
            if (e.getActionCommand().equals("Enter") && (InputFromBox == null)) {
                System.out.println("Going into username");
                passwordInput.setVisible(true);
                InputFromBox = usernameInput.getText();
                usernameInput.setVisible(false);
                Title.setText("Enter Password");

            }
            if (e.getActionCommand().equals("Teacher")||e.getActionCommand().equals("Student")){
                String StudORTeach;
                StudORTeach = usernameInput.getText();
                if (MainHandler.NewAccount(InputFromBox, InputPassword, e.getActionCommand())) {
                    if (Objects.equals(e.getActionCommand(), "Student")) {
                        frame.setVisible(false);
                        //switches to new frame and hides the last one

                        new GUImain(2, InputFromBox);
                    } else {
                        frame.setVisible(false);
                        new GUImain(1, InputFromBox);
                    }
                }
                GUIMainMenu();
            }
        }
        if (e.getActionCommand().equals("Enter") & Objects.equals(WhichMenu, "Login") & InputFromBox != null)  {
            String InputPassword;
            InputPassword = String.valueOf(passwordInput.getPassword());
            System.out.println("Both done");
            if (MainHandler.Login(InputFromBox, InputPassword) == 'T') {
                frame.setVisible(false);
                new GUImain(1,InputFromBox);
            } else if (MainHandler.Login(InputFromBox, InputPassword) == 'S') {
                frame.setVisible(false);
                new GUImain(2,InputFromBox);
            } else {
                new GUImain(4,"Username or Password is incorrect!");
                usernameInput.setText("");
                passwordInput.setText("");
               GUIMainMenu();
            }
        }
        if (e.getActionCommand().equals("Enter") & Objects.equals(WhichMenu, "Login")) {
            InputFromBox = usernameInput.getText();
            EnterClick++;
            Title.setText("Enter Password");
            passwordInput.setVisible(true);
            usernameInput.setVisible(false);
        }
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
