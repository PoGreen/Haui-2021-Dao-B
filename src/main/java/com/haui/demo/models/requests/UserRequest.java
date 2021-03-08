package com.haui.demo.models.requests;


import com.haui.demo.annotations.PhoneNumber;

import javax.validation.constraints.NotBlank;

public class UserRequest {
    @PhoneNumber
    private String phone;
    private String otp;
    @NotBlank
    private String os;
    @NotBlank
    private String versionApp;
    @NotBlank
    private String versionOs;
    @NotBlank
    private String deviceName;
    @NotBlank
    private String deviceToken;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersionApp() {
        return versionApp;
    }

    public void setVersionApp(String versionApp) {
        this.versionApp = versionApp;
    }

    public String getVersionOs() {
        return versionOs;
    }

    public void setVersionOs(String versionOs) {
        this.versionOs = versionOs;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
