import java.io.*;
import java.net.*;
import java.util.*;

import bd.Desenho;
import bd.Desenhos;
import bd.Figura;
import bd.Figuras;

public class SupervisoraDeConexao extends Thread
{
    private Vector<String>         valor;
    private Parceiro             usuario;
    private Socket               conexao;
    private ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao
    (Socket conexao, ArrayList<Parceiro> usuarios)
    throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.conexao  = conexao;
        this.usuarios = usuarios;
    }
    private boolean vivo = true;

    public void run ()
    {

        ObjectOutputStream transmissor;
        try
        {
            transmissor =
            new ObjectOutputStream(
            this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            return;
        }
        
        ObjectInputStream receptor=null;
        try
        {
            receptor=
            new ObjectInputStream(
            this.conexao.getInputStream());
        }
        catch (Exception err0)
        {
            try
            {
                transmissor.close();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread
            
            return;
        }

        try
        {
            this.usuario =
            new Parceiro (this.conexao,
                          receptor,
                          transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos
        
        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add (this.usuario);
            }


            for(;vivo;) {
            	Comunicado comunicado = usuario.envie();
            	if (comunicado == null)
                    return;
//            	else if(comunicado instanceof PedidoDeSalvamento) {
//                    System.out.println("Salvamento recebido");
//            		PedidoDeSalvamento pedido = (PedidoDeSalvamento) comunicado;
//            		System.out.println("Comunicado:\n" + pedido + "\n lido");
//            		String email = pedido.getEMailDoDono();
//            		String nome = pedido.getNome();
//            		Desenho desenho = new Desenho(email, nome);
//            		Desenhos.incluir(desenho);
//            		
//            		Desenho desenhoGuardado = Desenhos.getDesenho(email, nome);
//            		System.out.println("Desenho:\n"
//            				+ desenhoGuardado
//            				+ "\nlido");
//            		long idDesenho = desenhoGuardado.getIdDesenho();
//            		Vector<String> figuras = pedido.getFiguras();
//            		
//            		for(int i = 0 ; i < figuras.size() ; i++) {
//            			bd.Figura figura = new bd.Figura(idDesenho, figuras.elementAt(i), i+1);
//            			Figuras.incluir(figura);
//            		}
//            	}
//            	else if(comunicado instanceof PedidoDeDesenhoSalvo) {
//                    System.out.println("Abertura recebida");
//            		PedidoDeDesenhoSalvo pedido = (PedidoDeDesenhoSalvo) comunicado;
//            		System.out.println("Comunicado:\n" + pedido + "\n lido");
//            		Desenho desenho = Desenhos.getDesenho(pedido.getEMailDoDono(), pedido.getNome());
//            		System.out.println("Desenho:\n"
//            				+ desenho
//            				+ "\npronto para ser enviado");
//            		long idDesenho = desenho.getIdDesenho();
//            		Vector<String> figuras = new Vector<String>();
//            		for(int i = 0;;i++) {
//            			if(!Figuras.registrado(i+1, idDesenho))
//            				break;
//            			Figura figura = Figuras.getFigura(i+1, idDesenho);
//            			
//            			figuras.add(figura.getFigura());
//            		}
//            		usuario.receba(new DesenhoSalvo(figuras));
//
//                    System.out.println("Desenho enviado");
//            	}
            }
            this.usuario.adeus();
        }
        catch(Exception erro) {
        	erro.printStackTrace();
        	try {
        		transmissor.close();
        		receptor.close();
        	}
        	catch(Exception falha) {}
        	return;
        }
    }
    public void morra() {
    	vivo = false;
    }
}
