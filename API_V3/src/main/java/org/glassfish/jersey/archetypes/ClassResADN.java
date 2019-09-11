package org.glassfish.jersey.archetypes;

import java.util.List;

public class ClassResADN {

	private long count_mutant_dna=0, count_human_dna=0;
	float ratio=0;
	
	public ClassResADN()
	{
		long count_mutant_dna=0, count_human_dna=0;
		float ratio=0;
	}

	public long getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(long countMut) {
		this.count_mutant_dna = countMut;
	}

	public long getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(long countHuman) {
		this.count_human_dna = countHuman;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) {
		this.ratio = ratio;
	}

	public void CalculoPromedio(List<ClassRegistro> reg) {
		
		for (ClassRegistro item : reg) {
			
			if(item.count_human_dna!=0&&item.count_mutant_dna!=0)
			{
				this.ratio=(float)(item.count_mutant_dna/item.count_human_dna);
				setCount_human_dna(item.count_human_dna);
				setCount_mutant_dna(item.count_mutant_dna);
			}	
			else
			{
				setCount_human_dna(item.count_human_dna);
				setCount_mutant_dna(item.count_mutant_dna);
			}
		}	
	}
}

