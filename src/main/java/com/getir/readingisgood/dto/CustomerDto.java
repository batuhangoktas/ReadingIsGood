package com.getir.readingisgood.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    @ApiModelProperty(hidden = true)
    private String id;
    private String name;
    private String email;
    private String password;

    public void setId(String id) {
        this.id = id;
    }
}
