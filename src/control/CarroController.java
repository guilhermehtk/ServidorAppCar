package control;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import model.dao.CarroDao;
import model.Carro;

public class CarroController implements InterfaceControllerCrud {

    CarroDao carDao = new CarroDao();
    ClienteController cliControl = new ClienteController();

    @Override
    public int add(Object car) {
        return carDao.add((Carro) car);
    }

    @Override
    public void remove(int id) {
        carDao.remove(id);
    }

    @Override
    public void altera(Object car) {
        Carro carro = (Carro) car;
        carDao.altera(car);
    }

    @Override
    public Object get(int id) {
        return carDao.get(id);
    }

    @Override
    public ArrayList<model.Carro> getAll() {
        return carDao.getAll();
    }

    @Override
    public ArrayList<String> valida(Object carro) {
        Carro car = (Carro) carro;
        ArrayList<String> erros = new ArrayList();
        if (car.getMarca().equals("Selecione...")) {
            erros.add("Marca");
        }
        if (car.getModelo().equals("Selecione...")) {
            erros.add("Modelo");
        }
        if (car.getPlaca().isEmpty() || car.getPlaca().equals("   -    ")) {
            erros.add("Placa");
        }

        return erros;
    }

    public ArrayList<String> validaUniques(Object carro) {
        Carro car = (Carro) carro;
        ArrayList<String> erros = new ArrayList();
        for (model.Carro carros : getAll()) {
            if (carros.getChassi().equals(car.getChassi())) {
                erros.add("Chassi");
            }
            if (carros.getPlaca().equals(car.getPlaca())) {
                erros.add("Placa");
            }
        }
        return erros;
    }

    public DefaultComboBoxModel procurar(int tipo) {
        DefaultComboBoxModel cbArray = new DefaultComboBoxModel();
        cbArray.insertElementAt("Selecione...", 0);
        switch (tipo) {
            case 0:
                // Código
                for (model.Carro carro : getAll()) {
                    cbArray.addElement(carro.getCod() + " | " + carro.getMarca() + " " + carro.getModelo() + " | " + carro.getPlaca());
                }
                break;
            case 1:
                // Placa
                for (model.Carro carro : getAll()) {
                    cbArray.addElement(carro.getPlaca() + " | " + carro.getMarca() + " " + carro.getModelo() + " | Código " + carro.getCod());
                }
                break;
            case 2:
                // Chassi
                for (model.Carro carro : getAll()) {
                    cbArray.addElement(carro.getChassi() + " | " + carro.getMarca() + " " + carro.getModelo() + " | " + carro.getPlaca());
                }
                break;
            case 3:
                // Dono
                for (model.Carro carro : getAll()) {
                    cbArray.addElement(cliControl.get(carro.getDono().getCodigo()).getNome() + " | " + carro.getMarca() + " " + carro.getModelo() + " | " + carro.getPlaca());
                }
                break;
        }

        return cbArray;
    }
}
