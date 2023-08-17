package med.voll.api.domain.medicos;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//<QuemVouMapear, TipoDaChavePrimaria>
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);//segundo o padrao de nomeclatura, o Spring monta o sql
	

	@Query("""
            select m from Medico m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                    select c.medico.id from Consulta c
                    where
                    c.data = :data
            )
            order by rand()
            limit 1
            """)
	Medico escolherMedicoAleatorioLivreData(Especialidade especialidade, LocalDateTime data);
	
	@Query("""
			select m.ativo
			from Medico as m
			where m.id = :idMedico
			""")
	Boolean findAtivoId(Long idMedico);
	

}
