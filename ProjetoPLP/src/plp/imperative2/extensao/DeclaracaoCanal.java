package plp.imperative2.extensao;

import plp.expressions1.expression.ValorInteiro;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;
import plp.expressions2.memory.IdentificadorNaoDeclaradoException;
import plp.imperative1.declaration.Declaracao;
import plp.imperative1.memory.AmbienteCompilacaoImperativa;
import plp.imperative1.memory.AmbienteExecucaoImperativa;
import plp.imperative1.memory.EntradaVaziaException;

public class DeclaracaoCanal extends Declaracao{

	private Id id;
	
	public DeclaracaoCanal(Id id) {
		this.setId(id);
	}

	@Override
	public AmbienteExecucaoImperativa elabora(
			AmbienteExecucaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		ambiente.map(getId(), (Valor) new ValorInteiro(0));
		return ambiente;
	}

	@Override
	public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
			throws IdentificadorJaDeclaradoException,
			IdentificadorNaoDeclaradoException, EntradaVaziaException {
		return true;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}
}
