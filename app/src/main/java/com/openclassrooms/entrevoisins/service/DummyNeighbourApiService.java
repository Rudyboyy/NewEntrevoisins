package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbour() {
        List<Neighbour> favoriteNeighbour = new ArrayList<>();
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).isFavorite()){
                favoriteNeighbour.add(neighbours.get(i));
            }
        }
        return favoriteNeighbour;
    }

    @Override
    public void toggleIsNeighbourFavorite(Neighbour neighbour) {
        int location = neighbours.indexOf(neighbour);
        neighbours.get(location).setFavorite(!neighbour.isFavorite());
    }

    @Override
    public Neighbour getNeighbourById(long id) {
        for (int i = 0; i < neighbours.size(); i++) {
            if (neighbours.get(i).getId() == id) {
                return neighbours.get(i);
            }
        }
        return null;
    }
}
