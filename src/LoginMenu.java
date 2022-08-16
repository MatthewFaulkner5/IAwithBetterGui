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
    private String InputFromBox;
    private String InputPassword;
    JLabel Title = new JLabel("Quiz Master");


    public LoginMenu(){
        frame = new JFrame("Quizz Master");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 365, 250);
        frame.setLayout(null);
        frame.setVisible(true);
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
        Title.setBounds(80, 0, 200, 40);
        Title.setHorizontalAlignment(JLabel.CENTER);
        frame.add(Exit);
        frame.add(LoginButton);
        frame.add(CreateAccountButton);
        frame.add(Title);
        frame.add(EnterButton);
        EnterButton.setBounds(230, 60, 100, 40);
        EnterButton.setVisible(false);

        usernameInput = new JTextField("");
        usernameInput.setBounds(20, 60, 200, 40);
        usernameInput.getDocument().addDocumentListener(this);
        passwordInput = new JPasswordField();
        passwordInput.setBounds(20, 110, 200, 40);
        passwordInput.getDocument().addDocumentListener(this);
        frame.add(usernameInput);
        frame.add(passwordInput);
        usernameInput.setVisible(false);
        passwordInput.setVisible(false);
        EnterButton.addActionListener(this);
        GUIMainMenu();
    }
    public void GUIMainMenu(){
        Exit.setVisible(true);
        LoginButton.setVisible(true);
        CreateAccountButton.setVisible(true);
        usernameInput.setVisible(false);
        passwordInput.setVisible(false);
        WhichMenu = "Main";
    }
    public void GUILogin(){
        LoginButton.setVisible(false);
        CreateAccountButton.setVisible(false);
        Exit.setText("Go back");
        Exit.setBounds(250, 150, 100, 40);
        Title.setText("Enter Username");
        usernameInput.setVisible(true);
        EnterButton.setVisible(true);
        WhichMenu = "Login";
    }
    public void GUINewAccount(){
        LoginButton.setVisible(false);
        CreateAccountButton.setVisible(false);
        Exit.setText("Go back");
        Exit.setBounds(250, 150, 100, 40);
        Title.setText("Enter Username");
        usernameInput.setVisible(true);
        passwordInput.setBounds(20, 110, 200, 40);
        passwordInput.setVisible(true);
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
            LoginButton.setVisible(true);
            CreateAccountButton.setVisible(true);
            Exit.setText("Exit");
            Exit.setBounds(250, 100, 100, 40);
            WhichMenu = "Main";
            Title.setText("Quiz Master");
        }

        if (Objects.equals(WhichMenu, "NewAccount")) {
            if (e.getActionCommand().equals("Enter")) {

                InputFromBox = usernameInput.getText();
                InputPassword = String.valueOf(passwordInput.getPassword());
                passwordInput.setVisible(false);
                usernameInput.setText("");
                Title.setText("Student or Teacher");
                EnterButton.setText("Create Account");

            }
            if (e.getActionCommand().equals("Create Account")) {
                String StudORTeach;
                StudORTeach = usernameInput.getText();
                if (Objects.equals(StudORTeach, "Student") || Objects.equals(StudORTeach, "Teacher")) {
                    if (MainHandler.NewAccount(InputFromBox, InputPassword, StudORTeach)) {
                        if (Objects.equals(StudORTeach, "Student")) {
                            frame.setVisible(false);
                            //GUIStudent();
                        } else {
                            frame.setVisible(false);
                            //GUITeacher();
                        }
                    }
                    GUIMainMenu();
                } else {
                    System.out.println("Wrong spelling try again");
                    Title.setText("Wrong spelling, try again");
                }
            }
        }
        if (e.getActionCommand().equals("Enter") & Objects.equals(WhichMenu, "Login") & InputFromBox != null)  {
            String InputPassword;
            InputPassword = String.valueOf(passwordInput.getPassword());
            System.out.println("Both done");
            if (MainHandler.Login(InputFromBox, InputPassword) == 'T') {
                frame.setVisible(false);
                //GUITeacher();
            } else if (MainHandler.Login(InputFromBox, InputPassword) == 'S') {
                frame.setVisible(false);
                //GUIStudent();
            } else {
                LoginButton.setVisible(true);
                CreateAccountButton.setVisible(true);
                usernameInput.setVisible(false);passwordInput.setVisible(false);
                Exit.setText("Exit");
                Exit.setBounds(250, 100, 100, 40);
                WhichMenu = "Main";
                Title.setText("Quiz Master");
            }
        }
        if (e.getActionCommand().equals("Enter") & Objects.equals(WhichMenu, "Login")) {
            InputFromBox = usernameInput.getText();
            EnterClick++;
            Title.setText("Enter Password");
            passwordInput.setBounds(20, 60, 200, 40);
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
