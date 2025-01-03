package med.voll.api.doctor;

import com.jayway.jsonpath.JsonPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByActiveTrue(Pageable pageable);
}
