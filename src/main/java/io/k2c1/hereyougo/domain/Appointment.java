package io.k2c1.hereyougo.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Appointment
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Member supplier;

    @ManyToOne
    @JoinColumn(name = "demand_id")
    private Member demand;

    private LocalDateTime timestamp;
}
