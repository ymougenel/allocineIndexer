package web;

import java.io.IOException;

import model.Film;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;

public class WebConnection {

    public static final String PAGE_URL = "http://www.allocine.fr";
    public static HttpHost proxy;

    public static String getPage(String page) throws IllegalStateException, IOException {

        System.out.println("Getting page " + page + "!!!");
        BasicCookieStore cookieStore = new BasicCookieStore();
        HttpClient client = HttpClientBuilder.create().setProxy(proxy).setDefaultCookieStore(cookieStore).build();
        String result = IOUtils.toString(client.execute(new HttpGet(page)).getEntity().getContent());

        return result;
    }

    public static Film extractMovie(String movieTitle) throws IOException {
        String resURL;
        String htmlResult = getPage(PAGE_URL + "/recherche/?q=" + movieTitle); // Html research page
        resURL = PageParsing.getResearchFirstResult(htmlResult); // Getting the first link
        htmlResult = getPage(PAGE_URL + resURL); // Html from the first link (ie : movie description page)
        return PageParsing.getFilm(movieTitle, htmlResult);
    }

}
