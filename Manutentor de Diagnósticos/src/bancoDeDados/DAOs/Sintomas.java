package bancoDeDados.DAOs;
import bancoDeDados.DBOs.Sintoma;
import bd.BDSQLServer;
import bd.MeuResultSet;

public class Sintomas {
	public static Sintoma getSintoma(int idSintoma) throws Exception 
    {
		Sintoma Sintoma = null;
        try 
        {
                String sql = "select * from Sintoma where idSintoma = ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setInt(1, idSintoma);
                MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
                if(!resultado.first())
                        throw new Exception("Sintoma não cadastrado.");
     
                Sintoma = new Sintoma(idSintoma, 
                					resultado.getString("descricao"),
                                    resultado.getString("nome"),
                      	            resultado.getByte("grau"));
        }
        catch(Exception ex) 
        {
                throw new Exception("Erro ao procurar doença.");
        }
        return Sintoma;
    }
    
    public static boolean registrado(int idSintoma) throws Exception
    {
            boolean retorno = false;
            try 
            {
                    String sql;
                    sql = "select * from Sintoma where idSintoma = ?";
                    BDSQLServer.COMANDO.prepareStatement(sql);
                    BDSQLServer.COMANDO.setInt(1,idSintoma);
                    MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
                    retorno = resultado.first();
            }
            catch(Exception ex) 
            {
                    throw new Exception("Erro ao verificar sintoma");
            }

            return retorno;
    }
    
    public static void alterar(Sintoma Sintoma) throws Exception
    {
            if(Sintoma == null)
                    throw new Exception("Não é possível alterar um sintoma vazio.");

            try 
            {
                    String sql;
                    sql = "update Sintoma set descricao = ?, nome = ?, grau = ? where idSintoma = ?";

                    BDSQLServer.COMANDO.prepareStatement(sql);
                    BDSQLServer.COMANDO.setString(1, Sintoma.getDescricao());
                    BDSQLServer.COMANDO.setString(2, Sintoma.getNome());
                    BDSQLServer.COMANDO.setByte(3, Sintoma.getGrau());
                    BDSQLServer.COMANDO.setInt(4, Sintoma.getIdSintoma());

                    
                    BDSQLServer.COMANDO.executeUpdate();
                    BDSQLServer.COMANDO.commit();
            }
            catch(Exception ex) 
            {
            		ex.printStackTrace();
                    throw new Exception("Erro ao alterar os dados.");
            }
    }
    
    public static void incluir(Sintoma Sintoma) throws Exception
    {
            if(Sintoma == null)
                    throw new Exception("Sintoma a incluir vazio.");
            if(Sintomas.registrado(Sintoma.getIdSintoma()))
                    throw new Exception("Sintoma já registrado.");
            try 
            {
                    String sql;
                    sql = "insert into Sintoma values(?, ?, ?, ?)";
                    BDSQLServer.COMANDO.prepareStatement(sql);
                    BDSQLServer.COMANDO.setInt(1, Sintoma.getIdSintoma());
                    BDSQLServer.COMANDO.setString(2, Sintoma.getDescricao());
                    BDSQLServer.COMANDO.setString(3, Sintoma.getNome());
                    BDSQLServer.COMANDO.setByte(4, Sintoma.getGrau());
                    BDSQLServer.COMANDO.executeUpdate();
                    BDSQLServer.COMANDO.commit();
            }
            catch(Exception ex) 
            {
                    throw new Exception("Erro ao incluir doença.");
            }
    }
    
    public static void deletar(int idSintoma) throws Exception
    {
        if(0 > idSintoma)
            throw new Exception("Sintoma inválido");
        if(!Sintomas.registrado(idSintoma))
            throw new Exception("Sintoma não registrado");
        try 
        {
                String sql;
                sql = "delete from Sintoma where idSintoma = ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setInt(1, idSintoma);
                BDSQLServer.COMANDO.executeUpdate();
                BDSQLServer.COMANDO.commit();
        }
        catch(Exception ex) 
        {
                throw new Exception("Erro ao deletar sintoma.");
        }
    }
}
