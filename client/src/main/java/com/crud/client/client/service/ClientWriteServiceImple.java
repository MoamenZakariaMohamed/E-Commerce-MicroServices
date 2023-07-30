package com.crud.client.client.service;

import com.crud.client.client.domain.Address;
import com.crud.client.infrastructure.exceptions.DuplicateResourceException;
import com.crud.client.infrastructure.globalResponse.GlobalResponse;
import com.crud.client.client.data.EntityMapper;
import com.crud.client.infrastructure.exceptions.DataHasNotChangedException;
import com.crud.client.infrastructure.exceptions.NotFoundException;
import com.crud.client.client.data.SaveClientDTO;
import com.crud.client.client.domain.Client;
import com.crud.client.client.repository.ClientRepository;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientWriteServiceImple implements ClientWriteService {
    private final ClientRepository clientRepository;
    private final EntityMapper entityMapper;


    @Override
    public Client saveClient(SaveClientDTO formRequest) {
        List<Address> addressList = formRequest.getAddressDTO().stream()
                .map(entityMapper::mapToAddress)
                .collect(Collectors.toList());
        Client client = Client.createClient(formRequest, addressList);

        try {
            Client submitForm = clientRepository.save(client);
            return submitForm;
        } catch (
                DataIntegrityViolationException e) {
            throw new DuplicateResourceException(e.getMessage());
        }
    }

    @Override
    public Client updateClient(SaveClientDTO updateRequest , Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NotFoundException("this client not found" + clientId));
        List<Address> addressList = updateRequest.getAddressDTO().stream()
                .map(entityMapper::mapToAddress)
                .collect(Collectors.toList());

        boolean changes = false;
        changes = Client.updateIfNotSame(changes, updateRequest, addressList, client );
        if (!changes) {
            throw new DataHasNotChangedException("لم يوجد اي تغيير في البيانات ");
        }

        try {
            Client submitForm = clientRepository.save(client);
            return submitForm;

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException(e.getMessage());        }
    }

}
