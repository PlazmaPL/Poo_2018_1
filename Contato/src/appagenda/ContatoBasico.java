package appagenda;

import java.util.ArrayList;

public class ContatoBasico {
	 private String nome;
     private ArrayList<Telefone>telefones;
     public ContatoBasico()
     {  nome="";
        telefones=new ArrayList();
     }
     public ContatoBasico(String nome)
     {  this.nome = nome;
         telefones=new ArrayList();
     }
     public String getNome() { return nome; }
     public void setNome(String nome) { this.nome = nome;}
     public void setTelefone(Telefonetf)
     {  telefones.add(tf);
     }
     public String getDados()
     {   String s=nome+"\n";
          for(Telefone t:telefones)
          {  s+=t.getTelefone()+"\n";
          }
          return s;
     }
}
