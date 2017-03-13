import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class PageParsing {
	public static String valueClassGrade = "acLnk 1F4446484E1F44464245434446484E1E2A2B242422211F42C146CB46CAC343C21FC0C143C2C2431F";
	
	/**
	 * Gets the first link from the Html research page
	 * @param codeHtml
	 * @return The link found
	 * @Note The link could be checked by the date (past or current year)
	 */
	public static String getResearchFirstResult(String codeHtml) {
		String result ="";
		Document doc;
		
		try {
			doc = Jsoup.parse(codeHtml);
			Elements tables = doc.select("table");
			if (tables.isEmpty()) {
				return "notFound";
			}
			else {
			Element table = tables.first();
			Elements links = table.select("a[href]");
			Element link = links.first();
			result = link.attr("href");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Creates the Film out of the Html page containing all the information (dates, types, grades ...)
	 * @param filmName : title added so the movie return is complete
	 * @param codeHtml
	 * @return the Film created
	 */
	public static Film getFilm(String filmName,String codeHtml) {
		Film film = new Film();
		Document doc;
		film.name = filmName.replace("+", " ");
		try {
			doc = Jsoup.parse(codeHtml);
			//Elements tables = doc.select("table");
			Elements spans = doc.select("span");
			for (Element e : spans) {
			// Date published
				if (e.hasAttr("itemprop") && e.attr("itemprop").equals("datePublished")) {
					String found = e.ownText();
					film.date = e.ownText().substring(found.length()-4, found.length());
					//System.out.println("Date "+film.date);
				}
			// categories
				if (e.hasAttr("itemprop") && e.attr("itemprop").equals("genre")) {
					//System.out.println("Genre "+e.ownText());
					film.categories.add(e.ownText());
				}
			// Grades
				if (e.className().equals("stareval-note")) {
					if (e.hasAttr("itemprop")) {
						film.publicGrade =  e.attr("content");
					}
					else {
						if(e.nextElementSibling().className().equals("stareval-review"))
							film.presseGrade = e.text();
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return film;
	}
}
