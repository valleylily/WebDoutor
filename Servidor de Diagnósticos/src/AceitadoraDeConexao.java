 import java.io.IOException;
import java.net.*;
import java.util.*;
//Thread que conecta novos clientes
public class AceitadoraDeConexao extends Thread
{
    private ServerSocket        pedido;
    private ArrayList<Parceiro> usuarios;
    

    public AceitadoraDeConexao
    (int porta, ArrayList<Parceiro> usuarios)
    throws Exception
    {
        if (porta==0)
            throw new Exception ("Porta ausente");

        try
        {
            this.pedido =
            new ServerSocket (porta);
        }
        catch (Exception  erro)
        {
            throw new Exception ("Porta invalida");
        }

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.usuarios = usuarios;
    }
    private boolean vivo = true;
    SupervisoraDeConexao supervisoraDeConexao=null;
    
    public void run ()
    {
        for(;vivo;)
        {
            Socket conexao=null;
            try
            {
                conexao = this.pedido.accept();

                System.out.println("Conexão criada");
            }
            catch (Exception erro)
            {
                continue;
            }

            try
            {
                supervisoraDeConexao =
                new SupervisoraDeConexao (conexao, usuarios);

                System.out.println("Supervisora criada");
            }
            catch (Exception erro)
            {} // sei que passei parametros corretos para o construtor
            if(vivo)
            	supervisoraDeConexao.start();
        }

    }
    
    public void morra() {
    	if(supervisoraDeConexao != null)
    		supervisoraDeConexao.morra();
    	vivo = false;
    	if(pedido == null)
			try {
				pedido.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
}
