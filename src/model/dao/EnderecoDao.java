package model.dao;

import model.Endereco;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EnderecoDao implements InterfaceDao {

    private EntityManager manager;

    public EnderecoDao() {
       manager = JpaUtil.getEntityManager();
    }

    public int add(Object end) {
        Endereco endereco = (Endereco) end;
        manager.getTransaction().begin();
        manager.persist(endereco);
        manager.getTransaction().commit();
        return endereco.getCod();
    }

    public void remove(int id) {
      manager.getTransaction().begin();
        manager.remove(manager.getReference(Endereco.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object end) {
        Endereco endereco = (Endereco) end;
        manager.getTransaction().begin();
        manager.merge(endereco);
        manager.getTransaction().commit();
    }

    @Override
    public Endereco get(int id) {
        Endereco endereco = manager.find(Endereco.class, id);
        return endereco;
    }

    public ArrayList<Endereco> getAll() {
        Query query = manager.createQuery("from Endereco");
        List<Endereco> lista = query.getResultList();              
        return (ArrayList<Endereco>)lista;
    }

}
