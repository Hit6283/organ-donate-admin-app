package com.app.oda_admin;

public class donorsModel {

    private String donor_name;
    private String donatedOrgan;
    private String id;

    private donorsModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    donorsModel(String donor_name, String donatedOrgan, String id) {
        this.donor_name = donor_name;
        this.donatedOrgan = donatedOrgan;
        this.id = id;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public void setDonor_name(String donor_name) {
        this.donor_name = donor_name;
    }

    public String getDonatedOrgan() {
        return donatedOrgan;
    }

    public void setDonatedOrgan(String donatedOrgan) {
        this.donatedOrgan = donatedOrgan;
    }
}
