package dev.buno.app;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface KnowledgeRepository extends JpaRepository<KnowledgeDocument,Long>{
 List<KnowledgeDocument> findTop20ByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title,String content);
}
