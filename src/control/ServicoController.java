package control;

import java.util.ArrayList;
import model.dao.ServicoDao;
import model.Servico;

public class ServicoController implements InterfaceControllerCrud {

    ServicoDao servicoDao = new ServicoDao();

    @Override
    public int add(Object ser) {
        return servicoDao.add((Servico) ser);
    }

    @Override
    public void remove(int id) {
        servicoDao.remove(id);
    }

    @Override
    public void altera(Object ser) {
        Servico serro = (Servico) ser;
        servicoDao.altera(serro);
    }

    @Override
    public model.Servico get(int id) {
        return servicoDao.get(id);
    }

    @Override
    public ArrayList<model.Servico> getAll() {
        return servicoDao.getAll();
    }

    @Override
    public ArrayList<String> valida(Object servico) {
        Servico ser = (Servico) servico;
        ArrayList<String> erros = new ArrayList();
        if (ser.getDescricao().isEmpty()) {
            erros.add("Nome");
        }
        return erros;
    }

}
