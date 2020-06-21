package intmedsys.utils.alerts;

public enum AlertMessages {
    MESSAGE_NO_INTERACTION("Não há interação entre estes medicamentos."),
    MESSAGE_HAS_INTERACTION("Existem interações entre estes medicamentos."),
    MESSAGE_BLANK_SPACE("Os campos de buscas não podem estar vazios."),
    MESSAGE_SUCESS_SAVE_INTERACTION("A interação foi salva com sucesso"),
    MESSAGE_ALREADY_HAS_INTERACTION("Já existe uma interação registrada entre estes dois medicamentos na base de dados"),
    MESSAGE_SAME_NAME_FAIL("Não é possivel salvar a interação entre medicamentos com o mesmo nome"),
    MESSAGE_NOT_FOUUND("O nome do medicamento está incorreto, ou este medicamento não existe nos registros. Não se esqueça de verificar acentos e erros ortográficos");

    private String message;

    AlertMessages(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
