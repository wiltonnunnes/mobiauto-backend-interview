package com.wilton.mobiauto_backend_interview.controller;

public class VideoController {
    
    private static String encodeId(Long id) {
        String out = "";
        String index = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int base = index.length();

        for(int t = (int) (id != 0 ? Math.floor(customLog(base, id)) : 0); t >= 0; t--) {
            double bcp = Math.pow(base, t);
            double a = Math.floor(id / bcp) % base;
            out += index.substring((int) a, (int) (a + 1));
            id -= (int) (a * bcp);
        }

        return out;
    }

    private static double customLog(double base, double logNumber) {
        return Math.log(logNumber) / Math.log(base);
    }
}
