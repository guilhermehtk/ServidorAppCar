package control;

import model.dao.EnderecoDao;
import model.Endereco;
import java.util.ArrayList;

public class EnderecoController implements InterfaceControllerCrud {

    EnderecoDao endDao = new EnderecoDao();

    @Override
    public int add(Object end) {
        return endDao.add((Endereco) end);
    }

    @Override
    public void remove(int id) {
        endDao.remove(id);
    }

    @Override
    public void altera(Object endereco) {
        Endereco end = (Endereco) endereco;
        endDao.altera(end);
    }

    @Override
    public Object get(int id) {
        return endDao.get(id);
    }

    @Override
    public ArrayList getAll() {
        return endDao.getAll();

    }

    @Override
    public ArrayList<String> valida(Object endereco) {
        Endereco end = (Endereco) endereco;
        ArrayList<String> erros = new ArrayList();
        if (end.getNumero().isEmpty()) {
            erros.add("Numero");
        }
        if (end.getRua().isEmpty()) {
            erros.add("Rua");
        }
        if (end.getBairro().isEmpty()) {
            erros.add("Bairro");
        }
        if (end.getCidade().isEmpty()) {
            erros.add("Cidade");
        }
        return erros;
    }

}
