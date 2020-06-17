package intmedsys.model;

public enum AlertMessages {
    MESSAGE_NO_INTERACTION("Não há interação entre estes medicamentos."),
    MESSAGE_BLANK_SPACE("Os campos de buscas não podem estar vazios."),
    MESSAGE_NOT_FOUUND("O nome do medicamento está incorreto, ou este medicamento não existe nos registros. Não se esqueça de verificar acentos e erros ortográficos");

    private String message;

    AlertMessages(String msg){
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
