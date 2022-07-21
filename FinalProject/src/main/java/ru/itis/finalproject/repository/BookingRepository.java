package ru.itis.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.finalproject.models.BookingEntity;

import java.util.Date;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query("select count(b) = 0 from BookingEntity b where :start <= b.dateEnd AND :end >= b.dateStart and b.apartment.id = :id")
    Boolean isAvailable(@Param("id") Long id, @Param("start") Date start, @Param("end") Date end);
}