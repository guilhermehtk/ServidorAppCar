package model.dao;

import model.Carro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CarroDao implements InterfaceDao {

    private EntityManager manager;

    public CarroDao() {
        manager = JpaUtil.getEntityManager();
    }

    public int add(Object car) {
        Carro carro = (Carro) car;
        manager.getTransaction().begin();
        manager.persist(carro);
        manager.getTransaction().commit();
        return carro.getCod();
    }

    public void remove(int id) {
        manager.getTransaction().begin();
        manager.remove(manager.getReference(Carro.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object car) {
        Carro carro = (Carro) car;
        manager.getTransaction().begin();
        manager.merge(carro);
        manager.getTransaction().commit();
    }

    @Override
    public Carro get(int id) {
        Carro carro = manager.find(Carro.class, id);
        return carro;
    }

    public ArrayList<Carro> getAll() {
        Query query = manager.createQuery("from Carro");
        List<Carro> lista = query.getResultList();
        return (ArrayList<Carro>) lista;
    }

}
