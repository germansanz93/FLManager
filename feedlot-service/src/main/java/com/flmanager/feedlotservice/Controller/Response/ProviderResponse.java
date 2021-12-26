package com.flmanager.feedlotservice.Controller.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderResponse {
    private String idProvider;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
}
