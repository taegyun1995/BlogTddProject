package tdd.blogProject.blog.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBlogRepository extends JpaRepository<BlogEntity, Long> {



}