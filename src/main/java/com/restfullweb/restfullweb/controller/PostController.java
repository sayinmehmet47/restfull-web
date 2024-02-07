package com.restfullweb.restfullweb.controller;


import com.restfullweb.restfullweb.entity.Post;
import com.restfullweb.restfullweb.repository.PostRepository;
import com.restfullweb.restfullweb.user.User;
import com.restfullweb.restfullweb.user.UserNotFoundException;
import com.restfullweb.restfullweb.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {


    private PostRepository postRepository;
    private UserRepository userRepository;


    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id) {
        return this.postRepository.findByUserId(id);
    }

    @PostMapping(path = "/users/{id}/posts")
    public List<Post> createUserPosts(@PathVariable int id, @Valid() @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id" + id);
        post.setUser(user.get());
        postRepository.save(post);
        return user.get().getPosts();
    }
}
