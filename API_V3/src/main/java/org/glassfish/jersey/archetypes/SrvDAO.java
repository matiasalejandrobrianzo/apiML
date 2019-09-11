package org.glassfish.jersey.archetypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions.Builder;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query.SortDirection;

public class SrvDAO {

	public static void insert(final long pCount_mutant_dna, final long pCount_human_dna) 
	{
		// recuperacion del datastore
		final DatastoreService datastoreService = SrvDSF.getDatastoreService();
		
		long p1=0,p2=0;		
		for (ClassRegistro item : getRegistro()) {
			p1+=item.count_mutant_dna;
			p2+=item.count_human_dna;
		}
		long countMu= pCount_mutant_dna+p1;
		long countHu= pCount_human_dna+p2;
		delet();
		
		final ClassRegistro source = new ClassRegistro(countMu, countHu);		
	    datastoreService.put(source.getEntity());
	}
	public static void delet() 
	{
		// recuperacion del datastore
		final DatastoreService datastoreService = SrvDSF.getDatastoreService();		
		
		Query query = configureQuery();
		long starttime = (new Date()).getTime();
		for (Entity entity : datastoreService.prepare(query).asIterable()) {
			
			datastoreService.delete(entity.getKey());
		        if ((new Date().getTime()) > (starttime + 10000))
		               break;
		}
	}
	public static List<ClassRegistro> getRegistro() {
		// recuperación del datastore y configuracion de la consulta
		final DatastoreService datastoreService = SrvDSF.getDatastoreService();
		final Query query = configureQuery();
		
		// declaracion de un listado donde volcar los resultados
		final List<ClassRegistro> lstSource = new ArrayList();
		
		// lanzamiento de la consulta, la cual recupera entidades
		for (Entity entity: datastoreService.prepare(query).asIterable())
		{
			// conversion de las entidades a tutoriales
			lstSource.add(convertEntityRegistro (entity));	
		}		
		return lstSource;
	}
	private static ClassRegistro convertEntityRegistro (final Entity entity)
	{	
		final long Count_Mutan = (long) entity.getProperty(ClassRegistro.Count_Mutan);
		final long Count_Human = (long) entity.getProperty(ClassRegistro.Count_Human);
		
		return new ClassRegistro(Count_Mutan, Count_Human);
	}
	 private static Query configureQuery () {
		final Query query = new Query(ClassRegistro.REGISTRO_ENTITY);
		return query;
	}
	

}
