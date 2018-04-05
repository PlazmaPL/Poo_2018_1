package appagenda;

import java.util.Calendar;

public class Econtato {
	protected String email,homepage;
     public void EContato(String nome, Calendar dtnasc, String email, String hp)
         this.email=email;
         homepage=hp;
    }
     public String getDados() {
       return super.getDados()+"email: "+email+"\nhomepage:"+homepage+"\nIdade: ";
     }
     public String getEmail() {
       return email;
     }
     public String getHomepage() {
        return homepage;
     }
}
