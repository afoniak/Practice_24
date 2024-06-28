package com.example.shinydishes.repositories;

import com.example.shinydishes.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {
}
