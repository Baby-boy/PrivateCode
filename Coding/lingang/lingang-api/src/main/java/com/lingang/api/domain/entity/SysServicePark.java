package com.lingang.api.domain.entity;

import java.io.Serializable;

public class SysServicePark implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1536370297055386840L;

	private Integer serviceParkId;

    private Integer serviceId;

    private Integer parkId;

    public Integer getServiceParkId() {
        return serviceParkId;
    }

    public void setServiceParkId(Integer serviceParkId) {
        this.serviceParkId = serviceParkId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }
}