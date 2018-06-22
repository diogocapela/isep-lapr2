package lapr.project.controller;

import lapr.project.model.Application;
import lapr.project.model.Event;
import lapr.project.utils.Logger;

public class UC30ShowEventAcceptanceRateController extends SuperController {

    public float getEventAcceptanceRate(Event event) {
        int counterApproved = 0;
        int counterTotal = event.getApplicationList().size();
        for (Application tmp : event.getApplicationList()) {
            if (("accepted").equalsIgnoreCase(tmp.getStatus())) {
                counterApproved++;
            }
        }
        Logger.log(USERSTR + getLoggedInUser().getUsername() + " enumerated acceptance rate of event:"+event);
        return counterTotal == 0 ? 0 : (100f * counterApproved / counterTotal);
    }

}
