package bancoDeDados.DBOs;

public class Doenca implements Comparable<Doenca>, Cloneable{
	private int    idDoenca ;
	private String nome     ;
	private byte   gravidade;
	
	public Doenca (int idDoenca, String nome, byte gravidade) throws Exception {
		this.setIdDoenca(idDoenca);
		this.setNome(nome);
		this.setGravidade(gravidade);
	}
	
	private Doenca (Doenca aCopiar){
		try {
			this.setIdDoenca(aCopiar.idDoenca);
			this.setNome(aCopiar.nome);
			this.setGravidade(aCopiar.gravidade);
		} catch (Exception e) {/*Não vai dar erro. This não é nulo*/}
	}
	
	public int getIdDoenca() {return idDoenca;}
	public String getNome() {return nome;}
	public byte getGravidade() {return gravidade;}
	
	public void setIdDoenca(int idDoenca) throws Exception {
		if(idDoenca <= 0)
			throw new Exception("O id de uma doença deve ser positivo.");
		this.idDoenca = idDoenca;
	}
	public void setNome(String nome) throws Exception {
		if(nome == "")
			throw new Exception("Uma doença não pode ter nome vazio.");
		if(nome.length() > 30)
			throw new Exception("O nome da doença não pode ter mais de 30 caracteres.");
		this.nome = nome;
	}
	public void setGravidade(byte gravidade) throws Exception {
		if(idDoenca <= 0)
			throw new Exception("A gravidade de uma doença deve ser positiva.");
		this.gravidade = gravidade;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int hashCode() {
		int hashCode = -66;
		hashCode += 13 * new Integer(idDoenca).hashCode();
		hashCode += 13 * nome.hashCode();
		hashCode += 13 * new Byte(gravidade).hashCode();
		
		return hashCode;
	}
	
	@Override
	public String toString() {
		String retorno = "Doença: ";
		retorno += "\nId: "        + idDoenca ;
		retorno += "\nNome: "      + nome     ;
		retorno += "\nGravidade: " + gravidade;
		
		return retorno;
	}
	
	@Override
	public boolean equals(Object objetoAComparar) {
		if(objetoAComparar == null)
			return false;
		if(!(objetoAComparar instanceof Doenca))
			return false;
		
		Doenca doencaAComparar = (Doenca) objetoAComparar;
		if(this.idDoenca != doencaAComparar.idDoenca || this.nome != doencaAComparar.nome
				|| this.gravidade != doencaAComparar.gravidade)
			return false;
		
		return true;
	}
	
	public Object clone() {return new Doenca(this);}

	public int compareTo(Doenca doencaAComparar) {
		int idDoencaAComparar = doencaAComparar.idDoenca;
		int idUsuarioThis = this.idDoenca;
		
		if(idUsuarioThis > idDoencaAComparar)
			return 1;
		if(idUsuarioThis < idDoencaAComparar)
			return -1;
		return 0;
	}
}
