package model.dao;

import model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Login;

public class FuncionarioDao implements InterfaceDao {

    private EntityManager manager;

    public FuncionarioDao() {
        manager = JpaUtil.getEntityManager();
    }

    public int add(Object car) {
        Funcionario funcionario = (Funcionario) car;
        manager.getTransaction().begin();
        manager.persist(funcionario);
        manager.getTransaction().commit();
        return funcionario.getCodigo();
    }

    public void remove(int id) {
         manager.getTransaction().begin();
        manager.remove(manager.getReference(Funcionario.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object car) {
        Funcionario funcionario = (Funcionario) car;
        manager.getTransaction().begin();
        manager.merge(funcionario);
        manager.getTransaction().commit();
    }

    @Override
    public Funcionario get(int id) {
        Funcionario funcionario = manager.find(Funcionario.class, id);
        return funcionario;
    }

    public ArrayList<Funcionario> getAll() {
        Query query = manager.createQuery("from Funcionario");
        List<Funcionario> lista = query.getResultList();
        return (ArrayList<Funcionario>) lista;
    }

    public Funcionario getLogin(int idLogin) {
        Query query = manager.createQuery("from Funcionario where login_cod=:cod").setParameter("cod", idLogin);

         try {
            return (Funcionario) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
