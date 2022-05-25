package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get all my favorite Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavoriteNeighbour();

    /**
     * Toggle Neighbour in favorite
     * @param neighbour
     */
    void toggleIsNeighbourFavorite(Neighbour neighbour);

    /**
     * Get neighbour by is ID
     * @param id
     */
    Neighbour getNeighbourById(long id);
}
