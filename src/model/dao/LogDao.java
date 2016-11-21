package model.dao;

import model.Log;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LogDao implements InterfaceDao {

    private EntityManager manager;

    public LogDao() {
        manager = JpaUtil.getEntityManager();
    }

    public int add(Object lg) {
        Log log = (Log) lg;
        manager.getTransaction().begin();
        manager.persist(log);
        manager.getTransaction().commit();
        return log.getCod();
    }

    public void remove(int id) {
       manager.getTransaction().begin();
        manager.remove(manager.getReference(Log.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object lg) {
        Log log = (Log) lg;
        manager.getTransaction().begin();
        manager.merge(log);
        manager.getTransaction().commit();
    }

    @Override
    public Log get(int id) {
        Log log = manager.find(Log.class, id);
        return log;
    }

    public ArrayList<Log> getAll() {
        Query query = manager.createQuery("from Log");
        List<Log> lista = query.getResultList();
        return (ArrayList<Log>) lista;
    }

}
