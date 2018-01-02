import java.io.IOException;

import model.Film;
import output.ResultLayoutEdition;
import util.ArgumentsUtil;
import util.FileUtil;
import web.WebConnection;

import java.util.ArrayList;
import java.util.List;

import static util.ArgumentsUtil.resultFile;
import static util.ArgumentsUtil.sourceFile;


public class AllocineExtraction {



    public static void main(String[] args) {
        ArgumentsUtil.parseArgs(args);

        List<String> titles = FileUtil.readFromFile(sourceFile);
        List<Film> films = new ArrayList<Film>();

        for (String title : titles) {
            try {
                films.add(WebConnection.extractMovie(title));
            } catch (IOException e) {
                System.err.println("Error encountered for: " + title);
                System.err.println(e.getMessage());
            }
        }
        ResultLayoutEdition.editFilms(films, resultFile); // Edition of the results and writing into file
    }


}
