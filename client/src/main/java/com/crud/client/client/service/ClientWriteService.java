package com.crud.client.client.service;

import com.crud.client.client.data.SaveClientDTO;
import com.crud.client.client.domain.Client;
import com.crud.client.infrastructure.globalResponse.GlobalResponse;

import java.util.Collection;

public interface ClientWriteService {
    Client saveClient(SaveClientDTO formRequest );
    Client updateClient(SaveClientDTO formRequest , Long clientId );

}
