package lapr.project.controller;

import lapr.project.model.Stand;
import lapr.project.utils.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UC17ListEventStandsInformationController extends SuperController {
    public List<List<Double>> prepareStandDisplayInfo(List<Stand> standList) {

        /*
        calculate sturge's optimal number of columns
         */
        int sturgeOptimalNumberColumns = (int) (!standList.isEmpty() ? 1 + Math.ceil(Math.log(standList.size()) / Math.log(2)) : 0);
        BigDecimal minStandArea = new BigDecimal("0");
        BigDecimal maxStandArea = new BigDecimal("0");

        for (int standIndex = 0; standIndex < standList.size(); standIndex++) {
            minStandArea = standList.get(standIndex).getArea() < minStandArea.doubleValue() || standIndex == 0 ? BigDecimal.valueOf(standList.get(standIndex).getArea()) : minStandArea;
            maxStandArea = standList.get(standIndex).getArea() > maxStandArea.doubleValue() || standIndex == 0 ? BigDecimal.valueOf(standList.get(standIndex).getArea()) : maxStandArea;
        }

        if (minStandArea.equals(maxStandArea) && minStandArea.doubleValue() > 0) {
            sturgeOptimalNumberColumns = 1;
        }

        /*
        calculate sturge's optimal column width
         */
        double sturgeOptimalColumnWidth = sturgeOptimalNumberColumns > 1 ? (maxStandArea.doubleValue() - minStandArea.doubleValue()) / (1 + Math.log(standList.size()) / Math.log(2)) : 0;

        List<List<Double>> standDisplayInfo = new ArrayList<>();

        for (int intervalcounter = 0; intervalcounter < sturgeOptimalNumberColumns; intervalcounter++) {
            List<Double> intervalRange = new ArrayList<>();
            intervalRange.add(standList.size() == 1 ? 0 : minStandArea.doubleValue() + sturgeOptimalColumnWidth * intervalcounter);
            intervalRange.add(minStandArea.doubleValue() + sturgeOptimalColumnWidth * (intervalcounter + 1));
            intervalRange.add(Double.parseDouble("0"));
            standDisplayInfo.add(intervalRange);
        }

        /*
        calculate average and count the number of stands per interval
         */

        double standAreaAverage = 0;

        for (Stand standTmp : standList) {
            standAreaAverage = standAreaAverage + standTmp.getArea();
            for (int intervalRangeIndex = 0; intervalRangeIndex < sturgeOptimalNumberColumns; intervalRangeIndex++) {
                if (standTmp.getArea() >= standDisplayInfo.get(intervalRangeIndex).get(0) && standTmp.getArea() < standDisplayInfo.get(intervalRangeIndex).get(1) || sturgeOptimalNumberColumns == 1) {
                    standDisplayInfo.get(intervalRangeIndex).set(2, standDisplayInfo.get(intervalRangeIndex).get(2) + 1);
                    break;
                }
            }
        }

        standAreaAverage = sturgeOptimalNumberColumns > 0 ? standAreaAverage / standList.size() : 0;

        /*
        calculate standard deviation
         */
        double standAreaStandardDeviation = 0;

        for (int standardDeviationCounter = 0; standardDeviationCounter < standList.size(); standardDeviationCounter++) {
            standAreaStandardDeviation = standAreaStandardDeviation + Math.pow(standList.get(standardDeviationCounter).getArea() - standAreaAverage, 2);
        }

        standAreaStandardDeviation = sturgeOptimalNumberColumns > 0 ? Math.sqrt(standAreaStandardDeviation / standList.size()) : 0;

        List<Double> statistics = new ArrayList<>();
        statistics.add(standAreaAverage);
        statistics.add(standAreaStandardDeviation);

        standDisplayInfo.add(statistics);
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " enumerated event stands information");
        return standDisplayInfo;
    }
}
