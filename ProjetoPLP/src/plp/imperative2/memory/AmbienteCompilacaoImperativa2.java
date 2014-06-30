package plp.imperative2.memory;

import plp.expressions2.expression.Id;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative2.declaration.ListaDeclaracaoParametro;
import plp.imperative2.memory.ProcedimentoJaDeclaradoException;
import plp.imperative2.memory.ProcedimentoNaoDeclaradoException;

public interface AmbienteCompilacaoImperativa2 extends AmbienteCompilacaoImperativa {

    public void mapParametrosProcedimento(Id idArg, ListaDeclaracaoParametro parametrosId) throws ProcedimentoJaDeclaradoException;
    public ListaDeclaracaoParametro getParametrosProcedimento(Id idArg) throws ProcedimentoNaoDeclaradoException;  
}
