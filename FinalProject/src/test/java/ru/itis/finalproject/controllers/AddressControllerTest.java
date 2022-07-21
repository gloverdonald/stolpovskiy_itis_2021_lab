package ru.itis.finalproject.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.finalproject.dto.request.AddressRequest;
import ru.itis.finalproject.dto.response.AddressResponse;
import ru.itis.finalproject.repository.BlackListRepository;
import ru.itis.finalproject.security.details.UserDetailsServiceImpl;
import ru.itis.finalproject.service.AddressService;
import ru.itis.finalproject.service.impl.TokenAuthorizationServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
@DisplayName("AddressController is working when ...")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AddressControllerTest {
    private static final AddressRequest NEW_ADDRESS = AddressRequest.builder()
            .country("RUSSIA").city("KAZAN").street("ST").houseNumber("25").build();

    private static final AddressResponse CREATE_ADDRESS = AddressResponse.builder()
            .id(3L).country("RUSSIA").city("KAZAN").street("ST").houseNumber("25").build();


    private static final AddressResponse ADDRESS_FIRST = AddressResponse.builder()
            .id(1L).country("RUSSIA").city("KAZAN").street("ST").houseNumber("15").build();

    private static final AddressResponse ADDRESS_SECOND = AddressResponse.builder()
            .id(2L).country("RUSSIA").city("MOSCOW").street("ST").houseNumber("34").build();


    private static final List<AddressResponse> ADDRESS_LIST = Arrays.asList(ADDRESS_FIRST, ADDRESS_SECOND);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private TokenAuthorizationServiceImpl tokenAuthorizationService;

    @MockBean
    private BlackListRepository blackListRepository;

    @Value("${test.token}")
    private String token;

    @BeforeEach
    void setUp() {
        when(addressService.save(NEW_ADDRESS)).thenReturn(CREATE_ADDRESS);
        when(addressService.get(3L)).thenReturn(CREATE_ADDRESS);
        when(addressService.getAll()).thenReturn(ADDRESS_LIST);
    }


    @Test
    public void return_401_without_auth() throws Exception {
        mockMvc.perform(get("/address/all"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void return_addresses() throws Exception {
        mockMvc.perform(get("/address/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].city", is("KAZAN")));
    }

    @Test
    @WithMockUser
    public void add_address() throws Exception {
        mockMvc.perform(post("/address/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\"country\":\"RUSSIA\",\n" +
                                "\"city\":\"KAZAN\",\n" +
                                "\"street\":\"ST\",\n" +
                                "\"houseNumber\":\"25\"\n" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id", is(3)))
                .andExpect(jsonPath("city", is("KAZAN")))
                .andExpect(jsonPath("street", is("ST")))
                .andDo(print());
    }

    @Test
    @WithMockUser
    public void return_address() throws Exception {
        mockMvc.perform(get("/address/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(3)))
                .andExpect(jsonPath("city", is("KAZAN")));
    }

}
