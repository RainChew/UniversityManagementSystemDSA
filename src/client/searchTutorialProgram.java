package client;

import adt.ArrList;
import adt.ListInterface;
import boundary.TutorialProgramUI;
import control.TutorialProgramMaintenance;
import dao.TutorialPrDAO;
import entity.TutorialProgram;
import java.util.Iterator;
import java.util.Scanner;
import utility.ConsoleColor;
import static utility.MessageUI.printFormattedText;

/**
 * @author Lim Yi Leong
 */
public class searchTutorialProgram {

    private ListInterface<TutorialProgram> tpList = new ArrList<>();
    private static TutorialProgramMaintenance tpM = new TutorialProgramMaintenance();
    private static TutorialProgramUI tpU = new TutorialProgramUI();
    private static TutorialPrDAO tpDAO = new TutorialPrDAO();

    public searchTutorialProgram(ListInterface<TutorialProgram> tpList) {
        Scanner scanner = new Scanner(System.in);
        boolean continueSearching = true;

        while (continueSearching) {
            System.out.print("\n\n");
            System.out.print("Enter a search term: ");
            String searchTerm = scanner.nextLine().toLowerCase();

            String outputStr = "";
            Iterator<TutorialProgram> iterator = tpList.getIterator();
            String header = String.format("%-15s | %-80s | %-20s | %-25s | %-30s | %s%n",
                    "Program Code", "Program Name", "Tutorial Group",
                    "Number of Students", "Class Rap", "Intake");// Labels
            String row = ""; 
            while (iterator.hasNext()) {
                TutorialProgram tutorialProgram = iterator.next();
                if (matchesSearchTerm(tutorialProgram, searchTerm)) {
                    row = String.format("%-15s | %-80s | %-20s | %-25d | %-30s | %d-%02d%n",
                            tutorialProgram.getCode(),
                            tutorialProgram.getProgramname(),
                            tutorialProgram.getGroupname(),
                            tutorialProgram.getNumStudent(),
                            tutorialProgram.getClassRap(),
                            tutorialProgram.getIntakeYear(),
                            tutorialProgram.getIntakeMonth());
                    outputStr += row;
                }
            }
            if (!outputStr.isEmpty()) {
                System.out.println("\nSearch Results :\n");
                System.out.print(header);
                System.out.println(outputStr);
            } else {
                printFormattedText("\nNo matching tutorial programs found.", ConsoleColor.BRIGHTRED);
            }
            System.out.print("\n\n");
            printFormattedText("\nDo you want to continue searching? (y/n): ", ConsoleColor.BRIGHTBLUE);
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("y")) {
                continueSearching = false;
            }
        }
    }

    private boolean matchesSearchTerm(TutorialProgram tutorialProgram, String searchTerm) {
        return tutorialProgram.getCode().toLowerCase().contains(searchTerm)
                || tutorialProgram.getProgramname().toLowerCase().contains(searchTerm)
                || tutorialProgram.getGroupname().toLowerCase().contains(searchTerm)
                || String.valueOf(tutorialProgram.getNumStudent()).contains(searchTerm)
                || tutorialProgram.getClassRap().toLowerCase().contains(searchTerm)
                || String.valueOf(tutorialProgram.getIntakeYear()).contains(searchTerm)
                || String.valueOf(tutorialProgram.getIntakeMonth()).contains(searchTerm);
    }
}