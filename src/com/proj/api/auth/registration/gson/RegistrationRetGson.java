package com.proj.api.auth.registration.gson;

import com.proj.api.exception.error.Reason;

/**
 * Created by jangitlau on 2017/11/3.
 */
public class RegistrationRetGson {
    private int err_code = 0;
    private String reason = Reason.getReason(0);
    private InnerData data;

    public RegistrationRetGson(String login_id, String user_id, String username, String phone_num, int type, int authority, int status, String pre_token, int expire) {
        this.data = new InnerData(login_id, user_id, username, phone_num, type, authority, status, pre_token, expire);
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
        String login_id;
        String user_id;
        String username;
        String phone_num;
        int type;
        int authority;
        int status;
        String pre_token;
        int token_expire;

        public InnerData(String login_id, String user_id, String username, String phone_num, int type, int authority, int status, String pre_token, int expire) {
            this.login_id = login_id;
            this.user_id = user_id;
            this.username = username;
            this.phone_num = phone_num;
            this.type = type;
            this.authority = authority;
            this.status = status;
            this.pre_token = pre_token;
            this.token_expire = expire;
        }

        public String getLogin_id() {
            return login_id;
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

        public String getPre_token() {
            return pre_token;
        }

        public int getToken_expire() {
            return token_expire;
        }
    }

}
