import model.Film;
import org.apache.http.HttpHost;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import web.WebConnection;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ConnectionTest {

    @BeforeClass
    public static void setUp() {
        Properties props = System.getProperties();
        String proxyHost = props.getProperty("http.proxyHost", null);
        String proxyPort = props.getProperty("http.proxyPort", null);
        if (proxyHost != null && proxyPort != null) {
            try {
                WebConnection.proxy = new HttpHost(proxyHost, Integer.parseInt(proxyPort));
            } catch (NumberFormatException e) {
                System.err.println("Error while parsing proxy port:" + proxyPort);
            }
        }
    }

    // TODO implement this test
    @Test
    public void testConnection() {

        assert (true);
    }

    @Test
    public void filmGrades() {
        final String title = "pulp_fiction";
        Film pulpFiction = new Film();
        pulpFiction.name = title;
        pulpFiction.presseGrade = "4,2";
        pulpFiction.publicGrade = "4,5";

        try {
            Film retrieved = WebConnection.extractMovie(title);
            assertNotNull(retrieved);
            assertEquals(pulpFiction.presseGrade, retrieved.presseGrade);
            assertEquals(pulpFiction.publicGrade, retrieved.publicGrade);
        } catch (IOException e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Test
    public void filmCategories() {
        final String title = "vice_versa";
        Film viceVersa = new Film();
        viceVersa.name = title;
        viceVersa.categories = Arrays.asList("Animation", "Comédie", "Famille");
        try {
            Film retrieved = WebConnection.extractMovie(title);
            assertNotNull(retrieved);
            assertEquals(viceVersa.categories, retrieved.categories);
        } catch (IOException e) {
            e.printStackTrace();
            assert (false);
        }
    }
}
