package plp.imperative1.memory;

import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.*;

public interface AmbienteExecucaoImperativa extends AmbienteExecucao {

    public void changeValor(Id idArg, Valor valorId) 
        throws VariavelNaoDeclaradaException;  
    public Valor read() 
        throws EntradaVaziaException;
    public void write(Valor v);
    public ListaValor getSaida();
    public Object clone();
}
