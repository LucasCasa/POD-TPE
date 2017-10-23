package ar.edu.itba.pod.tpe;

import com.hazelcast.nio.serialization.DataSerializable;

import java.io.Serializable;

public class CensusEntry implements Serializable {
    private long serialVersionUID = 1L;
    private int id, ocupation;
    private String department, province;

    public CensusEntry(int ocupation, int id, String department, String province){
        loadData(ocupation,id,department,province);
    }

    public CensusEntry(String next) {
        String[] data = next.split(",");
        loadData(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[3]);
    }

    private void loadData(int ocupation, int id, String department, String province){
        this.ocupation = ocupation;
        this.id = id;
        this.department = department;
        this.province = province;
    }
    public int getId() {
        return id;
    }

    public int getOcupation() {
        return ocupation;
    }

    public String getDepartment() {
        return department;
    }

    public String getProvince() {
        return province;
    }

    @Override
    public String toString() {
        return this.id + " " + this.province;
    }
}
