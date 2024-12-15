package pu.fmi.wsp.hw.carmanagement.model.dto.report;

import java.time.LocalDate;

public class GarageDailyAvailabilityReportDTO implements Comparable<GarageDailyAvailabilityReportDTO> {
	private LocalDate date;
	private int requests;
	private int availableCapacity;

	public GarageDailyAvailabilityReportDTO() {
	}

	public GarageDailyAvailabilityReportDTO(LocalDate date, long requests, long availableCapacity) {
		this.date = date;
		this.requests = (int)requests;
		this.availableCapacity = (int)availableCapacity;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getRequests() {
		return requests;
	}

	public void setRequests(int requests) {
		this.requests = requests;
	}

	public int getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(int availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	@Override
	public int compareTo(GarageDailyAvailabilityReportDTO otherReport) {
		return date.compareTo(otherReport.getDate());
	}
}
