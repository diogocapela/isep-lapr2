package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.model.Keyword;
import lapr.project.utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for the UC16 (List Event Topics).
 * <p>
 * Organisers want to be able to have information about which topics are in
 * vogue. As a result, a topic frequency table for each event should be created
 * and a ranking using that frequency table should be shown.
 * <p>
 * Created by Diogo Capela (1171316@isep.ipp.pt) on 05/06/2018.
 */
public class UC16ListEventTopicsController extends SuperController {

    /**
     * Returns the List of keywords for all the accepted Applications for a
     * specific event.
     *
     * @param event An event.
     * @param status status of event
     * @return keywords
     */
    public List<Keyword> getKeywordsFromEvent(Event event, String status) {
        List<Keyword> keywords = new ArrayList<>();
        for (Application application : event.getApplicationList()) {
            if ("submitted".equals(status)) {
                keywords.addAll(application.getKeywords());
            } else {
                if (status.equals(application.getStatus())) {
                    keywords.addAll(application.getKeywords());
                }
            }
        }
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " enumerated keywords of event:" + event);
        return keywords;
    }

    /**
     * Returns a HashMap with the number of occurrence of each keyword.
     *
     * @param keywords List of the keywords.
     * @return map
     */
    public Map<String, Integer> getKeywordFrequencyMap(List<Keyword> keywords) {
        Map<String, Integer> map = new HashMap<>();
        for (Keyword k : keywords) {
            String keyword = k.getValue();
            if (map.containsKey(keyword)) {
                // if map already contains keyword increase its value by 1
                map.put(keyword, map.get(keyword) + 1);
            } else {
                // if map does not contains keyword set its value as 1
                map.put(keyword, 1);
            }
        }
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " enumerated keywords frequency map");
        return map;
    }

}
