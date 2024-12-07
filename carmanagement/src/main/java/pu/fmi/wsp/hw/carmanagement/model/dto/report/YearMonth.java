package pu.fmi.wsp.hw.carmanagement.model.dto.report;

public class YearMonth {
	private int year;
	private Month month;
	private boolean leapYear;
	private int monthValue;

	public YearMonth() {
	}

	public YearMonth(int year, Month month, boolean leapYear, int monthValue) {
		this.year = year;
		this.month = month;
		this.leapYear = leapYear;
		this.monthValue = monthValue;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public boolean isLeapYear() {
		return leapYear;
	}

	public void setLeapYear(boolean leapYear) {
		this.leapYear = leapYear;
	}

	public int getMonthValue() {
		return monthValue;
	}

	public void setMonthValue(int monthValue) {
		this.monthValue = monthValue;
	}
}
