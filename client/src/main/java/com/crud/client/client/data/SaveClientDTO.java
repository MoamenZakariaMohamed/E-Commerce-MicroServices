package com.crud.client.client.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class SaveClientDTO {
    @NotNull
    @Valid
    private List<AddressDTO>  addressDTO;
    @NotNull(message = "الرقم القومي لا يمكن أن يكون فارغ")
    private Integer nationalId;
    @NotBlank(message = "الاسم  لا يمكن أن يكون فارغ")
    private String name;

}
