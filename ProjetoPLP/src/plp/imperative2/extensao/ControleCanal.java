package plp.imperative2.extensao;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative2.command.ListaExpressao;
import plp.imperative1.command.*;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;

public abstract class ControleCanal implements Comando{

	@Override
	public abstract AmbienteExecucaoImperativa executar(
			AmbienteExecucaoImperativa ambiente)
					throws IdentificadorJaDeclaradoException,
					IdentificadorNaoDeclaradoException, EntradaVaziaException ;

	@Override
	public abstract boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException;


}
