package br.com.workmade.algamoneybackendapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LancamentoFilter {
	
	private String descricao;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVencimentoDe;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataVencimentoAte;

}
