package org.glassfish.jersey.archetypes;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.appengine.api.datastore.Entity;
import com.googlecode.objectify.annotation.Id;

public class ClassRegistro {
	
	public long  count_mutant_dna;
	public long  count_human_dna;
    public static final String REGISTRO_ENTITY = "Registro";
	public static final String Count_Mutan = "count_mutant_dna";
	
	public static final String Count_Human = "count_human_dna";	
		
	private Entity entity = new Entity (REGISTRO_ENTITY);
		
	
	public ClassRegistro(long pCount_mutant_dna, long pCount_human_dna) {
		
		this.count_mutant_dna= pCount_mutant_dna;
		this.count_human_dna=pCount_human_dna;
		
		entity.setProperty(Count_Mutan, pCount_mutant_dna);
		entity.setProperty(Count_Human, pCount_human_dna);		
	}
	
	public long getCount_mutant_dna() {
		 return (long) entity.getProperty(Count_Mutan); 
	}
	public long getCount_human_dna() {
		return (long) entity.getProperty(Count_Human);
	}	
	public Entity getEntity () {
		return entity;
	}
}

 