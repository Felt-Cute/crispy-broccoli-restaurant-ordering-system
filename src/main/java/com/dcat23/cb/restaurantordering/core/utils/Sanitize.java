package com.dcat23.cb.restaurantordering.core.utils;

import org.springframework.util.StringUtils;

public class Sanitize {
    public static String lower(String text) {
        if (text == null) return "";
        return text.toLowerCase().strip();
    }
}
