package de.danielrhein;

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
    private int daysUntilCultivation;
    private int daysUntilHarvest;
    private float distance;
    Map<Integer,Month> monthMap = new HashMap<>();

    public PlantsDTO()
    {
            monthMap.put(1,Month.JANUARY);
            monthMap.put(2,Month.FEBRUARY);
            monthMap.put(3,Month.MARCH);
            monthMap.put(4,Month.APRIL);
            monthMap.put(5,Month.MAY);
            monthMap.put(6,Month.JUNE);
            monthMap.put(7,Month.JULY);
            monthMap.put(8,Month.AUGUST);
            monthMap.put(9,Month.SEPTEMBER);
            monthMap.put(10,Month.OCTOBER);
            monthMap.put(11,Month.NOVEMBER);
            monthMap.put(11,Month.DECEMBER);
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


}
