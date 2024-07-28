package com.dcat23.cb.restaurantordering.user.dto;

import java.util.Date;

public record JwtToken(String accessToken, Date expiration) {
}
