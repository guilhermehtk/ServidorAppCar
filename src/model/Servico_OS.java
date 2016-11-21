package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Servico_OS {

    private int cod;
    private Servico servico;
    private OrdemServico ordemservico;
    private Funcionario funcionario;

    public Servico_OS(Servico servico, OrdemServico osCod, Funcionario mecCod) {
        this.servico = servico;
        this.ordemservico = osCod;
        this.funcionario = mecCod;
    }

    public Servico_OS() {

    }

    @Id
    @GeneratedValue
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @ManyToOne
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @ManyToOne
    public OrdemServico getOrdemservico() {
        return ordemservico;
    }

    public void setOrdemservico(OrdemServico ordemservico) {
        this.ordemservico = ordemservico;
    }

    @ManyToOne
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
