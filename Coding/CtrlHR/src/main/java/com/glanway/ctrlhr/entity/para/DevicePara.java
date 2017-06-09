package com.glanway.ctrlhr.entity.para;

public class DevicePara extends BasePara {

    private String keyword;// 搜索关键字

    private Integer state;// 设备状态

    private Integer syncState;// 同步状态: 1.未同步 2.处理中 3.已同步

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSyncState() {
        return syncState;
    }

    public void setSyncState(Integer syncState) {
        this.syncState = syncState;
    }

}
