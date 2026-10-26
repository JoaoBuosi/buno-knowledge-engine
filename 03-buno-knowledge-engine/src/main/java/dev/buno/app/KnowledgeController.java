package dev.buno.app;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotBlank;
import java.util.*;
@RestController @RequestMapping("/api/knowledge")
public class KnowledgeController {
 private final KnowledgeRepository repo; private final AegisRuntimeClient aegis;
 public KnowledgeController(KnowledgeRepository r,AegisRuntimeClient a){repo=r;aegis=a;}
 public record Ingest(@NotBlank String title,@NotBlank String content){}
 public record Ask(@NotBlank String question){}
 @PostMapping("/documents") public KnowledgeDocument ingest(@RequestBody Ingest i){return repo.save(new KnowledgeDocument(i.title(),i.content()));}
 @GetMapping("/documents") public List<KnowledgeDocument> all(){return repo.findAll();}
 @PostMapping("/ask") public Map<String,Object> ask(@RequestBody Ask q){
  var docs=repo.findTop20ByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(q.question(),q.question());
  String context=docs.stream().map(d->"["+d.title+"] "+d.content).reduce("",(a,b)->a+"\n"+b);
  if(context.isBlank()) context="Nenhum documento foi recuperado. Informe que a base não contém contexto suficiente.";
  String answer=aegis.complete("Você é o Buno Knowledge Engine. Responda apenas com base no contexto; se faltar informação, declare isso. Contexto:\n"+context,q.question());
  return Map.of("question",q.question(),"answer",answer,"sourceDocumentIds",docs.stream().map(d->d.id).toList(),"retrieval","PostgreSQL text matching");
 }
}
