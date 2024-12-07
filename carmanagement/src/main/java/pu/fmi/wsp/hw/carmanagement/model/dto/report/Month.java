package pu.fmi.wsp.hw.carmanagement.model.dto.report;

public enum Month {
	JANUARY("JANUARY"),
	FEBRUARY("FEBRUARY"),
	MARCH("MARCH"),
	APRIL("APRIL"),
	MAY("MAY"),
	JUNE("JUNE"),
	JULY("JULY"),
	AUGUST("AUGUST"),
	SEPTEMBER("SEPTEMBER"),
	OCTOBER("OCTOBER"),
	NOVEMBER("NOVEMBER"),
	DECEMBER("DECEMBER");
	
	private final String month;
	
	Month(String month) {
		this.month = month;
	}
	
	@Override
    public String toString() {
        return month;
    }
}
