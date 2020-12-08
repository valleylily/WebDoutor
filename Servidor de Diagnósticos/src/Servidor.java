import java.util.ArrayList;

public class Servidor
{
	public static String PORTA_PADRAO = "50327";
    
	public static void main (String[] args)
    {
	    boolean vivo = true;
	    AceitadoraDeConexao aceitadoraDeConexao = null;
    	
    	System.out.println("Rodando, irmão");
        if (args.length>1)
        {
            System.err.println ("Uso esperado: java Servidor [PORTA]\n");
            return;
        }

        int porta = Integer.parseInt(Servidor.PORTA_PADRAO);
        
        if (args.length==1)
            porta = Integer.parseInt(args[0]);

        ArrayList<Parceiro> usuarios =
        new ArrayList<Parceiro> ();

        try
        {
            aceitadoraDeConexao =
            new AceitadoraDeConexao (porta, usuarios);
            aceitadoraDeConexao.start();
            System.out.println("Aceitadora criada");
        }
        catch (Exception erro)
        {
            System.err.println (erro);
            System.err.println ("Escolha uma porta apropriada e liberada para uso!\n");
            return;
        }
        for(;vivo;) {
        	String mensagem = Teclado.getUmString();
        	if(mensagem.toUpperCase() == "MORRA") {        
                aceitadoraDeConexao.morra();
        		vivo = false;
        	}
        }
        System.exit(0);

    }
}
