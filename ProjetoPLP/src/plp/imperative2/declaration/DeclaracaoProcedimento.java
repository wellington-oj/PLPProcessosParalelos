package plp.imperative2.declaration;

import plp.expressions2.expression.Id;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.declaration.Declaracao;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;
import plp.imperative2.memory.AmbienteCompilacaoImperativa2;
import plp.imperative2.memory.AmbienteExecucaoImperativa2;
import plp.imperative2.memory.Procedimento;

public class DeclaracaoProcedimento extends Declaracao<Id, DefProcedimento> {

	private ListaDeclaracaoParametro parametrosFormais;

	public DeclaracaoProcedimento(Id nome, DefProcedimento defProcedimento) {
		super(nome, defProcedimento);
		this.parametrosFormais = defProcedimento.getParametrosFormais();
	}

	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		((AmbienteExecucaoImperativa2) ambiente).mapProcedimento(parametro1,new Procedimento(parametrosFormais, parametro2));
		return ambiente;
	}

	public boolean checaTipo(AmbienteCompilacaoImperativa amb)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		boolean resposta;
		AmbienteCompilacaoImperativa2 ambiente = (AmbienteCompilacaoImperativa2) amb;
		if (parametrosFormais.checaTipo(ambiente)) {
			ambiente.mapParametrosProcedimento(parametro1, parametrosFormais);
			ambiente.incrementa();
			ambiente = parametrosFormais.declaraParametro(ambiente);
			resposta = parametro2.getComando().checaTipo(ambiente);
			ambiente.restaura();
		} else {
			resposta = false;
		}
		return resposta;
	}
}
