package period_module;

import java.util.Date;
import java.util.List;

import entity.Period;

public interface PeriodModule {
	//period master
	boolean deletePeriod(Period period);
	List<Period> getPeriod();
	int insertPeriod(int id, String name, int terms, int tests, Date date, Date date2, int userid, String status,
			String value);
	
	//period detail
	int insertPeriodDetail(int pdetail_id, int pid, String month, int userid, String ischeck, String key);
	boolean deletePeriodDetail(Period period);
	List<Period> getPeriodDetail(int pid);
	List<String> getMonths(int pid);
}
