package com.flmanager.feedlotservice.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProviderRequest {
    @Size(min = 3)
    private String name;
    @Size(min = 3)
    private String lastName;
    @Size(min = 3)
    private String address;
    @Size(min = 6, max = 20)
    private String phone;
    @Email
    private String email;
}
