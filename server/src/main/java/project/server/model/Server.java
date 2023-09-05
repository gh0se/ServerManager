package project.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.server.enumeration.Status;

import static jakarta.persistence.GenerationType.AUTO;

@Entity

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
//lombok

public class Server {
    @Id @GeneratedValue(strategy = AUTO)
    private long id;
    @Column(unique = true)
    @NotEmpty(message = "IP Adress cannot be empty or NULL!")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;
}