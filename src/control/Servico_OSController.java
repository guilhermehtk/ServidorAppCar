package control;

import java.util.ArrayList;
import model.dao.Servico_OSDao;
import model.Servico_OS;

public class Servico_OSController implements InterfaceControllerCrud {

    Servico_OSDao servico_OSDao = new Servico_OSDao();

    @Override
    public int add(Object ser) {
        return servico_OSDao.add((Servico_OS) ser);
    }

    @Override
    public void remove(int id) {
        servico_OSDao.remove(id);
    }

    @Override
    public void altera(Object ser) {
        Servico_OS serro = (Servico_OS) ser;
        servico_OSDao.altera(serro);
    }

    @Override
    public Servico_OS get(int id) {
        return servico_OSDao.get(id);
    }

    @Override
    public ArrayList<Servico_OS> getAll() {
        return servico_OSDao.getAll();
    }

    public ArrayList<Servico_OS> getAllOS(int os) {
        return servico_OSDao.getOS(os);
    }
    
  @Override
    public ArrayList<String> valida(Object ser) {
        Servico_OS serv = (Servico_OS) ser;
        ArrayList<String> erros = new ArrayList();
        if (serv.getOrdemservico()== null) {
            erros.add("Código da OS");
        }
        if (serv.getFuncionario()== null) {
            erros.add("Código do Funcionário");
        }
        if (serv.getServico() == null) {
            erros.add("Código do Serviço");
        }
        return erros;
    }

}
