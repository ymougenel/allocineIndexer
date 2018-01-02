package output;

import model.Film;
import util.FileUtil;

import java.util.List;

/**
 * Write the result list into a file
 *
 * TODO: Handle i18n (issue #7)
 */
public class ResultLayoutEdition {

	/**
	 *
	 * @param films
	 * @param fileLocation the file paht
	 */
	public static void editFilms(List<Film> films, String fileLocation) {
		String name;
		int count = 0;
		
		String t="\t|\t";
		String tt="\t\t\t|\t\t\t";
		String result="";
		result+="Nom : \t\t\t\t\t\t\t\t\t\t\t\tDate : \t\t\t\t Note Spectateur : \t\t\t Note Presse : \t\t\t Genres : \n";
		result+="--------------------------------------------------------------------------------------------------------------------------------\n";
		for (Film f : films) {
			name = (f.name.length()<37) ? f.name : f.name.substring(0,37-3)+"...";
			while (name.length()<37) { name+=" "; } // Align the lines by completing the names with blank (same size)
			
			
			result+=name + "\t|\t\t\t" + f.date + tt + f.publicGrade + tt + f.presseGrade + "\t\t\t|\t\t" +f.categories.toString() + "\n";
			// Incrementing the result
			if (f.presseGrade!=null && !f.presseGrade.trim().isEmpty() && f.publicGrade != null && ! f.publicGrade.trim().isEmpty())
				count++;
		}
		result+="--------------------------------------------------------------------------------------------------------------------------------\n";
		result+=count+" films processed out of: "+ films.size();
		FileUtil.writeFileResult(result, fileLocation);
	}
}
