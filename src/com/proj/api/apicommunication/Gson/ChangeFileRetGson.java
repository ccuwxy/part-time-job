package com.proj.api.apicommunication.Gson;

import com.proj.api.exception.error.Reason;

import java.util.ArrayList;
import java.util.List;

public class ChangeFileRetGson {
        private int err_code=0;
        private String reason= Reason.getReason(0);

        public ChangeFileRetGson() { }
        public int getErr_code() {
            return err_code;
        }

        public String getReason() {
            return reason;
        }

}