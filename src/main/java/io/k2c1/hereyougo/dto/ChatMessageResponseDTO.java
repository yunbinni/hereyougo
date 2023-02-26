package io.k2c1.hereyougo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChatMessageResponseDTO {
    private String sender;
    private String message;
    private LocalDateTime sendDate;
}
