package med.voll.api.domain.consultas;

import org.springframework.stereotype.Service;

@Service
public class AgendaConsultasService {

    // this is where the business logic should be
    public void agendarConsulta(ConsultaRequestDTO consulta) {
        System.out.println("NOT implemented: " + consulta.toString());
    }
}
