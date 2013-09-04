package com.citi.prepaid.utilities;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * This is a Utility that will assist in the parsing and formatting of monetary
 * data that is stored in an Integer format. This class
 * provides a consistant and localized fashion to acomplish that task.
 * 
 * @author <a href="mailto:gbpaffett@gmail.com">Geoffrey B. Paffett</a>
 * @version $Revision$
 * 
 */
public class MoneyParser {

	/**
	 * Useful Constant for Country: United States
	 */
	public static MoneyParser US = new MoneyParser(Locale.US);
	/**
	 * Useful Constant for Country: Canada
	 */
	public static MoneyParser CANADA = new MoneyParser(Locale.CANADA);
	/**
	 * Useful Constant for Country: China
	 */
	public static MoneyParser CHINA = new MoneyParser(Locale.CHINA);
	/**
	 * Useful Constant for Country: France
	 */
	public static MoneyParser FRANCE = new MoneyParser(Locale.FRANCE);
	/**
	 * Useful Constant for Country: Italy
	 */
	public static MoneyParser ITALY = new MoneyParser(Locale.ITALY);
	/**
	 * Useful Constant for Country: Japan
	 */
	public static MoneyParser JAPAN = new MoneyParser(Locale.JAPAN);
	/**
	 * Useful Constant for Country: Korea
	 */
	public static MoneyParser KOREA = new MoneyParser(Locale.KOREA);
	/**
	 * Useful Constant for Country: Taiwan
	 */
	public static MoneyParser TAIWAN = new MoneyParser(Locale.TAIWAN);
	/**
	 * Useful Constant for Country: United Kingdom
	 */
	public static MoneyParser UK = new MoneyParser(Locale.UK);

	private NumberFormat nf;
	private int decimalDigits;

	/**
	 * Creates Money Parser Object with the default locale for this instance of
	 * the Java Virtual Machine
	 */
	public MoneyParser() {
		this(Locale.getDefault());
	}

	/**
	 * Creates Money Parser Object with the locale
	 * 
	 * @param locale
	 */
	public MoneyParser(Locale locale) {
		// TODO Figure out a more accurate way to determine
		nf = NumberFormat.getInstance(locale);
		decimalDigits = NumberFormat.getCurrencyInstance(locale)
				.getMaximumFractionDigits();
		nf.setGroupingUsed(false);
	}

	/**
	 * 
	 * Parses a String representation of a monetary value, removes the
	 * fractional separator (i.e. decimal point in the US) and returns it in an
	 * integral format (e.g. - "100.00" -> 10000.
	 * 
	 * @param moneyAsString
	 * @return Integral representation of the String value with fractional
	 *         separator removed
	 */
	public Integer parse(String moneyAsString) {
		Number formattedMoney;

		try {
			formattedMoney = nf.parse(moneyAsString);
		} catch (ParseException pe) {
			throw new IllegalArgumentException("Amount: '" + moneyAsString
					+ "' unable to be parsed");
		}

		BigDecimal amount = new BigDecimal(formattedMoney.toString())
				.movePointRight(decimalDigits);

		return Integer.valueOf(amount.intValue());

	}

	/**
	 * Formats a Integral representation of a monetary value, without a
	 * fractional separator (i.e. decimal point in the US) and returns it in an
	 * String format (e.g. - 10000 -> "100.00".
	 * 
	 * @param moneyAsInteger
	 * @return String representation of the Integral value with fractional
	 *         separator added
	 */
	public String format(Integer moneyAsInteger) {

		BigDecimal moneyAsBigDecimal = new BigDecimal(moneyAsInteger)
				.movePointLeft(decimalDigits);

		return nf.format(moneyAsBigDecimal);
	}

}