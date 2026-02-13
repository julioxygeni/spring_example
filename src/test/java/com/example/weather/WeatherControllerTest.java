package com.example.weather;

import com.example.weather.controller.WeatherController;
import com.example.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
@Import(WeatherService.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCities_returnsAllCities() throws Exception {
        mockMvc.perform(get("/api/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].name", is("New York")))
                .andExpect(jsonPath("$[0].country", is("USA")));
    }

    @Test
    void getForecast_returnsFiveDayForecast() throws Exception {
        mockMvc.perform(get("/api/forecast").param("city", "Tokyo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].city", is("Tokyo")))
                .andExpect(jsonPath("$[0].temperatureC", isA(Integer.class)))
                .andExpect(jsonPath("$[0].condition", notNullValue()));
    }

    @Test
    void getForecast_unknownCity_returnsEmptyList() throws Exception {
        mockMvc.perform(get("/api/forecast").param("city", "Atlantis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
