package lapr.project.controller;

import lapr.project.model.Stand;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UC17ListEventStandsInformationControllerTest {

    UC17ListEventStandsInformationController controller;
    DecimalFormat df = new DecimalFormat("#.##");

    Stand TEST_STAND_1 = new Stand("1", 1);
    Stand TEST_STAND_2 = new Stand("2", 2);
    Stand TEST_STAND_3 = new Stand("3", 3);
    Stand TEST_STAND_4 = new Stand("4", 4);
    Stand TEST_STAND_5 = new Stand("5", 5);

    Stand TEST_STAND_31 = new Stand("31", 5);
    Stand TEST_STAND_32 = new Stand("32", 5);
    Stand TEST_STAND_33 = new Stand("33", 5);
    Stand TEST_STAND_34 = new Stand("34", 5);

    List<Stand> TEST_STAND_LIST_0 = new ArrayList<>();
    List<Stand> TEST_STAND_LIST_1 = new ArrayList<>();
    List<Stand> TEST_STAND_LIST_2 = new ArrayList<>();
    List<Stand> TEST_STAND_LIST_3 = new ArrayList<>();

    public UC17ListEventStandsInformationControllerTest() {
        Locale.setDefault(new Locale("en", "GB"));
        controller = new UC17ListEventStandsInformationController();

        TEST_STAND_LIST_1.add(TEST_STAND_1);

        TEST_STAND_LIST_2.add(TEST_STAND_1);
        TEST_STAND_LIST_2.add(TEST_STAND_2);
        TEST_STAND_LIST_2.add(TEST_STAND_3);
        TEST_STAND_LIST_2.add(TEST_STAND_4);
        TEST_STAND_LIST_2.add(TEST_STAND_5);

        TEST_STAND_LIST_3.add(TEST_STAND_31);
        TEST_STAND_LIST_3.add(TEST_STAND_32);
        TEST_STAND_LIST_3.add(TEST_STAND_33);
        TEST_STAND_LIST_3.add(TEST_STAND_34);
    }

    @Test
    void prepareStandDisplayEmptyStandListInfo() {
        List<List<Double>> expected = new ArrayList<>();
        List<Double> statistics = new ArrayList<>();
        statistics.add(Double.parseDouble("0"));
        statistics.add(Double.parseDouble("0"));
        expected.add(statistics);

        List<List<Double>> result = controller.prepareStandDisplayInfo(TEST_STAND_LIST_0);

        assertEquals(expected,result);
    }

    @Test
    void prepareStandDisplayNotEmptyStandListOneStandInfo() {
        List<List<Double>> expected = new ArrayList<>();

        List<Double> standRange1 = new ArrayList<>();
        standRange1.add(Double.parseDouble("0"));
        standRange1.add(Double.parseDouble("1"));
        standRange1.add(Double.parseDouble("1"));
        expected.add(standRange1);

        List<Double> statistics = new ArrayList<>();
        statistics.add(Double.parseDouble("1"));
        statistics.add(Double.parseDouble("0"));
        expected.add(statistics);

        List<List<Double>> result = controller.prepareStandDisplayInfo(TEST_STAND_LIST_1);

        assertEquals(expected,result);
    }

    @Test
    void prepareStandDisplayNotEmptyStandFiveEqualStandsInfo() {
        List<List<Double>> expected = new ArrayList<>();

        List<Double> standRange1 = new ArrayList<>();
        standRange1.add(Double.parseDouble("5"));
        standRange1.add(Double.parseDouble("5"));
        standRange1.add(Double.parseDouble("4"));
        expected.add(standRange1);

        List<Double> statistics = new ArrayList<>();
        statistics.add(Double.parseDouble("5"));
        statistics.add(Double.parseDouble("0"));
        expected.add(statistics);

        List<List<Double>> result = controller.prepareStandDisplayInfo(TEST_STAND_LIST_3);

        assertEquals(expected,result);
    }

    @Test
    void prepareStandDisplayNotEmptyStandListFiveStandsInfo() {

        List<List<Double>> expected = new ArrayList<>();

        List<Double> standRange1 = new ArrayList<>();
        standRange1.add(Double.parseDouble("1"));
        standRange1.add(Double.parseDouble("2.20"));
        standRange1.add(Double.parseDouble("2"));
        expected.add(standRange1);

        List<Double> standRange2 = new ArrayList<>();
        standRange2.add(Double.parseDouble("2.20"));
        standRange2.add(Double.parseDouble("3.41"));
        standRange2.add(Double.parseDouble("1"));
        expected.add(standRange2);

        List<Double> standRange3 = new ArrayList<>();
        standRange3.add(Double.parseDouble("3.41"));
        standRange3.add(Double.parseDouble("4.61"));
        standRange3.add(Double.parseDouble("1"));
        expected.add(standRange3);

        List<Double> standRange4 = new ArrayList<>();
        standRange4.add(Double.parseDouble("4.61"));
        standRange4.add(Double.parseDouble("5.82"));
        standRange4.add(Double.parseDouble("1"));
        expected.add(standRange4);

        List<Double> statistics = new ArrayList<>();
        statistics.add(Double.parseDouble("3"));
        statistics.add(Double.parseDouble("1.41"));
        expected.add(statistics);

        List<List<Double>> result = controller.prepareStandDisplayInfo(TEST_STAND_LIST_2);

        for (int outer = 0; outer < result.size(); outer++) {
            for (int inner = 0; inner < result.get(outer).size(); inner++) {
                result.get(outer).set(inner,Double.valueOf(df.format(result.get(outer).get(inner))));
            }
        }

        assertEquals(expected,result);
    }
}