package lapr.project.utils;

import lapr.project.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vítor Hugo Silva <1140825@isep.ipp.pt>
 */
public class CsvUtilsTest {

    private String csvStr = "title;accepted;description;boothArea;invitesQuantity;text;staffTopicKnowledge;eventAdequacy;inviteAdequacy;recommendation;decision;name;email;username;password;text2;staffTopicKnowledge2;eventAdequacy2;inviteAdequacy2;recommendation2;decision2;name2;email2;username2;password2;topics\r\n"
            + "exhibition1;true;Description: 15886;158;86;text0153;0;1;5;3;accepted;Staff1;staff1@centre.pt;staff1@centre.pt;password;text5103;5;1;0;3;accepted;Staff2;staff2@centre.pt;staff2@centre.pt;password;Crabby,Memory,Attract,Natural";

    @Test
    public void testEvent() {
        File f = new File("./target/csvExample.csv");

        try {
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(csvStr);
            bw.close();
        } catch (IOException ioe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }

        //test
        CsvUtils cutils = new CsvUtils();
        try {
            cutils.prepareCsv(f.getAbsolutePath());
            List<Event> lstEvents = cutils.getEvents();
            f.delete();
            Assertions.assertEquals(lstEvents.size(), 1);
        } catch (FileNotFoundException fnoe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
    }

    @Test
    public void testEventNullFile() {
        CsvUtils cutil = new CsvUtils();
        Assertions.assertEquals(new ArrayList<>(), cutil.getEvents());
    }

    @Test
    public void testEventNullFilePath() {
        CsvUtils cutil = new CsvUtils();
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            cutil.prepareCsv("");
        });
    }

    @Test
    public void testEventIOExcept() {
        CsvUtils cutil = new CsvUtils();
        File f = new File("./target/csvExample.csv");
        Assertions.assertThrows(IOException.class, () -> {
            cutil.prepareCsv("");
        });
        try {
            f.createNewFile();
            cutil.prepareCsv("./target/csvExample.csv");
        } catch (FileNotFoundException ex) {
            //hm ok.
        } catch (IOException ioe) {

        }
        f.delete();
        Assertions.assertEquals(new ArrayList<>(), cutil.getEvents());
        //Assertions.assertNotNull(cutil.getEvents());
    }

    @Test
    public void testEventSimple() {
        File f = new File("./target/csvExample.csv");
        try {
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(csvStr);
            bw.close();
        } catch (IOException ioe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
        //test
        CsvUtils cutils = new CsvUtils();
        try {
            cutils.prepareCsv(f.getAbsolutePath());
            List<Event> lstEvents = cutils.getEvents();
            //test lstEvents
            Event e = lstEvents.get(0);
            Event expected = new Event();

            Application app = new Application();
            ApplicationReview appR = new ApplicationReview();
            ApplicationReview appR2 = new ApplicationReview();

            expected.setTitle("exhibition1");
            expected.setDescription("Description: 15886");
            app.setIntendedStandArea(158);
            app.setNumberOfInvitations(86);

            appR.setEventAdequacyRating(1);
            appR.setStaffTopicKnowledgeRating(0);
            appR.setInviteAdequacyRating(5);
            appR.setOverallRecommendationRating(5);
            appR.setIsAccepted(true);
            User u = new User();
            u.setName("Staff1");
            u.setEmail("staff1@centre.pt");
            u.setUsername("staff1@centre.pt");
            u.setPassword("password");
            appR.setUser(u);

            appR2.setEventAdequacyRating(1);
            appR2.setStaffTopicKnowledgeRating(5);
            appR2.setInviteAdequacyRating(0);
            appR2.setOverallRecommendationRating(3);
            appR2.setIsAccepted(true);
            User u2 = new User();
            u2.setName("Staff2");
            u2.setEmail("staff2@centre.pt");
            u2.setUsername("staff2@centre.pt");
            u2.setPassword("password");
            appR2.setUser(u);

            app.addApplicationReview(appR);
            app.addApplicationReview(appR2);
            expected.addApplication(app);
            f.delete();
            Assertions.assertEquals(expected, e);
            Assertions.assertEquals(expected.getTitle(), e.getTitle());


        } catch (FileNotFoundException fnoe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
    }

    @Test
    public void testEventSimpleRejected() {
        csvStr = "title;accepted;description;boothArea;invitesQuantity;text;staffTopicKnowledge;eventAdequacy;inviteAdequacy;recommendation;decision;name;email;username;password;text2;staffTopicKnowledge2;eventAdequacy2;inviteAdequacy2;recommendation2;decision2;name2;email2;username2;password2;topics\r\n"
                + "exhibition1;false;Description: 15886;158;86;text0153;0;1;5;3;accepted;Staff1;staff1@centre.pt;staff1@centre.pt;password;text5103;5;1;0;3;accepted;Staff2;staff2@centre.pt;staff2@centre.pt;password;Crabby,Memory,Attract,Natural";
        File f = new File("./target/csvExample.csv");
        try {
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(csvStr);
            bw.close();
        } catch (IOException ioe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
        //test
        CsvUtils cutils = new CsvUtils();
        try {
            cutils.prepareCsv(f.getAbsolutePath());
            List<Event> lstEvents = cutils.getEvents();
            //test lstEvents
            Event e = lstEvents.get(0);
            Event expected = new Event();

            Application app = new Application();
            ApplicationReview appR = new ApplicationReview();
            ApplicationReview appR2 = new ApplicationReview();

            expected.setTitle("exhibition1");
            expected.setDescription("Description: 15886");
            app.setIntendedStandArea(158);
            app.setNumberOfInvitations(86);

            appR.setEventAdequacyRating(1);
            appR.setStaffTopicKnowledgeRating(0);
            appR.setInviteAdequacyRating(5);
            appR.setOverallRecommendationRating(5);
            appR.setIsAccepted(true);
            User u = new User();
            u.setName("Staff1");
            u.setEmail("staff1@centre.pt");
            u.setUsername("staff1@centre.pt");
            u.setPassword("password");
            appR.setUser(u);

            appR2.setEventAdequacyRating(1);
            appR2.setStaffTopicKnowledgeRating(5);
            appR2.setInviteAdequacyRating(0);
            appR2.setOverallRecommendationRating(3);
            appR2.setIsAccepted(true);
            User u2 = new User();
            u2.setName("Staff2");
            u2.setEmail("staff2@centre.pt");
            u2.setUsername("staff2@centre.pt");
            u2.setPassword("password");
            appR2.setUser(u);

            app.addApplicationReview(appR);
            app.addApplicationReview(appR2);
            expected.addApplication(app);
            f.delete();
            Assertions.assertEquals(expected, e);

        } catch (FileNotFoundException fnoe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
    }

    @Test
    public void testEventFull() {
        csvStr = "title;accepted;description;boothArea;invitesQuantity;text;staffTopicKnowledge;eventAdequacy;inviteAdequacy;recommendation;decision;name;email;username;password;text2;staffTopicKnowledge2;eventAdequacy2;inviteAdequacy2;recommendation2;decision2;name2;email2;username2;password2;topics\r\n"
                + "exhibition1;false;Description: 15886;158;86;text0153;0;1;5;3;accepted;Staff1;staff1@centre.pt;staff1@centre.pt;password;text5103;5;1;0;3;accepted;Staff2;staff2@centre.pt;staff2@centre.pt;password;Crabby,Memory,Attract,Natural";
        File f = new File("./target/csvExample.csv");
        try {
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(csvStr);
            bw.close();
        } catch (IOException ioe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
        //test
        CsvUtils cutils = new CsvUtils();
        try {
            cutils.prepareCsv(f.getAbsolutePath());
            List<Event> lstEvents = cutils.getEvents();
            //test lstEvents
            Event e = lstEvents.get(0);

            Event expected = new Event();
            expected.setTitle("exhibition1");
            expected.setDescription("Description: 15886");

            Application app = new Application();
            Application expectedApp = new Application();

            app.setIntendedStandArea(158);
            app.setNumberOfInvitations(86);
            app.setRejected();
            expectedApp.setNumberOfInvitations(86);
            expectedApp.setIntendedStandArea(158);
            expectedApp.setRejected();

            expectedApp.addKeyword(new Keyword("Crabby"));
            expectedApp.addKeyword(new Keyword("Memory"));
            expectedApp.addKeyword(new Keyword("Attract"));
            expectedApp.addKeyword(new Keyword("Natural"));

            ApplicationReview appR = new ApplicationReview();
            appR.setEventAdequacyRating(1);
            appR.setStaffTopicKnowledgeRating(0);
            appR.setInviteAdequacyRating(5);
            appR.setOverallRecommendationRating(3);
            appR.setIsAccepted(true);

            User u = new User();
            u.setName("Staff1");
            u.setEmail("staff1@centre.pt");
            u.setUsername("staff1@centre.pt");
            u.setPassword("password");
            appR.setUser(u);
            app.addApplicationReview(appR);

            ApplicationReview expectedAppR = new ApplicationReview();
            expectedAppR.setEventAdequacyRating(1);
            expectedAppR.setStaffTopicKnowledgeRating(0);
            expectedAppR.setInviteAdequacyRating(5);
            expectedAppR.setOverallRecommendationRating(3);
            expectedAppR.setIsAccepted(true);

            User expectedU = new User();
            expectedU.setName("Staff1");
            expectedU.setEmail("staff1@centre.pt");
            expectedU.setUsername("staff1@centre.pt");
            expectedU.setPassword("password");
            expectedAppR.setUser(u);
            expectedApp.addApplicationReview(expectedAppR);

            ApplicationReview expectedAppR2 = new ApplicationReview();
            expectedAppR2.setEventAdequacyRating(1);
            expectedAppR2.setStaffTopicKnowledgeRating(5);
            expectedAppR2.setInviteAdequacyRating(0);
            expectedAppR2.setOverallRecommendationRating(3);
            expectedAppR2.setIsAccepted(true);

            User u2 = new User();
            u2.setName("Staff2");
            u2.setEmail("staff2@centre.pt");
            u2.setUsername("staff2@centre.pt");
            u2.setPassword("password");
            expectedAppR2.setUser(u2);
            expectedApp.addApplicationReview(expectedAppR2);

            expected.addApplication(expectedApp);

            f.delete();
            Assertions.assertEquals(expected, e);

            Assertions.assertEquals(expected.getApplicationList().get(0), e.getApplicationList().get(0));
            Assertions.assertEquals(expected.getApplicationList().get(0).getIntendedStandArea(), e.getApplicationList().get(0).getIntendedStandArea());
            Assertions.assertEquals(expected.getApplicationList().get(0).getNumberOfInvitations(), e.getApplicationList().get(0).getNumberOfInvitations());

            Assertions.assertEquals(expected.getApplicationList().get(0).getKeywords(), e.getApplicationList().get(0).getKeywords());


            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(0), e.getApplicationList().get(0).getReviews().get(0));
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(0).getEventAdequacyRating(), e.getApplicationList().get(0).getReviews().get(0).getEventAdequacyRating());
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(0).getInviteAdequacyRating(), e.getApplicationList().get(0).getReviews().get(0).getInviteAdequacyRating());
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(0).getOverallRecommendationRating(), e.getApplicationList().get(0).getReviews().get(0).getOverallRecommendationRating());
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(0).getRequestedStandAreaRating(), e.getApplicationList().get(0).getReviews().get(0).getRequestedStandAreaRating());
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(0).getStaffTopicKnowledgeRating(), e.getApplicationList().get(0).getReviews().get(0).getStaffTopicKnowledgeRating());

            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(1), e.getApplicationList().get(0).getReviews().get(1));
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(1).getEventAdequacyRating(), e.getApplicationList().get(0).getReviews().get(1).getEventAdequacyRating());
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(1).getInviteAdequacyRating(), e.getApplicationList().get(0).getReviews().get(1).getInviteAdequacyRating());
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(1).getOverallRecommendationRating(), e.getApplicationList().get(0).getReviews().get(1).getOverallRecommendationRating());
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(1).getRequestedStandAreaRating(), e.getApplicationList().get(0).getReviews().get(1).getRequestedStandAreaRating());
            Assertions.assertEquals(expected.getApplicationList().get(0).getReviews().get(1).getStaffTopicKnowledgeRating(), e.getApplicationList().get(0).getReviews().get(1).getStaffTopicKnowledgeRating());


        } catch (FileNotFoundException fnoe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
    }

    @Test
    public void testStd() {
        String sb = "﻿event;description;area;relativeDistanceSet\r\n"
                + "exhibition1;Stand 1;37;{(Stand 2,9),(Stand 3,7),(Stand 4,12)}\r\n"
                + "exhibition1;Stand 2;103;{(Stand 7,18),(Stand 5,10),(Stand 3,5)}\r\n"
                + "exhibition1;Stand 3;59;{(Stand 4,7),(Stand 5,9),(Stand 6,13)}\r\n"
                + "exhibition1;Stand 4;60;{(Stand 6,10),(Stand 8,20)}\r\n"
                + "exhibition1;Stand 5;103;{(Stand 6,12),(Stand 7,6),(Stand 9,18)}\r\n"
                + "exhibition1;Stand 6;32;{(Stand 8,8),(Stand 9,20)}\r\n"
                + "exhibition1;Stand 7;44;{(Stand 8,16),(Stand 9,17)}\r\n"
                + "exhibition1;Stand 8;117;{(Stand 9,15)}\r\n"
                + "exhibition1;Stand 9;34\r\n"
                + "exhibition1;Stand 10;102\r\n"
                + "exhibition1;Stand 11;40\r\n"
                + "exhibition1;Stand 12;17\r\n"
                + "exhibition1;Stand 13;67\r\n"
                + "exhibition1;Stand 14;27\r\n"
                + "exhibition1;Stand 15;52\r\n"
                + "exhibition1;Stand 16;43\r\n"
                + "exhibition1;Stand 17;125\r\n"
                + "exhibition1;Stand 18;108\r\n"
                + "exhibition1;Stand 19;15\r\n"
                + "exhibition1;Stand 20;13";
        File f = new File("./target/csvExampleStd.csv");
        try {
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(sb);
            bw.close();
        } catch (IOException ioe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
        CsvUtils cutils = new CsvUtils();
        try {
            cutils.prepareCsv(f.getAbsolutePath());
            List<Event> lstEventsWithStds = cutils.getEventsByStd();
            f.delete();
            Assertions.assertEquals(lstEventsWithStds.size(), 1);
        } catch (FileNotFoundException fnoe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
    }


    @Test
    public void testStdFull() {
        String sb = "﻿event;description;area;relativeDistanceSet\r\n"
                + "exhibition1;Stand 1;37;{(Stand 2,9)}\r\n"
                + "exhibition1;Stand 20;13";
        File f = new File("./target/csvExampleStd.csv");
        try {
            f.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(sb);
            bw.close();
        } catch (IOException ioe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
        CsvUtils cutils = new CsvUtils();
        try {
            cutils.prepareCsv(f.getAbsolutePath());
            List<Event> lstEventsWithStds = cutils.getEventsByStd();
            f.delete();
            Event e = lstEventsWithStds.get(0);
            if (e != null) {
                List<Stand> lstStands = e.getStandList();

                List<StandDistance> lstDistance1 = lstStands.get(0).getRelativeDistanceSet();
                List<StandDistance> lstDistance2 = lstStands.get(1).getRelativeDistanceSet();


                Assertions.assertEquals("exhibition1", e.getTitle());
                Assertions.assertEquals("Stand 1", lstStands.get(0).getDescription());
                Assertions.assertEquals(37, lstStands.get(0).getArea());

                Assertions.assertEquals("Stand 2", lstDistance1.get(0).getDistanceDescription());
                Assertions.assertEquals(9, lstDistance1.get(0).getDistanceValue());

                Assertions.assertEquals("Stand 20", lstStands.get(1).getDescription());
                Assertions.assertEquals(13, lstStands.get(1).getArea());

                Assertions.assertEquals(0, lstDistance2.size());
            }
        } catch (FileNotFoundException fnoe) {
            f.delete();
            Assertions.assertEquals(false, true);//fail
        }
    }
}
