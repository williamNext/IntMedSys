package intmedsys.utils.screen;

public enum ScreenPath {
    BUSCA_MEDICAMENTO("/fxml/buscarMedicamentos.fxml"),
    BUSCA_AVANCADA("/fxml/buscaAvancada.fxml"),
    ADICIONA_INTERACAO("/fxml/adicionaInteracao.fxml");

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
