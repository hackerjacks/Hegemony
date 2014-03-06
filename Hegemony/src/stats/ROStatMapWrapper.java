package stats;
import java.util.Map;


public class ROStatMapWrapper implements ROStatMap{
	private final Map<String, Object> map;
	public ROStatMapWrapper(Map<String, Object> orig) {
		map = orig;
	}
	@Override
	public Double getStatDouble(String statName) {
		Object val = map.get(statName);
		if (!(val instanceof Double)) val = Double.NaN;
		return (Double) val;
	}
	public String getStatString(String statName) {
		Object val = map.get(statName);
		return val.toString();
	}
}
