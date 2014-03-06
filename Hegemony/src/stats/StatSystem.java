package stats;
import java.util.HashMap;
import java.util.Map;


public class StatSystem implements ROStatMap{
	private Map<String, Stat> stats = new HashMap<String, Stat>();
	private Map<String, Object> valsCurrent = new HashMap<String, Object>();
	private Map<String, Object> valsUpdate = new HashMap<String, Object>(); //this is non-dense, an unchanged value won't be placed in here.
	
	@Override
	public Double getStatDouble(String statName) {
		Object val = valsCurrent.get(statName);
		if (!(val instanceof Double)) val = null;
		return (Double) val;
	}

	@Override
	public String getStatString(String statName) {
		Object val = valsCurrent.get(statName);
		return val.toString();
	}
	
	//as was obvious by the name, 
	public StatSystem cobindDelayed(String statName, Stat function) {
		Double valNew = function.updateDouble(statName, this);
		valsUpdate.put(statName, valNew);
		return this;
	}
	
	public StatSystem cobindAll() {
		
		return this;
	}
	
	public StatSystem addStat(String statName, Stat function) {
		stats.put(statName, function);
		return this;
	}
}
