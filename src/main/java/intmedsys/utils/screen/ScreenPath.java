package intmedsys.utils.screen;

public enum ScreenPath {
    BUSCA_MEDICAMENTO("/fxml/buscarMedicamentos.fxml"),
    BUSCA_AVANCADA("/fxml/buscaAvancada.fxml"),
    ADICIONA_MEDICAMENTO("/fxml/adicionaMedicamento.fxml"),
    ADICIONA_INTERACAO("/fxml/adicionaInteracao.fxml"),
    REMOVE_INTERACAO("/fxml/removeInteracao.fxml"),
    REMOVE_MEDICAMENTO("/fxml/removeMedicamento.fxml"),
    EDITA_INTERACAO("/fxml/editarInteracao.fxml");

    private String path;

    ScreenPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
