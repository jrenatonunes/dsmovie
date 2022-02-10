package com.devsuperior.dsmovie.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.entities.Score;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ScoreRepository scoreRepository;

	@Transactional
	public MovieDTO saveScore(ScoreDTO scoreDto) {

		User user = userRepository.findByEmail(scoreDto.getEmail());
		if (user == null) {
			user = new User();
			user.setEmail(scoreDto.getEmail());
			user = userRepository.saveAndFlush(user);
		}

		Movie movie = movieRepository.findById(scoreDto.getMovieId()).get();

		Score score = new Score();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(scoreDto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		Set<Score> scores = movie.getScores();
		Integer countScores = scores.size();
	
		double sumScores = 0.0;
		for ( Score currentScore: movie.getScores()) {
			sumScores += currentScore.getValue();
		}
		
		double averageScore = sumScores / countScores;
		
		movie.setScore(averageScore);
		movie.setCount(countScores);
		
		movie = movieRepository.saveAndFlush(movie);
		
		return new MovieDTO(movie);
	}
}
