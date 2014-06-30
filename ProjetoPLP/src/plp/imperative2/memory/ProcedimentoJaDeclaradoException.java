package plp.imperative2.memory;

import plp.expressions2.expression.Id;
import plp.expressions2.memory.IdentificadorJaDeclaradoException;

public class ProcedimentoJaDeclaradoException extends IdentificadorJaDeclaradoException {

  public ProcedimentoJaDeclaradoException(Id id) {
    super("Procedimento " + id + " já declarado.");
  }
  
}
