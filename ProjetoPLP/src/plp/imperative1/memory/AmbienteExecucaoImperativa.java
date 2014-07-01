package plp.imperative1.memory;

import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.*;
import plp.imperative2.extensao.IdCanal;
import plp.imperative2.extensao.ControleCanal;

public interface AmbienteExecucaoImperativa extends AmbienteExecucao {

    public boolean pilhaCanalTemAlgo();
	public void changeValor(Id idArg, Valor valorId) 
        throws VariavelNaoDeclaradaException;  
    public Valor read() 
        throws EntradaVaziaException;
    public void write(Valor v);
    public ListaValor getSaida();
    public Object clone();
	public void mapCanal(IdCanal idArg, ControleCanal valor)  throws VariavelJaDeclaradaException;
	public ControleCanal getCanal(IdCanal args) throws VariavelNaoDeclaradaException;
	public void incrementaCanal();
	public void restauraCanal();
}
