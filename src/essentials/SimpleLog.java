package essentials;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SimpleLog {

	private File file;
	private boolean useTimestamp = true;
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");

	boolean dummy = false;

	/**
	 * Constructor of 'SimpleLog' class, which creates the log file.
	 * 
	 * @param file
	 *            The File where the log will be saved to
	 * @param useTimestamp
	 *            If true, there will be a timestamp in front of every entry
	 * @throws IOException
	 *             if file does not exists and can not be created
	 */
	public SimpleLog(File file, boolean useTimestamp) throws IOException {

		this.file = file;
		this.useTimestamp = useTimestamp;

		if (!file.exists())
			file.createNewFile();
	}

	/**
	 * Will create a dummy-log that does not log anything but writes to console
	 */
	public SimpleLog() {
		dummy = true;
	}

	/**
	 * Add a new entry to the log file.
	 *
	 * @param string
	 *            This String will be written to the log file
	 * @return false if could not write to file
	 */
	public boolean log(String string) {

		if (useTimestamp) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			string = (CharSequence) DATE_FORMAT.format(timestamp) + " " + string;
		}

		System.out.println(string);

		if (!dummy)
			try {
				FileUtils.writeToFile(file, string + "\n");
			} catch (IOException e) {
				return false;
			}

		return true;
	}

	/**
	 * Add a new debug entry to the log file.
	 * 
	 * @param string
	 *            This String will be written to the log file
	 * @return false if could not write to file
	 */
	public boolean debug(String string) {
		return log("DEBUG: " + string);
	}

	/**
	 * Add a new info entry to the log file.
	 * 
	 * @param string
	 *            This String will be written to the log file
	 * @return false if could not write to file
	 */
	public boolean info(String string) {
		return log("INFO: " + string);
	}

	/**
	 * Add a new warning entry to the log file.
	 * 
	 * @param string
	 *            This String will be written to the log file
	 * @return false if could not write to file
	 */
	public boolean warning(String string) {
		return log("WARNING: " + string);
	}

	/**
	 * Add a new error entry to the log file.
	 * 
	 * @param string
	 *            This String will be written to the log file
	 * @return false if could not write to file
	 */
	public boolean error(String string) {
		return log("ERROR: " + string);
	}

	/**
	 * Add a new fatal error entry to the log file.
	 * 
	 * @param string
	 *            This String will be written to the log file
	 * @return false if could not write to file
	 */
	public boolean fatal(String string) {
		return log("FATAL ERROR: " + string);
	}

	/**
	 * Add a new StackTrace to the log file.
	 * 
	 * @param exception
	 *            The Exception, whose StackTrace should be logged
	 * @return false if could not write to file
	 */
	public boolean logStackTrace(Exception exception) {

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);

		exception.printStackTrace(printWriter);
		String string = stringWriter.toString();

		if (useTimestamp) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			string = (CharSequence) DATE_FORMAT.format(timestamp) + " " + string;
		}

		exception.printStackTrace();

		if (!dummy)
			try {
				FileUtils.writeToFile(file, string + "\n");
			} catch (IOException e) {
				return false;
			}

		return true;
	}

	/**
	 * Print a startup message to the log file.
	 * 
	 * @param string
	 *            The startup message
	 * @return false if could not write to file
	 */
	public boolean startUpMessage(String string) {

		if (useTimestamp) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			string = (CharSequence) DATE_FORMAT.format(timestamp) + " " + string;
		}

		String line = "";
		for (int i = 0; i < string.length(); i++)
			line = line + "=";

		string = line + "\n" + string + "\n" + line;
		System.out.println(string);

		if (!dummy)
			try {
				FileUtils.writeToFile(file, string + "\n");
			} catch (IOException e) {
				return false;
			}

		return true;
	}
}
