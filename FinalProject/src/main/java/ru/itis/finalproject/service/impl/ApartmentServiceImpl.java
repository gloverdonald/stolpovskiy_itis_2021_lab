package ru.itis.finalproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.finalproject.dto.ApartmentSearchDto;
import ru.itis.finalproject.dto.page.ApartmentPage;
import ru.itis.finalproject.dto.request.ApartmentRequest;
import ru.itis.finalproject.dto.request.ApartmentWithAddressRequest;
import ru.itis.finalproject.dto.response.ApartmentResponse;
import ru.itis.finalproject.exceptions.AddressNotFoundException;
import ru.itis.finalproject.exceptions.ApartmentNotFoundException;
import ru.itis.finalproject.exceptions.UserNotFoundException;
import ru.itis.finalproject.mapper.AddressMapper;
import ru.itis.finalproject.mapper.ApartmentMapper;
import ru.itis.finalproject.models.AddressEntity;
import ru.itis.finalproject.models.ApartmentEntity;
import ru.itis.finalproject.models.UserEntity;
import ru.itis.finalproject.repository.AddressRepository;
import ru.itis.finalproject.repository.ApartmentRepository;
import ru.itis.finalproject.repository.BookingRepository;
import ru.itis.finalproject.repository.UserRepository;
import ru.itis.finalproject.service.ApartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    private final Integer defaultPageSize = 5;

    @Override
    public Boolean isAvailable(ApartmentSearchDto searchDto) {
        return bookingRepository.isAvailable(searchDto.getId(), searchDto.getDateStart(), searchDto.getDateEnd());
    }

    @Override
    public ApartmentResponse save(ApartmentRequest apartmentRequest) {
        AddressEntity addressEntity = addressRepository
                .findById(apartmentRequest.getAddressId()).orElseThrow(AddressNotFoundException::new);
        UserEntity userEntity = userRepository
                .findById(apartmentRequest.getOwnerId()).orElseThrow(UserNotFoundException::new);

        ApartmentEntity apartment = apartmentMapper.toApartment(apartmentRequest);
        apartment.setAddress(addressEntity);
        apartment.setOwner(userEntity);
        return apartmentMapper.toResponse(apartmentRepository.save(apartment));
    }

    @Override
    public ApartmentResponse get(Long id) {
        ApartmentEntity apartment = apartmentRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);
        return apartmentMapper.toResponse(apartment);
    }

    @Override
    public ApartmentPage getApartmentByCity(Integer page, String param, String city) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.by(param));
        Page<ApartmentEntity> postPage = apartmentRepository.findByAddress_CityIgnoreCase(city, pageRequest);
        return ApartmentPage.builder()
                .apartments(apartmentMapper.toResponses(postPage.getContent()))
                .totalPages(postPage.getTotalPages())
                .build();
    }

    @Override
    public List<ApartmentResponse> getAll() {
        return apartmentMapper.toResponses(apartmentRepository.findAll());
    }

    @Override
    public ApartmentResponse saveWithAddress(ApartmentWithAddressRequest apartmentWithAddressRequest) {
        UserEntity userEntity = userRepository
                .findById(apartmentWithAddressRequest.getOwnerId()).orElseThrow(UserNotFoundException::new);
        ApartmentEntity apartmentEntity = apartmentMapper.toApartment(apartmentWithAddressRequest);
        AddressEntity addressEntity = addressMapper.toAddress(apartmentWithAddressRequest);
        apartmentEntity.setAddress(addressRepository.save(addressEntity));
        apartmentEntity.setOwner(userEntity);
        return apartmentMapper.toResponse(apartmentRepository.save(apartmentEntity));
    }
}
