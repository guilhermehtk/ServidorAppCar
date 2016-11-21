package model.dao;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClienteDao implements InterfaceDao {

    private EntityManager manager;

    public ClienteDao() {
 manager = JpaUtil.getEntityManager();
    }

    public int add(Object cli) {
        Cliente cliente = (Cliente) cli;
        manager.getTransaction().begin();
        manager.persist(cliente);
        manager.getTransaction().commit();
        return cliente.getCodigo();
    }

    public void remove(int id) {
          manager.getTransaction().begin();
        manager.remove(manager.getReference(Cliente.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object cli) {
        Cliente cliente = (Cliente) cli;
        manager.getTransaction().begin();
        manager.merge(cliente);
        manager.getTransaction().commit();
    }

    @Override
    public Cliente get(int id) {
        Cliente cliente = manager.find(Cliente.class, id);
        return cliente;
    }

    public ArrayList<Cliente> getAll() {
        Query query = manager.createQuery("from Cliente");
        List<Cliente> lista = query.getResultList();   
        return (ArrayList<Cliente>)lista;
    }

}
