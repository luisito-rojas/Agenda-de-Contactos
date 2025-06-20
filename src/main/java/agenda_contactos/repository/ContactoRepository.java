package agenda_contactos.repository;

import agenda_contactos.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepository  extends JpaRepository<Contacto, Integer> {
}
