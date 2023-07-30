package com.crud.client.client.data;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class AddressDTO {
    @NotBlank(message = "اسم الشارع  لا يمكن أن يكون فارغ")
    private String street;
    @NotBlank(message = "اسم المدينة  لا يمكن أن يكون فارغ")
     private String city;
    @NotBlank(message = "اسم الولاية  لا يمكن أن يكون فارغ")
     private String state;
    @NotBlank(message = "الرقم البريدي  لا يمكن أن يكون فارغ")
     private String zipCode;
}
