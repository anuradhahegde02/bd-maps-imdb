package com.amazon.ata.maps;

import java.util.*;

/**
 * Stores the relationships between movies and actors, allowing releasing a new movie
 * with all actors in the cast, adding a single actor to an existing (or new) movie,
 * unreleasing a movie completely, and querying actors by movie and vice versa.
 */
public class Imdb {
    private Map<Movie,Set<Actor>> db;

    public Imdb(){
        db=new HashMap<>();
    }

    /**
     * Adds the new movie to the set of movies that an actor has appeared in.
     * If the movie already exists in the database, this will overwrite actors
     * associated with the movie with the new values provided.
     *
     * @param movie the movie being released
     * @param actors a set of actors that appear in the movie
     */
    public void releaseMovie(Movie movie, Set<Actor> actors) {
        //TODO
            db.put(movie,actors);
    }

    /**
     * Removes the given movie from the database, including any actors
     * credited in the movie.
     *
     * @param movie the movie to remove
     * @return true if the movie was removed, false if it wasn't in Imdb
     *         to begin with
     */
    public boolean removeMovie(Movie movie) {
        // TODO: replace
        if(db.containsKey(movie)){
            db.remove(movie);
            return true;
        }
        return false;
    }

    /**
     * Adds a new movie to the set of movies that an actor has appeared in.
     * If the movie already exists in the database, will add the actor
     * if they haven't been added already. If the movie doesn't yet exist
     * in the database, this will add the movie with the actor as the only
     * credit.
     *
     * @param movie the movie to add to the actors set of movies
     * @param actor the actor that appears in this movie
     */
    public void tagActorInMovie(Movie movie, Actor actor) {
        //TODO
        Set<Actor> actr=new HashSet<>();
        if (db.containsKey(movie)){
            actr=getActorsInMovie(movie);
            if (!actr.contains(actor)){
                actr.add(actor);
                System.out.println("Actor "+actor.toString()+" tagged to movie "+movie.toString());
            }
           db.put(movie,actr); 
        }else {           
            actr.add(actor);
            db.put(movie, actr);
        }
    }

    /**
     * Returns a set of actors who are credited in the given movie. If a movie is not
     * released on IMDB throw an IllegalArgumentException.
     *
     * @param movie the movie to get actors for
     * @return the set of actors who are credited in the passed in movie
     */
    public Set<Actor> getActorsInMovie(Movie movie) {
        // TODO: replace
        for (Map.Entry<Movie, Set<Actor>> entry : db.entrySet()) {
            if (entry.getKey().getName().equals(movie.getName())) {
                return entry.getValue();
            }
        }

        throw new IllegalArgumentException("Movie is not released yet!!");


    }

    /**
     * Returns a set of movies that the specified actor has appeared in. If the
     * actor has not appeared in any movies, return an empty Set.
     *
     * @param actor the actor to get movies for
     * @return the set of movies that the passed in actor has appeared in
     */
    public Set<Movie> getMoviesForActor(Actor actor) {
        // TODO: replace
        Set<Movie> movies=new HashSet<>();
      for(Map.Entry<Movie,Set<Actor>> entry:db.entrySet()){
          Set<Actor> a=db.get(entry.getKey());
          if (a.contains(actor)){
              movies.add(entry.getKey());
          }
      }
        return movies;
    }

    /**
     * Returns all actors that IMDB has in its records as having appeared in a movie.
     *
     * @return a set of actors that IMDB has as appeared in movies
     */
    public Set<Actor> getAllActorsInIMDB() {
        // TODO: replace
        Set<Actor> actrosInImdb=new HashSet<>();
        for(Map.Entry<Movie,Set<Actor>> entry: db.entrySet()){
            for(Actor a:entry.getValue()){
                if(!actrosInImdb.contains(a)){
                    actrosInImdb.add(a);
                }
            }
        }
        return actrosInImdb;
    }

    /**
     * Returns the total number of individual movie-actor pairs in the database.
     *
     * So if there are 2 movies, the first movie has 1 actor and the second one
     * has 6 actors, this method will return 7.
     *
     * @return The total number of movie-actor pairings: the number of times
     *         any actor has appeared in any movie
     */
    public int getTotalNumCredits() {
        // TODO: replace
        int total=0;
        for(Map.Entry<Movie,Set<Actor>> entry:db.entrySet()){
            total+=entry.getValue().size();
        }
        return total;
    }
}
