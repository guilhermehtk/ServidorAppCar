package model.dao;

import model.Pessoa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PessoaDao implements InterfaceDao {

    private EntityManager manager;

    public PessoaDao() {
        manager = JpaUtil.getEntityManager();
    }

    public int add(Object pes) {
        Pessoa pessoa = (Pessoa) pes;
        manager.getTransaction().begin();
        manager.persist(pessoa);
        manager.getTransaction().commit();
        return pessoa.getCodigo();
    }

    public void remove(int id) {
       manager.getTransaction().begin();
        manager.remove(manager.getReference(Pessoa.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object pes) {
        Pessoa pessoa = (Pessoa) pes;
        manager.getTransaction().begin();
        manager.merge(pessoa);
        manager.getTransaction().commit();
    }

    @Override
    public Pessoa get(int id) {
        Pessoa pessoa = manager.find(Pessoa.class, id);
        return pessoa;
    }

    public ArrayList<Pessoa> getAll() {
        Query query = manager.createQuery("from Pessoa");
        List<Pessoa> lista = query.getResultList();
        return (ArrayList<Pessoa>) lista;
    }

}
