package lapr.project.controller;

import lapr.project.model.Stand;
import lapr.project.model.StandDistance;
import lapr.project.ui.UC34CalculateMinimumAmountElectricalCableUI;
import lapr.project.utils.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UC34CalculateMinimumAmountElectricalCableController extends SuperController {

    public String calculateMinimumSpanningTree(List<Stand> standList) {
        List<UC34CalculateMinimumAmountElectricalCableUI.StandCable> standCablePairList = new ArrayList<>();
        List<List<String>> standDescriptionList = new ArrayList<>();

        for (Stand tmpStand : standList) {
            List<String> descriptionSet = new ArrayList<>();
            descriptionSet.add(tmpStand.getDescription());
            standDescriptionList.add(descriptionSet);
            for (StandDistance tmpStandDistance : tmpStand.getRelativeDistanceSet()) {
                standCablePairList.add(new UC34CalculateMinimumAmountElectricalCableUI.StandCable(tmpStand.getDescription(), tmpStandDistance.getDistanceDescription(), tmpStandDistance.getDistanceValue()));
            }
        }

        Collections.sort(standCablePairList);

        float resultingCable = 0;
        StringBuilder sb = new StringBuilder();

        for (UC34CalculateMinimumAmountElectricalCableUI.StandCable standCablePair : standCablePairList) {
            int standSet1 = -1;
            int standSet2 = -1;
            for (int set = 0; set < standDescriptionList.size(); set++) {
                if (standDescriptionList.get(set).contains(standCablePair.getStand1())) {
                    standSet1 = set;
                }
                if (standDescriptionList.get(set).contains(standCablePair.getStand2())) {
                    standSet2 = set;
                }
                if (standSet1 >= 0 && standSet2 >= 0) {
                    break;
                }
            }

            if (standSet1 != standSet2) {
                standDescriptionList.get(standSet1).addAll(standDescriptionList.get(standSet2));
                standDescriptionList.remove(standSet2);
                resultingCable = resultingCable + standCablePair.getDistance();
                sb.append(String.format("%s -> %s (%.2f)\n", standCablePair.getStand1(), standCablePair.getStand2(), standCablePair.getDistance()));
            }

            if (standDescriptionList.size() == 1) {
                break;
            }
        }

        sb.append(String.format("This Event requires at least %.2f meters of electrical cabling", resultingCable));
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " calculated minimum spanning tree");

        return sb.toString();
    }
}
