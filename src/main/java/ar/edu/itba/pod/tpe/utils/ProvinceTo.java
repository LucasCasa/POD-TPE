package ar.edu.itba.pod.tpe.utils;

import java.util.HashMap;
import java.util.Map;

public class ProvinceTo {
	public static Map<String, Integer> provinceToRegionId = new HashMap<String, Integer>() {{
		put("ciudad autónoma de buenos aires", 1);
		put("buenos aires", 1);
		put("tierra del fuego", 2);
		put("santa cruz", 2);
		put("chubut", 2);
		put("río negro", 2);
		put("neuquén", 2);
		put("la pampa", 2);
		put("córdoba", 3);
		put("santa fe", 3);
		put("entre ríos", 3);
		put("mendoza", 4);
		put("san juan", 4);
		put("la rioja", 4);
		put("san luis", 4);
		put("corrientes", 5);
		put("misiones", 5);
		put("formosa", 5);
		put("chaco", 5);
		put("santiago del estero", 5);
		put("salta", 5);
		put("jujuy", 5);
		put("catamarca", 5);
		put("tucumán", 5);
	}};

	public static Map<Integer,String> idToRegion = new HashMap<Integer,String>() {{
		put(1, "Region Buenos Aires");
		put(2, "Region Patagonica");
		put(3, "Region Centro");
		put(4, "Region del Nuevo Cuyo");
		put(5, "Region del Norte Grande Argentino");
	}};
}
