package stats;


public class Main {
	
	public static void main(String [] args) {}
	
	
	final static StatSystem statSystem = new StatSystem();
	
	static {
	statSystem.addStat("returnsItself", new Stat(){public double updateDouble(String statName, ROStatMap stats) {
		return stats.getStatDouble(statName);
	}});
	statSystem.addStat("gdp", new Stat(){public double updateDouble(String statName, ROStatMap stats) {
		return  ( stats.getStatDouble("agriculturalGDP")
				+ stats.getStatDouble("industrialGDP")
				+ stats.getStatDouble("serviceGDP"));
	}});
	statSystem.addStat("alaskaAgrGDP", constantStat(223000000));
	
	//temporary, this will be user-defined
	statSystem.addStat("localAlaskaStatus", 
			constantStat(StatConsts.fromSphereType(StatConsts.SphereType.ANNEXED)));
	statSystem.addStat("localAlaskaAgrGDP", localGDPStat("localAlaskaStatus", "alaskaAgrGDP"));
	
	
	
	
	}
	
	public static Stat localGDPStat(String ownerStat, String gdpStat) {
		final class LocalGDPStat implements Stat {
			String ownerName; String gdpName;
			
			public LocalGDPStat(String ownerStatName, String gdpStatName) {
				ownerName = ownerStatName;
				gdpName = gdpStatName;
			}
			
			@Override
			public double updateDouble(String statName, ROStatMap stats) {
				double multiplier = 0;
				/*
				switch (StatConsts.toSphereType(stats.getStatVal(ownerName))){
					case ANNEXED: multiplier = 1; break;
					case SATTELITE: multiplier = 0.1; break;
					case ALLY: multiplier = 0.05; break;
					case MAJOR_TRADE: multiplier = 0.03; break;
					case MINOR_TRADE: multiplier = 0.01; break;
				}//*/
				return multiplier * stats.getStatDouble(gdpName);
			}
		}
		
		return new LocalGDPStat(ownerStat, gdpStat);
	}
	
	public static Stat constantStat(double value) {
		final class ConstStat implements Stat {
			private final double value;
			private ConstStat(double val) {
				value = val;
			}
			public double updateDouble(String statName, ROStatMap stats) {
				return value;
			}
		}
		return new ConstStat(value);
	}
}
