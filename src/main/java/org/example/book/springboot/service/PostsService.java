package org.example.book.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.book.springboot.domain.posts.Posts;
import org.example.book.springboot.domain.posts.PostsRepository;
import org.example.book.springboot.web.dto.PostsSaveRequestDto;
import org.example.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
        private final PostsRepository postsRepository;

        @Transactional
        public long save(PostsSaveRequestDto requestDto)
        {
            return postsRepository.save(requestDto.toEntity()).getId();
        }

         @Transactional
        public Long update(Long id, PostsUpdateRequestDto requestDto) {
              Posts posts = postsRepository.findById(id)
                      .orElseThrow(() ->
                              new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

              posts.update(requestDto.getTitle(), requestDto.getContent());

              return id;
         }


}
