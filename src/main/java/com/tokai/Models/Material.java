package com.tokai.Models;

public class Material {
    int materialID;
    String materialName;
    float materialUnitPrice;
    String materialUnit;
    String materialType;

    int points;

    public int getMaterialID() {
        return materialID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public float getMaterialUnitPrice() {
        return materialUnitPrice;
    }

    public String getMatrialUnit() {
        return materialUnit;
    }

    public String getMaterialType() {
        return materialType;
    }

    public int getPoints() {
        return points;
    }

    public void setMaterialData(int id,
                                String name,
                                float materialUnitprice,
                                String materialUnit,
                                String type,
                                int points){
        this.materialID = id;
        this.materialName = name;
        this.materialUnitPrice = materialUnitprice;
        this.materialUnit = materialUnit;
        this.materialType = type;
        this.points = points;
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialID=" + materialID +
                ", materialName='" + materialName + '\'' +
                ", materialUnitPrice=" + materialUnitPrice +
                ", materialUnit='" + materialUnit + '\'' +
                '}';
    }
}
