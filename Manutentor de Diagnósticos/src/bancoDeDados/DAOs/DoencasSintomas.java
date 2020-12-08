package bancoDeDados.DAOs;
import bancoDeDados.DBOs.DoencaSintoma;
import bd.BDSQLServer;
import bd.MeuResultSet;

public class DoencasSintomas {
	public static DoencaSintoma getDoencaSintoma(int idDoenca, int idSintoma) throws Exception 
    {
		DoencaSintoma DoencaSintoma = null;
        try 
        {
                String sql = "select * from DoencaSintoma where idDoenca = ? and idSintoma = ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setInt(1, idDoenca);
                BDSQLServer.COMANDO.setInt(2, idSintoma);
                MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
                if(!resultado.first())
                        throw new Exception("Doen�a n�o cadastrada.");
     
                DoencaSintoma = new DoencaSintoma(resultado.getInt("idDoenca"),
                      	            			  resultado.getInt("idSintoma"));
        }
        catch(Exception ex) 
        {
                throw new Exception("Erro ao procurar sintoma relacionado a doen�a.");
        }
        return DoencaSintoma;
    }
    
    public static boolean registrado(int idDoenca, int idSintoma) throws Exception
    {
            boolean retorno = false;
            try 
            {
                    String sql;
                    sql = "select * from DoencaSintoma where idDoenca = ? and idSintoma = ?";
                    BDSQLServer.COMANDO.prepareStatement(sql);
                    BDSQLServer.COMANDO.setInt(1, idDoenca);
                    BDSQLServer.COMANDO.setInt(2, idSintoma);
                    MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
                    retorno = resultado.first();
            }
            catch(Exception ex) 
            {
                    throw new Exception("Erro ao verificar DoencaSintoma");
            }

            return retorno;
    }
    
    public static void alterar(DoencaSintoma DoencaSintoma) throws Exception
    {/*N�o tem porque criar um m�todo para alterar uma tabela de liga��o,
    	sendo que ela consiste apenas em pares de valores unique.*/}
    
    public static void incluir(DoencaSintoma DoencaSintoma) throws Exception
    {
            if(DoencaSintoma == null)
                    throw new Exception("Doen�aSintoma a incluir vazia.");
            if(DoencasSintomas.registrado(DoencaSintoma.getIdDoenca(), DoencaSintoma.getIdSintoma()))
                    throw new Exception("Doen�aSintoma j� registrada");
            try 
            {
                    String sql;
                    sql = "insert into DoencaSintoma values(?, ?)";
                    BDSQLServer.COMANDO.prepareStatement(sql);
                    BDSQLServer.COMANDO.setInt(1, DoencaSintoma.getIdDoenca());
                    BDSQLServer.COMANDO.setInt(2, DoencaSintoma.getIdSintoma());
                    BDSQLServer.COMANDO.executeUpdate();
                    BDSQLServer.COMANDO.commit();
            }
            catch(Exception ex) 
            {
                    throw new Exception("Erro ao incluir doen�a.");
            }
    }
    
    public static void deletar(int idDoenca, int idSintoma) throws Exception
    {
        if(!DoencasSintomas.registrado(idDoenca, idDoenca))
            throw new Exception("Doen�a n�o registrada");
        try 
        {
                String sql;
                sql = "delete from DoencaSintoma where idDoenca = ? and idSintoma = ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setInt(1, idDoenca);
                BDSQLServer.COMANDO.setInt(2, idSintoma);
                BDSQLServer.COMANDO.executeUpdate();
                BDSQLServer.COMANDO.commit();
        }
        catch(Exception ex)
        {
                throw new Exception("Erro ao deletar Doen�aSintoma.");
        }
    }
}
