package bancoDeDados.DAOs;
import bancoDeDados.DBOs.Doenca;
import bd.BDSQLServer;
import bd.MeuResultSet;

public class Doencas {
	public static Doenca getDoenca(int idDoenca) throws Exception 
    {
		Doenca Doenca = null;
        try 
        {
                String sql = "select * from Doenca where idDoenca = ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setInt(1, idDoenca);
                MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
                if(!resultado.first())
                        throw new Exception("Doença não cadastrada.");
     
                Doenca = new Doenca(idDoenca, 
                                    resultado.getString("nome"),
                      	            resultado.getByte("gravidade"));
        }
        catch(Exception ex) 
        {
                throw new Exception("Erro ao procurar doença.");
        }
        return Doenca;
    }
    
    public static boolean registrado(int idDoenca) throws Exception
    {
            boolean retorno = false;
            try 
            {
                    String sql;
                    sql = "select * from Doenca where idDoenca = ?";
                    BDSQLServer.COMANDO.prepareStatement(sql);
                    BDSQLServer.COMANDO.setInt(1,idDoenca);
                    MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
                    retorno = resultado.first();
            }
            catch(Exception ex) 
            {
                    throw new Exception("Erro ao verificar doenca");
            }

            return retorno;
    }
    
    public static void alterar(Doenca Doenca) throws Exception
    {
            if(Doenca == null)
                    throw new Exception("Não é possível alterar uma doença vazia.");

            try 
            {
                    String sql;
                    sql = "update Doenca set nome = ?, gravidade = ? where idDoenca = ?";

                    BDSQLServer.COMANDO.prepareStatement(sql);
                    BDSQLServer.COMANDO.setString(1, Doenca.getNome());
                    BDSQLServer.COMANDO.setByte(2, Doenca.getGravidade());
                    BDSQLServer.COMANDO.setInt(3, Doenca.getIdDoenca());

                    
                    BDSQLServer.COMANDO.executeUpdate();
                    BDSQLServer.COMANDO.commit();
            }
            catch(Exception ex) 
            {
            		ex.printStackTrace();
                    throw new Exception("Erro ao alterar os dados");
            }
    }
    
    public static void incluir(Doenca Doenca) throws Exception
    {
            if(Doenca == null)
                    throw new Exception("Doeça a incluir vazia.");
            if(Doencas.registrado(Doenca.getIdDoenca()))
                    throw new Exception("Doença já registrada.");
            try 
            {
                    String sql;
                    sql = "insert into Doenca values(?, ?, ?)";
                    BDSQLServer.COMANDO.prepareStatement(sql);
                    BDSQLServer.COMANDO.setInt(1, Doenca.getIdDoenca());
                    BDSQLServer.COMANDO.setString(2, Doenca.getNome());
                    BDSQLServer.COMANDO.setByte(3, Doenca.getGravidade());
                    BDSQLServer.COMANDO.executeUpdate();
                    BDSQLServer.COMANDO.commit();
            }
            catch(Exception ex) 
            {
                    throw new Exception("Erro ao incluir doença.");
            }
    }
    
    public static void deletar(int idDoenca) throws Exception
    {
        if(0 > idDoenca)
            throw new Exception("Doença inválida");
        if(!Doencas.registrado(idDoenca))
            throw new Exception("Doença não registrada");
        try 
        {
                String sql;
                sql = "delete from Doenca where idDoenca = ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setInt(1, idDoenca);
                BDSQLServer.COMANDO.executeUpdate();
                BDSQLServer.COMANDO.commit();
        }
        catch(Exception ex) 
        {
                throw new Exception("Erro ao deletar doença.");
        }
    }
}
