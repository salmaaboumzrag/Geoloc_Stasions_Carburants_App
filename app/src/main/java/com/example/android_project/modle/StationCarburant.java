package com.example.android_project.modle;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationCarburant implements Parcelable {
    @SerializedName("name")
    @Expose
    private String nom;

    @SerializedName("brand")
    @Expose
    private String marque;

    @SerializedName("address")
    @Expose
    private String adresse;

    @SerializedName("reg_code")
    @Expose
    private String regCode;

    @SerializedName("cp")
    @Expose
    private String codePostal;

    @SerializedName("com_arm_name")
    @Expose
    private String ville;

    @SerializedName("price_gazole")
    @Expose
    private Double priceGazole;

    @SerializedName("price_sp95")
    @Expose
    private Double priceSp95;

    @SerializedName("price_sp98")
    @Expose
    private Double priceSp98;

    @SerializedName("price_gplc")
    @Expose
    private Double priceGplc;

    @SerializedName("price_e10")
    @Expose
    private Double priceE10;

    @SerializedName("price_e85")
    @Expose
    private Double priceE85;

    @SerializedName("timetable")
    @Expose
    private String timetable;

    @SerializedName("geo_point")
    @Expose
    private GeoPoint geoPoint;

    //All args constructor
    public StationCarburant(String nom,String cp, String regCode ,String timetable, String marque, String adresse, String ville, Double priceGazole, Double priceSp95, Double priceSp98, Double priceGplc, Double priceE10, Double priceE85, GeoPoint geoPoint) {
        this.nom = nom;
        this.codePostal=cp;
        this.marque = marque;
        this.adresse = adresse;
        this.ville = ville;
        this.priceGazole = priceGazole;
        this.priceSp95 = priceSp95;
        this.priceSp98 = priceSp98;
        this.priceGplc = priceGplc;
        this.priceE10 = priceE10;
        this.priceE85 = priceE85;
        this.geoPoint = geoPoint;
        this.timetable=timetable;
        this.regCode=regCode;
    }


    // Getters and setters for each field
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public String getTimetable() {
        return timetable;
    }
    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Double getPriceGazole() {
        return priceGazole;
    }

    public void setPriceGazole(Double priceGazole) {
        this.priceGazole = priceGazole;
    }

    public Double getPriceSp95() {
        return priceSp95;
    }

    public void setPriceSp95(Double priceSp95) {
        this.priceSp95 = priceSp95;
    }

    public Double getPriceSp98() {
        return priceSp98;
    }

    public void setPriceSp98(Double priceSp98) {
        this.priceSp98 = priceSp98;
    }

    public Double getPriceGplc() {
        return priceGplc;
    }

    public void setPriceGplc(Double priceGplc) {
        this.priceGplc = priceGplc;
    }

    public Double getPriceE10() {
        return priceE10;
    }

    public void setPriceE10(Double priceE10) {
        this.priceE10 = priceE10;
    }

    public Double getPriceE85() {
        return priceE85;
    }

    public void setPriceE85(Double priceE85) {
        this.priceE85 = priceE85;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }
    @Override
    public String toString() {
        return "Nom de la station : " + nom + "\n" +
                "Prix du gazole : " + priceGazole;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeString(nom);
        dest.writeString(marque);
        dest.writeString(adresse);
        dest.writeString(ville);
        dest.writeString(codePostal);
        dest.writeString(timetable);
        dest.writeString(regCode);
        dest.writeParcelable(geoPoint, flags);
        if (priceGazole == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(priceGazole);
        }
        if (priceSp95 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(priceSp95);
        }
        if (priceSp98 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(priceSp98);
        }
        if (priceGplc == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(priceGplc);
        }
        if (priceE10 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(priceE10);
        }
        if (priceE85 == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(priceE85);
        }
    }
    protected StationCarburant(Parcel in) {
        nom = in.readString();
        marque = in.readString();
        adresse = in.readString();
        ville = in.readString();
        codePostal=in.readString();
        timetable=in.readString();
        regCode=in.readString();
        geoPoint = in.readParcelable(GeoPoint.class.getClassLoader());
        if (in.readByte() == 0) {
            priceGazole = null;
        } else {
            priceGazole = in.readDouble();
        }
        if (in.readByte() == 0) {
            priceSp95 = null;
        } else {
            priceSp95 = in.readDouble();
        }
        if (in.readByte() == 0) {
            priceSp98 = null;
        } else {
            priceSp98 = in.readDouble();
        }
        if (in.readByte() == 0) {
            priceGplc = null;
        } else {
            priceGplc = in.readDouble();
        }
        if (in.readByte() == 0) {
            priceE10 = null;
        } else {
            priceE10 = in.readDouble();
        }
        if (in.readByte() == 0) {
            priceE85 = null;
        } else {
            priceE85 = in.readDouble();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StationCarburant> CREATOR = new Creator<StationCarburant>() {
        @Override
        public StationCarburant createFromParcel(Parcel in) {
            return new StationCarburant(in);
        }

        @Override
        public StationCarburant[] newArray(int size) {
            return new StationCarburant[size];
        }
    };

}
