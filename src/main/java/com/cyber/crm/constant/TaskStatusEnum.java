package com.cyber.crm.constant;

public enum TaskStatusEnum {
    CHUA_THUC_HIEN(1,"Chưa thực hiện"),
    DANG_THUC_HIEN(2,"Đang thực hiện"),
    HOAN_THANH(3,"Đã hoàn thành"),
    ;

    TaskStatusEnum(int id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }
    private int id;
    private String statusName;

    public int getId() {
        return id;
    }

    public String getStatusName() {
        return statusName;
    }
}
