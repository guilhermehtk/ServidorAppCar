package control;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import model.dao.CarroDao;
import model.dao.ClienteDao;
import model.dao.OrdemServicoDao;
import model.OrdemServico;

public class OrdemServicoController implements InterfaceControllerCrud {

    OrdemServicoDao osDao = new OrdemServicoDao();
    CarroDao carDao = new CarroDao();
    ClienteDao cliDao = new ClienteDao();

    @Override
    public int add(Object ordemServico) {
        OrdemServico os = (OrdemServico) ordemServico;    
        os.setData(new Timestamp(System.currentTimeMillis()));
        return osDao.add(os);
    }

    @Override
    public void remove(int id) {
        osDao.remove(id);
    }

    @Override
    public void altera(Object ordemServico) {
        OrdemServico os = (OrdemServico) ordemServico;
        osDao.altera(os);
    }

    @Override
    public model.OrdemServico get(int id) {
        return osDao.get(id);
    }

    @Override
    public ArrayList<model.OrdemServico> getAll() {
        return osDao.getAll();

    }

    @Override
    public ArrayList<String> valida(Object ordemServico) {
        OrdemServico os = (OrdemServico) ordemServico;
        ArrayList<String> erros = new ArrayList();
        if (os.getCliente()== null) {
            erros.add("Cliente");
        }
        if (os.getCarro()== null) {
            erros.add("Carro");
        }
        if (os.getTipo().isEmpty()) {
            erros.add("Tipo");
        }
        return erros;
    }
    
      public DefaultComboBoxModel procurar(int tipo) {
        DefaultComboBoxModel cbArray = new DefaultComboBoxModel();
        cbArray.insertElementAt("Selecione...", 0);
        switch (tipo) {
            case 0:
                // Código
                for (model.OrdemServico os : getAll()) {
                    cbArray.addElement(os.getCod() + " | " + os.getCarro().getPlaca());
                }
                break;
            case 1:
                // Cliente
                for (model.OrdemServico os : getAll()) {
                    cbArray.addElement(cliDao.get(os.getCliente().getCodigo()).getNome() + " | " + os.getCarro().getPlaca());
                }
                break;
            case 2:
                // Carro
                for (model.OrdemServico os : getAll()) {
                    cbArray.addElement(carDao.get(os.getCarro().getCod()).getPlaca() + " | " + os.getData());
                }
                break;
            case 3:
                // Data
                for (model.OrdemServico os : getAll()) {
                    cbArray.addElement(new SimpleDateFormat("dd/MM/yyyy").format(os.getData()) + " | " + os.getCarro().getPlaca());
                }
                break;
        }

        return cbArray;
    }

   

}
