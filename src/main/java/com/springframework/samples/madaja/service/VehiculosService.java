package com.springframework.samples.madaja.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.repository.CambioRepository;
import com.springframework.samples.madaja.repository.CombustibleRepository;
import com.springframework.samples.madaja.repository.ConcesionarioRepository;
import com.springframework.samples.madaja.repository.DisponibleRepository;
import com.springframework.samples.madaja.repository.SeguroVehiculoRepository;
import com.springframework.samples.madaja.repository.VehiculosRepository;

@Service
public class VehiculosService {
	private VehiculosRepository vehiculosRepository;	
	private CambioRepository cambioRepository;
	private ConcesionarioRepository concesionarioRepository;
	private CombustibleRepository combustibleRepository;
	private DisponibleRepository disponibleRepository;
	private SeguroVehiculoRepository seguroVehiculoRepository;
	private EntityManager entityManager;
	
	@Autowired
	public VehiculosService(VehiculosRepository vehiculosRepository, CambioRepository cambioRepository, 
			ConcesionarioRepository concesionarioRepository, CombustibleRepository combustibleRepository,
			DisponibleRepository disponibleRepository, SeguroVehiculoRepository seguroVehiculoRepository, EntityManager entityManager) {
		this.vehiculosRepository=vehiculosRepository;
		this.cambioRepository=cambioRepository;
		this.concesionarioRepository=concesionarioRepository;
		this.combustibleRepository=combustibleRepository;
		this.disponibleRepository=disponibleRepository;
		this.seguroVehiculoRepository=seguroVehiculoRepository;
		this.entityManager = entityManager;
	}
	
	@Transactional(readOnly = true)
	public Collection<Vehiculos> findAllVehiculos() throws DataAccessException {
		return vehiculosRepository.findAllByDisponible();
	}
	
	@Transactional(readOnly = true)
	public Vehiculos findVehiculoById(int id) throws DataAccessException {
		return vehiculosRepository.findById(id);
	}
	
	@Transactional
	public void saveVehiculo(Vehiculos vehiculo) {
		vehiculosRepository.save(vehiculo);
	}
	
	@Transactional
	public void deleteVehiculo(Vehiculos vehiculo) {
		vehiculosRepository.delete(vehiculo);
	}

	@Transactional(readOnly = true)
	public Collection<Vehiculos> findVehiculoByPlazas(int plazas) {
		return vehiculosRepository.findByPlazas(plazas);
	}
	
	@Transactional(readOnly = true)
	public Collection<Cambio> findAllCambios() {
		return cambioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<Combustible> findAllCombustibles() {
		return combustibleRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<Disponible> findAllDisponibles() {
		return disponibleRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Disponible findDisponibleById(int id) {
		return disponibleRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<SeguroVehiculo> findAllSeguros() {
		return seguroVehiculoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Collection<Vehiculos> findByDisponible(@Param("id") int id){
		return vehiculosRepository.findByDisponible(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<Vehiculos> findAllVehiculosDisponiblesYsinOfertas(){
		return vehiculosRepository.findAllVehiculosDisponiblesYsinOfertas();
	}
	
	@Transactional(readOnly = true)
	public Vehiculos findByMatricula(String matricula) {
		return vehiculosRepository.findByMatricula(matricula);
	}
	
	@Transactional(readOnly = true)
	public Collection<Vehiculos> findByOferta(int id_oferta){
		return vehiculosRepository.findByOferta(id_oferta);
	}
	
	//PAGINACIÓN
	@Transactional(readOnly = true)
	public Page<Vehiculos> getAllPagVehiculos(Pageable pageable) throws DataAccessException {
		return vehiculosRepository.findAllPagVehiculos(pageable);
	}
	
	//PAGINACIÓN (Disponible)
	@Transactional(readOnly = true)
	public Page<Disponible> getAllPagDisponible(Pageable pageable) throws DataAccessException {
		return disponibleRepository.findAllPagDisponible(pageable);
	}
	//
		
	@Transactional
	public List<Vehiculos> searchVehiculos(String searchText){
		FullTextEntityManager fullTextEntityManager = 
				Search.getFullTextEntityManager(entityManager);
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Vehiculos.class)
				.overridesForField("marca", "edgeNGram_query")
				.overridesForField("modelo", "edgeNGram_query")
				.get();
		
		Query q = qb.keyword()
				.onFields("marca","modelo","plazas","puertas")
				.matching(searchText)
				.createQuery();
		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(q, Vehiculos.class);
		
		List<Vehiculos> vehiculosList = fullTextQuery.getResultList();
		
		return vehiculosList;
	}
}
