package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{
}
