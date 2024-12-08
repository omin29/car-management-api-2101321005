package pu.fmi.wsp.hw.carmanagement.model.dto.report;

public class MonthlyRequestsReportDTO {
	private java.time.YearMonth yearMonth;
	private int requests;

	public MonthlyRequestsReportDTO() {
	}

	public MonthlyRequestsReportDTO(int year, int month, int requests) {
		this.yearMonth = java.time.YearMonth.of(year, month);
		this.requests = requests;
	}
	
	public MonthlyRequestsReportDTO(java.time.YearMonth yearMonth, int requests) {
		this.yearMonth = yearMonth;
		this.requests = requests;
	}

	public java.time.YearMonth getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(java.time.YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}
}
