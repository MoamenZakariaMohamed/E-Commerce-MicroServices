package com.crud.client.client.domain;


import com.crud.client.client.data.SaveClientDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "client")
@Table(name = "client", uniqueConstraints = {@UniqueConstraint(name = "client_national_id_unique", columnNames = "national_id")})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "national_id", nullable = false)
    private Integer nationalId;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Address> addresses;


    public static Client createClient(SaveClientDTO saveClientDTO, List<Address> addresses) {
        return Client.builder()
                .name(saveClientDTO.getName())
                .nationalId(saveClientDTO.getNationalId())
                .addresses(addresses)
                .build();

    }


    public static boolean updateIfNotSame(
            boolean changes, SaveClientDTO updateRequest, List<Address> addresses, Client client) {
        boolean modified = changes;

        if (!updateRequest.getName().equals(client.getName())) {
            client.setName(updateRequest.getName());
            modified = true;
        }

        if (!updateRequest.getNationalId().equals(client.getNationalId())) {
            client.setNationalId(updateRequest.getNationalId());
            modified = true;
        }

        if (!addresses.equals(client.getAddresses())) {
            client.setAddresses(addresses);
            modified = true;
        }

        return modified;
    }
}
