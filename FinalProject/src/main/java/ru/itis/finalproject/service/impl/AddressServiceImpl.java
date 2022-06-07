package ru.itis.finalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.finalproject.dto.request.AddressRequest;
import ru.itis.finalproject.dto.response.AddressResponse;
import ru.itis.finalproject.exceptions.AddressNotFoundException;
import ru.itis.finalproject.mapper.AddressMapper;
import ru.itis.finalproject.models.AddressEntity;
import ru.itis.finalproject.repository.AddressRepository;
import ru.itis.finalproject.service.AddressService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressResponse save(AddressRequest addressRequest) {
        AddressEntity address = addressMapper.toAddress(addressRequest);
        return addressMapper.toResponse(addressRepository.save(address));
    }

    @Override
    public AddressResponse get(Long id) {
        AddressEntity address = addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);
        return addressMapper.toResponse(address);
    }

    @Override
    public List<AddressResponse> getAll() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toResponse)
                .collect(Collectors.toList());
    }
}
