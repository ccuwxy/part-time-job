package com.proj.api.cert.gson;
/**
 * author:Jerry
 */
import com.proj.api.exception.other.InvalidParamsException;

public class ModifyCompanyRecvGson {
    private String action;
    private String auth_id;
    private String user_id;
    private int cert_status;
    private String co_name;
    private String co_nickname;
    private int industry_type;
    private int co_type;
    private String org_code;
    private String biz_code;
    private String org_pic;
    private String biz_pic;
    private int co_scale;
    private String co_desc;
    private String contact_person;
    private String contact_phone;
    private String contact_mail;
    private String co_site;
    private String[] co_pic;
    private int co_city;
    private String co_addr;
    private String check_code;

    public String getAction() throws InvalidParamsException {
        if (action == null) {
            throw new InvalidParamsException();
        }else {
            return action;
        }
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAuth_id() throws InvalidParamsException {
        if (auth_id == null) {
            throw new InvalidParamsException();
        }else {
            return auth_id;
        }
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getUser_id() throws InvalidParamsException {
        if (user_id == null) {
            throw new InvalidParamsException();
        }else {
            return user_id;
        }
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getCert_status() throws InvalidParamsException {
        if (cert_status <= 0 ) {
            throw new InvalidParamsException();
        }else {
            return cert_status;
        }
    }

    public void setCert_status(int cert_status) {
        this.cert_status = cert_status;
    }

    public String getCo_name() throws InvalidParamsException {
        if (co_name == null ) {
            throw new InvalidParamsException();
        }else {
            return co_name;
        }
    }

    public void setCo_name(String co_name) {
        this.co_name = co_name;
    }

    public String getCo_nickname() throws InvalidParamsException {
        if (co_nickname == null ) {
            throw new InvalidParamsException();
        }else {
            return co_nickname;
        }
    }

    public void setCo_nickname(String co_nickname) {
        this.co_nickname = co_nickname;
    }

    public int getIndustry_type() throws InvalidParamsException {
        if (industry_type <= 0 ) {
            throw new InvalidParamsException();
        }else {
            return industry_type;
        }
    }

    public void setIndustry_type(int industry_type) {
        this.industry_type = industry_type;
    }

    public int getCo_type() throws InvalidParamsException {
        if (co_type <= 0 ) {
            throw new InvalidParamsException();
        }else {
            return co_type;
        }
    }

    public void setCo_type(int co_type) {
        this.co_type = co_type;
    }

    public String getOrg_code() throws InvalidParamsException {
        if (org_code == null ) {
            throw new InvalidParamsException();
        }else {
            return org_code;
        }
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code;
    }

    public String getBiz_code() throws InvalidParamsException {
        if (biz_code == null ) {
            throw new InvalidParamsException();
        }else {
            return biz_code;
        }
    }

    public void setBiz_code(String biz_code) {
        this.biz_code = biz_code;
    }

    public String getOrg_pic() throws InvalidParamsException {
        if (org_pic == null ) {
            throw new InvalidParamsException();
        }else {
            return org_pic;
        }
    }

    public void setOrg_pic(String org_pic) {
        this.org_pic = org_pic;
    }

    public String getBiz_pic() throws InvalidParamsException {
        if (biz_pic == null ) {
            throw new InvalidParamsException();
        }else {
            return biz_pic;
        }
    }

    public void setBiz_pic(String biz_pic) {
        this.biz_pic = biz_pic;
    }

    public int getCo_scale() throws InvalidParamsException {
        if (co_scale <= 0 ) {
            throw new InvalidParamsException();
        }else {
            return co_scale;
        }
    }

    public void setCo_scale(int co_scale) {
        this.co_scale = co_scale;
    }

    public String getCo_desc() throws InvalidParamsException {
        if (co_desc == null ) {
            throw new InvalidParamsException();
        }else {
            return co_desc;
        }
    }

    public void setCo_desc(String co_desc) {
        this.co_desc = co_desc;
    }

    public String getContact_person() throws InvalidParamsException {
        if (contact_person == null ) {
            throw new InvalidParamsException();
        }else {
            return contact_person;
        }
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getContact_phone() throws InvalidParamsException {
        if (contact_phone == null ) {
            throw new InvalidParamsException();
        }else {
            return contact_phone;
        }
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_mail() {
        return contact_mail;
    }

    public void setContact_mail(String contact_mail) {
        this.contact_mail = contact_mail;
    }

    public String getCo_site() {
        return co_site;
    }

    public void setCo_site(String co_site) {
        this.co_site = co_site;
    }

    public String[] getCo_pic() {
        return co_pic;
    }

    public void setCo_pic(String[] co_pic) {
        this.co_pic = co_pic;
    }

    public int getCo_city() throws InvalidParamsException {
        if (co_city <= 0 ) {
            throw new InvalidParamsException();
        }else {
            return co_city;
        }
    }

    public void setCo_city(int co_city) {
        this.co_city = co_city;
    }

    public String getCo_addr() throws InvalidParamsException {
        if (co_addr == null ) {
            throw new InvalidParamsException();
        }else {
            return co_addr;
        }
    }

    public void setCo_addr(String co_addr) {
        this.co_addr = co_addr;
    }

    public String getCheck_code() throws InvalidParamsException {
        if (check_code == null) {
            throw new InvalidParamsException();
        } else {
            return check_code;
        }
    }

    public void setCheck_code(String check_code) {
        this.check_code = check_code;
    }
}
