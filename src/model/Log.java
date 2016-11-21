package model;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Log {

    private int cod;
    private String descricao;
    private Timestamp data;
    private Funcionario funcionario;

    public Log(String descricao, Funcionario funcionario) {
        this.descricao = descricao;
        this.data = new Timestamp(System.currentTimeMillis());
        this.funcionario = funcionario;
    }

    @Id
    @GeneratedValue
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }


}
