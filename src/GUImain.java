public class GUImain {
    GUImain(int WhichScreen){
        switch(WhichScreen) {
            case 0:
                new LoginMenu();
                break;
            case 1:
                new TeacherMenu();
                break;
            case 2:
                new StudentMenu();
                break;
        }

        }
    }

