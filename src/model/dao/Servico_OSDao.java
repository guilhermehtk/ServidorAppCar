package model.dao;

import model.Servico_OS;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Servico_OSDao implements InterfaceDao {

    private EntityManager manager;

    public Servico_OSDao() {
        manager = JpaUtil.getEntityManager();
    }

    public int add(Object serv) {
        Servico_OS servico_os = (Servico_OS) serv;
        manager.getTransaction().begin();
        manager.persist(servico_os);
        manager.getTransaction().commit();
        return servico_os.getCod();
    }

    public void remove(int id) {
        manager.getTransaction().begin();
        manager.remove(manager.getReference(Servico_OS.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object serv) {
        Servico_OS servico_os = (Servico_OS) serv;
        manager.getTransaction().begin();
        manager.merge(servico_os);
        manager.getTransaction().commit();
    }

    @Override
    public Servico_OS get(int id) {
        Servico_OS servico_os = manager.find(Servico_OS.class, id);
        return servico_os;
    }

    public ArrayList<Servico_OS> getAll() {
        Query query = manager.createQuery("from Servico_OS");
        List<Servico_OS> lista = query.getResultList();
        return (ArrayList<Servico_OS>) lista;
    }

    public ArrayList<Servico_OS> getOS(int os) {
        Query query = manager.createQuery("from Servico_OS where ordemservico_cod=:osCod").setParameter("osCod", os);
        List<Servico_OS> lista = query.getResultList();
        return (ArrayList<Servico_OS>) lista;
    }

}
