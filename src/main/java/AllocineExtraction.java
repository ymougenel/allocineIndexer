import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yann on 13/03/17.
 */
public class AllocineExtraction {

  public static void main(String[] args) {
    List<String> titles = FileUtil.readFromFile("source.txt");
    List<Film> films = new ArrayList<Film>();

    //titles = titles.subList(0, 14);
    String resURL;
    String htmlResult;
    for (String title : titles) {
      try {
        films.add(WebConnection.extractMovie(title));
      }
      catch (IOException e) {
        System.err.println("Error encountered for: " + title);
        System.err.println(e.getMessage());
      }
    }
    ResultLayoutEdition.editFilms(films, "Resultats.txt"); // Edition of the results and writing into file
    //System.out.println(films.toString());
  }
}
