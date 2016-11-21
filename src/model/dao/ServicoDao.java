package model.dao;

import model.Servico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ServicoDao implements InterfaceDao {

    private EntityManager manager;

    public ServicoDao() {
        manager = JpaUtil.getEntityManager();
    }

    public int add(Object serv) {
        Servico servico = (Servico) serv;
        manager.getTransaction().begin();
        manager.persist(servico);
        manager.getTransaction().commit();
        return servico.getCod();
    }

    public void remove(int id) {
         manager.getTransaction().begin();
        manager.remove(manager.getReference(Servico.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object serv) {
        Servico servico = (Servico) serv;
        manager.getTransaction().begin();
        manager.merge(servico);
        manager.getTransaction().commit();
    }

    @Override
    public Servico get(int id) {
        Servico servico = manager.find(Servico.class, id);
        return servico;
    }

    public ArrayList<Servico> getAll() {
        Query query = manager.createQuery("from Servico");
        List<Servico> lista = query.getResultList();
        return (ArrayList<Servico>) lista;
    }

}
