package control;

import model.dao.FuncionarioDao;
import model.Cliente;
import model.Funcionario;
import model.Login;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import org.mindrot.jbcrypt.BCrypt;

public class FuncionarioController implements InterfaceControllerCrud {

    FuncionarioDao funDao = new FuncionarioDao();

    @Override
    public int add(Object funcionario) {
        Funcionario fun = (Funcionario) funcionario;
        Login login = fun.getLogin();
        login.setSenha(BCrypt.hashpw(fun.getLogin().getSenha(), BCrypt.gensalt()));
        fun.setLogin(login);
        System.out.println(login.getSenha().length());
        return funDao.add(fun);
    }

    @Override
    public void remove(int id) {
        funDao.remove(id);
    }

    @Override
    public void altera(Object funcionario) {
        Funcionario fun = (Funcionario) funcionario;
        funDao.altera(fun);
    }

    @Override
    public Funcionario get(int id) {
        return funDao.get(id);
    }

    @Override
    public ArrayList<Funcionario> getAll() {
        return funDao.getAll();
    }

    @Override
    public ArrayList<String> valida(Object funcionario) {
        Funcionario fun = (Funcionario) funcionario;
        ArrayList<String> erros = new ArrayList();
        if (fun.getNome().isEmpty()) {
            erros.add("Nome");
        }
        if (fun.getCpf().isEmpty()) {
            erros.add("CPF");
        }
        if (fun.getRg().isEmpty()) {
            erros.add("RG");
        }
        if (fun.getTelefoneM().isEmpty()) {
            erros.add("Telefone");
        }
        if (fun.getSexo().isEmpty()) {
            erros.add("Sexo");
        }
        // Endereço
        if (fun.getEndereco().getRua().isEmpty()) {
            erros.add("Rua");
        }
        if (fun.getEndereco().getNumero().isEmpty()) {
            erros.add("Número");
        }
        if (fun.getEndereco().getComplemento().isEmpty()) {
            erros.add("Complemento");
        }
        if (fun.getEndereco().getBairro().isEmpty()) {
            erros.add("Bairro");
        }
        if (fun.getEndereco().getCidade().isEmpty()) {
            erros.add("Cidade");
        }
        if (fun.getEndereco().getCep().isEmpty()) {
            erros.add("CEP");
        }
        // Login
        if (fun.getLogin().getUsuario().isEmpty()) {
            erros.add("Login");
        }
        if (fun.getLogin().getUsuario().isEmpty()) {
            erros.add("Senha");
        }
        return erros;
    }

    public DefaultComboBoxModel procurar(int tipo) {
        DefaultComboBoxModel cbArray = new DefaultComboBoxModel();
        cbArray.insertElementAt("Selecione...", 0);
        switch (tipo) {
            case 0:
                // Código
                for (Funcionario funcionario : getAll()) {
                    cbArray.addElement(funcionario.getCodigo() + " | " + funcionario.getNome());
                }
                break;
            case 1:
                // Nome
                for (Funcionario funcionario : getAll()) {
                    cbArray.addElement(funcionario.getNome() + " | " + funcionario.getCpf() + "| Código " + funcionario.getCodigo());
                }
                break;
            case 2:
                // CPF
                for (Funcionario funcionario : getAll()) {
                    cbArray.addElement(funcionario.getCpf() + " | " + funcionario.getNome());
                }
                break;
            case 3:
                // RG
                for (Funcionario funcionario : getAll()) {
                    cbArray.addElement(funcionario.getRg() + " | " + funcionario.getNome());
                }
                break;
        }

        return cbArray;
    }

    public ArrayList<String> validaUniques(Object funcionario) {
        Funcionario fun = (Funcionario) funcionario;
        ArrayList<String> erros = new ArrayList();
        for (Cliente cliente : new ClienteController().getAll()) {
            if (cliente.getCpf().equals(fun.getCpf())) {
                erros.add("CPF");
            }
            if (cliente.getRg().equals(fun.getRg())) {
                erros.add("RG");
            }
        }
        for (Funcionario funcionarios : getAll()) {
            if (funcionarios.getCpf().equals(fun.getCpf())) {
                erros.add("CPF");
            }
            if (funcionarios.getRg().equals(fun.getRg())) {
                erros.add("RG");
            }
            if (funcionarios.getLogin().getUsuario().equals(fun.getLogin().getUsuario())) {
                erros.add("Login");
            }
        }
        return erros;
    }

}
