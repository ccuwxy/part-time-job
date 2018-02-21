package com.proj.api.auth.backstage.gson;

import com.proj.api.exception.error.Reason;

/**
 * Created by jangitlau on 2017/12/8.
 */
public class GetUserInfoRetGson {
    private int err_code = 0;
    private String reason = Reason.getReason(err_code);
    private InnerData data;
    private String check_code;

    public GetUserInfoRetGson(String user_id, String username, String phone_num, int type, int authority, int status, String _check_code) {
        this.data = new InnerData(user_id, username, phone_num, type, authority, status);
        this.check_code = _check_code;
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

    public class InnerData {
        private String user_id;
        private String username;
        private String phone_num;
        private int type;
        private int authority;
        private int status;

        public InnerData(String user_id, String username, String phone_num, int type, int authority, int status) {
            this.user_id = user_id;
            this.username = username;
            this.phone_num = phone_num;
            this.type = type;
            this.authority = authority;
            this.status = status;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getUsername() {
            return username;
        }

        public String getPhone_num() {
            return phone_num;
        }

        public int getType() {
            return type;
        }

        public int getAuthority() {
            return authority;
        }

        public int getStatus() {
            return status;
        }

        public String getCheck_code() {
            return check_code;
        }
    }
}
