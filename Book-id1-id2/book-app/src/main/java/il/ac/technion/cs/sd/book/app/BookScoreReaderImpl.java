package il.ac.technion.cs.sd.book.app;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

public class BookScoreReaderImpl implements BookScoreReader {

	@Override
	public boolean gaveReview(String reviewerId, String bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OptionalDouble getScore(String reviewerId, String bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReviewedBooks(String reviewerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getAllReviewsByReviewer(String reviewerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalDouble getScoreAverageForReviewer(String reviewerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReviewers(String bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Integer> getReviewsForBook(String bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalDouble getAverageReviewScoreForBook(String bookId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
