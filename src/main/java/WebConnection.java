import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;

public class WebConnection {
	public static String pageURL="http://www.allocine.fr";
	
	public static String getPage(String page) throws IllegalStateException, ClientProtocolException, IOException {
		
		System.out.println("Getting page "+page+"!!!");
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
		String result = IOUtils.toString(client.execute(new HttpGet(page)).getEntity().getContent());
		
		return result;
	}
	
	public static void main(String[] args) {
		List<String> titles = FileUtil.readFromFile("source.txt");
		List<Film> films = new ArrayList<Film>();
		
		//titles = titles.subList(0, 14);
		String resURL;
		String htmlResult;
		for (String title : titles) {
			try {
				htmlResult = getPage(pageURL+"/recherche/?q="+title); // Html research page
				resURL = PageParsing.getResearchFirstResult(htmlResult); // Getting the first link
				htmlResult = getPage(pageURL+resURL); // Html from the first link (ie : movie desciption page)
				films.add( PageParsing.getFilm(title,htmlResult) ); // Film created and added to the List
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		ResultLayoutEdition.editFilms(films, "Resultats.txt"); // Edition of the results and writing into file
		//System.out.println(films.toString());
	}
}
