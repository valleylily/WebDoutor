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
                        throw new Exception("Doen�a n�o cadastrada.");
     
                Doenca = new Doenca(idDoenca, 
                                    resultado.getString("nome"),
                      	            resultado.getByte("gravidade"));
        }
        catch(Exception ex) 
        {
                throw new Exception("Erro ao procurar doen�a.");
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
                    throw new Exception("N�o � poss�vel alterar uma doen�a vazia.");

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
                    throw new Exception("Doe�a a incluir vazia.");
            if(Doencas.registrado(Doenca.getIdDoenca()))
                    throw new Exception("Doen�a j� registrada.");
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
                    throw new Exception("Erro ao incluir doen�a.");
            }
    }
    
    public static void deletar(int idDoenca) throws Exception
    {
        if(0 > idDoenca)
            throw new Exception("Doen�a inv�lida");
        if(!Doencas.registrado(idDoenca))
            throw new Exception("Doen�a n�o registrada");
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
                throw new Exception("Erro ao deletar doen�a.");
        }
    }
}
