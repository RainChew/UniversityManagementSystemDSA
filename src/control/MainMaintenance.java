/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ListInterface;
import boundary.MainMaintenanceUI;
import dao.Initializer;
import entity.Course;
import utility.MessageUI;

/**
 *
 * @author Chew Lip Sin
 */
public class MainMaintenance {

    private MainMaintenanceUI mainUI = new MainMaintenanceUI();
    private CourseMaintenance courseMain = new CourseMaintenance();
    private Initializer in = new Initializer();

    public static void main(String[] args) {
        MainMaintenance mainMain = new MainMaintenance();
        mainMain.runMainMaintenance();
    }

    public void runMainMaintenance() {
        ListInterface<Course> courseList = in.initializeCourse();
        int choice = 0;
        do {
            choice = mainUI.getMenuChoices();
            switch (choice) {

                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
//                    runProgramMaintenance(courseList);
                    break;
                case 2:
//                    runTutorMaintenance(courseList);
                    break;
                case 3:
//                   runTutorialGroupMaintenance(courseList, "search");
                    break;
                case 4:
                    courseMain.runCourseMaintenance(courseList);
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != 0);
    }
}