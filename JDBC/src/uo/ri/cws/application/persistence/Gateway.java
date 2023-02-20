package uo.ri.cws.application.persistence;

import java.util.List;
import java.util.Optional;

/**
 *	Common interface for a gateway that provides common operations for any gateway.
 *  That is, CRUD operations: add, remove, update, find by id and find all.
 * @param <T>
 */
public interface Gateway<T> {
	/*
	 * Adds a new item to the table 
	 * @param new item 
	 */
	void add(T t) ;
	/*
	 * Removes an object from the table
	 * @param key to delete
	 */
	void remove(String id) ;
	/*
	 * Updates a row
	 * @param new data to overwrite old one
	 */
	void update(T t) ;
	/*
	 * Finds a row in the table
	 * @param record's primary key to retrieve
	 * @return dto from that record, probably null
	 */
	Optional<T> findById(String id) ;
	/*
	 * Retrieves all data in a table
	 */
	List<T> findAll( ) ;
	

}

