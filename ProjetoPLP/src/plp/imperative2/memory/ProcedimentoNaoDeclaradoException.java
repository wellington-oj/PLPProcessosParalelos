package plp.imperative2.memory;

import plp.expressions2.expression.Id;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;

public class ProcedimentoNaoDeclaradoException extends IdentificadorJaDeclaradoException {

  public ProcedimentoNaoDeclaradoException(Id id) {
    super("Procedimento " + id + " nao declarado.");
  }
  
}
