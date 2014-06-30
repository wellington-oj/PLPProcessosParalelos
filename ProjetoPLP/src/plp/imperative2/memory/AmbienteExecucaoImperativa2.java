package plp.imperative2.memory;


import plp.expressions2.expression.Id;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative2.memory.Procedimento;
import plp.imperative2.memory.ProcedimentoJaDeclaradoException;
import plp.imperative2.memory.ProcedimentoNaoDeclaradoException;

public interface AmbienteExecucaoImperativa2 extends AmbienteExecucaoImperativa {

    public void mapProcedimento(Id idArg, Procedimento procedimentoId) throws ProcedimentoJaDeclaradoException;
    public Procedimento getProcedimento(Id idArg) throws ProcedimentoNaoDeclaradoException;

}
