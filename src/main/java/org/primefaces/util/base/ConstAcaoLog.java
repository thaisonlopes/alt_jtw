package org.primefaces.util.base;

public class ConstAcaoLog {

    public static final String PESQUISA = "pesquisa", ACAO = "ação", INSERCAO = "inserção", ALTERACAO = "alteração",
            EXCLUSAO = "exclusão", SALVAMENTO = "salvamento", PROCESSAMENTO = "processamento", IMPRESSAO = "impressão";

    public static boolean eAcaoValida(String acao) {
        return acao.equals(PESQUISA) || acao.equals(ACAO) || acao.equals(INSERCAO) || acao.equals(ALTERACAO)
                || acao.equals(EXCLUSAO) || acao.equals(SALVAMENTO) || acao.equals(PROCESSAMENTO) || acao.equals(IMPRESSAO);
    }

    public static String[] getArrayAcoes() {
        return new String[]{PESQUISA, ACAO, INSERCAO, ALTERACAO, EXCLUSAO, SALVAMENTO, PROCESSAMENTO, IMPRESSAO};
    }
}