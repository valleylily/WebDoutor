package bancoDeDados.DBOs;

public class Sintoma  implements Comparable<Sintoma>, Cloneable  {
	private int    idSintoma;
	private String descricao;
	private String nome     ;
	private byte   grau     ;
	private final short TAMANHO_DESCRICAO = 100;
	private final byte  TAMANHO_NOME      = 20 ;

	//"Numa escala de assintomático à sintoma forte o quanto você se enquadrra com " + sintomaDaVez.getNome();
	
	public Sintoma(int idSintoma, String descricao, String nome, byte grau) throws Exception {
		this.setIdSintoma(idSintoma);
		this.setDescricao(descricao);
		this.setNome(nome);
		this.setGrau(grau);
	}
	
	private Sintoma(Sintoma antigoSintoma) {
		try {
			this.setIdSintoma(antigoSintoma.idSintoma);
			this.setDescricao(antigoSintoma.descricao);
			this.setNome(antigoSintoma.nome);
			this.setGrau(antigoSintoma.grau);
		}
		catch(Exception e){}
	}
	
	public int getIdSintoma() {return idSintoma;}
	
	public String getDescricao() {return descricao;}
	
	public byte getGrau() {return grau;}
	
	public String getNome() {return nome;}
	
	public void setIdSintoma(int idSintoma) throws Exception {
		if(idSintoma <= 0)
			throw new Exception("O identificador do sintoma n�o pode ser nulo ou negativo!!");
		
		this.idSintoma = idSintoma;
	}
	
	public void setDescricao(String descricao) throws Exception {
		if(descricao == "")
			throw new Exception("A descri��o do sintoma n�o pode ser nulo!");
		if(descricao.length() > TAMANHO_DESCRICAO)
			throw new Exception("A descri��o do sintoma n�o ser t�o longo!");
			
		this.descricao = descricao;
	}
	
	public void setNome(String nome) throws Exception {
		if(nome == "")
			throw new Exception("O nome do sintoma n�o pode ser nulo!");
		if(nome.length() > TAMANHO_NOME)
			throw new Exception("O nome do sintoma n�o ser t�o longo!");
			
		this.nome = nome;
	}
	
	public void setGrau(byte grau) throws Exception {
		if(grau <= 0)
			throw new Exception("A gravidade de um sintoma deve ser positiva.");
		
		this.grau = grau;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int hashCode() {
		int hashCode = -66;
		hashCode += 13 * new Integer(idSintoma).hashCode();
		hashCode += 13 * descricao.hashCode();
		hashCode += 13 * nome.hashCode();
		hashCode += 13 * new Byte(grau).hashCode();
		
		return hashCode;
	}
	
	@Override
	public String toString() {
		String retorno = "Sintoma: ";
		retorno += "\nId: "         + idSintoma;
		retorno += "\nDescri��o: "  + descricao;
		retorno += "\nNome: "       + nome     ;
		retorno += "\nGrau: "       + grau     ;
		
		return retorno;
	}
	
	@Override
	public boolean equals(Object objetoAComparar) {
		if(objetoAComparar == null)
			return false;
		if(!(objetoAComparar instanceof Sintoma))
			return false;
		
		Sintoma sintomaAComparar = (Sintoma) objetoAComparar;
		if(this.idSintoma != sintomaAComparar.idSintoma || this.descricao != sintomaAComparar.descricao
				|| this.nome != sintomaAComparar.nome || this.grau != sintomaAComparar.grau)
			return false;
		
		return true;
	}
	
	public Object clone() {return new Sintoma(this);}

	public int compareTo(Sintoma sintomaAComparar) {
		int idSintomaAComparar = sintomaAComparar.idSintoma;
		int idSintomaThis = this.idSintoma;
		
		if(idSintomaThis > idSintomaAComparar)
			return 1;
		if(idSintomaThis < idSintomaAComparar)
			return -1;
		return 0;
	}
}
