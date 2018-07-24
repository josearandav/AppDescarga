package appDescarga;

import java.sql.Connection;
import java.util.HashMap;
import javax.swing.text.Document;
import org.jsoup.Jsoup;

public class Descarga {

	public static void main(String[] args) {
		
		//User agent para pasar por navegados en el svr
				final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" +  
				         "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";  
				//Variables
				String loginFormUrl = "https://app.machali.cl/manage/cms/form";  
				 String loginActionUrl = "https://app.machali.cl/manage/cms/form/";  
				 String username = "soporte@dnet.cl";  
				 String password = "P0o9i8u7"; 
				 
				 //HashMap para guardar form y cookies
				 HashMap<String, String> cookies = new HashMap<>();  
				 HashMap<String, String> formData = new HashMap<>(); 
				 
				 //Conexion de la URL y recuperacion de respuesta
				 Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(Connection.Method.GET).userAgent(USER_AGENT).execute();  
				 Document loginDoc = loginForm.parse(); 
				 
				 cookies.putAll(loginForm.cookies()); //Guardar las cookies
				 
				 String authToken = loginDoc.select("")  //Aca debe ir el select del token de seguridad
					      .first()  
					      .attr("value");  
				 
				 //Datos del formulario para enviar al servidor
				 formData.put("identification", "true");   
				 formData.put("login", username);  
				 formData.put("password", password);  
				 formData.put("authenticity_token", authToken); 
				 
				 //Lanzando la conexion
				 Connection.Response homePage = Jsoup.connect(loginActionUrl)  
				         .cookies(cookies)  
				         .data(formData)  
				         .method(Connection.Method.POST)  
				         .userAgent(USER_AGENT)  
				         .execute(); 
				 
				 System.out.println(homePage.parse().html());  
				 

	}

}
