package com.proj.api.auth.backstage.gson;

import com.proj.api.exception.error.Reason;

/**
 * Created by jangitlau on 2017/12/8.
 */
public class BackgroundInfoDetailRetGson {
    private int err_code=0;
    private String reason= Reason.getReason(err_code);
    private InnerData data;
    private String check_code;

    public BackgroundInfoDetailRetGson(String user_id, String check_code) {
        this.data = new InnerData(user_id);
        this.check_code = check_code;
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
        public String user_id;

        public InnerData(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_id() {
            return user_id;
        }
    }
}
