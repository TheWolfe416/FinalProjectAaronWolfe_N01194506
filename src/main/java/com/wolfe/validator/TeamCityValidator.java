package com.wolfe.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Arrays;

@FacesValidator
public class TeamCityValidator implements Validator {
    private String[] teamCities = { "Atlanta", "Boston", "Brooklyn", "Charlotte", "Chicago ", "Cleveland",
            "Dallas", "Denver", "Detroit", "San Francisco (Golden State)", "Houston", "Indiana", "Los Angeles",
            "Los Angeles", "Memphis", "Miami", "Milwaukee", "Minneapolis (Minnesota)", "New Orleans",
            "New York", "Oklahoma City", "Orlando", "Philadelphia", "Phoenix", "Portland", "Sacramento", "San Antonio",
            "Toronto", "Salt Lake City (Utah)", "Washington" };



    public void validate(FacesContext facesContext, UIComponent uiComponent, Object teamCity) {
        if (!Arrays.asList(teamCities).contains(teamCity)) {
            FacesMessage msg =
                    new FacesMessage("Residing city should be one of these: " + Arrays.toString(teamCities));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
