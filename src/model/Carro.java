package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;

@Entity
public class Carro implements Serializable {

    private int cod;
    private String marca;
    private String modelo;
    private String cor;
    private String ano;
    private String chassi;
    private String km;
    private String placa;
    private String obs;
    private Cliente dono;
    private List<OrdemServico> ordens;

    public Carro() {

    }

    public Carro(int cod, String marca, String modelo, String cor, String ano, String chassi, String km, String placa, String obs, model.Cliente dono) {
        this.cod = cod;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.chassi = chassi;
        this.km = km;
        this.placa = placa;
        this.obs = obs;
        this.dono = dono;
    }

    @Id
    @GeneratedValue
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @ManyToOne
    public model.Cliente getDono() {
        return dono;
    }

    public void setDono(model.Cliente dono) {
        this.dono = dono;
    }

    @OneToMany(mappedBy="carro",cascade={CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<OrdemServico> getOrdens() {
        return ordens;
    }

    public void setOrdens(List<OrdemServico> ordens) {
        this.ordens = ordens;
    }
    
    

}
