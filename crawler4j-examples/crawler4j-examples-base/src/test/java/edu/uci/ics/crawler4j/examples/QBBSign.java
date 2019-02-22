package edu.uci.ics.crawler4j.examples;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.security.SignatureException;
import java.util.*;

public class QBBSign {

    //https://apipt.qbb6.com/lib/items/group/search/v2?
    // protocol=1&channel=1015&appKey=48vt2wum0nev4s68
    // &lang=zh-CN&key=%25E5%25AE%259D%25E5%25AE%259D
    // &versionCode=307&token=d9eb40ba-215e-4646-b94b-f4276c390a0c
    // &timestamp=1550708609396&
    //sign=oOIMshlj3obTAo0OffzrSB536nc=
//    https://apipt.qbb6.com/lib/items/group/search/v2?
//    protocol=1
//    channel=1015
//    appKey=48vt2wum0nev4s68
//    lang=zh-CN
//    key=%25E5%25AE%259D%25E5%25AE%259D%25E5%2593%25AD%25E9%2597%25B9
//    versionCode=307
//    token=d9eb40ba-215e-4646-b94b-f4276c390a0c
//    timestamp=1550747504176
//    sign=jQHUu8ZlVy3x0cARdWHqow9sj1w=
    public static void main(String[] args) throws Exception {
        String token = "d9eb40ba-215e-4646-b94b-f4276c390a0c";
        String APP_KEY = "48vt2wum0nev4s68";
        String APP_SECRET = "a78f3bec60";
        //String APP_SECRET = new String(Hex.decodeHex("a78f3bec60".toCharArray()));

        Map<String, Object> map = new HashMap<>();
        map.put("protocol", 1);
        map.put("channel", 1015);
        map.put("appKey", APP_KEY);
        map.put("lang", "zh-CN");
        map.put("key", "%25E5%25AE%259D%25E5%25AE%259D%25E5%2593%25AD%25E9%2597%25B9");
        map.put("versionCode", 307);
        map.put("token", token);
        map.put("timestamp", "1550747504176");

        List<Object> valList = new ArrayList<>();
        StringBuffer url = new StringBuffer();
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            String key = next.getKey();
            Object value = next.getValue();

            if(url.length() > 0){
                url.append("&");
            }
            url.append(key).append("=").append(value);

            valList.add(value);
        }

        String urlStr = url.toString();
        Collections.sort(valList, Comparator.comparing(String::valueOf));

        StringBuffer sb = new StringBuffer();
        sb.append(APP_SECRET);
        for (int i=0;i<valList.size();i++) {
            Object val = valList.get(i);
            sb.append(paramURIDecode(val.toString()));
        }
        sb.append(APP_SECRET);

        String sign = calculateRFC2104HMAC(sb.toString(), APP_SECRET);

        System.out.println("sign => " + sign);
    }

    public static String paramURIDecode(String var0) {
        try {
            return URLDecoder.decode(URLDecoder.decode(var0, "utf-8"), "utf-8");
        } catch (Exception var1) {
            var1.printStackTrace();
            return null;
        }
    }

    public static String calculateRFC2104HMAC(String var0, String var1) throws SignatureException {
        try {

            SecretKeySpec var5 = new SecretKeySpec(var1.getBytes(), "HmacSHA1");
            Mac var2 = Mac.getInstance("HmacSHA1");
            var2.init(var5);
            var0 = Base64.encodeToString(var2.doFinal(var0.getBytes()), true);
            return var0;
        } catch (Exception var3) {
            StringBuilder var4 = new StringBuilder();
            var4.append("Failed to generate HMAC : ");
            var4.append(var3.getMessage());
            throw new SignatureException(var4.toString());
        }
    }
}
