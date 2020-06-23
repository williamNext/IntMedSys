package intmedsys.utils.alerts;

public enum AlertMessages {
    MESSAGE_NO_INTERACTION("Não há interação entre estes medicamentos."),
    MESSAGE_HAS_INTERACTION("Existem interações entre estes medicamentos."),
    MESSAGE_BLANK_SPACE("Nenhum dos campos podem estar vazios."),
    MESSAGE_SUCESS_SAVE_INTERACTION("A interação foi salva com sucesso"),
    MESSAGE_ALREADY_HAS_INTERACTION("Já existe uma interação registrada entre estes dois medicamentos na base de dados"),
    MESSAGE_SAME_NAME_FAIL("Não é possivel salvar a interação entre medicamentos com o mesmo nome"),
    MESSAGE_SAME_MED_FAIL("Já existe um medicamento com este nome na base de dados"),
    MESSAGE_SAVE_MED_CONFIRMATION("Você tem certeza que quer salvar este medicamento?"),
    MESSAGE_SAVE_MED_SUCESS("Medicamento salvo com sucesso"),
    MESSAGE_CONFIRM_MED_DELETE("Você tem certeza que quer deletar este medicamento?"),
    MESSAGE_MED_DELETE_SUCESS("Medicamento deletado com sucesso"),
    MESSAGE_NOT_FOUUND("O nome do medicamento está incorreto, ou este medicamento não existe nos registros. Não se esqueça de verificar acentos e erros ortográficos");

    private String message;

    AlertMessages(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
