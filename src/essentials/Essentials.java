package essentials;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Essentials {

	/**
	 * Assembles a String[] to one String, in which the parts are separated by
	 * blanks.
	 * 
	 * @param array
	 *            This String array will be assembled to one String
	 * @return The assembled String
	 */
	public static String getAssembledStringArray(String[] array) {

		StringBuilder builder = new StringBuilder();

		for (String string : array) {
			builder.append(string + " ");
		}

		String assembledString = new String(builder.toString());

		return assembledString.substring(0, assembledString.length() - 1);
	}

	/**
	 * Checks if a String is included in a String[]
	 * 
	 * @param array
	 *            The String array
	 * @param string
	 *            The String
	 * @return true if included, false if not
	 */
	public static boolean isIncluded(String[] array, String string) {

		for (String s : array)
			if (s.equals(string))
				return true;

		return false;
	}

	/**
	 * Writes a String (with a timestamp) to a File.
	 * 
	 * @deprecated {@link SimpleLog} handles this much more comfortably and
	 *             professionally
	 * @param string
	 *            The String that will be written to the file
	 * @param file
	 *            The File where the text will be written to
	 * @param printTimestamp
	 *            If true, there will be a timestamp in front of the text
	 * @return boolean false if exception occurred
	 * @throws IOException
	 *             if an exception occurred
	 */
	public static void log(File file, String string, boolean printTimestamp) throws IOException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		if (printTimestamp)
			FileUtils.writeToFile(file, (CharSequence) simpleDateFormat.format(timestamp) + " " + string + "\n");
		else
			FileUtils.writeToFile(file, string + "\n");
	}
	
	/**
	 *  Generates a String with random chars, ideal to generate passwords
	 * 
	 * @param length 
	 * 				the length of the returning String
	 * @param base
	 * 				a string with all desired characters
	 * 
	 * @return String with random characters
	 */
	
	public static String generateRandomString(int length, String base) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i< length; i++) {
            int character = (int)(Math.random()*base.length());
            builder.append(base.charAt(character));
        }
        return builder.toString();
	}
	
	/**
	 *  Generates a String with random chars, ideal to generate passwords
	 * 
	 * @param length 
	 * 				the length of the returning String
	 * @return String with random characters and given length from default base:
	 *         [A-Z,a-z,+,-,*,/,!,",�,$,%,&,/,(,),=,?]
	 */
	
	public static String generateRandomString(int length){
		String defaultBase = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyu0123456789+-*/!\"�$%&/()=?";
		return generateRandomString(length, defaultBase);
	    
    }
}