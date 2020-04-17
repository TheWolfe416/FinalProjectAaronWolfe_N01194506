package com.wolfe;

import com.wolfe.entity.NBATeams;
import com.wolfe.service.TeamService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AllTeamsView {
    private List<NBATeams> teams;

    @EJB
    private TeamService teamService;

    @PostConstruct
    public void init() {
        teams = new ArrayList<>();
        teams.addAll(teamService.getAll());
    }

    public List<NBATeams> getTeams() {
        return teams;
    }

    public void setTeams(List<NBATeams> teams) {
        this.teams = teams;
    }

    public String deleteTeam(long id) {
        teamService.delete(teamService.findById(id));
        return "/teams.xhtml?faces-redirect=true";
    }


    public String redirectToEditTeam() {
        return "/editTeam.xhtml?faces-redirect=true";
    }

}
