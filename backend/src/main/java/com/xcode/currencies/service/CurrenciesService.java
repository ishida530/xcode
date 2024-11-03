package com.xcode.currencies.service;

import com.xcode.currencies.entity.CurrencyRequestInfoEntity;
import com.xcode.currencies.model.CurrencyRequestDTO;
import com.xcode.currencies.model.CurrencyAPIResponseDTO;
import com.xcode.currencies.model.CurrencyRequestInfoDTO;
import com.xcode.currencies.model.CurrencyResponseDTO;
import com.xcode.currencies.repository.CurrenicesRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class CurrenciesService {

    private final CurrenicesRepository currenicesRepository;
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CurrencyResponseDTO getCurrentCurrencyValue(CurrencyRequestDTO requestDTO) {
        if (StringUtils.isBlank(requestDTO.getCurrency()) || StringUtils.isBlank(requestDTO.getName())) {
            throw new RuntimeException("Currency or Name cannot be null or empty");//TOdo
        }

        CurrencyAPIResponseDTO responseDTO = fetchCurrencyValueFromApi(requestDTO.getCurrency());
        saveCurrencyRequest(requestDTO, responseDTO);
        return CurrencyResponseDTO.builder()
                .value(extractValue( responseDTO))
                .build();
    }

    public BigDecimal extractValue(CurrencyAPIResponseDTO responseDTO) {
        return responseDTO.getRates().stream().findFirst().orElseThrow(()->new RuntimeException("tes2")).getMid();

    }

    public void saveCurrencyRequest(CurrencyRequestDTO requestDTO, CurrencyAPIResponseDTO responseDTO) {
        CurrencyRequestInfoEntity request = new CurrencyRequestInfoEntity();
        request.setCurrency(requestDTO.getCurrency());
        request.setName(requestDTO.getName());
        request.setValue(extractValue( responseDTO));
        currenicesRepository.save(request);

        log.info("Saved currency request: {}", request);
    }
    public CurrencyAPIResponseDTO fetchCurrencyValueFromApi(String currency) {
        String url = String.format("%s/rates/A/%s?format=json", apiUrl, currency);

        try {


            CurrencyAPIResponseDTO currencyAPIResponseDTO = restTemplate.getForObject(url, CurrencyAPIResponseDTO.class);

            if (currencyAPIResponseDTO == null || currencyAPIResponseDTO.getRates().isEmpty()) {
                log.info("Invalid currency code: {}", currency);
                throw new RuntimeException("Invalid currency code");
            }

            return currencyAPIResponseDTO;

        } catch (RestClientException e) {
            log.info("Error fetching currency value from API" + e);
            throw new RuntimeException("Error while fetching currency value: " + e.getMessage());
        }


    }
    public List<CurrencyRequestInfoDTO> getAllCurrencyRequests() {
        return currenicesRepository.findAll()
                .stream().map(CurrencyRequestInfoDTO::create)
                .toList();
    }


}
