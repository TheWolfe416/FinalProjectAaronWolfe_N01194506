package com.wolfe;

import com.wolfe.entity.NBATeams;
import com.wolfe.service.TeamService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EditTeamView implements Serializable {
    private String city;
    private String name;
    private int estYear;
    private int championships;

    @EJB
    private TeamService teamService;
    private transient NBATeams teamToUpdate;

    public void populateView(long teamId) {
        teamToUpdate = teamService.findById(teamId);
        this.setCity(teamToUpdate.getCity());
        this.setName(teamToUpdate.getName());
        this.setEstYear(teamToUpdate.getEstYear());
        this.setChampionships(teamToUpdate.getChampionships());
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstYear() {
        return estYear;
    }

    public void setEstYear(int estYear) {
        this.estYear = estYear;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    public String save() {
        NBATeams createdTeams = new NBATeams(city, name, estYear, championships);
        if (teamToUpdate != null) {
            createdTeams.setId(teamToUpdate.getId());
            teamService.update(createdTeams);
        } else {
            teamService.create(createdTeams);
        }
        nullifyFields();
        return "/teams.xhtml?faces-redirect=true";
    }

    private void nullifyFields() {
        teamToUpdate = null;
        this.setCity(null);
        this.setName(null);
        this.setEstYear(0);
        this.setChampionships(0);
    }
}
