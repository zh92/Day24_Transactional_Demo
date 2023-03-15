package sg.edu.nus.iss.day24_lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.day24_lecture.repositories.BookRepo;

@SpringBootApplication
public class Day24LectureApplication implements CommandLineRunner {

	@Autowired
	BookRepo bookRepo;
	public static void main(String[] args) {
		SpringApplication.run(Day24LectureApplication.class, args);
	}

	@Override
	public void run(String...args){
		System.out.println(bookRepo.findAll());
	}
}
