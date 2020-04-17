package com.wolfe.service;

import com.wolfe.entity.NBATeams;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class TeamService {
    @PersistenceContext(unitName = "teamPersistenceUnit")
    private EntityManager manager;

    public List<NBATeams> getAll() {
        return manager.createNamedQuery("findAllTeams", NBATeams.class).getResultList();
    }


    public NBATeams findById(long id) {
        return manager.find(NBATeams.class, id);
    }

    @Transactional
    public void update(NBATeams team) {
        manager.getTransaction().begin();
        manager.merge(team);
        manager.getTransaction().commit();
    }

    @Transactional
    public void create(NBATeams team) {
        manager.getTransaction().begin();
        manager.persist(team);
        manager.getTransaction().commit();
    }

    @Transactional
    public void delete(NBATeams team) {
        manager.getTransaction().begin();
        if (!manager.contains(team)) {
            team = manager.merge(team);
        }

        manager.remove(team);
        manager.getTransaction().commit();
    }
}
