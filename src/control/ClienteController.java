package control;

import model.dao.ClienteDao;
import model.Cliente;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

public class ClienteController implements InterfaceControllerCrud {

    ClienteDao cliDao = new ClienteDao();

    @Override
    public int add(Object cliente) {
        return cliDao.add((Cliente) cliente);
    }

    @Override
    public void remove(int id) {
        cliDao.remove(id);
    }

    @Override
    public void altera(Object cliente) {
        Cliente cli = (Cliente) cliente;
        cliDao.altera(cli);
    }

    public Cliente get(int id) {
        return cliDao.get(id);
    }

    @Override
    public ArrayList<Cliente> getAll() {
        return cliDao.getAll();
    }

    @Override
    public ArrayList<String> valida(Object cliente) {
        Cliente cli = (Cliente) cliente;
        ArrayList<String> erros = new ArrayList();
        if (cli.getNome().isEmpty()) {
            erros.add("Nome");
        }
        if (cli.getCpf().isEmpty()) {
            erros.add("CPF");
        }
        if (cli.getRg().isEmpty()) {
            erros.add("RG");
        }
        if (cli.getTelefoneM().isEmpty()) {
            erros.add("Telefone");
        }
        if (cli.getSexo().isEmpty()) {
            erros.add("Sexo");
        }
        // Endereço
        if (cli.getEndereco().getRua().isEmpty()) {
            erros.add("Rua");
        }
        if (cli.getEndereco().getNumero().isEmpty()) {
            erros.add("Número");
        }
        if (cli.getEndereco().getComplemento().isEmpty()) {
            erros.add("Complemento");
        }
        if (cli.getEndereco().getBairro().isEmpty()) {
            erros.add("Bairro");
        }
        if (cli.getEndereco().getCidade().isEmpty()) {
            erros.add("Cidade");
        }
        if (cli.getEndereco().getCep().isEmpty()) {
            erros.add("CEP");
        }
        return erros;
    }

    public DefaultComboBoxModel procurar(int tipo) {
        DefaultComboBoxModel cbArray = new DefaultComboBoxModel();
        cbArray.insertElementAt("Selecione...", 0);
        switch (tipo) {
            case 0:
                // Código
                for (Cliente cliente : getAll()) {
                    cbArray.addElement(cliente.getCodigo() + " | " + cliente.getNome());
                }
                break;
            case 1:
                // Nome
                for (Cliente cliente : getAll()) {
                    cbArray.addElement(cliente.getNome() + " | " + cliente.getCpf() + "| Código " + cliente.getCodigo());
                }
                break;
            case 2:
                // CPF
                for (Cliente cliente : getAll()) {
                    cbArray.addElement(cliente.getCpf() + " | " + cliente.getNome());
                }
                break;
            case 3:
                // RG
                for (Cliente cliente : getAll()) {
                    cbArray.addElement(cliente.getRg() + " | " + cliente.getNome());
                }
                break;
        }

        return cbArray;
    }

    public ArrayList<String> validaUniques(Object cliente) {
        Cliente cli = (Cliente) cliente;
        ArrayList<String> erros = new ArrayList();
        for (Cliente clientes : getAll()) {
            if (clientes.getCpf().equals(cli.getCpf())) {
                erros.add("CPF");
            }
            if (clientes.getRg().equals(cli.getRg())) {
                erros.add("RG");
            }
        }
        return erros;
    }
}