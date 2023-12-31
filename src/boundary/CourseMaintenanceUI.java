
package boundary;

import adt.ArrList;
import adt.ListInterface;
import entity.Course;
import entity.Course.Sem;
import entity.CourseCodeComparator;
import entity.CreditHoursComparator;
import entity.SemesterComparator;
import entity.TitleComparator;
import java.util.Iterator;
import utility.Command;
import utility.ConsoleColor;
import utility.MessageUI;
import utility.InputValue;
import utility.Search;

/**
 *
 * @author Chew Lip Sin
 */
public class CourseMaintenanceUI {

    InputValue iv = new InputValue();
    private final TitleComparator titleC = new TitleComparator();
    private final CourseCodeComparator cCodeC = new CourseCodeComparator();
    private final CreditHoursComparator cHoursC = new CreditHoursComparator();
    private final SemesterComparator semC = new SemesterComparator();

    public CourseMaintenanceUI() {
    }

    public int getMenuChoices() {
        int choice = 0;
        System.out.println("====================================");
        System.out.println("||Course Management Subsystem Menu||");
        System.out.println("====================================");
        System.out.println("1. Add new course");
        System.out.println("2. Remove course");
        System.out.println("3. Search course");
        System.out.println("4. Amend course details");
        System.out.println("5. List all course");
        System.out.println("6. Course and Program subsystem menu");
        System.out.println("7. Generate Report");
        System.out.println("0. Exit");
        do {
            System.out.print("Enter choice: ");
            choice = iv.readInteger();
            if (choice > 7 || choice < 0) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice > 7 || choice < 0);

        return choice;
    }

    public Course inputCourseDetails(ListInterface<Course> courseList) {
        System.out.println("    ||==============||");
        System.out.println("    ||Add New Course||");
        System.out.println("    ||==============||");
        System.out.println("");
        String courseCode = inputCourseCode(courseList);
        if ("0".equals(courseCode)) {
            return new Course(null, null, 0, null);
        }
        String title = inputTitle();
        if ("0".equals(title)) {
            return new Course(null, null, 0, null);
        }
        int creditHour = inputCreditHour();
        if (creditHour == 0) {
            return new Course(null, null, 0, null);
        }
        Sem semester = inputSemester();
        if (semester == null) {
            return new Course(null, null, 0, null);
        }
        Course newCourse = new Course(courseCode, title, creditHour, semester);
        System.out.println();
        courseCode = "0";
        title = "0";
        creditHour = 0;
        semester = null;
        return newCourse;
    }

    public String inputCourseCode(ListInterface<Course> courseList) {
        Iterator<Course> it = courseList.getIterator();
        int i = 1;
        String courseCode = "";
        boolean match;
        do {
            match = false;
            System.out.print("Enter Course Code(Enter '0' to exit): ");
            courseCode = iv.readCourseCode();
            if ("0".equals(courseCode)) {
                return "0";
            }
            courseCode = courseCode.toUpperCase();
            while (it.hasNext()) {
                Course course = it.next();
                String oldCourseCode = course.getCourseCode();
                oldCourseCode = oldCourseCode.toUpperCase();
                match = oldCourseCode.equals(courseCode);
                i++;
                if (match) {
                    MessageUI.printFormattedText("This " + courseCode + " course code already exist\n", ConsoleColor.YELLOW);
                    break;
                }
            }
        } while (match);
        courseCode = courseCode.toUpperCase();
        return courseCode;
    }

    public String inputTitle() {
        System.out.print("Enter the title(Enter '0' to exit): ");
        String title = iv.readString();
        if ("0".equals("0")) {
            return title;
        }
        title = title.substring(0, 51);
        return title;

    }

    public int inputCreditHour() {
        int creditHour = 0;
        do {
            System.out.print("Enter credit hour(Enter '0' to exit): ");
            creditHour = iv.readInteger();
            MessageUI.displayInvalidCreditHourMessage(creditHour);
        } while (creditHour < 0 || creditHour > 20);
        return creditHour;
    }

    public Sem inputSemester() {
        Sem semester = null;
        int choice;

        do {
            System.out.println("SEMESTER");
            System.out.println("1. JAN");
            System.out.println("2. JUL");
            System.out.println("3. ALL");
            System.out.println("0. Exit");
            System.out.print("Select course semester: ");
            choice = iv.readInteger();
            switch (choice) {
                case 1:
                    semester = Sem.JAN;
                    break;
                case 2:
                    semester = Sem.JUL;
                    break;
                case 3:
                    semester = Sem.ALL;
                    break;
                case 0:
                    semester = null;
                default:
                    break;
            }
            if (choice < 0 || choice > 3) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice < 0 || choice > 3);
        return semester;
    }

    public void listAllCourses(ListInterface<Course> courseList) {
        Command.cls();
        System.out.println("\nList of Courses:");
        System.out.println("=======================================================================================================================");
        System.out.println("No |Course Code |Course Title                                        |Credit Hours  |Semester  |Created At  |Updated At");
        System.out.println("=======================================================================================================================");
        System.out.print(getAllCourses(courseList));
        System.out.println("=======================================================================================================================");

    }

    public String getAllCourses(ListInterface<Course> courseList) {
//        int currentIndex = 0;
//        for (int i = 1; i <= courseList.size(); i++) {
//            outputStr += courseList.getEntry(i) + "\n";
//        }
        String outputStr = "";
        Iterator it = courseList.getIterator();
        int i = 1;

        while (it.hasNext()) {
//            System.out.println(it.next());
            outputStr += String.format("%-3d", i) + it.next() + "\n";
            i++;
        }
        return outputStr;
    }

    public void displayCourse(Course course, String val) {
        String word = " " + val + " Course Details";
        MessageUI.printFormattedText(word + "\n", ConsoleColor.CYAN);
        for (int i = 0; i < word.length() + 2; i++) {
            MessageUI.printFormattedText("-", ConsoleColor.CYAN);
        }
        System.out.println("");
        MessageUI.printFormattedText("Course Code : " + course.getCourseCode() + "\n", ConsoleColor.CYAN);
        MessageUI.printFormattedText("Course Title: " + course.getTitle() + "\n", ConsoleColor.CYAN);
        MessageUI.printFormattedText("Credit Hours: " + course.getCreditHours() + "\n", ConsoleColor.CYAN);
        MessageUI.printFormattedText("Semester    : " + course.semToString(course.getSemester()) + "\n", ConsoleColor.CYAN);

        Command.pressEnterToContinue();
    }

    //Ask confirmation message is 1 and 0 is ask again message.
    public boolean getConfirmationChoice(String val, int type) {
        int choice = 0;
        do {
            if (type == 1) {
                MessageUI.askConfirmationMessage(val);
            } else {
                MessageUI.displayAskAgainMessage(val);
            }
            choice = iv.readInteger();
            if (choice < 0 || choice > 1) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice < 0 || choice > 1);
        if (choice == 0) {
            return false;
        } else {
            return true;
        }
    }

    public int inputRemoveCode(ListInterface<Course> courseList) {
        Iterator<Course> it = courseList.getIterator();
        int i = 1;
        boolean match = false;
        System.out.print("Enter the course code you want to delete(Enter '0' to exit): ");
        String courseCode = iv.readCourseCode();
        if ("0".equals(courseCode)) {
            return -1;
        }
        courseCode = courseCode.toUpperCase();
        while (it.hasNext()) {
            Course course = it.next();
            String oldCourseCode = course.getCourseCode();
            oldCourseCode = oldCourseCode.toUpperCase();
            match = oldCourseCode.equals(courseCode);

            if (match) {
                return i;
            }
            i++;
        }
        MessageUI.printFormattedText("This " + courseCode + " is not in the list.\n", ConsoleColor.YELLOW);
        Command.pressEnterToContinue();
        return -1;
    }

    public int getSearchMenuChoices() {
        int choice = 0;

        System.out.println("=================");
        System.out.println("||Search Course||");
        System.out.println("=================");
        System.out.println("1. Search Course Code");
        System.out.println("2. Search Course Title");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        do {
            choice = iv.readInteger();
            if (choice > 2 || choice < 0) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice > 2 || choice < 0);
        return choice;
    }

    public int getSearchCourseCode(ListInterface<Course> courseList) {
        Search search = new Search();
        String key;
        System.out.print("Enter the course code you want to search: ");
        key = iv.readString();
        key = key.toUpperCase();
        int found = search.binarySearch(courseList, key);
        return found;
    }

    public void displayCourseDetailsFounded(ListInterface<Course> courseList, int found) {
        MessageUI.displayFoundMessage(courseList.getEntry(found + 1).getCourseCode());
        Course courseSearch = courseList.getEntry(found + 1);
        displayCourse(courseSearch, "Search");
    }

    public int getSearchCourseTitle(ListInterface<Course> courseList, ListInterface<Course> courseList2) {
        Iterator<Course> it = courseList.getIterator();
        String key;
        boolean find, find2 = false;
        int i = 1;

        System.out.print("Enter the course title you want to search(Enter '0' to exit): ");
        key = iv.readString();
        if ("0".equals(key)) {
            return 0;
        }

        key = key.toLowerCase();
        while (it.hasNext()) {
            it.next();
            Course course = it.next();
            find = course.getTitle().toLowerCase().equals(key);

            if (find) {
                courseList2.add(course);
                find2 = true;
            }

            i++;
        }
        if (find2) {
            return 1;
        }
        return -1;
    }

    public void displayCourseFounded(ListInterface<Course> courseList2) {
        boolean loop = true;
        int choice;
        ArrList.insertionSort(courseList2, cCodeC,"asc");
        ArrList.insertionSort(courseList2, titleC,"asc");
        do {
            displayCourseFoundedList(courseList2);
            System.out.print("Enter the choice you want to search for(Enter '0' to exit): ");
            choice = iv.readInteger();
            if (choice == 0) {
                loop = false;
            } else if (choice > 0 && choice <= courseList2.size()) {
                displayCourse(courseList2.getEntry(choice), "Search");

            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (loop);
    }

    public void displayCourseFoundedList(ListInterface<Course> courseList2) {
        int i;
        System.out.println("Course");
        System.out.println("============================================================================================");
        System.out.println("No|Course Code |Course Title                                        |Credit Hours  |Semester");
        System.out.println("============================================================================================");
        for (i = 1; i <= courseList2.size(); i++) {
            System.out.printf("%-2d|%-12s|%-52s|  %-2d          |  %-3s\n",
                    i,
                    courseList2.getEntry(i).getCourseCode(),
                    courseList2.getEntry(i).getTitle(),
                    courseList2.getEntry(i).getCreditHours(),
                    courseList2.getEntry(i).getSemester().getString(courseList2.getEntry(i).getSemester()));
        }
        System.out.println("============================================================================================");
        MessageUI.printFormattedText(i - 1 + " result(s) founded!\n", ConsoleColor.GREEN);
    }

    public int getAmmendMenuChoices() {
        int choice = 0;
        System.out.println("=================");
        System.out.println("||Ammend Course||");
        System.out.println("=================");
        System.out.println("1. Ammend Course Code");
        System.out.println("2. Ammend Course Title");
        System.out.println("3. Ammend Credit Hours");
        System.out.println("4. Ammend Semester");
        System.out.println("0. Exit/Continue");
        System.out.print("Enter your choice: ");
        do {
            choice = iv.readInteger();
            if (choice > 4 || choice < 0) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice > 4 || choice < 0);
        return choice;
    }

    public int getCourseAmmend(ListInterface<Course> courseList, ListInterface<Course> courseList2) {
        Search search = new Search();
        boolean loop = true;
        int choice;
        ArrList.insertionSort(courseList2, cCodeC,"asc");
        ArrList.insertionSort(courseList2, titleC,"asc");
        do {
            displayCourseFoundedList(courseList2);
            System.out.print("Enter the choice you want to ammend for(Enter '0' to exit): ");
            choice = iv.readInteger();
            if (choice == 0) {
                loop = false;
            } else if (choice > 0 && choice <= courseList2.size()) {
                displayCourse(courseList2.getEntry(choice), "Ammend");
                int index = search.binarySearch(courseList, courseList2.getEntry(choice).getCourseCode());
                return index;
            } else {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (loop);
        return -1;
    }

    public int getSortMenu(ListInterface<Course> courseList) {
        int choice = 0;
        System.out.println("Sort");
        System.out.println("----");
        System.out.println("1. Ascending Order");
        System.out.println("2. Descending Order");
        System.out.println("0. Exit");
        do {
            System.out.print("Enter choice: ");
            choice = iv.readInteger();
            if (choice > 2 || choice < 0) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice > 2 || choice < 0);

        return choice;
    }

    public int getSortMenuChoice(ListInterface<Course> courseList, String val) {
        int choice = 0;
        System.out.println(val);
        System.out.println("----");
        System.out.println("1. Course Code");
        System.out.println("2. Course Title");
        System.out.println("3. Credit Hours");
        System.out.println("4. Semester");
        System.out.println("5. Created At");
        System.out.println("6. Updated At");
        System.out.println("0. Exit");
        do {
            System.out.print("Enter choice: ");
            choice = iv.readInteger();
            if (choice > 6 || choice < 0) {
                MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice > 6 || choice < 0);

        return choice;
    }

}
