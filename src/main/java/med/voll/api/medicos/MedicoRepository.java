package med.voll.api.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//<QuemVouMapear, TipoDaChavePrimaria>
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);//segundo o padrao de nomeclatura, o Spring monta o sql

}
