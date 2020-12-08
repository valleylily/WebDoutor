package bancoDeDados.DBOs;

public class DoencaSintoma implements Comparable<DoencaSintoma>, Cloneable{
	private int idDoenca ;
	private int idSintoma;
	
	public DoencaSintoma(int idDoenca, int idSintoma) throws Exception {
		this.setIdSintoma(idSintoma);
		this.setIdDoenca(idDoenca);
	}
	
	private DoencaSintoma(DoencaSintoma antigoSintoma) {
		try {
			this.setIdSintoma(antigoSintoma.idSintoma);
			this.setIdDoenca(antigoSintoma.idDoenca);
		}
		catch(Exception e){}
	}
	
	public int getIdDoenca() {return idDoenca;}
	public int getIdSintoma() {return idSintoma;}
	
	public void setIdDoenca(int idDoenca) throws Exception {
		if(idDoenca <= 0)
			throw new Exception("O id de uma doença deve ser positivo.");
		this.idDoenca = idDoenca;
	}
	public void setIdSintoma(int idSintoma) throws Exception {
		if(idSintoma <= 0)
			throw new Exception("O identificador do sintoma não pode ser nulo ou negativo!!");
		this.idSintoma = idSintoma;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int hashCode() {
		int hashCode = -66;
		hashCode += 13 * new Integer(idDoenca).hashCode();
		hashCode += 13 * new Integer(idSintoma).hashCode();
		
		return hashCode;
	}
	
	@Override
	public String toString() {
		String retorno = "Par: ";
		retorno += "\nId da Doença: "  + idDoenca ;
		retorno += "\nId do Sintoma: " + idSintoma;
		
		return retorno;
	}
	
	@Override
	public boolean equals(Object objetoAComparar) {
		if(objetoAComparar == null)
			return false;
		if(!(objetoAComparar instanceof DoencaSintoma))
			return false;
		
		DoencaSintoma doencaSintomaAComparar = (DoencaSintoma) objetoAComparar;
		if(this.idDoenca != doencaSintomaAComparar.idDoenca || this.idSintoma != doencaSintomaAComparar.idSintoma)
			return false;
		
		return true;
	}
	
	public Object clone() {return new DoencaSintoma(this);}

	@Override
	public int compareTo(DoencaSintoma doencaSintomaAComparar) {
		int idDoencaAComparar  = doencaSintomaAComparar.idDoenca ;
		int idSintomaAComparar = doencaSintomaAComparar.idSintoma;
		int idMultiplicado = idDoencaAComparar * idSintomaAComparar;
		int idUsuarioThis = this.idDoenca * this.idSintoma;
			
		if(idUsuarioThis > idMultiplicado)
			return 1;
		if(idUsuarioThis < idMultiplicado)
			return -1;
		return 0;
	}
}
