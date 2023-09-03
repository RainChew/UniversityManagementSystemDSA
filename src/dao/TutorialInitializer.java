package dao;

import adt.*;
import entity.Tutorial;
import java.io.IOException;
/**
 * @author Lim Yi Leong
 */
public class TutorialInitializer {

//  Method to return a collection of with hard-coded entity values
  public ListInterface<Tutorial> TutorialInitializer() {
    ListInterface<Tutorial> tList = new ArrList<>();
    tList.add(new Tutorial("RSDG1"));
    tList.add(new Tutorial("RBAG1"));
    tList.add(new Tutorial("RACG1"));
    tList.add(new Tutorial("RBFG1"));
    tList.add(new Tutorial("RMEG1"));
    tList.add(new Tutorial("RSDG2"));
    tList.add(new Tutorial("RBAG2"));
    tList.add(new Tutorial("RACG2"));
    tList.add(new Tutorial("RBFG2"));
    tList.add(new Tutorial("RMEG2"));

    return tList;
  }

    public static void inputTutorial() {
        TutorialInitializer t = new TutorialInitializer();
        ListInterface<Tutorial> tList = t.TutorialInitializer();

        // Save the program data to the file using ProgramDAO
        tDAO DAO = new tDAO();
        try {
            DAO.saveToFile(tList,"tutorial.dat");
        } catch (IOException ex) {
            System.out.println("Error saving data to file: " + ex.getMessage());
        }

        System.out.println("\nProgram: \n" + tList);
    }

}
