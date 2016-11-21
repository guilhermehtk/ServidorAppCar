package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;

@Entity
@DiscriminatorValue(value = "C")
public class Cliente extends Pessoa implements Serializable {

    private List<Carro> carros;
    private List<OrdemServico> ordens;

    public Cliente() {
        super();
    }

    public Cliente(String nome, String sexo, String email, String telefoneM, String telefoneF, String cpf, String rg, int tipo, Endereco endereco) {
        super(nome, cpf, sexo, email, telefoneM, telefoneF, endereco, rg);
    }

    @OneToMany(mappedBy = "dono", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<OrdemServico> getOrdens() {
        return ordens;
    }

    public void setOrdens(List<OrdemServico> ordens) {
        this.ordens = ordens;
    }

}
