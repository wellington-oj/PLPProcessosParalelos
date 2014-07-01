package plp.imperative1.memory;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Id;
import plp.expressions2.memory.*;
import plp.imperative2.extensao.IdCanal;
import plp.imperative2.extensao.ControleCanal;

public interface AmbienteCompilacaoImperativa extends AmbienteCompilacao {

	public Tipo getTipoEntrada() throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
		EntradaVaziaException;

}
