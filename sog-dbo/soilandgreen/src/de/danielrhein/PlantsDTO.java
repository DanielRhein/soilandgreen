package de.danielrhein;

import java.net.URL;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class PlantsDTO {
   private String plant_name;
    private Month cultivation_start;
    private Month cultivation_end;
    private Month sow_start;
    private Month sow_end;
    private Month harvest_start;
    private Month harvest_end;
    private URL url;
    private int daysUntilCultivation;
    private int daysUntilHarvest;
    private float distance;
    Map<Integer,Month> monthMap = new HashMap<>();

    public PlantsDTO()
    {

    }

    private void setOffset(int offset)
    {
        monthMap.clear();
       monthMap.put(offset+1,Month.JANUARY);
        monthMap.put(offset+2,Month.FEBRUARY);
        monthMap.put(offset+3,Month.MARCH);
        monthMap.put(offset+4,Month.APRIL);
        monthMap.put(offset+5,Month.MAY);
        monthMap.put(offset+6,Month.JUNE);
        monthMap.put(offset+7,Month.JULY);
        monthMap.put(offset+8,Month.AUGUST);
        monthMap.put(offset+9,Month.SEPTEMBER);
        monthMap.put(offset+10,Month.OCTOBER);
        monthMap.put(offset+11,Month.NOVEMBER);
        monthMap.put(offset+12,Month.DECEMBER);
    }

    public void setMonthMapOffset(int offset)
    {
        setOffset(offset);
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public Month getCultivation_start() {
        return cultivation_start;
    }

    public void setCultivation_start(Month cultivation_start) {
        this.cultivation_start = cultivation_start;
    }

    public Month getCultivation_end() {
        return cultivation_end;
    }

    public void setCultivation_end(Month cultivation_end) {
        this.cultivation_end = cultivation_end;
    }

    public Month getSow_start() {
        return sow_start;
    }

    public void setSow_start(Month sow_start) {
        this.sow_start = sow_start;
    }

    public Month getSow_end() {
        return sow_end;
    }

    public void setSow_end(Month sow_end) {
        this.sow_end = sow_end;
    }

    public Month getHarvest_start() {
        return harvest_start;
    }

    public void setHarvest_start(Month harvest_start) {
        this.harvest_start = harvest_start;
    }

    public Month getHarvest_end() {
        return harvest_end;
    }

    public void setHarvest_end(Month harvest_end) {
        this.harvest_end = harvest_end;
    }

    public Map<Integer, Month> getMonthMap() {
        return monthMap;
    }
    public int getDaysUntilCultivation() {
        return daysUntilCultivation;
    }

    public void setDaysUntilCultivation(int daysUntilCultivation) {
        this.daysUntilCultivation = daysUntilCultivation;
    }

    public int getDaysUntilHarvest() {
        return daysUntilHarvest;
    }

    public void setDaysUntilHarvest(int daysUntilHarvest) {
        this.daysUntilHarvest = daysUntilHarvest;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(plant_name).append(";");
        stringBuilder.append(url).append(";");
        stringBuilder.append(cultivation_start).append(";");
        stringBuilder.append(cultivation_end).append(";");
        stringBuilder.append(sow_start).append(";");
        stringBuilder.append(sow_end).append(";");
        stringBuilder.append(harvest_start).append(";");
        stringBuilder.append(harvest_end).append(";");
        stringBuilder.append(daysUntilCultivation).append(";");
        stringBuilder.append(daysUntilHarvest).append(";");
        stringBuilder.append(distance).append(";");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

}
