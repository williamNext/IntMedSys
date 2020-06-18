package intmedsys.model;

import org.springframework.stereotype.Component;


public class MedicamentoDTO {
    private String name;

    public MedicamentoDTO(Medicamento med) {
        this.name = med.getNome();
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }
}
