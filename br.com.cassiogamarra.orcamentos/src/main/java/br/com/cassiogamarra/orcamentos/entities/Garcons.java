package br.com.cassiogamarra.orcamentos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Garcons {
	
	private int numGarcons;
	private double txGargons;
}
