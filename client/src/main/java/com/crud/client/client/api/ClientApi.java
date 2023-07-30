package com.crud.client.client.api;

import com.crud.client.client.data.SaveClientDTO;
import com.crud.client.client.domain.Address;
import com.crud.client.client.domain.Client;
import com.crud.client.client.repository.AddressRepository;
import com.crud.client.client.repository.ClientRepository;
import com.crud.client.client.service.ClientWriteService;
import com.crud.client.infrastructure.exceptions.NotFoundException;
import com.crud.client.infrastructure.globalResponse.GlobalResponse;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Validated

public class ClientApi {
    private final ClientWriteService clientWriteService;
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;



    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@Valid @RequestBody SaveClientDTO formRequest ) {
        return clientWriteService.saveClient(formRequest);
    }


    @PutMapping("{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public Client updateClient(@Valid @RequestBody SaveClientDTO updateRequest, @PathVariable("id") Long reportId) {
        return  clientWriteService.updateClient(updateRequest, reportId);
    }


    @GetMapping("all")
    public Collection<Client> reportsDetail() throws Exception {
       Collection<Client> client = clientRepository.findAll();
        return client;

    }

    @GetMapping("{id}")
    public Client reportDetail(@PathVariable Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("this client not found" + id));
        return client;

    }


    @GetMapping("/productsno")
    public List<Address> getProductsWithout() {
        Instant start = Instant.now();

        List<Address> addresses = addressRepository.findAll();

        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        System.out.println("Query completed in " + duration + " milliseconds");

        return addresses;
    }


//    @GetMapping("/most-requested-products")
//    public List<Product> getMostRequestedProducts() {
//        // Get the list of all customer orders.
//        List<CustomerOrder> customerOrders = customerOrderRepository.findAll();
//
//        // Create a map to store the number of times each product has been ordered.
//        Map<Product, Integer> productCountMap = customerOrders.stream()
//                .flatMap(customerOrder -> customerOrder.getProducts().stream())
//                .collect(Collectors.groupingBy(Product::getId, Collectors.counting()));
//
//        // Get the list of the most requested products.
//        List<Product> mostRequestedProducts = productCountMap.entrySet().stream()
//                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
//                .limit(10)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
//
//        return mostRequestedProducts;
//    }


    @GetMapping("/allProducts")
    public CompletableFuture<List<Address>> getProducts() {
        Instant start = Instant.now();

        CompletableFuture<List<Address>> future = CompletableFuture.supplyAsync(() -> {
            return addressRepository.findAll();
        });

        future.thenAcceptAsync((result) -> {
            Instant end = Instant.now();
            long duration = Duration.between(start, end).toMillis();
            System.out.println("Query completed in " + duration + " milliseconds");
        });

        return future;
    }

    @GetMapping("/products")
    public ResponseEntity<Resource> downloadAddress() throws IOException {
        // Retrieve the product data from the ProductService
        List<Address> products = addressRepository.findAll();

        // Use a virtual thread to execute the CSV generation
        byte[] csvBytes = ForkJoinPool.commonPool().submit(() -> {
            try {
                // Use OpenCSV to write the product data to a byte array
                StringWriter writer = new StringWriter();
                StatefulBeanToCsv<Address> csvWriter = new StatefulBeanToCsvBuilder<Address>(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withSeparator(',')
                        .withOrderedResults(false)
                        .build();
                csvWriter.write(products);
                return writer.toString().getBytes(StandardCharsets.UTF_8);

            } catch (CsvException e) {
                e.printStackTrace();
                return null;
            }
        }).join();

        // Create a ByteArrayResource to represent the CSV file contents
        ByteArrayResource resource = new ByteArrayResource(csvBytes);

        // Return a ResponseEntity with the CSV file as the response body
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"products.csv\"")
                .contentLength(csvBytes.length)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }



    @GetMapping("/products.csv")
    public void downloadProducts(HttpServletResponse response) throws IOException {
        // Set the content type and headers for the response
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"products.csv\"");

        // Retrieve the product data from the ProductService
        List<Address> products = addressRepository.findAll();

        // Use a virtual thread to execute the CSV generation
        ForkJoinPool.commonPool().execute(() -> {
            try {
                // Use OpenCSV to write the product data to the response output stream
                StatefulBeanToCsv<Address> writer = new StatefulBeanToCsvBuilder<Address>(response.getWriter())
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withSeparator(',')
                        .withOrderedResults(false)
                        .build();
                writer.write(products);

            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        });
    }

}
