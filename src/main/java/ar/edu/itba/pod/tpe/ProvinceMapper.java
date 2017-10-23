package ar.edu.itba.pod.tpe;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ProvinceMapper implements Mapper<Integer,String,String,Integer>{
    Map<String,String> regions = new HashMap<>();

    public ProvinceMapper() {
        regions.put("ciudad autónoma de buenos aires", "Region Buenos Aires");
        regions.put("buenos aires", "Region Buenos Aires");
        regions.put("tierra del fuego", "Region Patagonica");
        regions.put("santa cruz", "Region Patagonica");
        regions.put("chubut", "Region Patagonica");
        regions.put("río negro", "Region Patagonica");
        regions.put("neuquén", "Region Patagonica");
        regions.put("la pampa", "Region Patagonica");
        regions.put("córdoba", "Region Centro");
        regions.put("santa fe", "Region Centro");
        regions.put("entre ríos", "Region Centro");
        regions.put("mendoza", "Region del Nuevo Cuyo");
        regions.put("san juan", "Region del Nuevo Cuyo");
        regions.put("la rioja", "Region del Nuevo Cuyo");
        regions.put("san luis", "Region del Nuevo Cuyo");
        regions.put("corrientes", "Region del Norte Grande Argentino");
        regions.put("misiones", "Region del Norte Grande Argentino");
        regions.put("formosa", "Region del Norte Grande Argentino");
        regions.put("chaco", "Region del Norte Grande Argentino");
        regions.put("santiago del estero", "Region del Norte Grande Argentino");
        regions.put("salta", "Region del Norte Grande Argentino");
        regions.put("jujuy", "Region del Norte Grande Argentino");
        regions.put("catamarca", "Region del Norte Grande Argentino");
        regions.put("tucumán", "Region del Norte Grande Argentino");

    }
    @Override
    public void map(Integer integer, String s, Context<String, Integer> context) {
        StringTokenizer tokenizer = new StringTokenizer(s.toLowerCase());
        while(tokenizer.hasMoreTokens()) {
            String aux = tokenizer.nextToken(",");
            String t = regions.get(aux);
            context.emit(t,1);
        }
    }
}
