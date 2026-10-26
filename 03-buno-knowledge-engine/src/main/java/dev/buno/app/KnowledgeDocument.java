package dev.buno.app;
import jakarta.persistence.*;
import java.time.Instant;
@Entity
public class KnowledgeDocument {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(nullable=false) public String title;
 @Column(nullable=false,length=30000) public String content;
 public Instant createdAt=Instant.now();
 protected KnowledgeDocument(){}
 public KnowledgeDocument(String title,String content){this.title=title;this.content=content;}
}
