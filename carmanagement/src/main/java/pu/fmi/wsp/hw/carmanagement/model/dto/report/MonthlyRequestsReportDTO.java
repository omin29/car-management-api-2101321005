package pu.fmi.wsp.hw.carmanagement.model.dto.report;

public class MonthlyRequestsReportDTO {
	private YearMonth yearMonth;
	private int requests;

	public MonthlyRequestsReportDTO() {
	}

	public MonthlyRequestsReportDTO(YearMonth yearMonth, int requests) {
		this.yearMonth = yearMonth;
		this.requests = requests;
	}

	public YearMonth getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(YearMonth yearMonth) {
		this.yearMonth = yearMonth;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}
}
