package med.voll.api.domain.doctor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByActiveTrue(Pageable pageable);

    @Query("""
        SELECT d FROM Doctor d
        WHERE d.active = true
        AND d.specialty = :specialty
        AND d.id NOT IN (
            SELECT a.doctor.id FROM Appointment a
            WHERE a.date = :date
        )
        ORDER BY RAND()
        LIMIT 1
        """)
    Doctor selectRandomAvailableDoctorOnDate(Specialty specialty, @NotNull @Future LocalDateTime date);

    @Query("""
        SELECT d.active FROM Doctor d
        WHERE d.id = :doctorId
        """)
    boolean findActiveById(Long doctorId);
}
