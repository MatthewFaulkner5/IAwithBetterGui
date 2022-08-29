import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;
public class StudentMenu extends JFrame implements ActionListener,DocumentListener {
    AccountHandler MainHandler = new AccountHandler();
    int EnterClick = 0;
    private JFrame TeacherFrame;
    private JButton QuestionsButton;
    private JButton SearchButton;
    private JButton Exit;
    private JButton QuestionaireMaker;
    private JButton EnterButton;
    private JTextField UserInputBox;
    private JPasswordField passwordInput;
    private String WhichMenu;
    private String InputFromBox;
    private JLabel Title;
    private String Question;
    private String Date;
    private String Subject;
    private String Topic;
    private FileHandler QuestionHandler;
    private String filename;

    public StudentMenu(String Username) {

        System.out.println("Opened teacher");
        TeacherFrame = new JFrame("Teacher Menu");
        TeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TeacherFrame.setBounds(0, 0, 750, 500);
        TeacherFrame.setLayout(null);
        TeacherFrame.setBackground(Color.RED);


        QuestionsButton = new JButton("Add Question");
        QuestionsButton.setBounds(5, 100, 150, 40);
        QuestionsButton.addActionListener(this);

        Exit = new JButton("Exit");
        Exit.setBounds(5, 250, 150, 40);
        Exit.setHorizontalAlignment(JButton.CENTER);
        Exit.addActionListener(this);

        SearchButton = new JButton("Search for Question");
        SearchButton.setBounds(5, 200, 150, 40);
        SearchButton.addActionListener(this);

        EnterButton = new JButton("Enter");
        EnterButton.setBounds(605, 200, 100, 40);
        EnterButton.setVisible(false);
        EnterButton.addActionListener(this);

        QuestionaireMaker = new JButton("Make Questionaire");
        QuestionaireMaker.setBounds(5, 150, 150, 40);
        QuestionaireMaker.addActionListener(this);


        Title = new JLabel("Hello, " + Username);
        Title.setFont(new Font("Arial", Font. BOLD, 24));
        Title.setBounds(180, 0, 530, 40);
        Title.setHorizontalAlignment(JLabel.CENTER);

        UserInputBox = new JTextField("");
        UserInputBox.setBounds(385,200,200, 40);
        UserInputBox.getDocument().addDocumentListener(this);
        UserInputBox.addActionListener(this);

        UserInputBox.setVisible(false);
        UserInputBox.addActionListener(this);

        filename = "C:\\Users\\Matthew\\Documents\\Questions.txt";
        QuestionHandler = new FileHandler(filename,50);
        TeacherFrame.add(UserInputBox);
        TeacherFrame.add(Exit);
        TeacherFrame.add(QuestionsButton);
        TeacherFrame.add(SearchButton);
        TeacherFrame.add(Title);
        TeacherFrame.add(QuestionaireMaker);
        TeacherFrame.add(EnterButton);
        TeacherFrame.setVisible(true);
        GUIMainMenu();

    }
    public void GUIMainMenu(){
        Exit.setVisible(true);
        SearchButton.setVisible(true);
        QuestionsButton.setVisible(true);
        UserInputBox.setVisible(false);
        EnterButton.setVisible(false);
        WhichMenu = "Main";
    }
    public void AddQuestion(String Subject,String Topic,String Date,String Question) {
        QuestionHandler.appendRecord(Subject + "," +Topic + "," + Date + "," + Question,50);

    }
    public void AddQuestionGui(){
        Question = null;Date = null;Topic = null;Subject = null;
        UserInputBox.setVisible(true);
        Title.setText("Enter Subject");
        EnterButton.setVisible(true);
        WhichMenu = "AddQuestion";
        EnterButton.setVisible(true);
    }
    public void AllOf(){
        UserInputBox.setVisible(false);
        EnterButton.setVisible(false);
    }

    public void DeleteQuestion() {
        //Deletes question from database using keywords
    }

    public void EditQuestion() {
        //Replaces question
    }
    public void QuestionaireMaker(){

        String[] QuestionArray = new String[QuestionHandler.countLines()];
        for(int i = 0;i<QuestionHandler.countLines();i++){
            QuestionArray[i] = QuestionHandler.getRecord(i);
        }
        JComboBox QuestionList = new JComboBox(QuestionArray);
        QuestionList.setEditable(true);
        QuestionList.addActionListener(this);
        QuestionList.setBounds(200, 60, 300, 40);
        TeacherFrame.add(QuestionList);
    }

    public void SearchQuestionGui(){
        AllOf();
        Title.setText("Enter keyword eg Date/Question/Topic/Subject");
        UserInputBox.setVisible(true);
        EnterButton.setVisible(true);
        WhichMenu = "FindQuestion";
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getActionCommand().equals("Search for Question"))) {
            SearchQuestionGui();
        }
        if (e.getActionCommand().equals("Add Question")){
            AllOf();
            AddQuestionGui();

        }

        if (Objects.equals(WhichMenu, "FindQuestion")) {
            if (e.getActionCommand().equals("Enter")) {
                new GUImain(3,UserInputBox.getText());
            }
        }
        if (e.getActionCommand().equals("Make Questionaire")){
            QuestionaireMaker();
        }

        if (Objects.equals(WhichMenu,"AddQuestion") & e.getActionCommand().equals("Enter")) {
            if (Title.getText() == "Add Question Content") {
                Question = UserInputBox.getText();
                UserInputBox.setText("");

                System.out.println("QuestionDone");
                Title.setText("Added!");
                AddQuestion(Subject,Topic,Date,Question);

            }
            if (Title.getText() == "Enter Date") {
                Date = UserInputBox.getText();
                UserInputBox.setText("");
                Title.setText("Add Question Content");
                System.out.println("Done date");
            }
            if (Title.getText() == "Enter Topic"){

                Topic = UserInputBox.getText();
                UserInputBox.setText("");
                System.out.println("Done topic");
                Title.setText("Enter Date");
            }
            if (Title.getText() == "Enter Subject") {
                Subject = UserInputBox.getText();
                UserInputBox.setText("");
                System.out.println("Done subject");
                Title.setText("Enter Topic");
            }
        }
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
        if (e.getActionCommand().equals("Go back")) {
            GUIMainMenu();
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