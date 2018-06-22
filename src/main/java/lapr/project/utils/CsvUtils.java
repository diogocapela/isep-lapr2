package lapr.project.utils;

import lapr.project.model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */
public class CsvUtils {

    /**
     * <h1>CSV Utility</h1>
     * <p>
     * This utility class is designed for a specific csv file.</p>
     *
     */
    /**
     * Instance variables
     */
    private FileReader f;
    private BufferedReader bf;
    private static final String separator = ";";

    /**
     * Default empty constructor
     */
    public CsvUtils() {
        bf = null;
        f = null;
    }

    /**
     * Prepare class to read a CSV file If the file exists, not exception is
     * produced.
     *
     * @param filePath Path of the file, String.
     * @throws FileNotFoundException : If file is not found.
     */
    public void prepareCsv(String filePath) throws FileNotFoundException {
        f = new FileReader(filePath);
    }

    /**
     * Get the list of events
     *
     * @return Event list
     */
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        if (f != null) {
            try {
                bf = new BufferedReader(f);
                String cLine = bf.readLine();
                if (cLine == null) {
                    throw new IOException("Empty file");
                }
                Event e = new Event();
                Application app = new Application();
                ApplicationReview appR = new ApplicationReview();
                ApplicationReview appR2 = new ApplicationReview();
                while ((cLine = bf.readLine()) != null) {
                    String[] contents = cLine.split(separator);
                    int j = 1;
                    User u = new User();
                    User u2 = new User();
                    for (String s : contents) {
                        switch (j) {
                            case 1:
                                //title
                                e.setTitle(s);
                                break;

                            case 2:
                                //accepted or not
                                if ("true".equalsIgnoreCase(s)) {
                                    app.setAccepted();
                                } else {
                                    app.setRejected();
                                }
                                break;
                            case 3:
                                //description
                                e.setDescription(s);
                                break;

                            case 4:
                                //boothArea
                                app.setIntendedStandArea(Double.parseDouble(s));
                                break;

                            case 5:
                                //invites qtd
                                app.setNumberOfInvitations(Integer.parseInt(s));
                                break;

                            case 6:
                                //Text ??
                                break;

                            case 7:
                                //staffTopicKnowledge
                                appR.setStaffTopicKnowledgeRating(Integer.parseInt(s));
                                break;

                            case 8:
                                //eventAdequacy
                                appR.setEventAdequacyRating(Integer.parseInt(s));
                                break;

                            case 9:
                                //inviteAdequacy
                                appR.setInviteAdequacyRating(Integer.parseInt(s));
                                break;

                            case 10:
                                //recommendation
                                appR.setOverallRecommendationRating(Integer.parseInt(s));
                                break;

                            case 11:
                                //decision
                                if ("accepted".equalsIgnoreCase(s)) {
                                    appR.setIsAccepted(true);
                                } else {
                                    appR.setIsAccepted(false);
                                }
                                break;
                            case 12:
                                //name (user)
                                u.setName(s);
                                break;

                            case 13:
                                //email
                                u.setEmail(s);
                                break;

                            case 14:
                                //username
                                u.setUsername(s);
                                break;

                            case 15:
                                //password
                                u.setPassword(s);
                                break;

                            case 16:
                                //text2 ??
                                break;

                            case 17:
                                //staffTopicKnowledge2
                                appR2.setStaffTopicKnowledgeRating(Integer.parseInt(s));
                                break;

                            case 18:
                                //eventAdequacy2
                                appR2.setEventAdequacyRating(Integer.parseInt(s));
                                break;

                            case 19:
                                //inviteAdequacy2
                                appR2.setInviteAdequacyRating(Integer.parseInt(s));
                                break;

                            case 20:
                                //recommendation2
                                appR2.setOverallRecommendationRating(Integer.parseInt(s));
                                break;

                            case 21:
                                //decision2
                                if ("accepted".equalsIgnoreCase(s)) {
                                    appR2.setIsAccepted(true);
                                } else {
                                    appR2.setIsAccepted(false);
                                }
                                break;
                            case 22:
                                //name2
                                u2.setName(s);
                                break;

                            case 23:
                                //email2
                                u2.setEmail(s);
                                break;

                            case 24:
                                //username2
                                u2.setUsername(s);
                                break;

                            case 25:
                                //password2
                                u2.setPassword(s);
                                break;

                            case 26:
                                //topics
                                for (String x : s.split(",")) {
                                    app.addKeyword(new Keyword(x));
                                }
                                break;

                            default:
                                //wat?
                                break;

                        }
                        j++;

                    }
                    appR.setUser(u);
                    appR2.setUser(u2);

                }
                app.addApplicationReview(appR);
                app.addApplicationReview(appR2);
                e.addApplication(app);
                events.add(e);
            } catch (IOException ex) {
                Logger.log(ex.getMessage());
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
        return events;
    }

    public List<Event> getEventsByStd() {
        List<Event> events = new ArrayList<>();
        if (f != null) {
            try {
                bf = new BufferedReader(f);
                String cLine = bf.readLine();
                if (cLine == null) {
                    throw new IOException("Empty file");
                }
                Event ev = new Event();
                Stand std = null;
                while ((cLine = bf.readLine()) != null) {
                    String[] contents = cLine.split(separator);
                    int j = 1;
                    std = new Stand();
                    for (String s : contents) {
                        switch (j) {
                            case 1:
                                //title of event
                                ev.setTitle(s);
                                break;

                            case 2:
                                //stand description
                                std.setDescription(s);
                                break;

                            case 3:
                                //area
                                std.setArea(Integer.parseInt(s));
                                break;

                            case 4:
                                //List of stand distance.
                                String[] str = s.replace('{', ' ').trim().replace('}', ' ').trim().replace("),(", "$").replace('(', ' ').trim().replace(')', ' ').trim().split("\\$");
                                for (String tmp : str) {
                                    String[] t = tmp.split(","); //2 elements only.
                                    if (t.length == 2) {
                                        std.addRelativeDistance(new StandDistance(t[0], Integer.parseInt(t[1])));
                                    }
                                }
                                break;
                            default:
                                //what
                                break;

                        }
                        j++;
                    }
                    ev.addStand(std);
                }
                events.add(ev);

            } catch (IOException ex) {
                Logger.log(ex.getMessage());
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
        return events;
    }
}
