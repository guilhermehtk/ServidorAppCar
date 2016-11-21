package model.dao;

import model.OrdemServico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class OrdemServicoDao implements InterfaceDao {

    private EntityManager manager;

    public OrdemServicoDao() {
        manager = JpaUtil.getEntityManager();
    }

    public int add(Object os) {
        OrdemServico ordemservico = (OrdemServico) os;
        manager.getTransaction().begin();
        manager.persist(ordemservico);
        manager.getTransaction().commit();
        return ordemservico.getCod();
    }

    public void remove(int id) {
        manager.getTransaction().begin();
        manager.remove(manager.getReference(OrdemServico.class, id));
        manager.getTransaction().commit();
    }

    public void altera(Object os) {
        OrdemServico ordemservico = (OrdemServico) os;
        manager.getTransaction().begin();
        manager.merge(ordemservico);
        manager.getTransaction().commit();
    }

    @Override
    public OrdemServico get(int id) {
        OrdemServico ordemservico = manager.find(OrdemServico.class, id);
        return ordemservico;
    }

    public ArrayList<OrdemServico> getAll() {
        Query query = manager.createQuery("from OrdemServico");
        List<OrdemServico> lista = query.getResultList();
        return (ArrayList<OrdemServico>) lista;
    }

}
