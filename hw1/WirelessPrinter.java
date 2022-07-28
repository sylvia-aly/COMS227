package hw1;

/**
 * A Wireless Printer uses ink and paper to be able to print things out when the
 * printer is on and connected. @author Sylvia Nguyen
 */
public class WirelessPrinter {

	/** Set the constant amount of ink being used per paper **/

	public static final int PAGES_PER_CARTRIDGE = 1000;
	/** Set the constant maximum amount of paper that the printer tray can hold */

	public static final int TRAY_CAPACITY = 500;
	/** Set the constant maximum amount of the ink for the printer */
	public static final double NEW_CARTRIDGE_INK_LEVEL = 1.0;

	/** hold the value of ink level (amount of ink in printer) */
	private double inkLevel;

	/** hold the value of paper level (amount of paper on tray) */
	private int paperLevel;

	/** hold the value to determine whether the printer is on or not */

	private boolean activatePrinter;
	/** hold the value to determine whether the printer is connected or not */
	private boolean connection;
	/** hold the value of the amount of paper being printed */
	private int pagesPrinted;
	/** hold the value of the amount of paper being used */
	private int usedPaper;

	/** set Wireless Printer inkLevel to be 0.5 */
	public WirelessPrinter() {

		inkLevel = 0.5;

	}

	/**
	 * set Wireless Printer inkLevel to the given value of ink. Set WireLess Printer
	 * paperLevel to the given value of paper.
	 */
	public WirelessPrinter(double ink, int paper) {

		inkLevel = ink;

		paperLevel = paper;
	}

	/** Set connection to true to indicate that the printer is connected */

	public void connect() {

		connection = true;

	}

	/** Set connection to false to indicate that the printer is disconnected */

	public void disconnect() {

		connection = false;

	}

	/**
	 * Set activate and connection to true to show that the printer is turn on and
	 * connected.
	 */

	public void turnOn() {

		activatePrinter = true;

		connection = true;
	}

	/**
	 * Set activate and connection to false to show that the printer is turn off and
	 * disconnected
	 */

	public void turnOff() {

		activatePrinter = false;

		connection = false;

	}

	/**
	 * Get the status if the printer is on or not. @return whether the printer is
	 * on(true) or not on(false).
	 */

	public boolean isOn() {

		return activatePrinter;
	}

	/**
	 * Get the status if the printer is connected or not. @return whether the
	 * printer is connected (true) or not connected (false)
	 */

	public boolean isConnected() {

		return connection;

	}

	/**
	 * Get the percentages of the amount of paper left in the printer. @return the
	 * percentages of the amount of paper that is left in the printer.
	 */

	public int getPaperLevel() {

		return paperLevel * 100 / TRAY_CAPACITY;

	}

	/**
	 * Get the amount of the inkLevel from the printer. The inkLevel must be between
	 * 0.0 - 1.0. @return the amount of inkLevel from the printer.
	 */

	public double getInkLevel() {

		return inkLevel;
	}

	/**
	 * Simulate how printer works. @param amount of paper being used and printed out
	 */

	public void print(int pages) {

		if (!isConnected()) {

			return;

		}

		if (pages > paperLevel) {

			paperLevel = 0;
			// Total amount of pages printed that goes beyond TRAY_CAPCITY. Use this total
			// amount of
			// pages being printed in the next equation to calculate the actual amount of
			// paper printed.
			pagesPrinted = pagesPrinted + pages;
			// Get the total amount of paper that was
			// actually printed. Thus, being below capacity.
			pagesPrinted = TRAY_CAPACITY - (pagesPrinted - TRAY_CAPACITY);

			inkLevel = ((inkLevel * PAGES_PER_CARTRIDGE) - pagesPrinted) / (double) PAGES_PER_CARTRIDGE;

			pagesPrinted = TRAY_CAPACITY;

			usedPaper = TRAY_CAPACITY;

		}

		else {

			paperLevel = paperLevel - pages;

			inkLevel = inkLevel - (pages / (double) PAGES_PER_CARTRIDGE);

			usedPaper = usedPaper + pages;

			pagesPrinted = pagesPrinted + pages;

		}

	}

	/**
	 * Get the total amount of paper being printed out. @return the amount of paper
	 * being printed.
	 */

	public int getTotalPagesPrinted() {

		return pagesPrinted;

	}

	/**
	 * Get the total amount of paper being used from the printer. @return the amount
	 * of paper being used from the printer.
	 */

	public int getTotalPaperUsed() {

		return usedPaper;

	}

	/**
	 * load the printer with papers. Number of papers cannot surpass the capacity of
	 * printer. @param the amount of paper being loaded
	 */

	public void loadPaper(int pages) {

		if ((pages + paperLevel) >= TRAY_CAPACITY) {
			paperLevel = TRAY_CAPACITY;

		} else {

			paperLevel += pages;
		}

	}

	/**
	 * Get the exact amount of paper in the printer. @return the amount of paper in
	 * the printer.
	 */

	public int getPaperLevelExact() {

		return paperLevel;

	}

	/**
	 * set the inkLevel to full capacity again (NEW_CARTRIDGE_INK_LEVEL = full
	 * capacity of ink in printer).
	 */

	public void replaceCartridge() {

		inkLevel = NEW_CARTRIDGE_INK_LEVEL;

	}
}
