public class GUImain {
    GUImain(int WhichScreen,String ExtraDetail){
        switch(WhichScreen) {
            case 0:
                new LoginMenu();
                break;
            case 1:
                System.out.println("Swapping to teacher menu");
                TeacherMenu TeachMenu = new TeacherMenu(ExtraDetail);
                break;
            case 2:
                new StudentMenu();
                break;
        }

        }
    }

