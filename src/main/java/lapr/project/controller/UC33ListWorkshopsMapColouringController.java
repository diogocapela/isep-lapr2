package lapr.project.controller;

import lapr.project.model.Workshop;
import lapr.project.ui.UC33ListWorkshopsMapColouringUI;
import lapr.project.utils.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UC33ListWorkshopsMapColouringController extends SuperController {

    private static int[][] workshopListMatrixify(List<Workshop> workshopList, int workshopListDimension) {
        int[][] workshopListMatrix = new int[workshopListDimension][workshopListDimension];
        for (int outerCounter = 0; outerCounter < workshopListDimension; outerCounter++) {
            List<Integer> workshopInterestedAttendees1 = workshopList.get(outerCounter).getInterestedUsers();
            for (int innerCounter = outerCounter; innerCounter < workshopListDimension; innerCounter++) {
                if (innerCounter == outerCounter) {
                    workshopListMatrix[innerCounter][outerCounter] = 0;
                } else {
                    List<Integer> workshopInterestedAttendees2 = workshopList.get(innerCounter).getInterestedUsers();
                    int isConnected = !Collections.disjoint(workshopInterestedAttendees1, workshopInterestedAttendees2) ? 1 : 0;
                    workshopListMatrix[outerCounter][innerCounter] = isConnected;
                    workshopListMatrix[innerCounter][outerCounter] = isConnected;
                }
            }
        }
        return workshopListMatrix;
    }

    public List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> listMapColouring(List<Workshop> workshopList) {
        int workshopListDimension = workshopList.size();
        /*
         * Matrixify the List of Workshops
         */
        int[][] workshopListMatrix = workshopListMatrixify(workshopList, workshopListDimension);
        /*
         * Assign Degree of Connections to WorkshopDegreeColor objects
         */
        List<UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor> workshopDegreeColorList = new ArrayList<>();
        for (int matrixRowIndex = 0; matrixRowIndex < workshopListDimension; matrixRowIndex++) {
            workshopDegreeColorList.add(new UC33ListWorkshopsMapColouringUI.WorkshopDegreeColor(workshopList.get(matrixRowIndex), 0));
            for (int matrixColIndex = 0; matrixColIndex < workshopListDimension; matrixColIndex++) {
                workshopDegreeColorList.get(matrixRowIndex).setDegree(workshopDegreeColorList.get(matrixRowIndex).getDegree() + workshopListMatrix[matrixRowIndex][matrixColIndex]);
            }
        }
        /*
         * Sort the List of Workshops by Degree of Connections
         */
        Collections.sort(workshopDegreeColorList);
        /*
         * Algorithm for determining the Map Color after Sorting the List of Workshops by Degree of Connections
         */
        int maxColor = 1;
        // first workshop
        workshopDegreeColorList.get(0).setColor(1);
        // loops following workshops
        for (int outerCtr = 1; outerCtr < workshopListDimension; outerCtr++) {
            int currentColor = 1;
            List<Integer> workshopInterestedAttendees1 = workshopDegreeColorList.get(outerCtr).getWorkshop().getInterestedUsers();
            int innerCtr = 0;
            while (innerCtr <= outerCtr) {
                List<Integer> workshopInterestedAttendees2 = workshopDegreeColorList.get(innerCtr).getWorkshop().getInterestedUsers();
                if (currentColor == workshopDegreeColorList.get(innerCtr).getColor() && !Collections.disjoint(workshopInterestedAttendees1, workshopInterestedAttendees2)) {
                    // if there are common users between interested users, get new color
                    currentColor++;
                    if (currentColor > maxColor) {
                        maxColor = currentColor;
                        workshopDegreeColorList.get(outerCtr).setColor(currentColor);
                        break;
                    } else {
                        innerCtr = -1;
                    }
                }
                innerCtr++;
            }
            if (workshopDegreeColorList.get(outerCtr).getColor() == 0) {
                workshopDegreeColorList.get(outerCtr).setColor(currentColor);
            }
        }
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " listed workshops map colouring");
        return workshopDegreeColorList;
    }
}
