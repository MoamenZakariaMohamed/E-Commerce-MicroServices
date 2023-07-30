package com.crud.client.client.data;

import com.crud.client.client.domain.Address;
import com.crud.client.client.data.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityMapper {
    private final ModelMapper modelMapper;



    public Address mapToAddress(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }
}
