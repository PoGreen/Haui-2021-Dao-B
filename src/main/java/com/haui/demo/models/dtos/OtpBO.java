package com.haui.demo.models.dtos;

import javax.validation.constraints.NotBlank;

public class OtpBO {

    @NotBlank
    private String phoneNum;
    @NotBlank
    private String companyId;
    @NotBlank
    private String otp;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public OtpBO() {
    }

    public OtpBO(@NotBlank String phoneNum, @NotBlank String companyId, @NotBlank String otp) {
        this.phoneNum = phoneNum;
        this.companyId = companyId;
        this.otp = otp;
    }
}
