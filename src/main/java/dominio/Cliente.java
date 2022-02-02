package dominio;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 20)
    private String cpf;


}
