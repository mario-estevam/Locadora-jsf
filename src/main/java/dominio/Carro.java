package dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;

    @Column(nullable = false)
    private boolean status = false;

    @Column(nullable = false, length = 50)
    private String nomeCarro;

    @Column(nullable = false)
    private String Descricao;

    @Column(nullable = false)
    private LocalDate deadLine;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;


}
