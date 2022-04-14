package dev.iskandev.internshiptestrussianpost.addresses.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dev.iskandev.internshiptestrussianpost.addresses.model.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AddressControllerTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    private static final String URL = "https://localhost/v1.0/addresses/";

    private static final String MOCK_ID = "e1fffad0-bbb7-11ec-8422-0242ac120002";

    private static final String MOCK_ID_URL = URL + MOCK_ID;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testDeleteGetResponseNotFound() throws Exception {
        this.mockMvc.perform(delete(MOCK_ID_URL));
        this.mockMvc.perform(get(MOCK_ID_URL))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletePostGetDeleteAddressResponseNoContent() throws Exception {
        var requestJson = parseToJson(createMockAddress());

        /* Mock requests */
        this.mockMvc.perform(delete(MOCK_ID_URL));
        this.mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(get(MOCK_ID_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{" +
                        "  \"id\": \"e1fffad0-bbb7-11ec-8422-0242ac120002\"," +
                        "  \"instruction\": null," +
                        "  \"index\": null," +
                        "  \"region\": null," +
                        "  \"area\": null," +
                        "  \"place\": null," +
                        "  \"district\": null," +
                        "  \"street\": null," +
                        "  \"house\": null," +
                        "  \"letter\": null," +
                        "  \"slash\": null," +
                        "  \"corpus\": null," +
                        "  \"building\": null," +
                        "  \"room\": null," +
                        "  \"plain_address\": \"test\"" +
                        "}"
                ));
        this.mockMvc.perform(delete(MOCK_ID_URL))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeletePostPutGetDeleteResponseNoContent() throws Exception {
        var mockAddress = createMockAddress();

        /* Mock requests */
        this.mockMvc.perform(delete(MOCK_ID_URL));
        this.mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(parseToJson(mockAddress)))
                .andExpect(status().isNoContent());

        // update address's attribute
        mockAddress.setPlainAddress("new");

        this.mockMvc.perform(put(MOCK_ID_URL)
                .contentType(APPLICATION_JSON_UTF8)
                .content(parseToJson(mockAddress)));
        this.mockMvc.perform(get(MOCK_ID_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{" +
                                "  \"id\": \"e1fffad0-bbb7-11ec-8422-0242ac120002\"," +
                                "  \"instruction\": null," +
                                "  \"index\": null," +
                                "  \"region\": null," +
                                "  \"area\": null," +
                                "  \"place\": null," +
                                "  \"district\": null," +
                                "  \"street\": null," +
                                "  \"house\": null," +
                                "  \"letter\": null," +
                                "  \"slash\": null," +
                                "  \"corpus\": null," +
                                "  \"building\": null," +
                                "  \"room\": null," +
                                "  \"plain_address\": \"new\"" +
                                "}"
                ));
        this.mockMvc.perform(delete(MOCK_ID_URL))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeletePostPostDeleteResponseConflict() throws Exception {
        var requestJson = parseToJson(createMockAddress());

        /* Mock requests */
        this.mockMvc.perform(delete(MOCK_ID_URL));
        this.mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isNoContent());
        this.mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isConflict());
        this.mockMvc.perform(delete(MOCK_ID_URL))
                .andExpect(status().isNoContent());
    }

    private Address createMockAddress() {
        var address = new Address();
        address.setId(UUID.fromString(MOCK_ID));
        address.setPlainAddress("test");
        return address;
    }

    private String parseToJson(Object obj) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        var writer = mapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(obj);
    }
}