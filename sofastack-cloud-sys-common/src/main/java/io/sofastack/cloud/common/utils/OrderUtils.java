package io.sofastack.cloud.common.utils;

import io.sofastack.cloud.common.model.TransferRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/3/11 11:23 PM
 * @since:
 **/
public class OrderUtils {

    public static String getSerialNo(TransferRequest request) {
        return currentTimeString() + getRandom(request);
    }

    public static String getTradingNo(TransferRequest request) {
        return currentTimeString() + getRandom(request);
    }

    private static String currentTimeString() {
        DateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        return format.format(new Date());
    }

    private static String getRandom(TransferRequest request) {
        if (request == null) {
            int sa = new Random().nextInt(1000);
            return String.format("%03d", sa);
        }
        int salt = request.getUserId() + request.hashCode();
        return String.format("%03d", salt > 100 ? salt % 100 : salt);
    }
}
