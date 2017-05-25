package com.yd.dby.app.entity;

public class YdOnlinebookingKey {
    private Integer id;

    private String addressPca;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressPca() {
        return addressPca;
    }

    public void setAddressPca(String addressPca) {
        this.addressPca = addressPca == null ? null : addressPca.trim();
    }
}