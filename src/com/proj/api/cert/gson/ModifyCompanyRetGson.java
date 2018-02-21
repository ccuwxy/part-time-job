package com.proj.api.cert.gson;
/**
 * author:Jerry
 */
import com.proj.api.exception.error.Reason;

public class ModifyCompanyRetGson {
    private int err_code=0;
    private String reason= Reason.getReason(0);
    private InnerData data;
    private String check_code;

    public ModifyCompanyRetGson(String user_id,boolean cert_status,boolean co_name,boolean co_nickname,boolean industry_type,
        boolean co_type,boolean org_code,boolean biz_code,boolean org_pic,boolean biz_pic,boolean co_scale,
        boolean co_desc,boolean contact_person,boolean contact_phone,boolean contact_mail,boolean co_site,
        boolean co_pic,boolean co_cit,boolean co_addr){
            this.data = new InnerData(user_id,cert_status,co_name,co_nickname,industry_type,co_type,org_code,biz_code,org_pic,
        biz_pic,co_scale,co_desc,contact_person,contact_phone,contact_mail,co_site, co_pic,co_cit,co_addr);
        this.check_code=check_code;
    }

    public int getErr_code() {
        return err_code;
    }

    public String getReason() {
        return reason;
    }

    public InnerData getData() {
        return data;
    }

    public String getCheck_code() {
        return check_code;
    }

    public class InnerData{
        private String user_id;
        private boolean cert_status;
        private boolean co_name;
        private boolean co_nickname;
        private boolean industry_type;
        private boolean co_type;
        private boolean org_code;
        private boolean biz_code;
        private boolean org_pic;
        private boolean biz_pic;
        private boolean co_scale;
        private boolean co_desc;
        private boolean contact_person;
        private boolean contact_phone;
        private boolean contact_mail;
        private boolean co_site;
        private boolean co_pic;
        private boolean co_city;
        private boolean co_addr;

        public InnerData(String user_id,boolean cert_status,boolean co_name,boolean co_nickname,boolean industry_type,
                boolean co_type,boolean org_code,boolean biz_code,boolean org_pic,boolean biz_pic,boolean co_scale,
                boolean co_desc,boolean contact_person, boolean contact_phone,boolean contact_mail,boolean co_site,
                boolean co_pic,boolean co_city,boolean co_addr){
            this.user_id =user_id;
            this.cert_status = cert_status;
            this.co_name = co_name;
            this.co_nickname = co_nickname;
            this.industry_type = industry_type;
            this.co_type = co_type;
            this.org_code = org_code;
            this.biz_code = biz_code;
            this.org_pic = org_pic;
            this.biz_pic = biz_pic;
            this.co_scale = co_scale;
            this.co_desc = co_desc;
            this.contact_person = contact_person;
            this.contact_phone = contact_phone;
            this.contact_mail = contact_mail;
            this.co_site = co_site;
            this.co_pic = co_pic;
            this.co_city = co_city;
            this.co_addr = co_addr;
        }

        public String getUser_id() {
            return user_id;
        }

        public boolean isCert_status() {
            return cert_status;
        }

        public boolean isCo_name() {
            return co_name;
        }

        public boolean isCo_nickname() {
            return co_nickname;
        }

        public boolean isIndustry_type() {
            return industry_type;
        }

        public boolean isCo_type() {
            return co_type;
        }

        public boolean isOrg_code() {
            return org_code;
        }

        public boolean isBiz_code() {
            return biz_code;
        }

        public boolean isOrg_pic() {
            return org_pic;
        }

        public boolean isBiz_pic() {
            return biz_pic;
        }

        public boolean isCo_scale() {
            return co_scale;
        }

        public boolean isCo_desc() {
            return co_desc;
        }

        public boolean isContact_person() {
            return contact_person;
        }

        public boolean isContact_phone() {
            return contact_phone;
        }

        public boolean isContact_mail() {
            return contact_mail;
        }

        public boolean isCo_site() {
            return co_site;
        }

        public boolean isCo_pic() {
            return co_pic;
        }

        public boolean isCo_city() {
            return co_city;
        }

        public boolean isCo_addr() {
            return co_addr;
        }
    }

}
