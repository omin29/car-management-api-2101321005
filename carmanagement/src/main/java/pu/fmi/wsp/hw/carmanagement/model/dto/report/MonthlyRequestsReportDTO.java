package pu.fmi.wsp.hw.carmanagement.model.dto.report;

import java.time.YearMonth;

public class MonthlyRequestsReportDTO implements Comparable<MonthlyRequestsReportDTO> {
	private YearMonth yearMonth;
	private int requests;

	public MonthlyRequestsReportDTO() {
	}

	public MonthlyRequestsReportDTO(int year, int month, long requests) {
		this.yearMonth = YearMonth.of(year, month);
		this.requests = (int)requests;
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

	//For sorting by year and month
	@Override
	public int compareTo(MonthlyRequestsReportDTO otherReport) {
		return yearMonth.compareTo(otherReport.yearMonth);
	}
}
