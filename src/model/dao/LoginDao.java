package model.dao;

import model.Login;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LoginDao implements InterfaceDao {

    private EntityManager manager;

    public LoginDao() {
 manager = JpaUtil.getEntityManager();
    }

    public int add(Object log) {
        Login login = (Login) log;
        manager.getTransaction().begin();
        manager.persist(login);
        manager.getTransaction().commit();
        return login.getCod();
    }

    public void remove(int id) {
        manager.getTransaction().begin();
        manager.remove(manager.getReference(Login.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object log) {
        Login login = (Login) log;
        manager.getTransaction().begin();
        manager.merge(login);
        manager.getTransaction().commit();
    }

    @Override
    public Login get(int id) {
        Login login = manager.find(Login.class, id);
        return login;
    }

    public ArrayList<Login> getAll() {
        List<Login> lista = (List<Login>) manager.createQuery("from Login");
        return (ArrayList<Login>)lista;
    }
    
     public Login getUsuario(Login log) {
       Query query =  manager.createQuery("from Login where usuario=:user").setParameter("user", log.getUsuario());
       return (Login) query.getSingleResult();
    }

}
