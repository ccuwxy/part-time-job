package com.proj.api.auth.registration.gson;

import com.proj.api.exception.error.Reason;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class PreRegistrationRetGson {
    private int err_code=0;
    private String reason= Reason.getReason(0);
    private InnerData data;

    public PreRegistrationRetGson(String phone_num, String key) {
        this.data = new InnerData(phone_num,key);
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


    public class InnerData{
        private String phone_num;

        private String key;

        public InnerData(String phone_num, String key) {
            this.phone_num = phone_num;
            this.key = key;
        }

        public String getPhone_num() {
            return phone_num;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setPhone_num(String phone_num) {
            this.phone_num = phone_num;
        }
    }
}
