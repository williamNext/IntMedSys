package intmedsys.model;

        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;

@Entity
public class InteracaoMedicamentosa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;
    String descricao;
    long idMedicamentoA;
    long idMedicamentob;

    public InteracaoMedicamentosa() {
    }
    public InteracaoMedicamentosa(String descricao,long idA,long idB) {
        this.idMedicamentoA =idA;
        this.idMedicamentob =idB;
        this.descricao  = descricao;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getIdMedicamentoA() {
        return idMedicamentoA;
    }

    public void setIdMedicamentoA(long idMedicamentoA) {
        this.idMedicamentoA = idMedicamentoA;
    }

    public long getIdMedicamentob() {
        return idMedicamentob;
    }

    public void setIdMedicamentob(long idMedicamentob) {
        this.idMedicamentob = idMedicamentob;
    }
}
