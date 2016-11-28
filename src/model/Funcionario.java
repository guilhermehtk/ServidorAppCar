package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;

@Entity
@DiscriminatorValue(value = "F")
public class Funcionario extends Pessoa implements Serializable {

    private Login login;
    private List<Servico_OS> servicosRealizados;

    public Funcionario(String nome, String sexo, String email, String telefoneM, String telefoneF, String cpf, String rg, Endereco endereco, Login login) {
        super(nome, cpf, sexo, email, telefoneM, telefoneF, endereco, rg);
        this.login = login;
    }

    public Funcionario() {
        super();
    }

    @OneToOne(cascade = {CascadeType.ALL})
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @OneToMany(mappedBy = "funcionario", cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<Servico_OS> getServicosRealizados() {
        return servicosRealizados;
    }

    public void setServicosRealizados(List<Servico_OS> servicosRealizados) {
        this.servicosRealizados = servicosRealizados;
    }

}
